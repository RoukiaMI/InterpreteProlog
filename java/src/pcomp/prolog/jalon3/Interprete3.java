package pcomp.prolog.jalon3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import pcomp.prolog.ast.Assert;
import pcomp.prolog.ast.Dec;
import pcomp.prolog.ast.Func;
import pcomp.prolog.ast.Goal;
import pcomp.prolog.ast.Program;
import pcomp.prolog.ast.Term;
import pcomp.prolog.jalon1.Unify;
import pcomp.prolog.jalon1.UnifyException;
import pcomp.prolog.jalon2.StepException;

public class Interprete3 {
	Program p;
	Solve s;
	public Interprete3(Program p) {
		// TODO Auto-generated constructor stub
		this.p=p;
		s=new Solve();
	}
	
	public Object interpreter() {
		ArrayList<Dec> dec=(ArrayList<Dec>) this.p.getDecs();
		ArrayList<Goal> lesButs=new ArrayList();
		ArrayList<Assert> lesRegles=new ArrayList();
		ArrayList e = new ArrayList();
		for(int i=0; i<dec.size();i++) {
			if(dec.get(i).getClass().getName()=="pcomp.prolog.ast.Assert") {
				Assert term1=(Assert)dec.get(i);
				lesRegles.add(term1);
			}
			else{
				if(dec.get(i).getClass().getName()=="pcomp.prolog.ast.Goal") {
					Goal term2=(Goal)dec.get(i);
					lesButs.add(term2);
				}
				
			}
		}
		for(Goal g: lesButs) {

			try {
				e.addAll((ArrayList) this.s.solve(new ArrayList(),g, lesRegles, new HashMap())) ;
				//System.out.println("--------------------\r\n"+"Env Produit par Solve: \r\n --------------------\r\n"+e);
			} catch (UnifyException | StepException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		
		return e;
	}
	
}
