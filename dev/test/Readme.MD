##  환경설정

---

1. DB

   Vendor : Oracle

   버전 :  12c

   DataSource방식 : JNDI

   JNDI Name : jdbc/ORACLE

   JNDI Name 설정 파일 :  Resource/props/db.properties

   Context.xml 위치 :  $Project_Home/META-INF/context.xml

   DDL 위치 : $Project_Home/query.sql

   

2. Log

   로그 경로 설정 : Resource/props/common.properties

## 인터페이스

---

1. ### 뿌리기

   URI : /distribute

   Method : POST

   Parameter Type : JSON

   Parameter : 

   ​					{ 

   ​							"amount" :  //뿌리는 금액(String, 필수)

   ​							"targetNum" : //대상인원(String, 필수)

   ​					}

   Return Type: JSON

   Return :

   ​					{

   ​							"status" : //결과 ("SUCCESS" / "FAIL")

   ​							"body" : {

   ​												"token" : //토큰 값 (Sting) 

   ​											}

   ​							"msg" : //예외 발생 시 메세지

   ​					}

      * status : 필수, body/msg : 옵션

        

2. ### 받기

   URI : /receive

   Method : POST

   Parameter Type : JSON

   Parameter : 

   ​					{ 

   ​							"token" :  //토큰 (String, 필수)

   ​					}

   Return Type: JSON

   Return :

   ​					{

   ​							"status" : //결과 ("SUCCESS" / "FAIL")

   ​							"body" : {

   ​												"amount" : //금액 (Sting) 

   ​											}

   ​							"msg" : //예외 발생 시 메세지

   ​					}

      * status : 필수, body/msg : 옵션

        

3. ### 조회

   URI : /inquiry

   Method : POST

   Parameter Type : JSON

   Parameter : 

   ​					{ 

   ​							"token" :  //토큰 (String, 필수)

   ​					}

   Return Type: JSON

   Return :

   ​					{

   ​							"status" : //결과 ("SUCCESS" / "FAIL")

   ​							"body" : {

   ​												"regDate" : //뿌리기 등록 일자

   ​												"totalAmount" : //뿌린 금액

   ​												"successTotalReceiveAmount" : //받은 총 금액

   ​												"successReceiveInfoList" : { //받은 완료된 정보 리스트

   ​																									{

   ​																										"recvUserId" : //받은 유저 아이디

   ​																										"amount" : //받은 금액

   ​																									}

   ​																							}

   ​											}

   ​							"msg" : //예외 발생 시 메세지

   ​					}

   ​					* status : 필수, body/msg : 옵션

## 주요 로직

---

1. 뿌리기

   1) 토큰 생성

   ​	시스템의 현재 타임을 Seed로 하여 Random함수를 생성 후 아스키코드 33 ~ 126의 값을 조합하여 3자리 문자열을 생성합니다.

   2) 뿌릴 금액 생성

   ​	시스템의 현재 타임을 Seed로 하여 Random함수를 생성 후 0~1 사이의 값을 랜덤으로 생성하여 토탈 금액 X 랜덤값을 통해 뿌릴금액을 생성합니다. 

   3) 생성된 정보를 DB에 저장합니다.

2. 받기

   1) 동일한 대화방에 토큰이 있는지 확인 없는 경우 Msg Return

   2) 토큰이 만료되었는지 확인, 만료된 경우 해당 토큰을 만료 처리 후 Msg Return

   3) 기존에 받은 이력이 없는지 확인, 있는 경우 Msg Return

   4) 뿌린유저가 받으려고 하는 유저와 다른지 확인, 같은 경우 Msg Return

   5) 금액이 남아있는지 확인, 없는 경우 Msg Return

   6) 금액을 Return하고 Receive History 등록

3. 조회

   1) 조회 가능한 토큰이 있는지 확인 없는 경우 Msg Return

   2) 해당 토큰에 대한 Receive History Return

## 기타 로직

---

1. 만료 처리

   등록시간이 현재 시간과 10분 차이가 나는 토큰에 대하여 만료 처리 후 남아있던 잔여 금액에 대하여 받은 사람 없음(None)으로 Receive History에 등록.

   Spring scheduler를 사용하여 5분마다 실행하며 받기 로직 실행중 만료된 토큰이 발견되면 즉시 실행

   

2. History 관리

   등록일자가 현재와 7일 이상 차이나는 히스토리 및 토큰에 대하여 삭제 처리\

   Spring scheduler를 사용하여 매일 밤 12시 하루에 1번 실행



## 테이블 구조

---

