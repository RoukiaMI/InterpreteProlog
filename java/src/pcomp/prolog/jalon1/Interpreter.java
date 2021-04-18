package pcomp.prolog.jalon1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import pcomp.prolog.ast.Assert;
import pcomp.prolog.ast.Dec;
import pcomp.prolog.ast.Func;
import pcomp.prolog.ast.Goal;
import pcomp.prolog.ast.Program;
import pcomp.prolog.ast.Term;

public class Interpreter {
	Program p;
	Unify u;
	public Interpreter(Program p) {
		// TODO Auto-generated constructor stub
		this.p=p;
		u=new Unify();
	}
	
	public void interpreter0() {
		ArrayList<Dec> dec=(ArrayList<Dec>) this.p.getDecs();
		Func t1 = null;
		Func t2 = null;
		for(int i=0; i<dec.size();i++) {
			if(dec.get(i).getClass().getName()=="pcomp.prolog.ast.Assert") {
				Assert term1=(Assert)dec.get(i);
				t1=term1.getHead();
			}
			else{
					Goal term2=(Goal)dec.get(i);
					for(Func f: term2.getBody()) {
						t2=f;
						try {
							Map<String,Term>env= this.u.unify(t1, t2, new HashMap<Term,Term>());
							t2=this.u.replace(env,t2);
							System.out.println("Environnement Produit :"+env+" Terme substitue : [ "+t2+" ]");
						} catch (UnifyException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				//}
			}
		}
		
		
	}
	
	
	public void interpreter1() {
		ArrayList<Dec> dec=(ArrayList<Dec>) this.p.getDecs();
		Func t1 = null;
		Func t2 = null;
		ArrayList<Assert> lesRegles=new ArrayList();
		for(int i=0; i<dec.size();i++) {
			if(dec.get(i).getClass().getName()=="pcomp.prolog.ast.Assert") {
				Assert term1=(Assert)dec.get(i);
				lesRegles.add(term1);
			}
			else{
				Goal term2=(Goal)dec.get(i);
				for(Func f: term2.getBody()) {
						for(Assert r: lesRegles) {
							t1=r.getHead();
					
							t2=f;
							System.out.println("Goal: "+term2+"  func: "+t2.getAtom());
							try {
								if(t2.getAtom().equals(t1.getAtom())) {
								Map<String,Term>env= this.u.unify(t1, t2, new HashMap<Term,Term>());
								t2=this.u.replace(env,t2);
								System.out.println("Environnement Produit :"+env+" Terme substitue : [ "+t2+" ]");
								//System.out.println("Environnement Produit :"+env+" Terme substitue :"+t1);
								}else{System.out.println("Ne peut pas interpreter :");}
								
							} catch (UnifyException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				}
		}
		
		
	}


}
