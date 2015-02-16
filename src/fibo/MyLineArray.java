package fibo;

import javax.media.j3d.Appearance;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.LineArray;
import javax.media.j3d.Shape3D;
import javax.media.j3d.TransparencyAttributes;
import javax.vecmath.Color3f;
import javax.vecmath.Point3f;

/*
============================================================================
Name        : MyLineArray.java
Author      : Catarina Moreira
Copyright   : Catarina Moreira all rights reserved
Description : 

============================================================================ */

public class MyLineArray extends LineArray
{
	/**
	 * 
	 * @param color
	 */
	public MyLineArray( Color3f color  )
	{
		/**
		 * Constructs an empty LineArray object with the specified number of vertices, vertex format. 
		 * The first parmeter corresponds to the number of vertex elements in this LineArray
		 * 
		 * The second parameter is a mask indicating which components are present in each vertex. 
		 * This is specified as one or more individual flags that are bitwise "OR"ed together to describe the per-vertex data. 
		 * The flags include: 
		 * 
		 * COORDINATES, to signal the inclusion of vertex positions--always present; 
		 * NORMALS, to signal the inclusion of per vertex normals; one of 
		 * COLOR_3 or COLOR_4, to signal the inclusion of per vertex colors (without or with alpha information);
		 * TEXTURE_COORDINATE_2 or TEXTURE_COORDINATE_3 or TEXTURE_COORDINATE_4, to signal the inclusion of per-vertex texture coordinates (2D , 3D or 4D); 
		 * BY_REFERENCE, to indicate that the data is passed by reference rather than by copying; 
		 * INTERLEAVED, to indicate that the referenced data is interleaved in a single array; 
		 * USE_NIO_BUFFER, to indicate that the referenced data is accessed via a J3DBuffer object that wraps an NIO buffer; 
		 * USE_COORD_INDEX_ONLY, to indicate that only the coordinate indices are used for indexed geometry arrays.
		 * 
		 */
		super( 2000, LineArray.COORDINATES );
		this.setCapability( LineArray.ALLOW_COORDINATE_WRITE );
		
		/**
		 * Changes the color of the MyLineArray object to the specified color
		 */
		setupAppearance( color );
	}
	
	/**
	 * 
	 * @param color
	 */
	public void setupAppearance( Color3f color )
	{
		ColoringAttributes colorAttr = new ColoringAttributes( color, ColoringAttributes.SHADE_FLAT );
		
		Appearance appearance = new Appearance( );
		appearance.setColoringAttributes( colorAttr );
		
		TransparencyAttributes attr = new TransparencyAttributes( );
		attr.setTransparency( 0.1f );
		
		appearance.setTransparencyAttributes( attr );
		
		Shape3D arrayShape = new Shape3D( this, appearance );
		
		BranchGroup tempBranch = new BranchGroup( );
		tempBranch.addChild( arrayShape );
		
		Fibonacci3D.branch.addChild( tempBranch );
	}
	
	/**
	 * Creates a new edge between the points ( x1, y1, z1 ) and (x2, y2, z2 )
	 * 
	 * @param ID an integer representing the identifier of the edge
	 * @param x1 a float corresponding to the coordinate in the x-axis
	 * @param y1 a float corresponding to the coordinate in the y-axis
	 * @param z1 a float corresponding to the coordinate in the z-axis
	 * @param x2 a float corresponding to the coordinate in the x-axis
	 * @param y2 a float corresponding to the coordinate in the y-axis
	 * @param z2 a float corresponding to the coordinate in the z-axis
	 */
	public void updateCoordinates( int ID, float x1, float y1, float z1, 
									float x2, float y2, float z2 )
	{
		Point3f[ ] points = { new Point3f( x1, y1, z1), new Point3f( x2, y2, z2 ) };
		this.setCoordinates( ID, points );
	}
	
	/**
	 * Updates the coordinates of the MyArray object with identifier ID to ( x1, x2, x3 )
	 * @param ID an integer representing the identifier of the edge
	 * @param x1 a float corresponding to the coordinate in the x-axis
	 * @param y1 a float corresponding to the coordinate in the y-axis
	 * @param z1 a float corresponding to the coordinate in the z-axis
	 */
	public void updateCoordinate( int ID, float x1, float y1, float z1 )
	{
		this.setCoordinate( ID, new Point3f( x1, y1, z1) );
	}
	
}
