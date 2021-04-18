package pcomp.prolog.jalon2;

import pcomp.prolog.jalon1.UnifyException;

public class StepException extends Exception {

	public StepException(UnifyException ue) {
		System.out.println(ue);
	}
	public StepException(String s) {
		super(s);
	}
}
