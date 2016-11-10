#! /bin/bash

i="$1";

	j=$( basename "$i" );

	#Unigrams
	EC1=$(grep -owiE "reply|forward" "$i"| wc -l);
	EC2=$(grep -owiE "subject" "$i"| wc -l);
	EC3=$(grep -owiE "mailed by" "$i"| wc -l);
	EC4=$(grep -owiE "from:|wrote:|to:|subject:" "$i"| wc -l);
	EC5=$(grep -owiE "regards" "$i"| wc -l);

	#title
        EC6=$(ls "$i" | grep -oiE "conversation|email"| wc -l);
		
	#no of > characters
	EC7=$(grep -oE ">" "$i" | wc -l);



	string=",?"

	item="\$EC1"; for i in {2..7}; do item+=,"\$EC$i";done;item+="$string";eval "echo $item">>"/home/nalinda/oct/leakhawk-app/Content/EC/vector.txt"
	cat "/home/nalinda/oct/leakhawk-app/Content/EC/header.txt" "/home/nalinda/oct/leakhawk-app/Content/EC/vector.txt" >> $j.EC.arff 
	mv "$j.EC.arff" "/home/nalinda/oct/IN/ARFF/"
	rm -rf "/home/nalinda/oct/leakhawk-app/Content/EC/vector.txt"	
