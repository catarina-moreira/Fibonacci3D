package action.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.vecmath.Point3f;

import fibo.Config;
import fibo.MyLineArray;
import fibo.MySphere;
import fibo.MyText;


/*
============================================================================
Name        : FibonacciListener.java
Author      : Catarina Moreira
Copyright   : Catarina Moreira all rights reserved
Description : 

============================================================================ */
public class FibonacciListener implements ActionListener
{
	/**
	 * Used to activate a zoom effect during the animation
	 */
	private static final boolean ZOOM_OUT = Config.ZOOM_ON;
	
	/**
	 * Used to control if the MyText object will be displayed in the animation or not
	 */
	private static final boolean TEXT = Config.TEXT_ON;
	
	/**
	 * Used only to establish an upperbound to stop the animation. Otherwise, the
	 * animation will run towards infinitum
	 * 
	 */
	private static final int THRESHOLD = Config.TOTAL_NODES;
	
	/**
	 * Unique identifier of each data point of the logarithmic spiral
	 */
	private static int ID;
	
	/**
	 * X coordinate to plot the logarithmic spiral
	 */
	private static float POSITION_X;
	
	/**
	 * Y coordinate to plot the logarithmic spiral
	 */
	private static float POSITION_Y;
	
	/**
	 * Z coordinate to plot the logarithmic spiral
	 */
	private static float POSITION_Z;
	
	/**
	 * Angle to plot the logarithmic spiral
	 */
	private static float THETA;
	
	/**
	 * The golden ratio corresponds to the approximated ratio computed for consecutive
	 * Fibonacci numbers (try varying this variable and see what happens! )
	 */
	private static final float GOLDEN_RATIO =  Config.GOLDEN_RATIO;
	
	/**
	 * The golden angle corresponds to the approximation of ratios of consecutive Fibonacci numbers
	 */
	private static final float GOLDEN_ANGLE = 360 - ( 360 / GOLDEN_RATIO );
	
	/**
	 * Structure to store all points of the simulation
	 */
	private HashMap< Integer, Point3f > data = new HashMap< Integer, Point3f >( );
	
	/**
	 * The listener interface for receiving action events. 
	 * The class that is interested in processing an action event implements this interface, and 
	 * the object created with that class is registered with a component, using the component's 
	 * addActionListener method. When the action event occurs, that object's actionPerformed method is invoked.
	 */
	public FibonacciListener( ) 
	{ 
		initializeSpiralVars( );	// sets all coordinates to zero
		
		initializePlot( );			// starts drawing the spiral
		
	}
	
	/**
	 * At each cicle, actionPerformed function must make two things:
	 * 
	 * (1) create a new MySphere object with position of the current spiral variables
	 * (2) create a new MyText object with position of the current spiral variables
	 * (3) update the variables to compute the spiral for the next time it is called
	 */
	@Override
	public void actionPerformed( ActionEvent e )
	{
		/**
		 *  if there are too many spheres on the screen, then end execution
		 */
		if( ID > THRESHOLD )
		{
			connectSpiralArms( );
			return;
		}
			
		/**
		 * update the coordinates according to the logarithmic spiral function for next iteration.
		 */
		updateSpiralVars( );
		
		/**
		 * create a new MySphere object with the current coordinates
		 */
		new MySphere( ID, POSITION_X, POSITION_Y, POSITION_Z );
		
		/**
		 * create a new MyText object with the current coordinates, but with a small shift in the x-axis
		 * this shift is to avoid being overlaped with the MySphere object
		 */
		if( TEXT )
			createTextElement( );	
	}
	
	/**
	 * This function is responsible for setting the coordinates POSITION_X and POSITION_Y 
	 * according to the logarithmic spiral formula:
	 * 
	 * POSITION_X = | r | * cos( THETA )
	 * POSITION_Y = | r | * sin( THETA )
	 * 
	 * Where | r | corresponds to the distance of the points to the origin:
	 * 
	 * | r | = sqrt( ID - 0 )
	 * 
	 * And THETA corresponds to the GOLDEN RATIO in radians
	 * 
	 */
	public void updateSpiralVars( )
	{
		/**
		 * Updating the id of the next plot
		 */
		ID = ID + 1;
		
		/**
		 * Corresponds to the distance of the current plot to the origin of the graph:
		 * Since ID start with 0, it is necessary to increment 1 value 
		 * 
		 * | r | = sqrt( ID - 0 );
		 * 
		 */
		float distanceFromCenter = ( float ) Math.sqrt( ID );
		
		/**
		 * Used only to avoid the spiral to expand so fast
		 */
		distanceFromCenter /= Config.EXPANSION_RATE;
		
		/**
		 * Updating the logarithmic spiral coordinates according to Fermat's formula:
		 * 
		 * x = | r | * cos( theta )
		 * y = | r | * sin( theta )
		 */
		POSITION_X = ( float ) ( distanceFromCenter * Math.cos( convertToRadians(THETA)  ) );
		POSITION_Y = ( float ) ( distanceFromCenter * Math.sin( convertToRadians(THETA)  ) );
		
		/** 
		 * Used only for debugging purposes
		 */
		//System.out.println(  "THETA = " +  THETA + "\tID = " + ID + "\tX = " + POSITION_X + "\tY = " + POSITION_Y + "\tr = " + distanceFromCenter );
		
		/**
		 * Updating THETA according to Fermat's formula
		 * 
		 * theta = theta + GOLDEN_ANGLE
		 */
		THETA += GOLDEN_ANGLE;
		
		/**
		 * Used only to enable a zoom in effect during the animation
		 */
		if( ZOOM_OUT )
			POSITION_Z += 0.05;		
		
		data.put( ID, new Point3f( POSITION_X, POSITION_Y, POSITION_Z ) );
		System.out.println( ID + " " + POSITION_X + " " + POSITION_Y + " " + POSITION_Z );	
	}
	
