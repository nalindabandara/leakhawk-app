i="$1";

if ls "$i" | grep -oiE "UGLegion|RetrOHacK|Anonymous|AnonSec|AnonGhost|ANONYMOUSSRILANKA|W3BD3F4C3R|SLCYBERARMY|DAVYJONES|BLACKHATANON|ANUARLINUX|UGLEGION|HUSSEIN98D|We Are Anonymous|We do not Forget, We do not Forgive|Laughing at your security since 2012|AnonGhost is Everywhere"; then
    echo "found"
else
    echo "not found"
fi
