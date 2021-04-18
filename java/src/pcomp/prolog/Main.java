 /*
 * PCOMP (LU3IN032), Licence 3, Sorbonne Université
 * année 2020-2021
 *
 * Projet Prolog
 */

package pcomp.prolog;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import pcomp.prolog.ast.Assert;
import pcomp.prolog.ast.Func;
import pcomp.prolog.ast.Program;
import pcomp.prolog.ast.Term;
import pcomp.prolog.ast.Var;
import pcomp.prolog.jalon1.Interpreter;
import pcomp.prolog.jalon1.Unify;
import pcomp.prolog.jalon2.Interpreter2;
import pcomp.prolog.jalon3.Interprete3;
import pcomp.prolog.parser.PrologParser;

public class Main {

	
	public static void main(String[] args) throws IOException {
		Program p1 = PrologParser.parseString("?- p(a,f(a,X)).");
		System.out.println(p1);
		Program i3=PrologParser.parseFile("exemples/Jalon3/test1.pl");
		Program i31=PrologParser.parseFile("exemples/Jalon3/test2.pl");
		System.out.println(i3);
		Program i2=PrologParser.parseFile("exemples/Jalon2/test.pl");
		Program i1=PrologParser.parseFile("exemples/Jalon1/interprete0.pl");
		Program i0=PrologParser.parseFile("exemples/Jalon1/interprete1.pl");
		System.out.println(i0);
		Interpreter monI0=new Interpreter(i0);
		Interpreter monI1=new Interpreter(i1);
		System.out.println("-----------------"+"\r\n"+"Interpreter 1"+"\r\n"+"-----------------"+"\r\n");
		monI1.interpreter1();
		System.out.println("-----------------"+"\r\n"+"Interpreter 0"+"\r\n"+"-----------------"+"\r\n");
		monI0.interpreter0();
		System.out.println("-----------------"+"\r\n");
		System.out.println("-----------------"+"\r\n"+"Interpreter 2"+"\r\n"+"-----------------"+"\r\n");
		Interpreter2 monI2= new Interpreter2(i2);
		Map<String, Term>e= (Map<String, Term>) monI2.interpreter2();
		System.out.println(e);
		
		System.out.println("------------------------"+"\r\n"+" Interpreter 3: Test 1  "+"\r\n"+"-------------------------"+"\r\n");
		Interprete3 monI3= new Interprete3(i3);
		ArrayList ch1= (ArrayList) monI3.interpreter();
		System.out.println(ch1);
		System.out.println("------------------------"+"\r\n"+"  Interpreter 3: Test 2  "+"\r\n"+"------------------------"+"\r\n");
		Interprete3 monI31= new Interprete3(i31);
		ArrayList ch2= (ArrayList) monI31.interpreter();
		System.out.println(ch1);
	}
}
