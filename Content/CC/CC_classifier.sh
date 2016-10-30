#! /bin/bash
item="\$CC1"; 
i="$1";
	j=$( basename "$i" );
#	echo "$j";	
	#$var = basename "$i" | (awk -F"_" '{printf $1"_"$2"\n"}');
	FN=$(echo "$j" | awk -F"_" '{printf $1"_"$2"\t"}');


	#Unigrams
	CC1=$(grep -owiE "card" "$i"| wc -l);
	CC2=$(grep -owiE "number" "$i"| wc -l);
	CC3=$(grep -owiE "credit" "$i"| wc -l);
	CC4=$(grep -owiE "expiration" "$i"| wc -l);
	CC5=$(grep -owE "CC" "$i"| wc -l);

	#Bigrams
	CC6=$(grep -owiE "Name On" "$i"| wc -l);
	CC7=$(grep -owiE "card number" "$i"| wc -l);
	CC8=$(grep -owiE "credit card" "$i"| wc -l);
	CC9=$(grep -owiE "Expiration Date | exp date" "$i"| wc -l);
	CC10=$(grep -owiE "Maiden Name" "$i"| wc -l);
	CC11=$(grep -owiE "zip code" "$i"| wc -l);
	CC12=$(grep -owiE "account number" "$i"| wc -l);
	CC13=$(grep -owiE "card type" "$i"| wc -l);
	CC14=$(grep -owiE "card information" "$i"| wc -l);
	CC15=$(grep -owiE "CC number" "$i"| wc -l);
	CC16=$(grep -owiE "card hack" "$i"| wc -l);
	CC17=$(grep -owiE "ATM pin" "$i"| wc -l);
	CC18=$(grep -owiE "account information" "$i"| wc -l);
	CC19=$(grep -owiE "mother maiden|mothers maiden|mother's maiden" "$i"| wc -l);

	#Trigrams
	CC20=$(grep -owiE "Date of Birth" "$i"| wc -l);
	CC21=$(grep -owiE "Credit Card Number" "$i"| wc -l);
	CC22=$(grep -owiE "Credit Card Information" "$i"| wc -l);
	CC23=$(grep -owiE "name on card" "$i"| wc -l);
	CC24=$(grep -owiE "card verification code" "$i"| wc -l);
	CC25=$(grep -owiE "card verification number" "$i"| wc -l);
	CC26=$(grep -owiE "bank account number" "$i"| wc -l);
	CC27=$(grep -owiE "visa card number" "$i"| wc -l);

	#number of Credit Card Numbers
	CC28=$(grep -oE "[2-6][0-9]{3}([ -]?)[0-9]{4}([ -]?)[0-9]{4}([ -]?)[0-9]{3,4}([ -]?)[0-9]{0,3}[?^a-zA-Z]?" "$i"| wc -l);

	#CC related terms (exp date)
	CC29=$(grep -owiE "Expiry Date|Expire|Exp.Date|Expiration|Exp. month|Exp. Years|expyear|expmonth|(exp)|ExpDate|ExpD[m/y]|Date D'expiration" "$i"| wc -l);

	#CC related terms (verification code)
	CC30=$(grep -owiE "CVV |card verification number| CVV2|CCV2|CVC|CVC2|verification code|CID|CAV2" "$i"| wc -l);
	CC31=$(ls "$i" | grep -oiE "credit_card|card_dump|working_card|cc_dump|skimmed|card_hack" | wc -l);
	CC32=$(grep -owE "CVV2|CVV|CVC2|CAV2" "$i"| wc -l);
	
	#CC related terms (card brands)
	CC33=$(grep -owiE "VISA|Mastercard|JCB|AMEX|american express|Discover|Diners Club" "$i"| wc -l);
	
	#CC related terms
	CC34=$(grep -owiE "Debit or credit card number|Credit Card Number|credit_number|Card Number|cardnum|Primary Account Number|CC Number" "$i"| wc -l);

	#blended features	
	CC35=$((CC29+CC30+CC31+CC32+CC33+CC34));
	

	#metadata based features

	N=$(tr -dc '[:digit:]' < "$i")		#NUMBERS
	L=$(tr -dc '[:alpha:]' < "$i")		#LETTERS
	A=$(tr -dc '[:alnum:]' < "$i")		#ALNUM

	a="${#N}";
	b="${#L}";
	c="${#A}";

	if [ $c -eq 0 ] ; then c=1 ; else c=$c; fi;
	NP=$(bc <<< "scale=2;$a/$c*100");
	CP=$(bc <<< "scale=2;$b/$c*100");

	string=",${#N},${#L},${#A},$NP,$CP,?"

	item="\$CC1"; for i in {2..35}; do item+=,"\$CC$i";done;item+="$string";eval "echo $item">>"/home/nalinda/oct/leakhawk-app/Content/CC/vector.txt"
#	k=$( basename "$i" ".txt");
	cat "/home/nalinda/oct/leakhawk-app/Content/CC/header.txt" "/home/nalinda/oct/leakhawk-app/Content/CC/vector.txt" >> $j.CC.arff 
	mv "$j.CC.arff" "/home/nalinda/oct/IN/ARFF/"
	rm -rf "/home/nalinda/oct/leakhawk-app/Content/CC/vector.txt"

