package com.enterprise.auctions;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.enterprise.beans.ItemBean;
import com.enterprise.dao.DataAccessException;
import com.enterprise.dao.support.ItemDAOImpl;

public class AuctionParser implements Runnable{
	private ThreadPoolExecutor executor;
	
	public AuctionParser() {
		BlockingQueue<Runnable> auctionQueue = new ArrayBlockingQueue<Runnable>(10);
		executor = new ThreadPoolExecutor(5,15,15,TimeUnit.SECONDS,auctionQueue);	//(corePoolSize,MaxPoolSize,KeepAliveTime,time unit for keep alive arugment)
																					//(the number of threads in pool, the maximum number of threads in pool)
	}
	
	/**
	 * Executes the AuctionTask Runnable
	 *  
	 * @param the item we want to check whether its done or not
	 */
	private void AuctionExecutor (ItemBean item) {	
		AuctionTask task = new AuctionTask(item);		//create new task 
		executor.execute(task);						//execute the auctiontask runnable some time later
	}
	
	
	/**
	 *This method gets a list of all the active auction items and creates a runnable auction task for each one 
	 */
	@Override
	public void run() {
		while(true) {
			try {
				List<ItemBean> activeItemsList = new ItemDAOImpl().getActiveItems();	//Get Active items
				for(int i =0;i < activeItemsList.size();i++) {	//Loop through list
					AuctionExecutor(activeItemsList.get(i));	//pass the item bean to the executor
				}
				
			} catch (DataAccessException e) {
				e.printStackTrace();
			}
			
			try {
				Thread.sleep(5000);		//Pause for 5 seconds
			} catch (InterruptedException e) {
				e.printStackTrace();
			} 
			
		}
		
	}

}
