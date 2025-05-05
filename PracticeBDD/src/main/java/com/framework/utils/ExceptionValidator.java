package com.framework.utils;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ExceptionValidator {

	public static void validateException(Runnable task, Class<? extends Throwable> expectedException, String expectedMessagePart) {
		try {
			task.run();  // Attempt to run the code
			Assert.fail("Expected exception of type " + expectedException.getName() + " was not thrown.");
		} catch (Throwable actualException) {
			if (!expectedException.isInstance(actualException)) {
				Assert.fail("Expected exception type: " + expectedException.getName() +
						" but got: " + actualException.getClass().getName());
			}

			if (expectedMessagePart != null && !actualException.getMessage().contains(expectedMessagePart)) {
				Assert.fail("Expected message to contain: \"" + expectedMessagePart +
						"\" but was: \"" + actualException.getMessage() + "\"");
			}

			// Optional: log or print success
			System.out.println("✅ Caught expected exception: " + actualException.getClass().getName() + " - " + actualException.getMessage());
		}
	}
}



class ExceptionTest {

	@Test
	public void testDivideByZeroException() {
		ExceptionValidator.validateException(() -> {
			int result = 10 / 0;
		}, ArithmeticException.class, "/ by zero");
	}

	@Test
	public void testNullPointerMessage() {
		ExceptionValidator.validateException(() -> {
			String str = null;
			str.length();
		}, NullPointerException.class, null); // No need to check message here
	}
}

