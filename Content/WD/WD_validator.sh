i="$1";

j=$(java -cp /home/nalinda/weka-3-9-0/weka.jar weka.classifiers.trees.RandomForest -T $i -l /home/nalinda/oct/leakhawk-app/Content/WD/WD.model -p 0| grep -o "WD")

echo "$j"

#echo "working";