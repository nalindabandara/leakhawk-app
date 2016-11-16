i=$1;


count=$(grep -oiE "lanka|lk|ceylon|sinhala|buddhist|colombo|kandy|kurunegala|gampaha|mahinda|sirisena|ranil" "$i" | wc -l);
#emails=$(grep -oE "([a-zA-Z0-9]{1,}\.)+[a-zA-Z]{1,}" "$i"| wc -l)
#domains=$(grep -oE "([a-zA-Z0-9]{1,10}\.)+(lk)" "$i"| wc -l)
#hashes=$(python "/home/nalinda/oct/leakhawk-app/predictor/Hash_ID_Finder-file.py" "$1"| grep -Ei "CRC|MD2|MD4|MD5|RC4|Haval128|GOST" | wc -l)

#if [ $emails -gt $hashes ] ; then echo $emails; fi; 
echo $count
#if [ $emails -le $hashes ] ; then echo $hashes ; fi;
