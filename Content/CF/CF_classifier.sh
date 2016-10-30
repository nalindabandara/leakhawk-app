#! /bin/bash
item="\$PK1";
i="$1";

	j=$( basename "$i" );

	#Unigrams
	CF1=$(grep -owiE "ip" "$i"| wc -l);
	CF2=$(grep -owiE "cisco" "$i"| wc -l);
	CF3=$(grep -owiE "password-encryption" "$i"| wc -l);
	CF4=$(grep -owiE "spanning-tree" "$i"| wc -l);
	CF5=$(grep -owiE "domain-lookup" "$i"| wc -l);
	CF6=$(grep -owiE "serial0/0/0" "$i"| wc -l);
	CF7=$(grep -owiE "access-list" "$i"| wc -l);
	#Bigrams
	CF8=$(grep -owiE "interface FastEthernet[0-9]|interface Serial[0-9]" "$i"| wc -l);
	CF9=$(grep -owiE "speed auto|duplex auto" "$i"| wc -l);
	CF10=$(grep -owiE "0 line" "$i"| wc -l);
	CF11=$(grep -owiE "line vty|line aux|line con" "$i"| wc -l);
	CF12=$(grep -owiE "service password" "$i"| wc -l);
	CF13=$(grep -owiE "ip address" "$i"| wc -l);
	CF14=$(grep -owiE "ip route" "$i"| wc -l);
	CF15=$(grep -owiE "banner motd" "$i"| wc -l);
	CF16=$(grep -owiE "no service" "$i"| wc -l);
	CF17=$(grep -owiE "clock rate" "$i"| wc -l);
	CF18=$(grep -owiE "ip cef|ipv6 cef" "$i"| wc -l);
	CF19=$(grep -owiE "service password-encryption" "$i"| wc -l);
	
	#Trigrams
	CF20=$(grep -owiE "line vty 0|line con 0|line aux 0" "$i"| wc -l);
	CF21=$(grep -owiE "no ip address" "$i"| wc -l);
	CF22=$(grep -owiE "no ipv6 cef" "$i"| wc -l);
	CF23=$(grep -owiE "switchport access vlan" "$i"| wc -l);


	#-----no of ! symbols------
	CF24=$(grep -oiE "!" "$i"| wc -l);



	string=",?"

	item="\$CF1"; for i in {2..24}; do item+=,"\$CF$i";done;item+="$string";eval "echo $item">>"/home/nalinda/oct/leakhawk-app/Content/CF/vector.txt"
#	k=$( basename "$i" ".txt");
	cat "/home/nalinda/oct/leakhawk-app/Content/CF/header.txt" "/home/nalinda/oct/leakhawk-app/Content/CF/vector.txt" >> $j.CF.arff 
	mv "$j.CF.arff" "/home/nalinda/oct/IN/ARFF/"
	rm -rf "/home/nalinda/oct/leakhawk-app/Content/CF/vector.txt"	
