# PCOMP (LU3IN032), Licence 3, Sorbonne Université
# année 2020-2021
#
# Projet Prolog
#

# Ce module contient les classes pour représenter un arbre syntaxique
# abstrait (AST) de programme Prolog.
# La classe principale est Program.


# Un programme est une liste de déclarations
class Program:

    # decs: liste de déclarations
    def __init__(self,decs):
        self.decs = decs

    # conversion en chaîne
    def __str__(self):
        return '\n'.join(map(str, self.decs))
    

# Classe abstraite pour les déclarations.
# Une déclaration est une assertion ou un but.
class Dec:
    pass

# Une assertion est une déclaration de la forme "head :- term1,...,termN."
class Assert(Dec):

    # head: un foncteur
    # body: une liste de termes
    def __init__(self,head,body):
        self.head = head
        self.body = body

    # conversion en chaîne
    def __str__(self):
        if self.body:
            return str(self.head) + ' :- ' + ', '.join(map(str, self.body)) + '.'
        else:
            return str(self.head) + '.'


# Un but est une déclaration de la forme "?- term1,...,termN."
class Goal(Dec):

    # body: liste de termes
    def __init__(self,body):
        self.body = body

    # conversion en chaîne
    def __str__(self):
        return ' ?- ' + ', '.join(map(str, self.body)) + '.'


# Classe abstraite pour les termes.
# Un terme est soit une variable, soit un foncteur.
class Term:
    pass

# Une variable est un terme de la forme "Var".
class Var(Term):

    # var: une chaîne (nom de la variable)
    def __init__(self,var):
        self.var = var

    # conversion en chaîne
    def __str__(self):
        return self.var


# Un foncteur est un terme de la forme "f(term1,...,termN)"
class Func(Term):

    # atom: une chaîne indiquant le nom du foncteur
    # terms: une liste de termes
    def __init__(self,atom,terms):
        self.atom = atom
        self.terms = terms

    # conversion en chaîne
    def __str__(self):
        if self.terms:
            return self.atom + '(' + ', '.join(map(str, self.terms)) + ')'
        else:
            return self.atom
