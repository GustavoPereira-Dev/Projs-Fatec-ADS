#!/bin/bash
#/tmp/loop_until.sh

n=1;

until [ $n -eq 5 ]
do
  printf "Valor de N: %d\s" "$n";
done