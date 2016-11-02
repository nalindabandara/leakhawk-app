i="$1";

if ls "$i" | grep -oqiE "dns-brute|dnsrecon|fierce|Dnsdict6|axfr|SQLmap"; then
    echo "found"
else
    echo "not found"
fi
