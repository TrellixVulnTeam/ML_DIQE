package com.ml.mlServiceConsumer.exceptionparser;

import java.util.List;
import java.util.Scanner;

import org.junit.Assert;
import org.junit.Test;

public class StackTraceParserTest  {

	private String getResourceContent(String path) {
		StringBuilder output = new StringBuilder();
		Scanner scanner = new Scanner(getClass().getResourceAsStream(path), "UTF-8");
		while(scanner.hasNextLine()) {
			String s = scanner.nextLine();

			output.append(s + "\n");
		}
		return output.toString();
	}
	@Test
	public void stack1Test() {
		String resourceContent = getResourceContent("stack1.txt");

		StackTraceParser stackTraceParser = new StackTraceParser(resourceContent);
		List<StackTrace> stackTraces = stackTraceParser.getStackTraces();

		Assert.assertEquals(4, stackTraces.size());
		Assert.assertEquals("java.lang.reflect.InvocationTargetException", stackTraces.get(1).getExceptionType());
		Assert.assertEquals("java.lang.instrument.IllegalClassFormatException", stackTraces.get(2).getExceptionType());
		Assert.assertEquals("java.lang.reflect.InvocationTargetException", stackTraces.get(3).getExceptionType());
	}

	@Test
	public void stack2Test() {
		String resourceContent = getResourceContent("stack2.txt");

		StackTraceParser stackTraceParser = new StackTraceParser(resourceContent);
		List<StackTrace> stackTraces = stackTraceParser.getStackTraces();

		Assert.assertEquals(1, stackTraces.size());
		Assert.assertEquals("java.net.ConnectException", stackTraces.get(0).getExceptionType());
		
		/*System.out.println("Stack2Test output");
		System.out.println("Caused By ==>"+ stackTraces.get(0).getCausedBy().getCausedBy());
		System.out.println("Message ==>"+ stackTraces.get(0).getMessage());
		System.out.println("ExceptionType ==>"+ stackTraces.get(0).getExceptionType());
		System.out.println("getCausedByList ==>"+ stackTraces.get(0).getCausedBy().getCausedByList());*/
		
	}

	@Test
	public void stack3Test() {
		String resourceContent = getResourceContent("stack2.txt");
		StackTraceParser stackTraceParser = new StackTraceParser(resourceContent);
		List<StackTrace> stackTraces = stackTraceParser.parseAll(resourceContent);
		Assert.assertEquals(1, stackTraces.size());
		Assert.assertEquals("java.net.ConnectException", stackTraces.get(0).getExceptionType());
	}
}
