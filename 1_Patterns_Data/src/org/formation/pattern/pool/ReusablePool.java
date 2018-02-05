package org.formation.pattern.pool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ReusablePool {

	private static ReusablePool instance;
	// une LinkedList est pas mal aussi
	BlockingQueue<Reusable> myPool; 
	
	private int maxPoolSize = 10;
	
	
	private NoMoreObjectAvailableException REUSABLE_EXCEPTION = new NoMoreObjectAvailableException(); 
	
	private ReusablePool() {
		myPool = new ArrayBlockingQueue<Reusable>(getMaxPoolSize());
		for (int i = 0; i < getMaxPoolSize(); i++) {
			myPool.add(new Reusable());
		}
	}

	public static ReusablePool getInstance() {
		if ( instance == null ) {
			instance = new ReusablePool();
		}
		return instance;
	}
	
	public Reusable getReusable() throws NoMoreObjectAvailableException, InterruptedException {
		return myPool.take();
	}

	public int getMaxPoolSize() {
		return maxPoolSize;
	}
	
	public void releaseReusable(Reusable reusable) {
		myPool.add(reusable);
	}

	/**
	 * Has side effect to initialize the pool.
	 * @param maxPoolSize
	 */
	public void setMaxPoolSize(int maxPoolSize) {
		this.maxPoolSize = maxPoolSize;
		
	}
	
	
}
