i="$1";

if grep -Eiqo "dns-brute|dnsrecon|fierce|Dnsdict6|axfr|SQLmap" $i; then
    echo "found"
else
    echo "not found"
fi


