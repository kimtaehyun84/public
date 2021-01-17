# 카카오 페이 뿌리기 기능 구현하기

## 요구사항

1. 뿌리기, 받기, 조회 기능을 수행하는 REST API구현

   - 사용자 식별값은 숫자 형태이며 X-USER-ID라는 http헤더를 통해 전달

   - 대화방의 식별값은 문자 형태이며 X-ROOM-ID라는 http헤더를 통해 전달

2. 다수의 서버에 다수의 인스턴스로 동작하더라도 기능에 문제가 없도록 설계되어야 함.

## 상세 구현 요건

#### 1. 뿌리기 API

```
- 입력값 
  ​	뿌릴 금액, 뿌릴 인원, 뿌리는 유저 아이디,  뿌리는 유저 대화방 아이디
- 반환값
  ​	Token
- 제약사항 
  ​	token은 3자리 문자열로 구성되며 예측이 불가능해야 함.
  ​	뿌릴 금액은 인원수에 맞게 분배하여 저장해야 함.
```

#### 2. 받기 API

```
- 입력값
  ​	Token, 받는 유저 대화방 아이디, 받는 유저 아이디
- 반환값
  ​	받은 금액
- 제약사항
  ​	해당 하는 뿌리기 건중 할당 되지 않은 건 하나를 한번만 받을 수 있음
  ​	자기 자신이 뿌린 건은 받을 수 없음
  ​	뿌리기가 호출된 대화방과 동일한 대화방에 속한 사용자만 받을 수 있음
  ​	뿌리기는 10분동안만 유효 함
```

#### 3. 조회 API

```
- 입력값
  ​	Token, 유저 대화방 아이디, 유저 아이디
-  반환값
  ​	뿌린 시각, 뿌린 금액, 받기 완료된 금액, 받기 완료된 정보[받은 금액, 받은 사용자 아이디]
- 제약사항
  ​	뿌린 사람 자신만 조회 할 수 있음
  ​	뿌린 건에 대해서 7일동안 조회 가능
```

## 요건 분석

Token은 3자리 문자열이며 각 Token은 뿌려진 대화방에서만 유효함 

​	=>  ROOM_ID + Token 이 Token을 구별하는 PK가 됨.

자신이 뿌린 건은 받을 수 없음/뿌린 사람만 조회할 수 있음

​	=> 뿌린 사람의 사용자 ID에 대해 관리가 필요함.

한 유저 아이디당 한번만 받을 수 있음

​	=> 받은 사용자 ID에 대해 관리가 필요함.

뿌리기는 10분동안 유효함/뿌린 건에 대해서 7일동안 조회 가능

​	=> 뿌린 날짜 시간에 대한 관리가 필요함.

조회시 뿌린 금액/받기 완료된 정보를 반환해야 함

​	=> 뿌린 금액 / 나눈 금액별 받은 사용자 아이디가 관리 필요함.

#### 결론 

```
뿌린 대화방 아이디, 뿌린 사용자 아이디, 뿌린 금액, 뿌린 시간,나눈 금액,받은 사용자 아이디, Token에 대한 정보를 DB에 저장해야함.
```



## 데이터베이스 설계

뿌린 대화방(ROOM_ID), 뿌린 사용자 아이디(USER_ID), Token(TOKEN), 뿌린 금액(TOTAL_AMOUNT), 뿌린 날짜 (REG_DATE), 받기 만료 시간(EXP_DATE), 조회 만료 시간(DEL_DATE)로 구성된 SUMMARY테이블 1개

구분용 SEQ(SEQ), 뿌린 대화방(ROOM_ID), Token(TOKEN), 나눈 금액(AMOUNT), 받은 사람(RECV_USER_ID)로 구성된 Detail 테일 1개로 구성

DB스크립트는 Project의 DB Script.sql에 작성함.

| TABLE명 | T_DISTRIBUTEMONEY_LIST.  ||                                | 
| ------- | ---------------------- | ------------ | ---------------- |
| NO      | COLUMN명               | TYPE         | DEFAULT          |
| 1       | ROOM_ID                | varchar(100) |                  |
| 2       | TOKEN                  | varchar(3)   |                  |
| 3       | USER_ID                | NUMBER       |                  |
| 4       | TOTAL_AMOUNT           | NUMBER       |                  |
| 5       | REG_DATE               | DATE         | SYSDATE          |
| 6       | EXP_DATE               | DATE         | SYSDATE + 1/24/6 |
| 7       | DEL_DATE               | DATE         | SYSDATE + 8      |
| PK      | ROOM_ID + TOKEN        |              |                  |

