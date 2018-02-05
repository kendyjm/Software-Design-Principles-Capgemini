package org.formation.pattern.adapter;

public class Adapter implements TargetIF {

	private Adaptee adaptee = new Adaptee();
	
	@Override
	public double interfaceMethod() {
		return adaptee.myMethod();
	}

}
