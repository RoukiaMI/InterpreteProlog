package pcomp.prolog.jalon1;

import java.awt.List;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import pcomp.prolog.ast.Assert;
import pcomp.prolog.ast.Dec;
import pcomp.prolog.ast.Func;
import pcomp.prolog.ast.Goal;
import pcomp.prolog.ast.Program;
import pcomp.prolog.ast.Term;
import pcomp.prolog.ast.TermVisitor;
import pcomp.prolog.ast.Var;
import pcomp.prolog.parser.PrologParser;

public class Unify{
	//private Map<String, Term> env;
	//private Assert term;
	Program p;
	
	public Unify() {
		//this.env=l;
		//this.term=t;
		//this.p=p;
	}

	
	public Func replace(Map<String,Term>env, Func f) {
		TermV t=new TermV(); // Mon visitor
		Func term_produit=f;
		if(t.visit(f).getClass().getName()!="java.util.Collections$EmptyList") {
			ArrayList<Term> lesTermes=(ArrayList<Term>) t.visit(f);
			
				for(Entry<String,Term> x:env.entrySet()) {
					for(Term y: lesTermes) {
				//for(int var=0;var<env.keySet().size();var++)
					if(new Var(x.getKey()).equals(y)){
						int i =lesTermes.indexOf(y);
						lesTermes.set(i, x.getValue());
						term_produit=new Func(f.getAtom(),lesTermes);
						//return term_produit;
					}
					else {
						if(y.getClass().getName()=="pcomp.prolog.ast.Func") {
							int i =lesTermes.indexOf(y);
							lesTermes.set(i, replace(env,(Func)y));
						}
					}
				}
			}
			
		}
		
		return term_produit;
	}
	
	
	
	public boolean occurs(Func t, Var v) {
		boolean r=false;
		if(!(t.getTerms().isEmpty())) {
			ArrayList<Term> term= (ArrayList)t.getTerms();
			for(Term i: term) {
				//System.out.println(i);
				if(v.equals(i)) {
					System.out.println("Term i= "+i+" avec Var= "+v);
					r=true;
				}
				else {
					if(i.getClass().getName()=="pcomp.prolog.ast.Func") {
						r= occurs((Func)i, v);
					}
				}
			}
		}
		return r;
	}
	
	
	public Map<String, Term> unify(Func a1,Func a2, Map<Term,Term> env) throws UnifyException{
		Func t1=a1;//Le terme 1
		Func t2=a2;//Le terme 1
		String atom1=t1.getAtom();
		String atom2=t2.getAtom();
		//System.out.println("t1 : "+atom1+" & t2 :"+atom2);
		TermV visitor=new TermV();
		env=new HashMap<Term,Term>();
		Map<String,Term> envP=new HashMap<String,Term>();
		if(!(atom1.equals(atom2))){
			System.out.println(t1.getAtom()+" Different de "+t2.getAtom());//Test si ils ont le meme atom
			throw new UnifyException("L’unification Impossible: les deux foncteurs n’ont pas le même symbole de tête");
		}
		else{
			if(t1.getTerms().size()!=t2.getTerms().size()) {// Test si ils ont le meme le nb d'arg
				throw new UnifyException("L’unification Impossible: les deux foncteurs n’ont pas le même nombre d'argument");
			}else {
				int nbArg=t1.getTerms().size();// si oui on commence l'unification
				for(int i=0; i<nbArg;i++) {// associe tous les termes 
					env.put(t1.getTerms().get(i), t2.getTerms().get(i));
					
				}
			
				for(Entry <Term,Term> e: env.entrySet()) {
					if((e.getKey().getClass().getName()=="pcomp.prolog.ast.Var") && ((e.getValue().getClass().getName()=="pcomp.prolog.ast.Func")&& this.occurs((Func)e.getValue(), (Var)e.getKey()))){
						throw new UnifyException(" l’unification Impossible: d’une variable et d’un terme contenant cette variable");
					}
					if((e.getValue().getClass().getName()=="pcomp.prolog.ast.Var") && ((e.getKey().getClass().getName()=="pcomp.prolog.ast.Func")&& this.occurs((Func)e.getKey(), (Var)e.getValue()))){
						throw new UnifyException(" l’unification Impossible: d’une variable et d’un terme contenant cette variable");
					}
					if(e.getKey().getClass().getName()=="pcomp.prolog.ast.Func" && e.getValue().getClass().getName()=="pcomp.prolog.ast.Func") {
						Map<String, Term>t;
						t= unify((Func)e.getKey(),(Func)e.getValue(),new HashMap<Term,Term>());
						for (Entry<String, Term> x: t.entrySet()) {
							envP.put(x.getKey(),x.getValue());
						}
					}
					if((e.getValue().getClass().getName()=="pcomp.prolog.ast.Func" && e.getKey().getClass().getName()=="pcomp.prolog.ast.Var")){
						Var a=(Var)e.getKey();
						Term t=e.getValue();
						HashMap<String,Term> map=new HashMap() {{put(a.getVar(),t);}};
						t1=(Func)this.replace(envP, t1);
						env.replace(a, t1);
						envP.put(a.getVar(),t);
					}
					if((e.getKey().getClass().getName()=="pcomp.prolog.ast.Func" && e.getValue().getClass().getName()=="pcomp.prolog.ast.Var") ) {
						Var a=(Var)e.getValue();
						Term t=e.getKey();
						HashMap<String,Term> map=new HashMap() {{put(a.getVar(),t);}};
						t1=(Func)this.replace(envP, t1);
						env.replace(t1, a);
						envP.put(a.getVar(),t);
						
					}
					
				}
				
			
		return envP;
			}
		}
	
	
	
	
		
	}


	
	
	
	
	
	
}
