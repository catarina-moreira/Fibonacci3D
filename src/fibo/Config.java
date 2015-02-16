package fibo;

import java.io.File;
import java.io.IOException;

import javax.vecmath.Color3f;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Config
{
	/**
	 * 
	 */
	public static Color3f SPHERE_COLOR;
	
	/**
	 * 
	 */
	public static Color3f SPHERE_EVEN_COLOR;
	
	/**
	 * 
	 */
	public static Color3f SPHERE_PRIME_COLOR;
	
	
	/**
	 * 
	 */
	public static boolean SPHERE_EVEN_NUMBERS;
	
	/**
	 * 
	 */
	public static boolean SPHERE_PRIME_NUMBERS;
	
	/**
	 * 
	 */
	public static float SPHERE_SIZE;
	
	/**
	 * 
	 */
	public static Color3f TEXT_COLOR;
	
	/**
	 * 
	 */
	public static Color3f TEXT_PRIME_COLOR;
	
	/**
	 * 
	 */
	public static Color3f TEXT_EVEN_COLOR;
	
	
	/**
	 * 
	 */
	public static boolean TEXT_ON;
	
	/**
	 * 
	 */
	public static int TEXT_SIZE;
	
	/**
	 * 
	 */
	public static Color3f EDGE_COLOR;
	
	/**
	 * 
	 */
	public static boolean EDGE_ON;
	
	/**
	 * 
	 */
	public static int TOTAL_NODES;
	
	/**
	 * 
	 */
	public static boolean ZOOM_ON;
	
	/**
	 * 
	 */
	public static float GOLDEN_RATIO;
	
	
	/**
	 * 
	 */
	public static int EXPANSION_RATE;
	
	/**
	 * 
	 */
	private String filePath;
	
	/**
	 * 
	 * @param filePath
	 */
	public Config( String filePath )
	{
		this.filePath = filePath;
	}
	
	/**
	 * @throws IOException 
	 * @throws ParserConfigurationException 
	 * @throws SAXException 
	 * 
	 */
	public void parseConfigurations( ) 
	{
		File configFile = new File( getFilePath( ) );
		
		try
		{
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance( );
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder( );
		
			Document doc = dBuilder.parse( configFile );
			doc.getDocumentElement( ).normalize( );
			
			/**
			 * Find the sphere node in the XML configuration file
			 * And start gathering all child nodes
			 */
			NodeList nodes = doc.getElementsByTagName( "sphere" );
			extractNodeSphereContent( nodes.item( 0 ) );
			
			/**
			 * Find the sphere node in the XML configuration file
			 * And start gathering all child nodes
			 */
			nodes = doc.getElementsByTagName( "text" );
			extractNodeTextContent( nodes.item( 0 ) );
			
			/**
			 * Find the sphere node in the XML configuration file
			 * And start gathering all child nodes
			 */
			nodes = doc.getElementsByTagName( "edges" );
			extractNodeEdgesContent( nodes.item( 0 ) );
			
			/**
			 * Find the sphere node in the XML configuration file
			 * And start gathering all child nodes
			 */
			nodes = doc.getElementsByTagName( "maxNumberOfNodesToDisplay" );
			extractNodeMaxNodesContent( nodes.item( 0 ) );

			/**
			 * Find the sphere node in the XML configuration file
			 * And start gathering all child nodes
			 */
			nodes = doc.getElementsByTagName( "zoomEffectOn" );
			extractNodeZoomContent( nodes.item( 0 ) );
			
			/**
			 * Find the sphere node in the XML configuration file
			 * And start gathering all child nodes
			 */
			nodes = doc.getElementsByTagName( "goldenRatio" );
			extractNodeGoldenRatioContent( nodes.item( 0 ) );
			
			/**
			 * Find the sphere node in the XML configuration file
			 * And start gathering all child nodes
			 */
			nodes = doc.getElementsByTagName( "expansionRate" );
			extractNodeExpansionRateContent( nodes.item( 0 ) );
			
		}
		catch( SAXException e )
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch( ParserConfigurationException e1 )
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		catch( IOException e )
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param node
	 */
	public void extractNodeSphereContent( Node node )
	{
		Element element = ( Element ) node;
		
		/**
		 * Read node sphere/color
		 */
		SPHERE_COLOR = MyColor.convertColor( element.getElementsByTagName( "color" ).item( 0 ).getTextContent( ) );
		
		SPHERE_EVEN_COLOR = MyColor.convertColor( element.getElementsByTagName( "evenNumbersColor" ).item( 0 ).getTextContent( ) );
		
		SPHERE_PRIME_COLOR = MyColor.convertColor( element.getElementsByTagName( "primeNumbersColor" ).item( 0 ).getTextContent( ) );
		
		/**
		 * Read node sphere/evenNumbers
		 */
		SPHERE_EVEN_NUMBERS = Boolean.parseBoolean( element.getElementsByTagName( "evenNumbers" ).item( 0 ).getTextContent( ) );
		
		/**
		 * Read node sphere/primeNumbers
		 */
		SPHERE_PRIME_NUMBERS = Boolean.parseBoolean( element.getElementsByTagName( "primeNumbers" ).item( 0 ).getTextContent( ) );
		
		/**
		 * Read node sphere/primeNumbers
		 */
		SPHERE_SIZE = Float.parseFloat( element.getElementsByTagName( "size" ).item( 0 ).getTextContent( ) );
	}
	
	/**
	 * 
	 * @param node
	 */
	public void extractNodeTextContent( Node node )
	{
		Element element = ( Element ) node;
		
		/**
		 * Read node sphere/color
		 */
		TEXT_ON = Boolean.parseBoolean( element.getElementsByTagName( "textOn" ).item( 0 ).getTextContent( ) );
		
		/**
		 * Read node sphere/evenNumbers
		 */
		TEXT_COLOR = MyColor.convertColor( element.getElementsByTagName( "color" ).item( 0 ).getTextContent( ) );
		
		/**
		 * 
		 */
		TEXT_EVEN_COLOR = MyColor.convertColor( element.getElementsByTagName( "evenNumbersColor" ).item( 0 ).getTextContent( ) );
		
		/**
		 * 
		 */
		TEXT_PRIME_COLOR = MyColor.convertColor( element.getElementsByTagName( "primeNumbersColor" ).item( 0 ).getTextContent( ) );
		
		
		/**
		 * Read node sphere/evenNumbers
		 */
		TEXT_SIZE = Integer.parseInt( element.getElementsByTagName( "size" ).item( 0 ).getTextContent( ) );
	}
	
	/**
	 * 
	 * @param node
	 */
	public void extractNodeEdgesContent( Node node )
	{
		Element element = ( Element ) node;
		
		/**
		 * Read node edges/color
		 */
		EDGE_ON = Boolean.parseBoolean( element.getElementsByTagName( "edgesOn" ).item( 0 ).getTextContent( ) );
		
		/**
		 * Read node edges/evenNumbers
		 */
		EDGE_COLOR = MyColor.convertColor( element.getElementsByTagName( "color" ).item( 0 ).getTextContent( ) );		
	}
	
	/**
	 * 
	 * @param node
	 */
	public void extractNodeMaxNodesContent( Node node )
	{
		Element element = ( Element ) node;
		
		/**
		 * Read node sphere/color
		 */
		TOTAL_NODES = Integer.parseInt( element.getElementsByTagName( "value" ).item( 0 ).getTextContent( ) );
	}
	
	/**
	 * 
	 * @param node
	 */
	public void extractNodeZoomContent( Node node )
	{
		Element element = ( Element ) node;
		
		/**
		 * Read node sphere/color
		 */
		ZOOM_ON = Boolean.parseBoolean( element.getElementsByTagName( "value" ).item( 0 ).getTextContent( ) );
	}
	
	/**
	 * 
	 * @param node
	 */
	public void extractNodeGoldenRatioContent( Node node )
	{
		Element element = ( Element ) node;
		
		/**
		 * Read node sphere/color
		 */
		GOLDEN_RATIO = Float.parseFloat( element.getElementsByTagName( "value" ).item( 0 ).getTextContent( ) );
	}
	
	/**
	 * 
	 * @param node
	 */
	public void extractNodeExpansionRateContent( Node node )
	{
		Element element = ( Element ) node;
		
		/**
		 * Read node sphere/color
		 */
		EXPANSION_RATE = Integer.parseInt( element.getElementsByTagName( "value" ).item( 0 ).getTextContent( ) );
	}
	
	/**
	 * 
	 * @param filePath
	 */
	public void setFilePath( String filePath )
	{
		this.filePath = filePath;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getFilePath( )
	{
		return this.filePath;
	}
	
	
}
