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
 * AST : classe des foncteurs.
 * 
 * Un foncteur est un terme de la forme "f(term1,...,termN)"
 */
public class Func implements Term {

	// attributs
	////////////////
	
	private final String atom; // en-tête
	private final List<Term> terms; // arguments du foncteur

	
	// constructeurs
	/////////////////////////
	
	// constructeur "atom(terms)"
	public Func(String atom, List<Term> terms) {
		this.atom = atom;
		this.terms = terms;
	}
	
	// constructeur d'une constante, i.e., un functeur sans argument
	public Func(String atom) {
		this(atom, Collections.emptyList());
	}
	
	
	// getters
	///////////////
	
	public String getAtom() {
		return atom;
	}
	
	public List<Term> getTerms() {
		return terms;
	}
	
	// égalité structurelle (récursive)
	///////////////////////////////////////////////////
	
	@Override public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Func)) return false;
		Func p = (Func)o;
		if (!atom.equals(p.atom)) return false;
		if (terms.size() != p.terms.size()) return false;
		for (int i = 0; i < terms.size(); i++) {
			if (!terms.get(i).equals(p.terms.get(i))) return false;
		}
		return true;
	}
	
	@Override public int hashCode() {
		int hash = atom.hashCode();
		for (Term t : terms) {
			hash = hash * 75 + t.hashCode();
		}
		return hash;
	}

	// conversion en chaîne
	/////////////////////////////////////
	
	@Override public String toString() {
		if (terms.isEmpty()) return atom;
		StringBuffer buf = new StringBuffer();
		buf.append(atom);
		buf.append("(");
		boolean first = true;
		for (Term t : terms) {
			if (first) first = false; else buf.append(", ");
			buf.append(t.toString());
		}
		buf.append(")");
		return buf.toString();
	}	
	
	
	// visiteur
	///////////////
	
	@Override public <T> T accept(TermVisitor<T> visitor) {
		return visitor.visit(this);
	}
}
