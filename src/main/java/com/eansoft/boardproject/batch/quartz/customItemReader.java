package com.eansoft.boardproject.batch.quartz;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

public class customItemReader implements ItemReader<Object>{

	@Override
	public Object read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		return null;
	}

}
