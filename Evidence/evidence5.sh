i="$1";

if grep -Eiqo "Hacked|leaked by|Pwned by|Doxed|Ow3ned|pawned by|Server Rootéd|\#opSriLanka|#OPlanka|#anonymous|Private key|Password leak|password dump|credential leak|credential dump|Credit card|Card dump | cc dump | credit_card|card_dump|working_card|cc_dump|skimmed|card_hack|sited hacked by|websited hacked by|website hacked by|site hacked by|websited hacked|domain hack|defaced|leaked by|site deface|mass deface|database dump|database dumped|db dumped|db dump|db leak|data base dump|data base leak|database hack|db hack|login dump|DNS LeAkEd|DNS fuck3d|zone transfer|DNS Enumeration|Enumeration Attack|cache snooping|cache poisoning|email hack|emails hack|emails leak,email leak|email dump|emails dump|email dumps|email-list|leaked email,leaked emails|email_hack" $i; then
    echo "found"
else
    echo "not found"
fi


