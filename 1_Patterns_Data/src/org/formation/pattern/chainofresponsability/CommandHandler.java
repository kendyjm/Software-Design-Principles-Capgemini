package org.formation.pattern.chainofresponsability;

public abstract class CommandHandler {
	protected abstract boolean handleCommand(String commande) ;
	
	public boolean process(CommandChain commandChain, String commande) {
		if(!handleCommand(commande)) {
			return commandChain.getNext().process(commandChain, commande);
		}
		System.out.println("now it's ok");
		return true;
	}
}
