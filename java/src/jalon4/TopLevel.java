package jalon4;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import pcomp.prolog.ast.Assert;
import pcomp.prolog.ast.Dec;
import pcomp.prolog.ast.Goal;
import pcomp.prolog.ast.Program;
import pcomp.prolog.ast.Term;
import pcomp.prolog.jalon2.Unify2;
import pcomp.prolog.parser.PrologParser;

public class TopLevel {
	Program p;
	File f;
	Unify2 i2;
	ArrayList<Assert> rules=new ArrayList();
	public TopLevel(String f) throws IOException {
		i2=new Unify2();
		this.p=PrologParser.parseFile(f);
		for(Dec decs: p.getDecs()) {
			this.rules.add((Assert) decs);
		}
	}
	public static void main (String [] args) throws IOException { 
		TopLevel i= new TopLevel(args[0]);
		System.out.println(i.rules);
	
	 System.out.println ("Voulez-vous continuer (O/N):"); 
	 BufferedReader bufferRead = new BufferedReader (new InputStreamReader (System.in)); 
	 String s="";
	 s = bufferRead.readLine ();
	 while(s.equals("O")) {
		 try {
			
			System.out.println ("Entrez votre but ici :"); 
			 BufferedReader bufferR = new BufferedReader (new InputStreamReader (System.in)); 
			 String b="";
			 b = bufferR.readLine ();
			Map<String,Term> env= new HashMap<String,Term>();
			Program p1 = PrologParser.parseString(b);
			Goal g=null;
			for(Dec decs: p1.getDecs()) {
				g=(Goal)decs;
			}

			env=(Map<String, Term>) i.i2.solve(g, i.rules, env);
			System.out.println ("Env Produit: "+env);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		 System.out.println ("Voulez-vous continuer (O/N):"); 
		 BufferedReader bufferReadi = new BufferedReader (new InputStreamReader (System.in)); 
		 s=bufferReadi.readLine();
			 System.out.println ("C'est tout pour vous ?"+s); 
			} 
			
	}
	

}
