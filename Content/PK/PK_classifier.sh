#! /bin/bash
item="\$PK1";
i="$1";

	j=$( basename "$i" );
#	echo "$j";	
	#$var = basename "$i" | (awk -F"_" '{printf $1"_"$2"\n"}');
	#FN=$(echo "$j" | awk -F"_" '{printf $1"_"$2"\t"}');


	#Unigrams
	PK1=$(grep -owiE "PRIVATE" "$i"| wc -l);
	PK2=$(grep -owiE "KEY" "$i"| wc -l);
	PK3=$(grep -owiE "RSA" "$i"| wc -l);
	PK4=$(grep -owiE "SSHRSA" "$i"| wc -l);
	PK5=$(grep -owiE "KEY-----" "$i"| wc -l);



	#Bigrams
	PK6=$(grep -owiE "PRIVATE KEY" "$i"| wc -l);
	PK7=$(grep -owiE "RSA PRIVATE" "$i"| wc -l);
	PK8=$(grep -owiE "BEGIN CERTIFICATE" "$i"| wc -l);
	PK9=$(grep -owiE "DSA PRIVATE" "$i"| wc -l);
	PK10=$(grep -owiE "ENCRYPTED PRIVATE" "$i"| wc -l);
	PK11=$(grep -owiE "BEGIN RSA" "$i"| wc -l);
	
	#Trigrams
	PK12=$(grep -owiE "RSA PRIVATE KEY" "$i"| wc -l);
	PK13=$(grep -owiE "DSA PRIVATE KEY" "$i"| wc -l);
	PK14=$(grep -owiE "BEGIN RSA PRIVATE" "$i"| wc -l);
	PK15=$(grep -owiE "END PRIVATE KEY" "$i"| wc -l);
	PK16=$(grep -owiE "BEGIN PRIVATE KEY" "$i"| wc -l);

	#fourgrams
	PK17=$(grep -owiE "BEGIN RSA PRIVATE KEY" "$i"| wc -l);
	PK18=$(grep -owiE "BEGIN DSA PRIVATE KEY" "$i"| wc -l);
	PK19=$(grep -owiE "END RSA PRIVATE KEY" "$i"| wc -l);

	#-----XXX------
	PK20=$(grep -oE "[-]{5}[A-Za-z0-9 ]+[-]{5}" "$i"| wc -l);

	#PK related terms
	PK21=$(grep -owiE "PRIVATE KEY|RSA PRIVATE|DSA PRIVATE|ENCRYPTED PRIVATE|BEGIN RSA|RSA PRIVATE KEY|DSA PRIVATE KEY|BEGIN RSA PRIVATE|END PRIVATE KEY|BEGIN PRIVATE KEY" "$i"| wc -l);

#### add others

	string=",?"

	item="\$PK1"; for i in {2..21}; do item+=,"\$PK$i";done;item+="$string";eval "echo $item">>"/home/nalinda/oct/leakhawk-app/Content/PK/vector.txt"
#	k=$( basename "$i" ".txt");
	cat "/home/nalinda/oct/leakhawk-app/Content/PK/header.txt" "/home/nalinda/oct/leakhawk-app/Content/PK/vector.txt" >> $j.PK.arff 
	mv "$j.PK.arff" "/home/nalinda/oct/IN/ARFF/"
	rm -rf "/home/nalinda/oct/leakhawk-app/Content/PK/vector.txt"	
