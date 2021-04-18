package pcomp.prolog.jalon3;


import java.util.ArrayList;
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


public class Choix {
	
	Goal UnButs;
	Func but;
	Assert UnRules;
	Map<String,Term> env;
	
	public Choix() {
		this.but=null;
		this.UnRules=null;
		this.env=null;
	}
	
	public Choix(Func but,Assert lesRules,Map<String,Term> env) {
		this.but=but;
		this.UnRules=lesRules;
		this.env=env;
	}
	
	public Map<String,Term> getEnv() {
		return this.env;
	}
	
	public Map<String,Term> setEnv(String key, Term value) {
		this.env.put(key, value);
		return this.env;
	}
	public Func getGoal() {
		return this.but;
	}
	
	public Assert getRules() {
		return this.UnRules;
	}
	public String toString() {
		return "("+this.but.toString()+" , ["+this.UnRules.toString()+"] , "+this.env+" )";
	}
	
	
}
