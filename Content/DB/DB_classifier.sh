#! /bin/bash
item="\$DB1";
i="$1";

	j=$( basename "$i" );

	#Unigrams
	DB1=$(grep -owiE "0|null|blank" "$i"| wc -l);
	DB2=$(grep -owiE "insert|update|create" "$i"| wc -l);
	DB3=$(grep -owE "INTO" "$i"| wc -l);
	DB4=$(grep -owiE "table|schema|database|db" "$i"| wc -l);
	DB5=$(grep -owE "PostgreSQL|mysql|mssql|oracle db|db2" "$i"| wc -l);
	DB6=$(grep -owiE "Database:|table:|data base:|DB Detection:" "$i"| wc -l);


	#Bigrams
	DB7=$(grep -owiE "database dump" "$i"| wc -l);
	DB8=$(grep -owiE "db leak" "$i"| wc -l);
	DB9=$(grep -owiE "Dumped from|dumped by" "$i"| wc -l);
	DB10=$(grep -owE "CREATE TABLE|ALTER TABLE" "$i"| wc -l);
	DB11=$(grep -owE "INSERT INTO" "$i"| wc -l);
	DB12=$(grep -owiE ") values" "$i"| wc -l);
	DB13=$(grep -owE "Found :" "$i"| wc -l);
	DB14=$(grep -owiE "Data found" "$i"| wc -l);
	DB15=$(grep -owE "NOT NULL" "$i"| wc -l);
	DB16=$(grep -owE "INTO users" "$i"| wc -l);
	DB17=$(grep -owiE "database dump|database dumped|db dumped|db dump|db leak|data base dump|data base leak|database hack|db hack|login dump" "$i"| wc -l);
	DB18=$(grep -owiE "available databases" "$i"| wc -l);
	DB19=$(grep -owiE "db dump" "$i"| wc -l);


	#number of symbols
	DB20=$(grep -oE "\-|\+|\\|" "$i"| wc -l);

	#Injection related terms
	DB21=$(grep -owiE "SQL Injection|SQLi|SQL-i|Blind SQL-i" "$i"| wc -l);

	#DB related terms
	DB22=$(grep -owiE "PRIMARY KEY|ALTER TABLE|TABLE FOUND" "$i"| wc -l);

	#SQL injection tool
	DB23=$(ls "$i" | grep -oiE "sqlmap"| wc -l);
	
	#filename
	DB24=$(ls "$i" | grep -oiE "SQL Injection|SQLi|SQL-i|Blind SQL-i|database dump|db dump|db leak|data base dump|data base leak|database hack|db hack|login dump" | wc -l);

	#blended features	
	DB25=$(grep -owiE "\[\*\]" "$i"| wc -l);

	string=",?"

	item="\$DB1"; for i in {2..25}; do item+=,"\$DB$i";done;item+="$string";eval "echo $item">>"/home/nalinda/oct/leakhawk-app/Content/DB/vector.txt"
#	k=$( basename "$i" ".txt");
	cat "/home/nalinda/oct/leakhawk-app/Content/DB/header.txt" "/home/nalinda/oct/leakhawk-app/Content/DB/vector.txt" >> $j.DB.arff 
	mv "$j.DB.arff" "/home/nalinda/oct/IN/ARFF/"
	rm -rf "/home/nalinda/oct/leakhawk-app/Content/DB/vector.txt"	
