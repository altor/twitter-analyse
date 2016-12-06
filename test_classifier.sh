echo "naif : $(java -jar test_pje.jar tweetsBase.csv naif positive.txt negative.txt)"


for i in $(seq 1 10)
do
       echo "knn $i : $(java -jar test_pje.jar tweetsBase.csv knn $i levenshtein)"
done
for s1 in "bigramme" "uniandbigramme" "unigramme"
do
    for s2 in "frequence" "presence"
    do
	 echo "bayes $s1 $s2 : $(java -jar test_pje.jar tweetsBase.csv bayes $s1 $s2)"
	# echo "bayes $s1 $s2 : java -jar test_pje.jar tweetsBase.csv bayes $s1 $s2"
    done
done
