package pcomp.prolog.jalon3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import pcomp.prolog.ast.Assert;
import pcomp.prolog.ast.Func;
import pcomp.prolog.ast.Goal;
import pcomp.prolog.ast.Term;
import pcomp.prolog.ast.Var;
import pcomp.prolog.jalon1.Unify;
import pcomp.prolog.jalon1.UnifyException;
import pcomp.prolog.jalon2.StepException;
import pcomp.prolog.jalon2.Unify2;

public class Solve {

	
	public Solve() {
		
	}
	
	public Object solve(ArrayList<Choix> lch,Goal lesButs,ArrayList<Assert> lesRules,Map<String,Term> env) throws UnifyException, StepException {
		Unify u=new Unify();
		Unify2 u1=new Unify2();
		Map<Assert, Map> envP = new HashMap<Assert, Map>();
		Map<String,Term> envTmp = new HashMap<String, Term>();
		Contexte ctx =new Contexte(lesButs, lesRules);
		Map<Func,ArrayList<Assert> > map= ctx.journalCh();
		lch.toString();
		System.out.println(map);
		Choix ch = null;
		int cpt=0;
		ArrayList<Choix> dejaExpl=new ArrayList();
		Map<String,Term> envtmp= new HashMap();
		for(Entry<Func,ArrayList<Assert> >m:map.entrySet()) {
			Func f=m.getKey();
			ArrayList<Assert> regles=m.getValue();
			//ArrayList<Assert> regle=new ArrayList();
			envP=u1.step2(f, regles, cpt);
			System.out.println(f+": "+envP+" : ");
			if(! envP.isEmpty()) {
				//System.out.println("VIDE");
			//}else {
				for(Entry<Assert, Map> t:envP.entrySet()) {
					Map<String , Term>envi=t.getValue();
					
					for(Entry<String,Term> ti:envi.entrySet()) {
						System.out.println(f+": env "+ti+" : ");
						env.put(ti.getKey(), ti.getValue());
						ch=new Choix(f,t.getKey(),envi);
					}
					
					lch.add(ch);
					System.out.println("NON VIDE -> "+lch);
			}
			
				
				
				
			}
			
		
		}
	
		return lch;
		
	}
	
	
public static void main(String []args) throws UnifyException {
		
		/*p ( Z1 , h ( Z1 , W1) , f (W1) ) :− r ( X1 , XX1 ) , q (XX1 ) .
		?− p ( f (X ) , h (Y, a ) , Y ) , q ( a ) .
		
		*
		*p (X) :−q (X ) .
		 p (X) :− r (X ) .
 		q ( a ) .
 		r ( b ) .
 		?− p ( b ) , p ( a ) .
		*
		*
		*/
		
		Unify2 u=new Unify2();
		Solve s=new Solve();
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
			try {
				env = s.solve(new ArrayList<Choix>(),g,  rules, new HashMap<String,Term>());
				System.out.println("-------------------------------\r\n"+"Env Produit par Solve Jalon3 : \r\n ------------------------------\r\n"+env);
			} catch (UnifyException | StepException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		//} catch (StepException | UnifyException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		//}
		
	}
}
