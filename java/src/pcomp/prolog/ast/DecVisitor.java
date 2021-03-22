 /*
 * PCOMP (LU3IN032), Licence 3, Sorbonne Université
 * année 2020-2021
 *
 * Projet Prolog
 */

package pcomp.prolog.ast;

/*
 * AST : interface de visiteur des déclarations.
 */
public interface DecVisitor<T> {
	public T visit(Assert a);
	public T visit(Goal a);
}
