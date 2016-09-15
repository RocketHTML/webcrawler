package Ringz.doll;
import java.awt.*;
import javax.swing.*;

////// /////////// ////////////// ////////////////// ////
///// This class is irrelevant to the current lesson! ////////
////////////////////////////////////////////////////////
/// //////////////////// ////////////// /////////// ////////////
public class TV extends JFrame{
	private Thread brain;
	private boolean on = false;


	private Clock clock;
	

	private Doll dolly;
	private JTextField nametag;

	////////////////////////////
	////// Constructor	//////
	////////////////////////////
	public TV(Doll loveMe){
		dolly = loveMe;
		nametag = new JTextField(dolly.name());
		nametag.setEditable(false);
		setVisible(false);
		clock = new Clock(loveMe);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new FlowLayout());
		getContentPane().add(nametag);
		getContentPane().add(clock);
		

		brain = new Thread(new Runnable(){
			public void run(){
				while(true){
					try{Thread.sleep(1000);}
					catch(Exception x){}
					clock.bumpTime();
				}
			}
		});

		Dimension screenSize, frameSize;
		int x,y;
		screenSize=Toolkit.getDefaultToolkit().getScreenSize();
		frameSize=getSize();
		x=(screenSize.width-frameSize.width)/ ((dolly.name().length()+1)*3);
		y=(screenSize.height-frameSize.height)/ (dolly.limbs()*3+1);
		setLocation(x,y);

	}
	////////////////////////////
	////////////////////////////


	///////////////////////////////////////////
	/////////////  Single Method  /////////////
	//////// Turn Me On/Off  //////////////////
	public void powerSwitch(){
		on = !on;
		this.setSize(new Dimension(400,400));
		setVisible(on);
		brain.start();

	}
	///////////////////////////////////////////
	///////////////////////////////////////////



	///////////////////////////////////////
	//////// Nested Clock Class Start ///////
	///////////////////////////////////////
	class Clock extends JPanel{
		int hourTime = 0;
		int minuteTime = 0;

		public Clock(Doll loveMe){
			this.setPreferredSize(new Dimension(400,400));
		}

		public void bumpTime(){
			if (dolly.battery() < 1)
				return;
			minuteTime++;
			if (minuteTime == 60){minuteTime=0; hourTime++;}
			if (hourTime == 12)hourTime = 0;
			repaint();
		}

		public void paint(Graphics g){
			Color invis = new Color(200+(dolly.name().length()*5), 200, 200, 100);
			//super.paintComponent(g);
	        this.setBackground(invis);
	        nametag.setBackground(invis);
			drawHand(g, hourTime, 70, 12, 0.2);
			drawHand(g, minuteTime, 100, 60, 0.1);
			g.drawOval(0,0,300,300);
		}

		private void drawHand(Graphics g, double hour, int length, int max, double width) {

		double hourAngle = hour/max*2.0*Math.PI- Math.PI/2;
		int[] hourHandX = new int[3];
		int[] hourHandY = new int[3];

		hourHandX[0] = 150;
		hourHandY[0] = 150;

		hourHandX[1] = (int) (150 + length*Math.cos(hourAngle+width));
		hourHandY[1] = (int) (150 + length*Math.sin(hourAngle+width));

		hourHandX[2] = (int) (150 + length*Math.cos(hourAngle-width));
		hourHandY[2] = (int) (150 + length*Math.sin(hourAngle-width));

		g.fillPolygon(hourHandX,hourHandY,3);
		
		}

	}
	/////////////////////////////////////////////////////
	/////////// Nested Clock Class End ///////////////////
	/////////////////////////////////////////////////////




}