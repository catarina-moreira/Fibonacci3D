package fibo;

import javax.media.j3d.Appearance;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.Material;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;

import com.sun.j3d.utils.geometry.Sphere;


/*
============================================================================
Name        : MySphere.java
Author      : Catarina Moreira
Copyright   : Catarina Moreira all rights reserved
Description : 

============================================================================*/
public class MySphere extends Sphere
{	
	/**
	 * 
	 */
	private int ID;

	/**
	 * 
	 */
	private static boolean PRIMES = Config.SPHERE_PRIME_NUMBERS;
	
	/**
	 * 
	 */
	private static boolean EVEN = Config.SPHERE_EVEN_NUMBERS;
	
	/**
	 * Default RADIUS of the Sphere
	 */
	private static final float RADIUS = Config.SPHERE_SIZE;
	
	/**
	 * 
	 * @param id
	 * @param x
	 * @param y
	 * @param z
	 */
	public MySphere( int ID, double x, double y, double z )
	{
		/**
		 * Create a sphere with a radius of RADIUS units
		 */
		super( RADIUS );
		this.ID = ID;
		
		/**
		 * Each MySphere will have a specific color
		 */
		setupAppearence( );
		
		/**
		 * Each MySphere object will occupy a specific position in the universe
		 */
		updatePosition( x, y, z );
	}	
	
	/**
	 * 
	 */
	public void setupAppearence( )
	{	
		/**
		 * 
		 */
		if( EVEN && isEven( ID )  )
			setColor( Config.SPHERE_EVEN_COLOR );

		/**
		 * 
		 */
		if( PRIMES && isPrime( ID )  )
			setColor( Config.SPHERE_PRIME_COLOR );
	}
	
	/**
	 * 
	 * @param color
	 */
	public void setColor( Color3f color )
	{
		/**
		 * Get current appearance
		 */
		Appearance appearance = this.getAppearance( );
		
		/**
		 * Get and set Material
		 */
		Material material = appearance.getMaterial( );
		material.setDiffuseColor( color );
		this.setAppearance( appearance );

		/**
		 * Get shape and set the material again
		 */
		Shape3D sh3D=this.getShape( );
		appearance = sh3D.getAppearance( );
		material = appearance.getMaterial( );
		material.setDiffuseColor( color );

		/**
		 * Set appearance back
		 */
		sh3D.setAppearance( appearance );
		
		/**
		 * setup a light with the desired color
		 */
		setupLight( color );
	}

	/**
	 * 
	 * @param lightColor
	 */
	public void setupLight( Color3f lightColor )
	{
		/**
		 * Setup bounds and light direction vector
		 */
		BoundingSphere bounds = new BoundingSphere( new Point3d( 0.0, 0.0, 0.0 ), 0.1 );
		Vector3f lightDirection = new Vector3f( 4.0f, -7.0f, -12.0f );
		
		/**
		 * Create directional light
		 */
		DirectionalLight light = new DirectionalLight( lightColor, lightDirection );
		light.setInfluencingBounds( bounds );
	
		/*
		 * Attatch this light to the MySphere object
		 */
		this.addChild( light );
	}
	
	/**
	 * Checks if a number is prime
	 * @param n
	 * @return true if number is prime
	 */
	public boolean isPrime( int n ) 
	{
	    return !new String( new char[ n ] ).matches( ".?|(..+?)\\1+" );
	}
	
	/**
	 * 
	 * @param n
	 * @return
	 */
	public boolean isEven( int n )
	{
		return ( n % 2 ) == 0;
	}
	
	/**
	 * Convert an integer type into a String object
	 * @return the String representation of the input integer
	 */
	public String integerToString( int integer )
	{
		return Integer.toString( integer );
	}
	
	/**
	 * Updates the position of the sphere using a Transform3D object
	 */
	public void updatePosition( double x, double y, double z )
	{	
		TransformGroup transformGroup = new TransformGroup( );
		Transform3D transform = new Transform3D( );
		
		/**
		 * Perform translation over MySphere object
		 * This translation is computed through the logarithmic spiral equation
		 * which is called in class TimerListener
		 */
		Vector3d vector = new Vector3d( x, y, z );
		transform.setTranslation( vector );

		transformGroup.setTransform( transform );
		transformGroup.addChild( this );
		
		/**
		 * Add both translations to the BranchGroup
		 */
		BranchGroup branch = new BranchGroup( );
		branch.addChild( transformGroup );

		/**
		 * Add the new translations to the main Branch
		 */
		Fibonacci3D.branch.addChild( branch );
		
	}
	
	// GETTERS AND SETTERS
	//******************************************************************
	
	/**
	 * 
	 * @return
	 */
	public int getID( )
	{
		return this.ID;
	}
	
	/**
	 * 
	 * @param id
	 */
	public void setID( int ID )
	{
		this.ID = ID;
	}
	
}
