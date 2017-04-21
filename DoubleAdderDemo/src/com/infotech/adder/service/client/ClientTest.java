package com.infotech.adder.service.client;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.infotech.adder.service.DoubleAdderService;
import com.infotech.adder.service.impl.DoubleAdderServiceImpl;
import com.infotech.adder.service.task.DecrementTask;
import com.infotech.adder.service.task.IncerementTask;

public class ClientTest {

	public static void main(String[] args) throws InterruptedException, ExecutionException {

		DoubleAdderService doubleAdderService = new DoubleAdderServiceImpl();
		
		IncerementTask task1 = new IncerementTask(doubleAdderService, 5000000L);
		DecrementTask task2 = new DecrementTask(doubleAdderService, 500000L);
		
		List<Callable<Long>> taskList = new LinkedList<>();
		
		taskList.add(task1);
		taskList.add(task2);
		
		ExecutorService executorService = Executors.newCachedThreadPool();
		
		List<Future<Long>> invokeAll = executorService.invokeAll(taskList);
		for (Future<Long> future : invokeAll) {
			future.get();
		}
		
		System.out.println(doubleAdderService.getValue());
	}

}
