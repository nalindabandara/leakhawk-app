i="$1";

#ori
#   j=$(java -cp /home/nalinda/weka-3-9-0/weka.jar weka.classifiers.trees.RandomForest -T $i -l /home/nalinda/oct/leakhawk-app/Content/DB/DB.model -p 0| grep -o "DB")


j=$(java -cp /home/nalinda/weka-3-9-0/weka.jar weka.classifiers.trees.RandomForest -T $i -l /home/nalinda/oct/leakhawk-app/Content/DB/DB.model -p 0 | grep -A1 "actual" | tail -n 1 | awk -F":" '{printf $3"\n"}');

echo "$j"

#echo "working";
