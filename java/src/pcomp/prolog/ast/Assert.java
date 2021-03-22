 /*
 * PCOMP (LU3IN032), Licence 3, Sorbonne Université
 * année 2020-2021
 *
 * Projet Prolog
 */

package pcomp.prolog.ast;

import java.util.Collections;
import java.util.List;

/*
 * AST : classe des assertions.
 * 
 * Une assertion est une déclaration de la forme "head :- term, ..., term."
 */
public class Assert implements Dec {

	// attributs
	////////////////
	
	private final Func head; // en-tête, à gauche de :-
	private final List<Func> body; // termes, à droite de :-
	
	
	// constructeurs
	/////////////////////////
	
	// assertion avec membres droit
	public Assert(Func head, List<Func> body) {
		this.head = head;
		this.body = body;
	}

	// fait : assertion sans membre droit
	public Assert(Func head) {
		this(head, Collections.emptyList());
	}
	
	
	// getters
	///////////////
	
	public Func getHead() {
		return head;
	}
	
	public List<Func> getBody() {
		return body;
	}
	
	
	// conversion en chaîne
	/////////////////////////////////////
	
	@Override public String toString() {
		StringBuffer buf = new StringBuffer();
		buf.append(head);
		if (!body.isEmpty()) {
			buf.append(" :- ");
			boolean first = true;
			for (Term t : body) {
				if (first) first = false; else buf.append(", ");
				buf.append(t.toString());
			}
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
