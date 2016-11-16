i=$1;

j=$(python "/home/nalinda/oct/leakhawk-app/predictor/Hash_ID_Finder-file.py" "$1"| grep -Ei "CRC|MD2|MD4|MD5|RC4|Haval128|GOST" | wc -l);


echo $j;
