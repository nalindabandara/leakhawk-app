i="$1";

if grep -Eiqo "SQL Injection|SQLi|SQL-i|Blind SQL-i" $i; then
    echo "found"
else
    echo "not found"
fi


