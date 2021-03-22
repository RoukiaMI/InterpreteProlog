 /*
 * PCOMP (LU3IN032), Licence 3, Sorbonne Université
 * année 2020-2021
 *
 * Projet Prolog
 */

package pcomp.prolog.ast;

/*
 * AST : interface des termes.
 * 
 * Un terme est soit une variable, soit un foncteur f(term1,...,termN)
 */
public interface Term {

	// Interface du visiteur de termes.
	public <T> T accept(TermVisitor<T> visitor);
}
