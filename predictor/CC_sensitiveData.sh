#! /bin/bash
i=$1
grep -owiE "CVV|card verification number|PIN|CVV2|CCV2|CVC|CVC2|verification code|CID|CAV2|card_dump|working_card|cc_dump|skimmed|card hack|card dump|cc dump|skimmed|card hack" "$i"| wc -l
