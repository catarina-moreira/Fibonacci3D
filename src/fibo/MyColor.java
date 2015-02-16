package fibo;

import javax.vecmath.Color3f;

/*
============================================================================
Name        : MyColor.java
Author      : Catarina Moreira
Copyright   : Catarina Moreira all rights reserved
Description : 

============================================================================ */
public final class MyColor
{
	/**
	 * RGB codes for color ORANGE
	 */
	public static final Color3f ORANGE = new Color3f( 1.0f, 0.6f, 0.0f );
	
	/**
	 * RGB codes for color WHITE
	 */
	public static final Color3f WHITE =  new Color3f( 1.0f, 1.0f, 1.0f);
	
	/**
	 * RGB codes for color LIGHT_GRAY
	 */
	public static final Color3f LIGHT_GRAY = new Color3f( 0.8f, 0.8f, 0.8f );
	
	/**
	 * RGB codes for color RED
	 */
	public static final Color3f RED = new Color3f( 1.0f, 0.0f, 0.0f );
	
	/**
	 * 
	 */
	public static final Color3f PINK = new Color3f( 1.0f, 0.5f, 0.7f );
	
	/**
	 * RGB codes for color BLUE
	 */
	public static final Color3f BLUE = new Color3f( 0.0f, 0.0f, 1.0f );
	
	/**
	 * RGB codes for color GREEN
	 */
	public static final Color3f GREEN = new Color3f( 0.0f, 1.0f, 0.0f );
	
	/**
	 * RGB codes for color DARK_GRAY
	 */
	public static final Color3f DARK_GRAY = new Color3f( 0.4f, 0.4f, 0.4f );
			
	/**
	 * RGB codes for color BROWN
	 */
	public static final Color3f BROWN = new Color3f( 0.4f, 0.2f, 0.0f );
	
	/**
	 * RGB codes for color PURPLE
	 */
	public static final Color3f PURPLE = new Color3f( 0.7f, 0.0f, 0.4f );
	
	/**
	 * RGB codes for color LIGHT_BLUE
	 */
	public static final Color3f LIGHT_BLUE = new Color3f( 0.7f, 0.7f, 0.8f );
	
	/**
	 * Converts a String into a Color3f object. Returns the color WHITE in a Color3f object if the 
	 * String is not recognized 
	 * 
	 * @param color a String with the name of a color
	 * @return a Color3f representation of the color specified in the String
	 */
	public static Color3f convertColor( String color )
	{
		/**
		 * Converts the input String into a Color3f object specifying the color ORANGE
		 */
		if( color.equals( "ORANGE" ) ) return ORANGE;
		
		/**
		 * Converts the input String into a Color3f object specifying the color LIGHT_GRAY
		 */
		if( color.equals( "LIGHT_GRAY" ) ) return LIGHT_GRAY;
		
		/**
		 * Converts the input String into a Color3f object specifying the color RED
		 */
		if( color.equals( "RED" ) ) return RED;
		
		/**
		 * Converts the input String into a Color3f object specifying the color PINK
		 */
		if( color.equals( "PINK" ) ) return PINK;
		
		/**
		 * Converts the input String into a Color3f object specifying the color BLUE
		 */
		if( color.equals( "BLUE" ) ) return BLUE;
		
		/**
		 * Converts the input String into a Color3f object specifying the color GREEN
		 */
		if( color.equals( "GREEN" ) ) return GREEN;
		
		/**
		 * Converts the input String into a Color3f object specifying the color DARK_GRAY
		 */
		if( color.equals( "DARK_GRAY" ) ) return DARK_GRAY;
		
		/**
		 * Converts the input String into a Color3f object specifying the color BROWN
		 */
		if( color.equals( "BROWN" ) ) return BROWN;
		
		/**
		 * Converts the input String into a Color3f object specifying the color PURPLE
		 */
		if( color.equals( "PURPLE" ) ) return PURPLE;
		
		/**
		 * Converts the input String into a Color3f object specifying the color LIGHT_BLUE
		 */
		if( color.equals( "LIGHT_BLUE" ) ) return LIGHT_BLUE;
		
		/**
		 * If any of the Strings are recognized,
		 * converts the input String into a Color3f object specifying the color WHITE
		 */
		return WHITE; 
	}
	
	
}
