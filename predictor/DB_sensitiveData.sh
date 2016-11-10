i=$1;

j=$(grep -owiE "database dump|database dumped|db dumped|logins dump|db dump|db leak|data base dump|data base leak|database hack|db hack|login dump|SQL Injection|SQLi|SQL-i|Blind SQL-i|available databases|password" "$i"| wc -l);

k=$();

echo $j;
