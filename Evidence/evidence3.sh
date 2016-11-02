i="$1";

if ls "$i" | grep -oiqE "SQL_Injection|SQLi|SQL-i|Blind SQL-i" ; then
    echo "found"
else
    echo "not found"
fi
