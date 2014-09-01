#!/bin/bash

echo "$1" > ./"$2";
r=$(( $RANDOM % 10 )); echo random $r for test "$1"
sleep $r;
exit 1;