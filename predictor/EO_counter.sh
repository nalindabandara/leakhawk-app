i=$1;

emails=$(grep -oE "(([a-zA-Z]|[0-9])|([-]|[_]|[.]))+[@](([a-zA-Z0-9])|([-])){2,63}([.]((([a-zA-Z0-9])|([-])){2,63})){1,4}" "$i"| wc -l)
#hashes=$(python "/home/nalinda/oct/leakhawk-app/predictor/Hash_ID_Finder-file.py" "$1"| grep -Ei "CRC|MD2|MD4|MD5|RC4|Haval128|GOST" | wc -l)

#if [ $emails -gt $hashes ] ; then echo $emails; fi; 
echo $emails
#if [ $emails -le $hashes ] ; then echo $hashes ; fi;
