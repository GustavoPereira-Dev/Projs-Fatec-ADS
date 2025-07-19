#!/bin/bash
#/tmp/loop_exemplo.sh

n=1;

while [ $n -lt 5 ]
do
  printf "Valor de N: %d\s" "$n";
  n=$(( $n + 1));
done