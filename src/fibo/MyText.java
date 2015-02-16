package fibo;

import javax.media.j3d.BranchGroup;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Vector3d;

import com.sun.j3d.utils.geometry.Text2D;

/*
============================================================================
Name        : MyText.java
Author      : Catarina Moreira
Copyright   : Catarina Moreira all rights reserved
Description : 

============================================================================ */

public class MyText extends Text2D
{
	/**
	 * 
	 */
	private static final String FONT_NAME = "Arial";
	
	/**
	 * 
	 */
	private static final int FONT_SIZE = Config.TEXT_SIZE;
	
	/**
	 * 
	 */
	private static final int FONT_STYLE = 1;
	
	/**
	 * Creates a MyText object containg a specific text, color and coordinates
	 * 
	 * @param text a string containing the textual content to display
	 * @param color a Color3f object specifying th color of the text
	 * @param x a double corresponding to the coordinate in the x-axis
	 * @param y a double corresponding to the coordinate in the y-axis
	 * @param z a double corresponding to the coordinate in the z-axis
	 */
	public MyText( String text, Color3f color, double x, double y, double z )
	{
		super( text, color, FONT_NAME, FONT_SIZE, FONT_STYLE );
		
		this.updatePosition( x, y, z );
	}
	
	/**
	 * Updates the current position of the MyText object to position ( x, y, z )
	 * 
	 * @param x a double corresponding to the coordinate in the x-axis
	 * @param y a double corresponding to the coordinate in the y-axis
	 * @param z a double corresponding to the coordinate in the z-axis
	 */
	public void updatePosition( double x, double y, double z )
	{
		TransformGroup transformGroup = new TransformGroup( );
		Transform3D transform = new Transform3D( );

		/**
		 * Perform translation over MyText object
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
}
