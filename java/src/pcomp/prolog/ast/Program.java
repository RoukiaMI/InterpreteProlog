 /*
 * PCOMP (LU3IN032), Licence 3, Sorbonne Université
 * année 2020-2021
 *
 * Projet Prolog
 */

package pcomp.prolog.ast;

import java.util.List;

/*
 * AST de programmes Prolog.
 * 
 * Un programme Prolog est une liste de déclarations.
 */
public class Program {

	// attribut
	//////////////
	
	private List<Dec> decs;
	

	// constructeur
	///////////////////////
	
	public Program(List<Dec> decs) {
		this.decs = decs;
	}
	
	
	// getter
	//////////////

	public List<Dec> getDecs() {
		return decs;
	}

	
	// conversion en chaîne
	/////////////////////////////////////
	
	@Override public String toString() {
		StringBuffer buf = new StringBuffer();
		for (Dec c : decs) {
			buf.append(c.toString());
			buf.append("\n");
		}
		return buf.toString();
	}
}
