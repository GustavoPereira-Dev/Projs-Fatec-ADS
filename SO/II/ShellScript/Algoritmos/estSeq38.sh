#!/bin/bash
# estSeq38.sh

indx=1
maior=0
menor=0
first=1

while [ "$indx" -le 100 ]; do
    read -p "Digite o número ($indx/100): " num

    if (( $(echo "$num >= 0" | bc -l) )); then
        if [ "$first" -eq 1 ]; then
            maior=$num
            menor=$num
            first=0
        else
            maior=$(echo "$num > $maior" | bc -l)
            if [ "$maior" -eq 1 ]; then
                maior=$num
            fi

            menor=$(echo "$num < $menor" | bc -l)
            if [ "$menor" -eq 1 ]; then
                menor=$num
            fi
        fi
        indx=$((indx + 1))
    else
        echo "Somente valores positivos são permitidos."
    fi
done

echo "O maior número entre os 100 é: $maior"
echo "O menor número entre os 100 é: $menor"