#!/bin/bash

echo '#####################################################################'

for n in 16000000 32000000 64000000 128000000
do
    for choice in 0 3 4 5 6
    do
        echo '############################' N = $n Choice = $choice '#########################################'
        $(which java) -Xmx32g -Xms16g idsa.Msort $n $choice > sp10_$(echo $n)_$(echo $choice).txt
    done
done
