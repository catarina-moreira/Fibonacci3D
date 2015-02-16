package fibo;

import java.math.BigInteger;

public class Fibonacci
{

	public static BigInteger fastRecursiveFibonacci( int n )
	{
		BigInteger N =  new BigInteger( Integer.toString( n ) );
		
		if( N.equals( BigInteger.ZERO ))			// Stop criteria: if N == 0, then 
			return BigInteger.ZERO;					// Fib( 0 ) = 1
		
		if( N.equals( BigInteger.ONE )) 			// Stop criteria: if N == 1, then 
			return BigInteger.ONE;					// Fib( 1 ) = 1
		
		return auxFibonacci( N, BigInteger.ZERO, BigInteger.ONE );
	}
	
	/**
	 * 
	 * @param N
	 * @param val1
	 * @param val2
	 * @return
	 */
	public static BigInteger auxFibonacci( BigInteger N, BigInteger val1, BigInteger val2 )
	{
		if( N.equals( BigInteger.ONE ) )
			return val2;
		
		return auxFibonacci( N.subtract( BigInteger.ONE ), val2, val1.add( val2 ) );
	}
	


}
