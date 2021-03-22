 /*
 * PCOMP (LU3IN032), Licence 3, Sorbonne Université
 * année 2020-2021
 *
 * Projet Prolog
 */

package pcomp.prolog.ast;

/*
* AST : Classe des variables.
* 
* Les variables sont des termes.
*/

public class Var implements Term {

	// attribut
	///////////////
	
	private final String var;
	
	
	// constructeur
	////////////////////////
	
	public Var(String var) { 
		this.var = var; 
	}
	
	
	// getter
	////////////
	
	public String getVar() { 
		return var; 
	}

	
	// égalité (de contenu)
	///////////////////////////////////
	
	@Override public boolean equals(Object o) {
		if (!(o instanceof Var)) return false;
		return var.equals(((Var)o).var);
	}
	
	@Override public int hashCode() {
		return var.hashCode();
	}
	
	// conversion en chaîne
	/////////////////////////////////////
	
	@Override public String toString() {
		return var;
	}

	
	// visiteur
	///////////////

	@Override public <T> T accept(TermVisitor<T> visitor) {
		return visitor.visit(this);
	}
}
