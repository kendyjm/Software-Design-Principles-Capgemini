package org.formation.pattern.observer;

public interface ObservableIF {

	public void registerObserver(ObserverIF observer);
	public void unregisterObserver(ObserverIF observer);
}
