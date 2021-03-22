 /*
 * PCOMP (LU3IN032), Licence 3, Sorbonne Université
 * année 2020-2021
 *
 * Projet Prolog
 */

package pcomp.prolog.ast;

import java.util.List;

/*
 * AST : classe des buts.
 * 
 * Un but est une déclaration de la forme "?- term, ..., term."
 */
public class Goal implements Dec {

	// attribut
	////////////////
	
	private final List<Func> body; // termes, à droite de ?-
	
	
	// constructeur
	/////////////////////////
	
	public Goal(List<Func> body) {
		this.body = body;
	}

	
	// getters
	//////////////
	
	public List<Func> getBody() {
		return body;
	}
	
	
	// conversion en chaîne
	/////////////////////////////////////
	
	@Override public String toString() {
		StringBuffer buf = new StringBuffer();
		buf.append(" ?- ");
		boolean first = true;
		for (Term t : body) {
			if (first) first = false; else buf.append(", ");
			buf.append(t.toString());
		}
		buf.append(".");
		return buf.toString();
	}	
	
	
	// interface du visiteur
	//////////////////////////////////
	
	@Override public <T> T accept(DecVisitor<T> visitor) {
		return visitor.visit(this);
	}

}