테이블 명 : T_PAY_DISTRIBUTE_SUMMARY

테이블 설명 : 뿌린 금액에 대한 요약 테이블

| No   | 컬럼 명          | 컬럼 타입     | Desc               |
| ---- | ---------------- | ------------- | ------------------ |
| 1    | REG_USER_ID      | varchar2(255) | 등록한 유저 아이디 |
| 2    | ROOM_ID          | varchar2(255) | 룸 아이디          |
| 3    | TOKEN            | varchar2(3)   | 토큰               |
| 4    | TOTAL_AMOUNT     | varchar2(30)  | 전체 뿌린 금액     |
| 5    | TOTAL_TARGET_NUM | varchar2(30)  | 뿌릴 대상 수       |
| 7    | DISABLE          | varchar2(1)   | 만료 여부(Y/N)     |
| 8    | REG_DATE         | DATE          | 등록 날짜          |



테이블 명 : T_PAY_DISTRIBUTE_DETAIL

테이블 설명 : 뿌린 금액에 대한 상세 테이블

| No   | 컬럼 명     | 컬럼 타입     | Desc               |
| ---- | ----------- | ------------- | ------------------ |
| 1    | REG_USER_ID | varchar2(255) | 등록한 유저 아이디 |
| 2    | ROOM_ID     | varchar2(255) | 룸 아이디          |
| 3    | TOKEN       | varchar2(3)   | 토큰               |
| 4    | AMOUNT      | varchar2(30)  | 뿌릴 금액          |
| 5    | REG_DATE    | Date          | 등록 날짜          |



테이블 명 : T_PAY_RECEIVE_HISTORY

테이블 설명 : 유저가 받은 금액에 대한 히스토리 테이블

| No   | 컬럼 명      | 컬럼 타입     | Desc               |
| ---- | ------------ | ------------- | ------------------ |
| 1    | REG_USER_ID  | varchar2(255) | 등록한 유저 아이디 |
| 2    | ROOM_ID      | varchar2(255) | 룸 아이디          |
| 3    | TOKEN        | varchar2(3)   | 토큰               |
| 4    | AMOUNT       | varchar2(30)  | 받은 금액          |
| 5    | RECV_USER_ID | varchar2(30)  | 받은 유저 아이디   |
| 6    | REG_DATE     | Date          | 등록 날짜          |

##  환경설정

---

1. DB

   Vendor : Oracle

   버전 :  12c

   DataSource방식 : JNDI

   JNDI Name : jdbc/ORACLE

   JNDI Name 설정 파일 :  Resource/props/db.properties

   Context.xml 위치 :  $Project_Home/META-INF/context.xml

   DDL 위치 : $Project_Home/query.sql

   

2. Log

   로그 경로 설정 : Resource/props/common.properties

## 인터페이스

---

1. ### 뿌리기

   URI : /distribute

   Method : POST

   Parameter Type : JSON

   Parameter : 

   ​					{ 

   ​							"amount" :  //뿌리는 금액(String, 필수)

   ​							"targetNum" : //대상인원(String, 필수)

   ​					}

   Return Type: JSON

   Return :

   ​					{

   ​							"status" : //결과 ("SUCCESS" / "FAIL")

   ​							"body" : {

   ​												"token" : //토큰 값 (Sting) 

   ​											}

   ​							"msg" : //예외 발생 시 메세지

   ​					}

      * status : 필수, body/msg : 옵션

        

2. ### 받기

   URI : /receive

   Method : POST

   Parameter Type : JSON

   Parameter : 

   ​					{ 

   ​							"token" :  //토큰 (String, 필수)

   ​					}

   Return Type: JSON

   Return :

   ​					{

   ​							"status" : //결과 ("SUCCESS" / "FAIL")

   ​							"body" : {

   ​												"amount" : //금액 (Sting) 

   ​											}

   ​							"msg" : //예외 발생 시 메세지

   ​					}

      * status : 필수, body/msg : 옵션

        

3. ### 조회

   URI : /inquiry

   Method : POST

   Parameter Type : JSON

   Parameter : 

   ​					{ 

   ​							"token" :  //토큰 (String, 필수)

   ​					}

   Return Type: JSON

   Return :

   ​					{

   ​							"status" : //결과 ("SUCCESS" / "FAIL")

   ​							"body" : {

   ​												"regDate" : //뿌리기 등록 일자

   ​												"totalAmount" : //뿌린 금액

   ​												"successTotalReceiveAmount" : //받은 총 금액

   ​												"successReceiveInfoList" : { //받은 완료된 정보 리스트

   ​																									{

   ​																										"recvUserId" : //받은 유저 아이디

   ​																										"amount" : //받은 금액

   ​																									}

   ​																							}

   ​											}

   ​							"msg" : //예외 발생 시 메세지

   ​					}

   ​					* status : 필수, body/msg : 옵션

