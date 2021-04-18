package pcomp.prolog.jalon1;

import java.awt.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import pcomp.prolog.ast.Func;
import pcomp.prolog.ast.Term;
import pcomp.prolog.ast.TermVisitor;
import pcomp.prolog.ast.Var;

public class TermV implements TermVisitor<Object>{

	
	
	@Override
	public  String visit(Var var) {
		// TODO Auto-generated method stub
		return var.getVar();
	}

	@Override
	public Object visit(Func func) {
		// TODO Auto-generated method stub
		Map<String, Object> a= new HashMap<String,Object>();
		Object l=func.getTerms();
		a.put(func.getAtom(),l);
		return l;
	}
	
	
	public static void main(String [] args) {
		TermV a=new TermV();
		
		Func ti=new Func("f",new ArrayList<Term>() {{add(new Func("a", new ArrayList<Term>() {{add(new Var("W"));}}));}{add(new Var("V"));}});
		Object o=a.visit(ti);
		Func c=new Func("c");
		System.out.println(a.visit(ti));
		System.out.println(a.visit(c).getClass());
	}
}
