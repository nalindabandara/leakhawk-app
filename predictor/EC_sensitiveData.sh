grep -owiE "CONFIDENTIAL|secret|do not disclose" "$1"| wc -l