## 주요 로직

---

1. 뿌리기

   1) 토큰 생성

   ​	시스템의 현재 타임을 Seed로 하여 Random함수를 생성 후 아스키코드 33 ~ 126의 값을 조합하여 3자리 문자열을 생성합니다.

   2) 뿌릴 금액 생성

   ​	시스템의 현재 타임을 Seed로 하여 Random함수를 생성 후 0~1 사이의 값을 랜덤으로 생성하여 토탈 금액 X 랜덤값을 통해 뿌릴금액을 생성합니다. 

   3) 생성된 정보를 DB에 저장합니다.

2. 받기

   1) 동일한 대화방에 토큰이 있는지 확인 없는 경우 Msg Return

   2) 토큰이 만료되었는지 확인, 만료된 경우 해당 토큰을 만료 처리 후 Msg Return

   3) 기존에 받은 이력이 없는지 확인, 있는 경우 Msg Return

   4) 뿌린유저가 받으려고 하는 유저와 다른지 확인, 같은 경우 Msg Return

   5) 금액이 남아있는지 확인, 없는 경우 Msg Return

   6) 금액을 Return하고 Receive History 등록

3. 조회

   1) 조회 가능한 토큰이 있는지 확인 없는 경우 Msg Return

   2) 해당 토큰에 대한 Receive History Return

## 기타 로직

---

1. 만료 처리

   등록시간이 현재 시간과 10분 차이가 나는 토큰에 대하여 만료 처리 후 남아있던 잔여 금액에 대하여 받은 사람 없음(None)으로 Receive History에 등록.

   Spring scheduler를 사용하여 5분마다 실행하며 받기 로직 실행중 만료된 토큰이 발견되면 즉시 실행

   

2. History 관리

   등록일자가 현재와 7일 이상 차이나는 히스토리 및 토큰에 대하여 삭제 처리\

   Spring scheduler를 사용하여 매일 밤 12시 하루에 1번 실행



## 테이블 구조

---

테이블 명 : T_PAY_DISTRIBUTE_SUMMARY

테이블 설명 : 뿌린 금액에 대한 요약 테이블

| No   | 컬럼 명          | 컬럼 타입     | Desc               |
| ---- | ---------------- | ------------- | ------------------ |
| 1    | REG_USER_ID      | varchar2(255) | 등록한 유저 아이디 |
| 2    | ROOM_ID          | varchar2(255) | 룸 아이디          |
| 3    | TOKEN            | varchar2(3)   | 토큰               |
| 4    | TOTAL_AMOUNT     | varchar2(30)  | 전체 뿌린 금액     |
| 5    | TOTAL_TARGET_NUM | varchar2(30)  | 뿌릴 대상 수       |
| 7    | DISABLE          | varchar2(1)   | 만료 여부(Y/N)     |
| 8    | REG_DATE         | DATE          | 등록 날짜          |



테이블 명 : T_PAY_DISTRIBUTE_DETAIL

테이블 설명 : 뿌린 금액에 대한 상세 테이블

| No   | 컬럼 명     | 컬럼 타입     | Desc               |
| ---- | ----------- | ------------- | ------------------ |
| 1    | REG_USER_ID | varchar2(255) | 등록한 유저 아이디 |
| 2    | ROOM_ID     | varchar2(255) | 룸 아이디          |
| 3    | TOKEN       | varchar2(3)   | 토큰               |
| 4    | AMOUNT      | varchar2(30)  | 뿌릴 금액          |
| 5    | REG_DATE    | Date          | 등록 날짜          |



테이블 명 : T_PAY_RECEIVE_HISTORY

테이블 설명 : 유저가 받은 금액에 대한 히스토리 테이블

| No   | 컬럼 명      | 컬럼 타입     | Desc               |
| ---- | ------------ | ------------- | ------------------ |
| 1    | REG_USER_ID  | varchar2(255) | 등록한 유저 아이디 |
| 2    | ROOM_ID      | varchar2(255) | 룸 아이디          |
| 3    | TOKEN        | varchar2(3)   | 토큰               |
| 4    | AMOUNT       | varchar2(30)  | 받은 금액          |
| 5    | RECV_USER_ID | varchar2(30)  | 받은 유저 아이디   |
| 6    | REG_DATE     | Date          | 등록 날짜          |

