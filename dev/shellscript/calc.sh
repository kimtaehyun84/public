#! /bin/bash
A=2
B=4

echo "A=$A"
echo "B=$B"

result=`expr $A + $B`
echo "A+B=$result"

result=`expr $A - $B`
echo "A-B=$result"

result=`expr $A \* $B`
echo "A*B=$result"

result=`expr $A \/ $B`
echo "A/B=$result"
