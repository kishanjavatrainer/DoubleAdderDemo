package com.infotech.adder.service.task;

import java.util.concurrent.Callable;

import com.infotech.adder.service.DoubleAdderService;

public class DecrementTask implements Callable<Long> {

	private DoubleAdderService doubleAdderService;
	private long noOfTimes;
	
	public DecrementTask(DoubleAdderService doubleAdderService, long noOfTimes) {
		super();
		this.doubleAdderService = doubleAdderService;
		this.noOfTimes = noOfTimes;
	}


	@Override
	public Long call() throws Exception {
		for (int i = 0; i < noOfTimes; i++) {
			doubleAdderService.decrement();
		}
		return doubleAdderService.getValue();
	}

}
