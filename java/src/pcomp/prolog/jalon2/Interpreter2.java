package pcomp.prolog.jalon2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import pcomp.prolog.ast.Assert;
import pcomp.prolog.ast.Dec;
import pcomp.prolog.ast.Func;
import pcomp.prolog.ast.Goal;
import pcomp.prolog.ast.Program;
import pcomp.prolog.ast.Term;
import pcomp.prolog.jalon1.UnifyException;
import pcomp.prolog.jalon2.Unify2;
import pcomp.prolog.parser.PrologParser;

public class Interpreter2 {
	
	Program p;
	Unify2 u;
	public Interpreter2(Program p) {
		this.p=p;
		u= new Unify2();
	}
	public Object interpreter2() {
		ArrayList<Dec> dec=(ArrayList<Dec>) this.p.getDecs();
		ArrayList<Goal> lesButs=new ArrayList();
		ArrayList<Assert> lesRegles=new ArrayList();
		Map<String,Term> e = new HashMap();
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

			e= (HashMap<String, Term>) this.u.solve(g, lesRegles, new HashMap());
			System.out.println("--------------------\r\n"+"Env Produit par Solve: \r\n --------------------\r\n"+e);
		}
		
		return e;
	}
	
	public static void main(String[] args) throws IOException {
		
		Program i2=PrologParser.parseFile("exemples/Jalon2/test.pl");
		System.out.println("-----------------"+"\r\n"+"Interpreter 2"+"\r\n"+"-----------------"+"\r\n");
		Interpreter2 monI2= new Interpreter2(i2);
		Map<String, Term>e= (Map<String, Term>) monI2.interpreter2();
		System.out.println(e);
	}
	
	
}
