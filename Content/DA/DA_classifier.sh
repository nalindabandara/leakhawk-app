#! /bin/bash

i="$1";

	j=$( basename "$i" );


	#Unigrams
	DA1=$(grep -owiE "MX|NS|PTR|CNAME|SOA" "$i"| wc -l);
	DA2=$(grep -owiE "dns|record|host" "$i"| wc -l);
	DA3=$(grep -owE "INTO" "$i"| wc -l);
	DA4=$(grep -owiE "snoop|axfr|brute|poisoning" "$i"| wc -l);


	#Bigrams
	DA5=$(grep -owE "43200 IN|10800 IN|86400 IN|3600 IN" "$i"| wc -l);
	DA6=$(grep -owE "IN A|IN MX|IN NS|IN CNAME" "$i"| wc -l);
	DA7=$(grep -owE "no PTR" "$i"| wc -l);
	DA8=$(grep -owE "hostnames found" "$i"| wc -l);
	DA9=$(grep -owE "zone transfer" "$i"| wc -l);
	DA10=$(grep -owiE "MX 10|MX 20|MX 30|MX 40|MX 50|MX 60" "$i"| wc -l);
	
	#TRIGRAMS
	DA11=$(grep -owE "transfer not allowed" "$i"| wc -l);
	DA12=$(grep -owiE "Trying zone transfer" "$i"| wc -l);

	#DA tools
	DA13=$(grep -owiE "dns-brute|dnsrecon|fierce|tsunami|Dnsdict6|axfr" "$i"| wc -l);
	DA14=$(ls "$i" | grep -oiE "dns-brute|dnsrecon|fierce|tsunami|Dnsdict6|axfr"| wc -l);

	#DA related terms
	DA15=$(grep -owiE "DNS LeAkEd|DNS fuck3d|zone transfer|DNS_Enumeration|Enumeration_Attack" "$i"| wc -l);
	DA16=$(ls "$i" | grep -oiE "DNS LeAkEd|DNS fuck3d|zone transfer|DNS_Enumeration|Enumeration_Attack"| wc -l);
	
	#DA related attack types
	DA17=$(grep -owiE "DNS Enumeration Attack|DNS enumeration|zone transfer|misconfigured DNS|DNS Cache Snooping" "$i"| wc -l);
	DA18=$(ls "$i" | grep -oiE "DNS Enumeration Attack|DNS enumeration|zone transfer|misconfigured DNS|DNS Cache Snooping"| wc -l);

	#blended features	
	DA19=$(grep -owiE "\[\*\]" "$i"| wc -l);



	string=",?"

	item="\$DA1"; for i in {2..19}; do item+=,"\$DA$i";done;item+="$string";eval "echo $item">>"/home/nalinda/oct/leakhawk-app/Content/DA/vector.txt"
#	k=$( basename "$i" ".txt");
	cat "/home/nalinda/oct/leakhawk-app/Content/DA/header.txt" "/home/nalinda/oct/leakhawk-app/Content/DA/vector.txt" >> $j.DA.arff 
	mv "$j.DA.arff" "/home/nalinda/oct/IN/ARFF/"
	rm -rf "/home/nalinda/oct/leakhawk-app/Content/DA/vector.txt"	