	/**
	 * Converts an angle in degrees to an angle in radians
	 * 1 degree corresponds to 0.0174532925 radians
	 * 
	 * @param val is an angle in degrees
	 * @return the parameter val in radians
	 */
	public double convertToRadians( double val )
	{
		return 0.0174532925*val;
	}
	
	/**
	 * Function that determines if an integer is a prime number.
	 * .? : The first part of the alternation matches String of length 0 or 1 (NOT prime by definition)
	 * (..+?)\1+ : The second part of the alternation, a variation of the regex explained above, matches 
	 * String of length n that is "a multiple" of a String of length k >= 2 (i.e. n is a composite, NOT a prime).
	 * 
	 * @param n an integer number
	 * @return a boolean stating if n is prime
	 */
	public boolean isPrime( int n ) 
	{
		String lengthN = new String( new char[ n ] );
	    boolean isNotPrimeN = lengthN.matches(".?|(..+?)\\1+");
	    
	    return !isNotPrimeN;
	}
	
	/**
	 * Checks if a number is even
	 * @param n an integer number
	 * @return a boolean stating if n is an even number
	 */
	public boolean isEven( int n )
	{
		return ( n % 2 ) == 0;
	}
	
	/**
	 * Initializes the coordinates and the theta angle to 0.
	 */
	public void initializeSpiralVars( )
	{
		POSITION_X = 0.0f;
		POSITION_Y = 0.0f;
		POSITION_Z = 1.0f;
		THETA = 0.0f;
		ID = 0;
	}
	
	/**
	 * Plots the first sphere at the origin of the scene
	 */
	public void initializePlot( )
	{
		if( ID == 0 )
		{
			new MySphere( ID, 0.0f, 0.0f, 0.0f );
			
			if( TEXT && Config.SPHERE_EVEN_NUMBERS )
				new MyText( "0", Config.TEXT_EVEN_COLOR, 0.25, 0, 0 );

			if( TEXT && !Config.SPHERE_EVEN_NUMBERS )
				new MyText( "0", Config.TEXT_COLOR, 0.25, 0, 0 );
		}
	}
	
	/**
	 * 
	 */
	public void createTextElement( )
	{
		if( Config.SPHERE_EVEN_NUMBERS && isEven( ID ) )
			new MyText( Integer.toString( ID ), Config.TEXT_EVEN_COLOR, POSITION_X + 0.25, POSITION_Y, POSITION_Z );
		
		if( Config.SPHERE_PRIME_NUMBERS && isPrime( ID ) )
			new MyText( Integer.toString( ID ), Config.TEXT_PRIME_COLOR, POSITION_X + 0.25, POSITION_Y, POSITION_Z );
		
		if( !isPrime( ID ) && !isEven( ID ) )
				new MyText( Integer.toString( ID ), Config.TEXT_COLOR, POSITION_X + 0.25, POSITION_Y, POSITION_Z );
	}
	
	/**
	 * Draws edges between dots of the spiral
	 */
	public void connectSpiralArms( )
	{
		/**
		 * In case the user wishes to display the edges
		 */
		if( !Config.EDGE_ON )
			return;
		
		Point3f dataOrigin = new Point3f(0.0f, 0.0f, 1.0f);
		Point3f nextPoint = new Point3f( 0.33333334f, 0.0f, 1.0f);
		
		try {
		    Thread.sleep(1000);                 //1000 milliseconds is one second.
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
		
		new MyLineArray( Config.EDGE_COLOR, dataOrigin, nextPoint );
		
		for( int j = 14; j < data.size( ); j++ )
		{
			Point3f dataPointOld = data.get( j - 13 );
			Point3f dataPointNew = data.get( j );
				
			//1.033f , -0.657f , 0.0f, 1.545f , -1.5374f , 0.0f
			new MyLineArray( Config.EDGE_COLOR, dataPointOld, dataPointNew );
			
		}
	}
}
