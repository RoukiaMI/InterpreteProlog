package pcomp.prolog.jalon2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import pcomp.prolog.ast.Assert;
import pcomp.prolog.ast.Func;
import pcomp.prolog.ast.Goal;
import pcomp.prolog.ast.Program;
import pcomp.prolog.ast.Term;
import pcomp.prolog.ast.Var;
import pcomp.prolog.jalon1.Unify;
import pcomp.prolog.jalon1.UnifyException;
import pcomp.prolog.jalon3.Solve;

public class Unify2 {
	Program p;
	public Unify2() {
		//this.p=p;
	}
	public Var rename(Var a,int cpt) {
		String nom=a.getVar();
		String cpts=String.valueOf(cpt);
		Var x=new Var(nom+cpts);
		return x;
	}
	public Func rename(Func a,int cpt) {
		String cpts=String.valueOf(cpt);
		Func t=a;
		if(!(t.getTerms().isEmpty())) {
			ArrayList<Term> term= (ArrayList)t.getTerms();
			for(Term i: term) {
				//System.out.println(i);
				if(i.getClass().getName()=="pcomp.prolog.ast.Var") {
					//System.out.println("Term i= "+i+" avec Var= "+v);
					int h=term.indexOf(i);
					i=rename((Var)i,cpt);
					term.set(h, i);
					
				
				}
				else {
					if(i.getClass().getName()=="pcomp.prolog.ast.Func") {
						int j=term.indexOf(i);
						i= rename((Func)i, cpt);
						term.set(j, i);
					}
				
				}
			
			}
			
		}
		return t;
	}
	public Assert rename(Assert a,int cpt) {
		Assert aprim=null;
		String atomHead=a.getHead().getAtom();
		Func head =a.getHead();
		head=rename(head, cpt);
		if(! a.getBody().equals(Collections.EMPTY_LIST)) {
		ArrayList<Func> termBody=(ArrayList<Func>) a.getBody();
		/*for(Term v: head.getTerms()) {
			if(v.getClass().getName()=="pcomp.prolog.ast.Var") {
				int i=head.getTerms().indexOf(v);
				head.getTerms().set(i,rename((Var)v,cpt));
			}
		}*/
		for(Func f: termBody) {
			f=rename(f,cpt);
		}
		
		
		aprim=new Assert(head, termBody);
		}else {
			aprim=new Assert(head);
		}
		
		return aprim;
	}
	
