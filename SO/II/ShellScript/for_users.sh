#!/bin/bash
#/tmp/for_users.sh

for u in `getent passwd | awk -F: '{print $1}'`
do
  printf "O valor de u é: %s\n" "$u";
done