/**
 * 
 */
package co.chlg.javaimpdec.rest;

import java.math.BigInteger;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * @author Jhosman Hoyos
 *
 */
public class Fibonacci {
	
	public BigInteger fibonacci(int n)
	{
		Supplier<BigInteger> generador= new Supplier<BigInteger>(){
			 
	        BigInteger fn1 = BigInteger.ZERO;
	        BigInteger fn2 = BigInteger.ONE;
	 
	        @Override
	        public BigInteger get() {
	 
	            BigInteger fibonacci = new BigInteger(fn1.toString());
	            fn1 = new BigInteger(fn2.toString());
	            fn2 = fn2.add(fibonacci);
	 
	            return fibonacci;
	        }
	    };
	 
	    return Stream.generate(generador)
	                    .skip(n)
	                    .limit(1)
	                    .findFirst()
	                    .get();
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Fibonacci fibonacci = new Fibonacci();
		System.out.println(fibonacci.fibonacci(21));
		

	}

}