| TABLE명 | T_DISTRIBUTEMONEY_DETAIL ||                           	     |
| ------- | -----------------------| ------------ | ----------------- |
| NO      | COLUMN명                | TYPE         | DEFAULT           |
| 1       | SEQ                    | NUMBER       | AUTO INCREASEMENT |
| 2       | ROOM_ID                | varchar(100) |                   |
| 3       | TOKEN                  | varchar(3)   |                   |
| 4       | AMOUNT                 | NUMBER       |                   |
| 5       | RECV_USER_ID           | NUMBER       |                   |
| PK      | SEQ                    | FK           | ROOM_ID + TOKEN   |

## USE-CASE설계

#### 1. 뿌리기 

```
1. 문자열 토큰 생성
2. 토큰 중복성 확인 (토큰 중복 조건 : ROOM ID, TOKEN이 같으면 중복)
   2-1. 중복시 1. 부터 다시 시작
   2-2. 중복이 아닐시 3.으로 진행
   2-3. 다시 시도하다가 지정 시간 초과시 실패 Return
3. 인원수에 맞춰 금액 분배
4. 생성된 Token Return
```

#### 2. 받기

```
1. Token의 유효성 체크(뿌린지 10분이 넘었는지, 뿌린사람이 자기자신은 아닌지)
  1-1. Token이 유효하면 2. 진행
  1-2. Token이 유효하지 않으면 실패 Return
2. Receive가 가능한지 체크(이미 받지 않았는지, 받을 금액이 남아있는지)
  2-1. Receive가 가능하면 3. 진행
  2-2. Receive가 불가능하면 실패 Return
3. Receive한 금액 Return
```

#### 3. 조회

```
1. 조회 가능한 Token인지 확인(뿌린지 7일이 안지났는지, 내가 만든 토큰인지)
  1-1. 조회 가능하면 2. 진행
  1-2. 조회 불가능하면 조회 실패 Return
2. 뿌린 시간, 뿌린 금액, 받기 완료된 금액, 받기 완료된 정보([받은 금액, 받은 사용자 아이디] 리스트) Return
```

## 개발 환경

JAVA : 1.8

Spring : 4.3

DB : Oracle 12c

## Interface 사양
POSTMAN Collection파일 별첨 함(DistributeMoney.postman_collection.json)

#### 1. 뿌리기 API

```
End Point : /webservice/distribute
Method : POST
Request
	- Header
		X-USER-ID : Integer
		X-ROOM-ID : String
		Content-Type : application/json
    	- Body
    	{ 
    		"totalAmount" : Integer, 	//뿌릴 금액
    		"targetNum" : Integer		//뿌릴 인원 수
    	}
Response
	- Body
	{ 
    		"result": "SUCCESS",		//결과 (SUCCESS/FAIL)
    		"body": "zjD"			//Token
	}
```

#### 2. 받기 API

```
End Point : /webservice/receive
Method : POST
Request
	- Header
		X-USER-ID : Integer
		X-ROOM-ID : String
		Content-Type : application/json
   	- Body
    	{ 
    		"token" : String, //토큰
    	}
Response
	- Body
	{ 
    		"result": "SUCCESS",		//결과 (SUCCESS/FAIL)
    		"body": 1581			//받은 금액
	}
```

#### 3. 조회 API

```
End Point : /webservice/inquiry
Method : POST
Request
	- Header
		X-USER-ID : Integer
		X-ROOM-ID : String
		Content-Type : application/json
    	- Body
    	{ 
    		"token" : String, //토큰
    	}
Response
	- Body
	{
        	"result": "SUCCESS",				//결과 (SUCCESS/FAIL)
        	"body": {
			"totalAmount": 3421,			//뿌린 금액
			"regDate": "2021-01-16 10:53:57", 	//뿌린 시간
			"receiveCompleteInfoList": 
				[				//받은 정보
				   {
					"receiveAmount": 1581,	//받은 금액
					"recvUserId": 4538	//받은 사용자 아이디
				   }
				   ...
            			],
            	"totalReceiveAmount": 1581			//받기 완료된 금액 합계
        }
```

