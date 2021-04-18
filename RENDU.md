_Documentation du rendu de projet, Ã  remplir._
#Jalon 1:
>Unify.java : Contient tous les algorithmes demandés à savoir replace, occurs et unify
Dans mon implémentation de l’algorithme replace dans la méthode replace, je récupère avec mon Visitor tous les termes d’un foncteur (je suppose qu’un terme est de type Func) ensuite je teste si la variable en clé de mon environnement est dans ma liste de terme recursivement si oui je remplace par la valeur de cette dans ma table d’association et renvoie le terme produit. #(Fichier Unify.java ligne 32 :59)
Quant à mon implémentation de l’algorithme occurs c’est simple je renvoie vraie si un func contient une variable. #(Fichier Unify.java ligne 63 :82)
L’algorithme unify est implemente dans la méthode unify, je vérifie si mes deux termes ont la même symbole de tête si non je lève une UnifyException, puis je teste si mes deux termes ont le même nombre d’arité si non je lève une UnifyException, et enfin  cette methode retourne  un environnement de type Map. #(Fichier Unify.java ligne 84 :145)
>Interpreter.java : Prend en argument un Program et a deux méthodes interpreter0 et interpreter1 implémentant respectivement les algorithmes interpreter0 et interpreter1.


#Jalon 2:
>Unify2.java : : Contient tous les algorithmes demandés à savoir rename, step, solve
Pour le renommage =, j’utilise des fonctions intermédiaire une qui renomme un variable dans un func et une qui renomme une variable et une qui renomme une règle rename(Assert a,int cpt).
Pour l’algorithme Step implementé dans la méthode step qui prend en parametre l’ensemble des règles et me produit un environnement en unifiant une règle avec un but et en prouvant le body de cette règle en cherchant d’autre règle dans l’ensemble des règles qui peut etre unifier avec ces dernière et on regarde si ça match avec les éléments de mon environnement sinon on lève une StepException parce que ce but ne peut pas être unifier parce qu’il est insatifiable.
Quant à l’algorithme Solve j’utilise Step pour tous les buts dans ma liste de goal.
>Interpreter2.java : : Contient la méthode qui permet d’interpréter un programme.


#Jalon 3:
>Choix.java : : Est la structure de données utilisés pour mon algorithme de backtracking
Solve.java : : a une méthode main et une méthode solve qui est l’implémentation de mon algorithme de Backtracking. Pour cet algorithme, j’utilise la méthode step2 de la classe unify2 du jalon2.
Et j’enregistre le choix de cette solution dans une liste de choix que je vais par la suite renvoyer.
PS : Solve.java fonctionne à elle seule et ne produit rien depuis Interpreter3 du main 
