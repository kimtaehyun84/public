#! /bin/bash
: << "END"
-n은 echo후 줄바꿈을 하지 않겠다는 의미이다.
한줄 주석은 # 블럭 주석은 ": << 'END"과 END를사용한다.
END


echo -n "Input : "
read input

echo "Your Input : $input"
