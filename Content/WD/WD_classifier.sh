#! /bin/bash
item="\$WD1";
i="$1";

	j=$( basename "$i" );


	#Unigrams
	WD1=$(grep -owiE "defaced" "$i"| wc -l);


	#Bigrams
	WD2=$(grep -owiE "domain hacked" "$i"| wc -l);
	WD3=$(grep -owiE "defaced by" "$i"| wc -l);
	WD4=$(grep -owiE "website[s] hacked" "$i"| wc -l);
	WD5=$(grep -owiE "site hacked" "$i"| wc -l);
	WD6=$(grep -owiE "got hacked" "$i"| wc -l);
	WD7=$(grep -owiE "leaked by" "$i"| wc -l);
	WD8=$(grep -owiE "sites defaced" "$i"| wc -l);
	WD9=$(grep -owiE "mass deface" "$i"| wc -l);
	WD10=$(grep -owiE "Server Rootéd" "$i"| wc -l);
	
	#Trigrams
	WD11=$(grep -owiE "sites got Hacked" "$i"| wc -l);


	#-----no of mirror------
	WD12=$(grep -oiE "mirror" "$i"| wc -l);

	#defacement related terms
	WD13=$(grep -owiE "sited hacked by|websited hacked by|website hacked by|site hacked by|websited hacked|domain hack|defaced|leaked by|site deface|mass deface" "$i"| wc -l);

	WD14=$(grep -owiE "mirror-zone.org|dark-h.net|zone-h.org|zone-hc.com|dark-h.org|turk-h.org|Zone-hc.com|add-attack.com/mirror|zone-db.com|hack-db.com" "$i"| wc -l);

	#URL count
#echo "$i";
	WD15=$(python "/home/nalinda/oct/leakhawk-app/Content/WD/URL_counter.py" "$i");

	count=0;
	if [ $WD15 -gt 0 ]
	then 
		count=1;
	fi
#### add from ods sheet

	temp1=$(grep -owiE "(web)*site(s)*[A-Za-z0-9_ ]*hack(ed)*|deface(d) by|hack(ed) by|deface|pwned|hacked|leaked" "$i"| wc -l);
	temp2=$(ls "$i" | grep -oiE "deface" | wc -l);
	WD16=$((temp1+temp2+count));
	string=",?"

	item="\$WD1"; for i in {2..16}; do item+=,"\$WD$i";done;item+="$string";eval "echo $item">>"/home/nalinda/oct/leakhawk-app/Content/WD/vector.txt"

	cat "/home/nalinda/oct/leakhawk-app/Content/WD/header.txt" "/home/nalinda/oct/leakhawk-app/Content/WD/vector.txt" >> $j.WD.arff 
	mv "$j.WD.arff" "/home/nalinda/oct/IN/ARFF/"
	rm -rf "/home/nalinda/oct/leakhawk-app/Content/WD/vector.txt"	
