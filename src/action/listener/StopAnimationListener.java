package action.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class StopAnimationListener implements ActionListener
{

	Timer timer;
	
	/**
	 * 
	 * @param timer
	 */
	public StopAnimationListener( Timer timer )
	{
		this.timer = timer;
	}

	@Override
	public void actionPerformed( ActionEvent e )
	{
		// TODO Auto-generated method stub
		getTimer( ).stop( );
	}

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
	 * @param timer
	 */
	public void setTimer( Timer timer )
	{
		this.timer = timer;
	}
}
