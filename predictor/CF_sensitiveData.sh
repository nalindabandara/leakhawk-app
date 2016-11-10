grep -owiE "enable password" "$1"| wc -l

# even the "service password-encryption" is set, it wont be hard to recover the password