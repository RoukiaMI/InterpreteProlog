_Documentation du rendu de projet, à remplir._
#Jalon 1:
>Unify.java : Contient tous les algorithmes demand�s � savoir replace, occurs et unify
Dans mon impl�mentation de l�algorithme replace dans la m�thode replace, je r�cup�re avec mon Visitor tous les termes d�un foncteur (je suppose qu�un terme est de type Func) ensuite je teste si la variable en cl� de mon environnement est dans ma liste de terme recursivement si oui je remplace par la valeur de cette dans ma table d�association et renvoie le terme produit. #(Fichier Unify.java ligne 32 :59)
Quant � mon impl�mentation de l�algorithme occurs c�est simple je renvoie vraie si un func contient une variable. #(Fichier Unify.java ligne 63 :82)
L�algorithme unify est implemente dans la m�thode unify, je v�rifie si mes deux termes ont la m�me symbole de t�te si non je l�ve une UnifyException, puis je teste si mes deux termes ont le m�me nombre d�arit� si non je l�ve une UnifyException, et enfin  cette methode retourne  un environnement de type Map. #(Fichier Unify.java ligne 84 :145)
>Interpreter.java : Prend en argument un Program et a deux m�thodes interpreter0 et interpreter1 impl�mentant respectivement les algorithmes interpreter0 et interpreter1.


#Jalon 2:
>Unify2.java : : Contient tous les algorithmes demand�s � savoir rename, step, solve
Pour le renommage =, j�utilise des fonctions interm�diaire une qui renomme un variable dans un func et une qui renomme une variable et une qui renomme une r�gle rename(Assert a,int cpt).
Pour l�algorithme Step implement� dans la m�thode step qui prend en parametre l�ensemble des r�gles et me produit un environnement en unifiant une r�gle avec un but et en prouvant le body de cette r�gle en cherchant d�autre r�gle dans l�ensemble des r�gles qui peut etre unifier avec ces derni�re et on regarde si �a match avec les �l�ments de mon environnement sinon on l�ve une StepException parce que ce but ne peut pas �tre unifier parce qu�il est insatifiable.
Quant � l�algorithme Solve j�utilise Step pour tous les buts dans ma liste de goal.
>Interpreter2.java : : Contient la m�thode qui permet d�interpr�ter un programme.


#Jalon 3:
>Choix.java : : Est la structure de donn�es utilis�s pour mon algorithme de backtracking
Solve.java : : a une m�thode main et une m�thode solve qui est l�impl�mentation de mon algorithme de Backtracking. Pour cet algorithme, j�utilise la m�thode step2 de la classe unify2 du jalon2.
Et j�enregistre le choix de cette solution dans une liste de choix que je vais par la suite renvoyer.
PS : Solve.java fonctionne � elle seule et ne produit rien depuis Interpreter3 du main 
