#! /bin/bash

echo -n "과일을 입력하세요 : " #줄바꿈을 사용하지 않기 위해 -n 옵션 사용
read input # input을 입력 받기 위한 변수 선언

if [ $input = "수박" ]
then
    echo "수박좋아"
else
    echo "맛이없어"
fi