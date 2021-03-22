Ce répertoire contient la version Python de l'analyseur syntaxique (parseur)
Prolog pour bien démarrer le projet.


# Contenu

- `prolog_ast/ast.py` classes définissant l'arbre syntaxique abstrait. Un programme Prolog est une instance de `Program`. C'est une liste de déclarations `Dec` (buts `Goal` ou assertions `Assert`), contenant des `Term` (variable `Var` ou foncteur `Func`). L'AST a un _printer_.
- `prolog_parser` contient un parseur. Les fichiers `Prolog*.py` ont été générés automatiquement par l'outil ANTLR 4 à partir du fichier de grammaire `Prolog.g4`.
- `main.py` est un programme exécutable d'exemple qui lit des sources Prolog, les convertit en `Program`, et réaffiche le programme à l'écran. Vous pourrez utiliser directement cette classe pour construire les objets `Program` nécessaires au projet.
- `exemples` contient des exemples de programmes Prolog.


# Exécution

Il faut installer au préalable la bibliothèque d'exécution d'ANTLR 4.
Pour cela, vous pouvez entrer dans un terminal `pip install antlr4-python3-runtime￼` (cf https://pypi.org/project/antlr4-python3-runtime/).

Ensuite `./main.py` devrait fonctionner.

Il ne sera pas nécessaire de modifier la grammaire `Prolog.g4` fournie. Toutefois, si vous le faites, vous devrez regénérer les fichiers Python du parseur en appelant ANTLR. Pour cela, vous devrez installer l'outil ANTLR 4, puis aller dans le répertoire `prolog_parser` et taper dans un shell : `antlr4 -Dlanguage=Python3 Prolog.g4`.
