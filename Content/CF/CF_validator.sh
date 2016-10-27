i="$1";

j=$(java -cp /home/nalinda/weka-3-9-0/weka.jar weka.classifiers.trees.RandomForest -T $i -l /home/nalinda/oct/leakhawk-app/Content/CF/CF.model -p 0| grep -o "CF")

echo "$j"

#echo "working";
