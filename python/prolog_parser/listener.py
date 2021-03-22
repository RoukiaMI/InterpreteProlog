# PCOMP (LU3IN032), Licence 3, Sorbonne Université
# année 2020-2021
#
# Projet Prolog
#

# Listener passé au parseur ANTLR 4 pour construire l'AST.

from prolog_parser.PrologListener import PrologListener
from prolog_ast import ast

class Listener(PrologListener):

    def exitProgram(self, ctx):
        l = map(lambda x: x.node, ctx.clauses)
        ctx.node = ast.Program(list(l))

    def exitAssertion(self, ctx):
        l = map(lambda x: x.node, ctx.body)
        ctx.node = ast.Assert(ctx.head.node, list(l))

    def exitGoal(self, ctx):
        l = map(lambda x: x.node, ctx.body)
        ctx.node = ast.Goal(list(l))

    def exitAtom(self, ctx):
        ctx.node = ast.Func(ctx.getText(), [])

    def exitStructure(self, ctx):
        l = map(lambda x: x.node, ctx.childs)
        ctx.node = ast.Func(ctx.atom.text, list(l))

    def exitVar(self, ctx):
        ctx.node = ast.Var(ctx.getText())

    def exitPred(self, ctx):
        ctx.node = ctx.pred.node

