i="$1";

if ls "$i" | grep -oqiE "UGLegion|RetrOHacK|Anonymous|AnonSec|AnonGhost|ANONYMOUSSRILANKA|W3BD3F4C3R|SLCYBERARMY|DAVYJONES|BLACKHATANON|ANUARLINUX|UGLEGION|HUSSEIN98D|We_Are_Anonymous|We_do_not_Forget|We_do_not_Forgive|Laughing_at_your_security_since_2012|AnonGhost_is_Everywhere"; then
    echo "found"
else
    echo "not found"
fi
