package fibo;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GraphicsConfiguration;

import javax.media.j3d.AmbientLight;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.Group;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;

import javax.swing.JButton;
import javax.swing.JFrame;

import javax.swing.Timer;
import javax.swing.WindowConstants;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;

import com.sun.j3d.utils.universe.SimpleUniverse;
import com.sun.j3d.utils.universe.Viewer;
import com.sun.j3d.utils.universe.ViewingPlatform;

import action.listener.StopAnimationListener;
import action.listener.FibonacciListener;
import action.listener.StartAnimationListener;

/*
============================================================================
Name        : Fibonacci3D.java
Author      : Catarina Moreira
Copyright   : Catarina Moreira all rights reserved
Description : 

============================================================================*/
@SuppressWarnings( "serial" )
public class Fibonacci3D extends JFrame
{
	/**
	 * Animation timer
	 */
	private Timer timer;
	
	/**
	 * Animation delay time
	 */
	private static final int DELAY = 70;
	
	/**
	 * Initialize branch that is going to store all java 3d objects
	 */
	public static BranchGroup branch = new BranchGroup( );
	
	/**
	 * 
	 */
	private SimpleUniverse universe;

	/**
	 * 
	 */
	public Fibonacci3D( )
	{
		super("Fibonacci 3D");

		/**
		 * Initialize a Layout
		 */
		setLayout( new BorderLayout( ) );
		
		/**
		 * Specify both an Ambient Light and a Directional Light according to some boundings
		 */
		BoundingSphere bounds = new BoundingSphere( new Point3d( ), 20f );
		setupAmbientLight( bounds );			
		setupDirectionLight( bounds, new Vector3f( 1.0f, -1.0f, -1.0f ), new Color3f( 0f, 0f, 1.0f )  );
		
		/**
		 * 
		 */
		Canvas3D canvas = setupCanvas( );		
		this.universe = setupUniverse( canvas );
		setupViewingPlatform( universe.getViewingPlatform( ) );
		
		/**
		 * 
		 */
		this.timer = new Timer( DELAY, new FibonacciListener( ) );	
		
		/**
		 * Animation options
		 */
		startAnimation( );
		
		/**
		 * Animation options
		 */
		stopAnimation( );
		
		/**
		 * Panel Layout
		 */
		setTitle( "Fibonacci 3D" );
		setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
		setSize( 750, 750 );
		setExtendedState(JFrame.MAXIMIZED_BOTH); 
		setResizable( true );
		setVisible( true );
		requestFocus( );
	}
	
	/**
	 * 
	 * @param args
	 */
	public static void main( String[ ] args  )
	{
		/**
		 * 
		 */
		Config config = new Config("config.xml");
		config.parseConfigurations( );

		EventQueue.invokeLater( new Runnable( ) {
			public void run( ) {
				
				new Fibonacci3D( );
			}
		});
	}
	
	/**
	 * 
	 * @param canvas
	 * @return
	 */
	public SimpleUniverse setupUniverse( Canvas3D canvas )
	{
		ViewingPlatform viewPlatform = new ViewingPlatform( );
		
		/**
		 * 
		 */
		SimpleUniverse universe = new SimpleUniverse( viewPlatform, new Viewer( canvas ) );
		universe.addBranchGraph( Fibonacci3D.branch );
		
		return universe;	
	}
	
	/**
	 * 
	 * @param viewPlatform
	 */
	public void setupViewingPlatform( ViewingPlatform viewPlatform )
	{
		viewPlatform.setNominalViewingTransform( );
		
		/**
		 * 
		 */
		TransformGroup transformGroup = viewPlatform.getViewPlatformTransform( );
		Transform3D transform = new Transform3D( );
		transformGroup.getTransform( transform );
		
		/**
		 * 
		 */
		Vector3d position = new Vector3d( );
		transform.get( position );
		
		/**
		 * 
		 */
		Vector3d offset = new Vector3d( 0, 0, -20f );
		position.sub( offset );
		transform.setTranslation( position );
		transformGroup.setTransform( transform );
	}
	
	/**
	 * 
	 * @return
	 */
	public Canvas3D setupCanvas( )
	{
		GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration( );
		Canvas3D canvas = new Canvas3D( config );
		add( canvas, BorderLayout.CENTER );
		
		return canvas;
	}
	
	/**
	 * 
	 * @param bounds
	 */
	public void setupAmbientLight( BoundingSphere bounds )
	{
		/**
		 * Initialize light and set its influence
		 */
		AmbientLight light = new AmbientLight( );
		light.setInfluencingBounds( bounds );
		
		/**
		 * Add light object to branchGroup;
		 */
		Fibonacci3D.branch.addChild( light );
	}
	
	/**
	 * 
	 * @param bounds
	 * @param direction
	 * @param color
	 */
	public void setupDirectionLight( BoundingSphere bounds, Vector3f direction, Color3f color )
	{
		DirectionalLight light = new DirectionalLight( );
		light.setInfluencingBounds( bounds );
		
		/**
		 * Add a direction and a color attribute to the light
		 */
		direction.normalize( );
		light.setDirection( direction );
		light.setColor( color );
		
		/**
		 * Add light object to branchGroup
		 */
		Fibonacci3D.branch.addChild( light );
		Fibonacci3D.branch.setCapability( Group.ALLOW_CHILDREN_EXTEND );
	}
	
	/**
	 * 
	 */
	public void startAnimation( )
	{
		/**
		 * Create start button in the end if the grid
		 */
		JButton button = new JButton( "Start" );		
		this.add( button, BorderLayout.PAGE_START );
		
		button.addActionListener( new StartAnimationListener( getTimer( ) ) );

	}
	
	/**
	 * 
	 */
	public void stopAnimation( )
	{
		/**
		 * Create start button in the end if the grid
		 */
		JButton button = new JButton( "Stop" );
		add( button, BorderLayout.PAGE_END );

		/**
		 * Stop Animation
		 */
		button.addActionListener( new StopAnimationListener( getTimer( ) ) );
		
	}
	
	// GETTERS AND SETTERS
	//******************************************************************
	
	/**
	 * 
	 * @return
	 */
	public Timer getTimer( )
	{
		return this.timer;
	}
	
	/**
	 * 
	 * @return
	 */
	public SimpleUniverse getSimpleUniverse( )
	{
		return this.universe;
	}
	
}
