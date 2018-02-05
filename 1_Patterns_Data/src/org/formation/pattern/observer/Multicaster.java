package org.formation.pattern.observer;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

public class Multicaster {

	Map<ObservableIF,Set<ObserverIF>> registry = new Hashtable<ObservableIF,Set<ObserverIF>>();
	
	public void registerObserver(ObservableIF observable, ObserverIF observer) {
		Set<ObserverIF> observers = registry.get(observable);
		if ( observers == null )  {
			observers = new HashSet<ObserverIF>();
			registry.put(observable, observers);
		}
		observers.add(observer);
		
	}
	
	public void unregisterObserver(ObservableIF observable, ObserverIF observer) {
		Set<ObserverIF> observers = registry.get(observable);
		if ( observers != null )  {
			observers.remove(observer);
		}
		
	}
	public void update(ObservableIF observable) {
		Set<ObserverIF> observers = registry.get(observable);
		if ( observers != null )  {
			observers.forEach(observer -> observer.update());
		}
	}
}
