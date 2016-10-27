i="$1";

#j=$(java -cp /home/nalinda/weka-3-9-0/weka.jar weka.classifiers.trees.RandomForest -T $i -l /home/nalinda/oct/leakhawk-app/Content/CC/CC.model -p 0| grep -o "CC")


j=$(java -cp /home/nalinda/weka-3-9-0/weka.jar weka.classifiers.trees.RandomForest -T $i -l /home/nalinda/oct/leakhawk-app/Content/CC/CC.model -p 0 | grep -A1 "actual" | tail -n 1 | awk -F":" '{printf $3"\n"}');


class=$(java -cp /home/nalinda/weka-3-9-0/weka.jar weka.classifiers.trees.RandomForest -T $i -l /home/nalinda/oct/leakhawk-app/Content/CC/CC.model -p 0 | grep -A1 "actual" | tail -n 1 | awk -F":" '{printf $3"\n"}' | awk -F" " '{printf $1"\n"}');

prediction=$(java -cp /home/nalinda/weka-3-9-0/weka.jar weka.classifiers.trees.RandomForest -T $i -l /home/nalinda/oct/leakhawk-app/Content/CC/CC.model -p 0 | grep -A1 "actual" | tail -n 1 | awk -F":" '{printf $3"\n"}' | awk -F" " '{printf $2"\n"}');

echo "$j"

#echo "working";
