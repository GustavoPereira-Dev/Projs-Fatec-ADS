#!/bin/bash
# estSeq43.sh

ana=1.10
maria=1.50 
anos=0

while (( $(echo "$ana <= $maria" | bc -l) )); do
    ana=$(echo "$ana + 0.03" | bc)
    maria=$(echo "$maria + 0.02" | bc)
    anos=$((anos + 1))
done

echo "Serão necessários ${anos} ano(s) para que Ana seja maior que Maria."