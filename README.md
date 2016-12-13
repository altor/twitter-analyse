PJE 2 - Analyse de comportement avec Twitter

author: D'AZEMAR Arthur
	EL GHARBI Norhane

20/09: Création du dépôt GitLab

# Dépendance

- [twitter4J](http://twitter4j.org/en/index.html)
- [opencsv](https://sourceforge.net/projects/opencsv/) : bibliothèque de manipulation de CSV
- [commons-lang3](https://commons.apache.org/proper/commons-lang/download_lang.cgi) : dépendance requise par opencsv
- [simmetrics](http://sourceforge.net/projects/simmetrics/) : bibliothèque de distance entre chaine de caractère
- [JFreeChart](https://sourceforge.net/projects/jfreechart/files/) : bibliothèqye de génération de graphique

# executables
* pje.jar : application final (à lancer avec la commande java -jar pje.jar tweets_determinant.csv bayes unigramme frequence)
* test_classifier.sh : permet de lancer les test des différentes méthodes de classifications
* gen_plot : script gnuplot permetant de générer les graphiques résultant de l'analyse experimental (avec la commande gnuplot gen_plot)


