
package tabs;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.util.Random;

import javax.swing.JOptionPane;


public class StartClick implements Runnable {
	
	private int nClick = 0;
	private int interval = 0;
	private Robot robot;
	private AutoClicker autoClicker;
	Random random = new Random();
	
	public StartClick( AutoClicker autoClicker, int nClick, int cinterval ) {
		try {
			robot = new Robot();
			
			this.nClick = nClick;
			this.interval = setInterval(cinterval);
			
			this.autoClicker = autoClicker;
		} catch( Exception e ) {
			e.printStackTrace();
		}
	}
	//returns the interval
	private int setInterval(int cinterval){
		return (int) (cinterval * .9);
		
	}
	
	//calculates random value;
	private int getRandomInterval(){
		int x = this.interval;
		int differential = this.random.nextInt(this.interval/5);
		x = x + differential;
		return x;
	}

	@Override
	public void run() {
		try {
			autoClicker.setProgressState( false );
			Thread.sleep( 3000 );

			for( int i=0; i<nClick; i++ ) {
				int r = getRandomInterval();
				robot.mousePress( InputEvent.BUTTON1_MASK );
				robot.mouseRelease( InputEvent.BUTTON1_MASK );
				Thread.sleep( r );
				System.out.println("Interval(milliseconds): " + r);
			}
			
			autoClicker.setProgressState( true );
			JOptionPane.showMessageDialog( null, "Done." );
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}