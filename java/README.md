Ce répertoire contient la version Java de l'analyseur syntaxique (parseur)
Prolog pour bien démarrer le projet.


# Contenu

- `src` contient les sources Java. Il s'agit d'un package `pcomp.prolog`.
- `src/pcom/prolog/ast` contient la structure d'AST (arbre syntaxique abstrait). Un programme est une instance de `Program`. C'est une liste de déclarations `Dec` (buts `Goal` ou assertions `Assert`), contenant des `Term` (variables `Var` ou foncteurs `Func`). L'AST implante le motif visiteur et a un _printer_
- `src/pcomp/prolog/parser` contient un parseur. Les fichiers `PrologANTLRGrammar*.java` ont été générés automatiquement par l'outil ANTLR 4 à partir du fichier de grammaire `PrologANTLRGrammar.g4`.
- `src/pcomp/prolog/Main.java` est une classe d'exemple qui lit des exemples de source Prolog, les convertit en `Program`, et réaffiche le programme à l'écran. Vous pourrez utiliser directement cette classe pour construire les objets `Program` nécessaires au projet.
- `exemples` contient des exemples de programmes Prolog.
- `jars` contient les classes pour faire fonctionner l'outil ANTLR 4 à l'exécution.


# Compilation

Dans un projet Eclipse, assurez-vous de :
- placer le contenu de `src` dans le répertoire `src` à la racine de votre projet
- placer le répertoire `exemples` à la racine du projet pour que `Main.java` puisse trouver les fichiers d'exemple à charger
- ajouter le fichier `jars/antlr-runtime.jar` dans le _Java Build Path_ (clic droit sur le projet > option _Properties_ > _Java Build Path_ > onglet _Libraries_ > ligne _Classpath_ > bouton _Add External JARs_)


Il ne sera pas nécessaire de modifier la grammaire `PrologANTLRGrammar.g4` fournie. Toutefois, si vous le faites, vous devrez regénérer les fichiers Java du parseur en appelant ANTLR. Pour cela, vous devez installer l'outil ANTLR 4, puis aller dans le répertoire `src/pcomp/prolog/parser` et taper dans un shell : `antlr4 PrologANTLRGrammar.g4`.
