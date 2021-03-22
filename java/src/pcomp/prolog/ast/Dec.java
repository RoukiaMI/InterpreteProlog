 /*
 * PCOMP (LU3IN032), Licence 3, Sorbonne Université
 * année 2020-2021
 *
 * Projet Prolog
 */

package pcomp.prolog.ast;

/*
 * AST : interface des déclarations.
 * 
 * Une déclaration est soit une assertion "head :- body.", soit un but "?- body."
 */
public interface Dec {
	
	// Interface du visiteur de termes.
	public <T> T accept(DecVisitor<T> visitor);
}
