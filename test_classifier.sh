for fichier in "tweets_normal.csv" "tweets_3_l.csv" "tweets_determinant.csv"
do
    echo "#naif $fichier"
    echo "naif $(java -jar test_pje.jar $fichier naif positive.txt negative.txt)"



    for distance in "levenshtein" "jaccard"
    do
	if [ "$distance" == "levenshtein" ]
	then
	    cmp=0
	else
	    cmp=1
	fi
	echo -e "\n\n#knn $fichier $distance"
	for i in $(seq 1 10)
	do
	    echo "$cmp $i $(java -jar test_pje.jar $fichier knn $i $distance)"
	    ((cmp+=2))
	done
    done

    echo -e "\n\n#bayes $fichier"
    cmp=0
    for s1 in "bigramme" "uniandbigramme" "unigramme"
    do
	for s2 in "frequence" "presence"
	do
	    echo "$cmp \"$s1 $s2\" $(java -jar test_pje.jar $fichier bayes $s1 $s2)"
	    ((cmp+=1))
	    # echo "bayes $s1 $s2 : java -jar test_pje.jar $fichier bayes $s1 $s2"
	done
    done
echo -e "\n\n"    
done
