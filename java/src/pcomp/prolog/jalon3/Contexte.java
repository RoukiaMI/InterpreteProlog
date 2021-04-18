package pcomp.prolog.jalon3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import pcomp.prolog.ast.Assert;
import pcomp.prolog.ast.Func;
import pcomp.prolog.ast.Goal;
import pcomp.prolog.ast.Term;

public class Contexte {
	Goal goals;
	ArrayList<Assert> rules;
	
	public Contexte(Goal goals, ArrayList<Assert> rules) {
		this.goals=goals;
		this.rules=rules;
	}
	
	public Map<Func, ArrayList<Assert>> journalCh(){
		Map<Func,ArrayList<Assert> > map=new HashMap();
		ArrayList<Choix> ch=new ArrayList();
		for(Func f: goals.getBody()) {
			map.put(f, rules);
		}
		return map;
	}
	
}
