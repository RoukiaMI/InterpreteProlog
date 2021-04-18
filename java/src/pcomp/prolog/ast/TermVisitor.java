 /*
 * PCOMP (LU3IN032), Licence 3, Sorbonne Université
 * année 2020-2021
 *
 * Projet Prolog
 */

package pcomp.prolog.ast;

/*
 * AST : interface de visiteur des termes.
 */
public interface TermVisitor<T>{
	public T visit(Var var);
	public T visit(Func func);
}
