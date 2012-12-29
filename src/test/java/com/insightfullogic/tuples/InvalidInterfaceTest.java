package com.insightfullogic.tuples;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class InvalidInterfaceTest {

	@Parameters
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] { { NoGettersOrSetters.class }, { InvalidReturnGetter.class }, { ParameterGetter.class },
				{ InvalidReturnSetter.class }, { NoParameterSetter.class } });
	}

	private Class<? extends Cursor> representingKlass;

	public InvalidInterfaceTest(Class<? extends Cursor> representingKlass) {
		this.representingKlass = representingKlass;
	}

	@Test(expected = InvalidInterfaceException.class)
	public void interfaceIsInvalid() {
		Allocator.of(representingKlass);
		System.err.println(representingKlass.getName());
	}

	// ---------------------------------------------------

	public interface NoGettersOrSetters extends Cursor {
		public void neitherGetterNorSetter();
	}

	public interface InvalidReturnGetter extends Cursor {
		public Object getFoo();
	}

	public interface ParameterGetter extends Cursor {
		public int getFoo(long bar);
	}

	public interface InvalidReturnSetter extends Cursor {
		public Object setFoo();
	}

	public interface NoParameterSetter extends Cursor {
		public void setFoo();
	}

}
