 /*
 * PCOMP (LU3IN032), Licence 3, Sorbonne Université
 * année 2020-2021
 *
 * Projet Prolog
 */

/*
 * Listener passé au parseur ANTLR 4 pour construire l'AST.
 */

package pcomp.prolog.parser;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import pcomp.prolog.ast.Assert;
import pcomp.prolog.ast.Dec;
import pcomp.prolog.ast.Goal;
import pcomp.prolog.ast.Func;
import pcomp.prolog.ast.Program;
import pcomp.prolog.ast.Term;
import pcomp.prolog.ast.Var;
import pcomp.prolog.parser.PrologANTLRGrammarParser.AssertionContext;
import pcomp.prolog.parser.PrologANTLRGrammarParser.AtomContext;
import pcomp.prolog.parser.PrologANTLRGrammarParser.ClauseContext;
import pcomp.prolog.parser.PrologANTLRGrammarParser.GoalContext;
import pcomp.prolog.parser.PrologANTLRGrammarParser.PredContext;
import pcomp.prolog.parser.PrologANTLRGrammarParser.PredicateContext;
import pcomp.prolog.parser.PrologANTLRGrammarParser.ProgramContext;
import pcomp.prolog.parser.PrologANTLRGrammarParser.StructureContext;
import pcomp.prolog.parser.PrologANTLRGrammarParser.TermContext;
import pcomp.prolog.parser.PrologANTLRGrammarParser.VarContext;

public class PrologParserListener implements PrologANTLRGrammarListener{

	@Override public void enterEveryRule(ParserRuleContext arg0) {}
	@Override public void exitEveryRule(ParserRuleContext arg0) {}
	@Override public void visitErrorNode(ErrorNode arg0) {}
	@Override public void visitTerminal(TerminalNode arg0) {}

	@Override public void enterProgram(ProgramContext ctx) {}

	@Override
	public void exitProgram(ProgramContext ctx) {
		List<Dec> l = new ArrayList<>();
		for (ClauseContext t : ctx.clauses) {
			l.add(t.node);
		}
		ctx.node = new Program(l);
	}

	@Override public void enterGoal(GoalContext ctx) {}

	@Override public void exitGoal(GoalContext ctx) {
		List<Func> l = new ArrayList<>();
		for (PredicateContext t : ctx.body) {
			l.add(t.node);
		}
		ctx.node = new Goal(l);
	}

	@Override public void enterAssertion(AssertionContext ctx) {}

	@Override public void exitAssertion(AssertionContext ctx) {
		List<Func> l = new ArrayList<>();
		for (PredicateContext t : ctx.body) {
			l.add(t.node);
		}
		ctx.node = new Assert(ctx.head.node, l);
	}

	@Override public void enterAtom(AtomContext ctx) {}

	@Override public void exitAtom(AtomContext ctx) {
		ctx.node = new Func(ctx.atom.getText());
	}

	@Override public void enterStructure(StructureContext ctx) {}

	@Override public void exitStructure(StructureContext ctx) {
		List<Term> l = new ArrayList<>();
		for (TermContext t : ctx.childs) {
			l.add(t.node);
		}
		ctx.node = new Func(ctx.atom.getText(), l);
	}

	@Override public void enterVar(VarContext ctx) {}

	@Override public void exitVar(VarContext ctx) {
		ctx.node = new Var(ctx.var.getText());
	}

	@Override public void enterPred(PredContext ctx) {}

	@Override public void exitPred(PredContext ctx) {
		ctx.node = ctx.pred.node;
	}
}
