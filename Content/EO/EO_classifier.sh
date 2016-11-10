#! /bin/bash

i="$1";

	j=$( basename "$i" );


	#related terms in the subject
	EO1=$(ls "$i" | grep -oiE "email_hacked|emails_hacked|email|emails_leak|email_dump|emails_dump|email_dumps|email-list|leaked_email|email_hack" | wc -l);	
	
	#related terms in the body text
	EO2=$(grep -oiE "leaked by|Emails LeakeD|domains hacked|leaked email list|email list leaked|leaked emails|leak of|email_hacked|emails_hacked|email|emails_leak|email_dump|emails_dump|email_dumps|email-list|leaked_email|email_hack" "$i"| wc -l);
	
	#no of email regex matches
	EO3=$(grep -oE "(([a-zA-Z]|[0-9])|([-]|[_]|[.]))+[@](([a-zA-Z0-9])|([-])){2,63}([.]((([a-zA-Z0-9])|([-])){2,63})){1,4}" "$i"| wc -l);
	
	#no of words
	EO4=$(cat "$i" | tr '[*],/:' ' ' | wc -w); if [ $EO4 -eq 0 ] ; then EO4=1; fi;
	
	# E05 == the percentage of emails in the total text (if higher than 89%, possible email dump)
	perc=$((((EO3) * 100)/ EO4)) ; if [ $perc -gt 89 ] ; then EO5=1 ; else EO5=0; fi;


	string=",?"

	item="\$EO1"; for i in {2..5}; do item+=,"\$EO$i";done;item+="$string";eval "echo $item">>"/home/nalinda/oct/leakhawk-app/Content/EO/vector.txt"
	cat "/home/nalinda/oct/leakhawk-app/Content/EO/header.txt" "/home/nalinda/oct/leakhawk-app/Content/EO/vector.txt" >> $j.EO.arff 
	mv "$j.EO.arff" "/home/nalinda/oct/IN/ARFF/"
	rm -rf "/home/nalinda/oct/leakhawk-app/Content/EO/vector.txt"	
