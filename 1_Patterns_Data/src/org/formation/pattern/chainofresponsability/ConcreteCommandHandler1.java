package org.formation.pattern.chainofresponsability;

public class ConcreteCommandHandler1 extends CommandHandler {

	protected boolean handleCommand(String commande) {
		System.out.println("First handler handles " + commande + " I will return false");
		return false;
	}

}