	public Map<String,Term> step(Func g, ArrayList<Assert> rules, int cpt) throws StepException, UnifyException {
		Map<String,Term> envP=new HashMap<String, Term>();
		Map<String,Term> envTmp=new HashMap<String, Term>();
		Unify u=new Unify();
		Func f= g;
		Assert choisie=null;
			for(Assert r: rules) {
				
				if(f.getAtom().equals(r.getHead().getAtom())&&f.getTerms().size()==r.getHead().getTerms().size()) {
					Func f2=r.getHead();
					
					try {
						Map<String,Term> t=u.unify(f, f2, new HashMap<Term, Term>());
						if(! t.isEmpty()) {
							
							for (Entry<String, Term> e: t.entrySet()) {// Stock l'env produit par l'unification du but avec la regle trouver
								envP.put(e.getKey(), e.getValue());
							}	
							//cpt++;
							//r=rename(r,cpt);
							if(! r.getBody().equals(Collections.EMPTY_LIST)) {
								
								ArrayList<Func> body=(ArrayList<Func>) r.getBody();
								for(int i =0; i<body.size();i++) {
									Func fb=body.get(i);
									for(Assert res: rules) {
										if(! res.equals(r)) {
										if(fb.getAtom().equals(res.getHead().getAtom())&&fb.getTerms().size()==res.getHead().getTerms().size()) {
											Map<String,Term> t1=u.unify(fb, res.getHead(), new HashMap());
											
											if(!t1.isEmpty()) {
												for (Entry<String, Term> e: t1.entrySet()) {// Stock l'env produit par l'unification des body avec une regles
													envTmp.put(e.getKey(), e.getValue());
													
												}
												
												for(Entry<String,Term> a: envTmp.entrySet()) {// Ici je prouve si les elements de body  et renome la regle qui permet cet unification
													for(Entry<String, Term> b: envP.entrySet()) {
														if(b.getKey().equals(a.getKey())){
															cpt++;
															if (!(b.getValue().equals(a.getValue()))) {
																//System.out.println("ICI ON A DES KEY PAREIL"+a.getKey()+"  =  "+b.getKey()+" AVEC CLES "+a.getValue()+" == "+b.getValue());
																//System.out.println("ICI ON A DES KEY PAREIL"+b);
																envP.remove(b);
																r=rename(r,cpt);
																
															}else {
																choisie=r;
																System.out.println(f+" ICI ON A DES KEY PAREIL"+r+" "+envP);
																
															}
														}
													}
												}
											}else {// Sinon on regarde si on peut prouver en utilisant les variables dans mon environnement si oui le but est unifié si non on leve une exception 
												for(Entry<String,Term> a: envTmp.entrySet()) {
													if(u.occurs(fb,new Var(a.getKey()))) {
														break;
													}else {
														throw new StepException("Aucune regle ne permet d'unifier ce But");
													}
												}
												
												}
										}
										}
									}
								}
							}	
						}
					} catch (UnifyException e) {
						// TODO Auto-generated catch block
						throw e;
						//e.printStackTrace();
					}
					//cpt++;
					//r=rename(r,cpt);
				}
				
			}
			
		return envP;
		
	}
	/*
	 *Cette méthode n'est pas utiliser dans le Jalon 2  
	 * */
	public Map<Assert,Map> step2(Func g, ArrayList<Assert> rules, int cpt) throws StepException, UnifyException {
		Map<String,Term> envP=new HashMap<String, Term>();
		Map<String,Term> envTmp=new HashMap<String, Term>();
		Map<Assert,Map> retour= new HashMap<Assert, Map>();
		Unify u=new Unify();
		Func f= g;
		Assert choisie=null;
			for(Assert r: rules) {
				
				if(f.getAtom().equals(r.getHead().getAtom())&&f.getTerms().size()==r.getHead().getTerms().size()) {
					Func f2=r.getHead();
					
					try {
						Map<String,Term> t=u.unify(f, f2, new HashMap<Term, Term>());
						if(! t.isEmpty()) {
							
							for (Entry<String, Term> e: t.entrySet()) {// Stock l'env produit par l'unification du but avec la regle trouver
								envP.put(e.getKey(), e.getValue());
							}	
							//cpt++;
							//r=rename(r,cpt);
							if(! r.getBody().equals(Collections.EMPTY_LIST)) {
								
								ArrayList<Func> body=(ArrayList<Func>) r.getBody();
								for(int i =0; i<body.size();i++) {
									Func fb=body.get(i);
									for(Assert res: rules) {
										if(! res.equals(r)) {
										if(fb.getAtom().equals(res.getHead().getAtom())&&fb.getTerms().size()==res.getHead().getTerms().size()) {
											Map<String,Term> t1=u.unify(fb, res.getHead(), new HashMap());
											
											if(!t1.isEmpty()) {
												for (Entry<String, Term> e: t1.entrySet()) {// Stock l'env produit par l'unification des body avec une regles
													envTmp.put(e.getKey(), e.getValue());
													
												}
												
												for(Entry<String,Term> a: envTmp.entrySet()) {// Ici je prouve si les elements de body  et renome la regle qui permet cet unification
													for(Entry<String, Term> b: envP.entrySet()) {
														if(b.getKey().equals(a.getKey())){
															cpt++;
															if (!(b.getValue().equals(a.getValue()))) {
																//System.out.println("ICI ON A DES KEY PAREIL"+a.getKey()+"  =  "+b.getKey()+" AVEC CLES "+a.getValue()+" == "+b.getValue());
																//System.out.println("ICI ON A DES KEY PAREIL"+b);
																envP.remove(b);
																r=rename(r,cpt);
																
															}else {
																choisie=r;
																retour.put(choisie, envP);
																System.out.println(f+" ICI ON A DES KEY PAREIL"+r+" "+envP);
																
															}
														}
													}
												}
											}else {// Sinon on regarde si on peut prouver en utilisant les variables dans mon environnement si oui le but est unifié si non on leve une exception 
												for(Entry<String,Term> a: envTmp.entrySet()) {
													if(u.occurs(fb,new Var(a.getKey()))) {
														break;
													}else {
														throw new StepException("Aucune regle ne permet d'unifier ce But");
													}
												}
												
												}
										}
										}
									}
								}
							}	
						}
					} catch (UnifyException e) {
						// TODO Auto-generated catch block
						throw e;
						//e.printStackTrace();
					}
					//cpt++;
					//r=rename(r,cpt);
				}
				
			}
			
		return retour;
		
	}
	public Object solve(Goal goal, ArrayList<Assert>a , Map<String, Term> map) {
		Map<String, Term> env=new HashMap<String, Term>();
		Unify u=new Unify();
		int i=-1;
		for(Func f: goal.getBody()) {
			
			try {
				Map<String,Term>e =(Map<String,Term>) step(f, a,0);
				
				for(Entry<String,Term> t:e.entrySet()) {
					
						env.put(t.getKey(), t.getValue());
				}
				
				i+= goal.getBody().indexOf(f);
				
				
			} catch (StepException | UnifyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Func f=goal.getBody().get(i);
		f=u.replace(env, f);
		goal.getBody().set(i, f);
		System.out.println(env+"\r\nLe goal :\r\n"+goal);
		return env;
	}
	
	
	public static void main(String []args) throws UnifyException {
		
		/*
		 * r ( a , a ) .
		q (X )
		p ( Z1 , h ( Z1 , W1) , f (W1) ) :− r ( X1 , XX1 ) , q (XX1 ) .
		?− p ( f (X ) , h (Y, a ) , Y ) , q ( a ) .*/
		
		Unify2 u=new Unify2();
		
		/*Var z= new Var("Z");
		Var x= new Var("X");
		Var w= new Var("W");
		Var y=new Var("Y");
		Var x1= new Var("X1");
		Var xx1= new Var("XX1");
		Var xx= new Var("XX");
		Func ra=new Func("r", new ArrayList<Term>() {{add(new Func("a"));{add(new Func("a"));}}});
		Func r=new Func("r", new ArrayList<Term>() {{add(x);{add(xx);}}});
		Func q=new Func("q",new ArrayList<Term>() {{add(xx);}});
		Func qas=new Func("q",new ArrayList<Term>() {{add(x);}});
		Func h=new Func("h", new ArrayList<Term>() {{add(z);{add(w);}}});
		Func f=new Func("f",new ArrayList<Term>() {{add(w);}});
		Func p=new Func("p",new ArrayList<Term>() {{add(z);}{add(h);}{add(f);}});
		Assert t= new Assert(p,new ArrayList<Func>() {{add(r);}{add(q);}});
		System.out.println("Regles 1 : "+t);
		Assert a=new Assert(ra);
		Assert qa=new Assert(qas);
		Func fg=new Func("f",new ArrayList<Term>() {{add(x);}});
		Func hg=new Func("h", new ArrayList<Term>() {{add(y);{add(new Func("a"));}}});
		Func pg=new Func("p",new ArrayList<Term>() {{add(fg);}{add(hg);}{add(y);}});
		Func qg=new Func("q",new ArrayList<Term>() {{add(new Func("a"));}});
		Goal g=new Goal(new ArrayList<Func>() {{add(pg);}{add(qg);}});
		System.out.println("Les buts : "+g);
		
		ArrayList<Assert>rules=new ArrayList() {{add(t);}{add(a);}{add(qa);}};
		System.out.println("Les regles : "+rules);*/
		
		
		Var x= new Var("X");
		Func a= new Func("a");
		Func b= new Func("b");
		Func rx=new Func("r", new ArrayList<Term>() {{add(x);}});
		Func qx=new Func("q",new ArrayList<Term>() {{add(x);}});
		Func p=new Func("p",new ArrayList<Term>() {{add(x);}});
		Assert t= new Assert(p,new ArrayList<Func>() {{add(qx);}});
		Assert t2= new Assert(p,new ArrayList<Func>() {{add(rx);}});
		Func qa=new Func("q",new ArrayList<Term>() {{add(a);}});
		Func rb=new Func("r",new ArrayList<Term>() {{add(b);}});
		Assert q=new Assert(qa);
		Assert r=new Assert(rb);
		//System.out.println("Regles 1 : "+t);
		//Assert tprim=u.rename(t, 0);
		//System.out.println("Regles 1 renomeé: "+t);
		
		Func pg=new Func("p",new ArrayList<Term>() {{add(a);}});
		Func qg=new Func("p",new ArrayList<Term>() {{add(b);}});
		Goal g=new Goal(new ArrayList<Func>() {{add(pg);}{add(qg);}});
		System.out.println("Les buts : "+g);
		
		ArrayList<Assert>rules=new ArrayList() {{add(t);}{add(t2);}{add(q);}{add(r);}};
		System.out.println("Les regles : "+rules);
		
		//try {
			Object env;
			env = u.solve(g,  rules, new HashMap<String,Term>());
			System.out.println("--------------------\r\n"+"Env Produit par Solve: \r\n --------------------\r\n"+env);
			
			
		//} catch (StepException | UnifyException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace(); new HashMap<String,Term>()
		//}
		
	}
}
