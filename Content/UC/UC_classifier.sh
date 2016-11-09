#! /bin/bash
item="\$UC1";
i="$1";

	j=$( basename "$i" );

	#Unigrams
	UC1=$(grep -owiE "password|passw0rd|passwd|passwort" "$i"| wc -l);
	UC2=$(grep -owiE "user|username|admin" "$i"| wc -l);


	#Bigrams
	UC3=$(grep -owiE "user name" "$i"| wc -l);
	UC4=$(grep -owiE "password dump" "$i"| wc -l);
	UC5=$(grep -owiE "Dumped from|dumped by" "$i"| wc -l);
	UC6=$(grep -owiE "login dump" "$i"| wc -l);
	UC7=$(grep -owiE "credential dump" "$i"| wc -l);

	#Trigram
	UC8=$(grep -owE "password dumped by|" "$i"| wc -l);


	#number of hashes
	UC9=$(python "/home/nalinda/oct/leakhawk-app/Content/UC/Hash_ID_Finder-file.py" "$i" | grep "Possible Hashes" | wc -l);


	#filename
	UC10=$(ls "$i" | grep -oiE "password|passwords|passw0rd|passwd|passwort|username|user name|credentials|password dump|Dumped from|dumped by|login dump|credentials dump|password dumped by" | wc -l);
	
	#body text
	UC11=$(grep -owE "password|passw0rd|passwd|passwords|passwort|username|user name|credentials|password dump|Dumped from|dumped by|login dump|credentials dump|password dumped by|email_hacked|emails_hacked|email|emails_leak|email_dump|emails_dump|email_dumps|email-list|leaked_email|email_hack" "$i"| wc -l);
	
	#email,username,password words located at nearby lines
	
	UC12=$(grep -iE "facebook|username|user name|login|email|e_mail|emailID|email_address|credentials|Dumped from|dumped by|login dump|credentials dump|password dumped by|id|email_hacked|emails_hacked|email|emails_leak|email_dump|emails_dump|email_dumps|email-list|leaked_email|email_hack" "$i" -A 5 -B 5 | grep -Eio "password|passw0rd|passwd|passwort|pass|credentials|passwords|password dump|Dumped from|dumped by|login dump|credentials dump|password dumped by" | wc -l);
	
	#no of email RegEx
	
	UC13=$(grep -oE "(([a-zA-Z]|[0-9])|([-]|[_]|[.]))+[@](([a-zA-Z0-9])|([-])){2,63}([.]((([a-zA-Z0-9])|([-])){2,63})){1,4}" "$i" | wc -l);
	
	string=",?"

	item="\$UC1"; for i in {2..13}; do item+=,"\$UC$i";done;item+="$string";eval "echo $item">>"/home/nalinda/oct/leakhawk-app/Content/UC/vector.txt"
#	k=$( basename "$i" ".txt");
	cat "/home/nalinda/oct/leakhawk-app/Content/UC/header.txt" "/home/nalinda/oct/leakhawk-app/Content/UC/vector.txt" >> $j.UC.arff 
	mv "$j.UC.arff" "/home/nalinda/oct/IN/ARFF/"
	rm -rf "/home/nalinda/oct/leakhawk-app/Content/UC/vector.txt"	
