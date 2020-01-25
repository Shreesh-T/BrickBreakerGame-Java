
package brickBreaker;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.TimeUnit;

import javax.swing.Timer;

import javax.swing.JPanel;


public class Gameplay extends JPanel implements KeyListener, ActionListener
 {
	private static final long serialVersionUID = -1175726918742874762L;
	private boolean play = false; 
    private int score = 0;
    private int TotalBricks = 10;
    private Timer time;
    private int delay = 6;
    private int playerX=210;
    private int ballposX = 120;
    private int ballposY = 350;
    private int balldirX = -1;
    private int balldirY=-2;
    private MapGenerator map;
    private Data data;
    private int level=1;
    private int i2=2, j2=5;
    private int life=2;
    private boolean k=false;
    public Gameplay()
    {
    	map= new MapGenerator(i2, j2);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false); 
        time = new Timer(delay, this);
        time.start();
    }
    
    
    public void paint(Graphics g)
    {
        g.setColor(Color.black);
        g.fillRect(1, 1, 692, 592);
        map.draw((Graphics2D)g);
        
        g.setColor(Color.blue);
        g.fillRect(0,0,3,592);
        g.fillRect(0,0,692,3);
        g.fillRect(691,0,3,592);
        
        
        if(life==2)
        {
        	g.setColor(Color.red);
        	g.fillArc(260, 10, 14, 14, 0, 180);
            g.fillArc(274, 10, 14, 14, 0, 180);
            g.fillPolygon(new int[] {260, 274, 288}, new int[] {17, 29, 17}, 3);
        	g.setColor(Color.red);
            g.fillArc(291, 10, 14, 14, 0, 180);
            g.fillArc(305, 10, 14, 14, 0, 180);
            g.fillPolygon(new int[] {291, 305, 319}, new int[] {17, 29, 17}, 3);
            g.fillArc(322, 10, 14, 14, 0, 180);
            g.fillArc(336, 10, 14, 14, 0, 180);
            g.fillPolygon(new int[] {322, 336, 350}, new int[] {17, 29, 17}, 3);
        }
        if(life==1)
        {
        	g.setColor(Color.red);
        	g.fillArc(260, 10, 14, 14, 0, 180);
            g.fillArc(274, 10, 14, 14, 0, 180);
            g.fillPolygon(new int[] {260, 274, 288}, new int[] {17, 29, 17}, 3);
        	
            g.fillArc(291, 10, 14, 14, 0, 180);
            g.fillArc(305, 10, 14, 14, 0, 180);
            g.fillPolygon(new int[] {291, 305, 319}, new int[] {17, 29, 17}, 3);
            
          
        }
        if(life==0)
        {
        	g.setColor(Color.red);
        	g.fillArc(260, 10, 14, 14, 0, 180);
            g.fillArc(274, 10, 14, 14, 0, 180);
            g.fillPolygon(new int[] {260, 274, 288}, new int[] {17, 29, 17}, 3);
        }
        
        g.setColor(Color.white);
        g.setFont(new Font("serif", Font.BOLD, 20));
        g.drawString(""+score,590, 30);
        g.setColor(Color.white);
        g.setFont(new Font("serif", Font.BOLD, 20));
        g.drawString("Level "+level,3, 30);
        g.setColor((Color.green));
        g.fillRect(playerX,550,100,8);
        
        g.setColor((Color.yellow));
        g.fillOval(ballposX,ballposY,20,20);
        
        //For Next Level
        if(TotalBricks<=0)
        {
        	if(level<14)
        	{
	        	play=true;
	        	balldirX=0;
	        	balldirY=0;
	        	g.setColor(Color.red);
	        	g.setFont(new Font("serif", Font.BOLD, 30));
	            g.drawString("LEVEL "+level+" Cleared   Score: "+score,260, 300);
	            g.setFont(new Font("serif", Font.BOLD, 20));
	            g.drawString("Press enter to continue",290, 350);	
	            k=true;
        	}
        	else
        	{
        		play=false;
	        	balldirX=0;
	        	balldirY=0;
	        	g.setColor(Color.red);
	        	g.setFont(new Font("serif", Font.BOLD, 30));
	            g.drawString("YOU WON!  Score: "+score,260, 300);
	            g.setFont(new Font("serif", Font.BOLD, 20));
	            g.drawString("Press enter to play again",290, 350);
	            k=true;
        	}
        }
        
        //For Game End
        if(ballposY> 570)
        {
        	if(life==0)
        	{
	        	play=false;
	        	balldirX=0;
	        	balldirY=0;
	        	
	        	
	        	g.setColor(Color.red);
	        	g.setFont(new Font("serif", Font.BOLD, 30));
	            g.drawString("GAME OVER   scores: "+score,190, 300);
	            g.setFont(new Font("serif", Font.BOLD, 20));
	            g.drawString("Press enter to restart",190, 350);
	            k=true;
	            time.stop();
	            data=new Data();
	            Input in = new Input();
	            in.button();
	            data.x = in.name;
	            data.score=score;
	        	data.call();
        	}
        	else
        	{
        		
        		life--;
				
	            try 
	            {
	            	TimeUnit.SECONDS.sleep(2);
					
				} 
	            catch (InterruptedException e) {
									
				}
	            if(level==1)
	            	score=0;
	            else
	            	score=5*(i2-1)*5;
        		ballposX=120;
        		ballposY=350;
        		balldirX= -1;
        		balldirY =-2;
        		playerX=310;
        		TotalBricks=i2*j2;
        		map= new MapGenerator(i2,j2);
        		k=false;
        	}
        }
        g.dispose();
    }
    
   
    
    
    public void actionPerformed(ActionEvent ae) {
    	time.start();
    	if(play)
    	{	
    		if(new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(playerX, 550, 100, 8)))
    			balldirY = -balldirY;
    		
    		A: for(int i = 0; i<map.map.length; i++)
    		{
    			for(int j = 0; j<map.map[0].length; j++)
    			{
    				k=false;
    				if(map.map[i][j]>0)
    				{
    					int brickX = j*map.brickwidth+80;
    					int brickY = i*map.brickheight +50;
    					int brickwidth = map.brickwidth;
    					int brickheight = map.brickheight;
    					Rectangle rect = new Rectangle(brickX, brickY, brickwidth, brickheight);
    					Rectangle ballRect = new Rectangle(ballposX, ballposY, 20,20);
    					Rectangle brickRect = rect;
    					if(ballRect.intersects(brickRect))
    					{
    						map.setBrickValue(0, i, j);
    						TotalBricks--;
    						score +=5;
    						if(ballposX+19<= brickRect.x || ballposX+1>= brickRect.x +brickRect.width)
    						{
    							balldirX= -balldirX;
    							
    						}
    						else
    						{
    							balldirY=-balldirY;
    						}
    						break A;
    					}
    				}
    			}
    		}
    		
    		k=false;
    		ballposX += balldirX;
    		ballposY += balldirY;
    		if(ballposX < 0)
    			balldirX = -balldirX;
    		if(ballposY < 0)
    			balldirY = -balldirY;
    		if(ballposX > 670)
    			balldirX = -balldirX;
    	}
    	repaint();
    }

    @Override
    public void keyTyped(KeyEvent ke) {}
 
    @Override
    public void keyReleased(KeyEvent ke) {}


    
    @Override
    public void keyPressed(KeyEvent ke) {
        if(ke.getKeyCode() == KeyEvent.VK_RIGHT)
        {
            if(playerX>= 600)
                playerX=600;
            else
                moveRight();
                   
        }
        if(ke.getKeyCode() == KeyEvent.VK_LEFT)
        {
            if(playerX < 10)
                playerX=10;
            else
                moveLeft();
        }
        if(ke.getKeyCode()== KeyEvent.VK_ENTER && k)
        {
        	//restart
        	if(!play)
        	{
        		life=2;
        		i2=2;
        		j2=5;
        		level=1;
        		time.start();
        		play=true;
        		ballposX=120;
        		ballposY=350;
        		balldirX= -1;
        		balldirY =-2;
        		playerX = 310;
        		score = 0;
        		TotalBricks=i2*j2;
        		map= new MapGenerator(i2,j2);
        		
        		
        	}
        	//next level
        	else
        	{
        		i2++;
        		
        		//delay -= 3;
        		//time = new Timer(delay, this);
        		time.start();
        		play=true;
        		ballposX=120;
        		ballposY=350;
        		balldirX= -1;
        		balldirY =-2;
        		playerX=310;
        		level++;
        		
        		TotalBricks = i2*j2;
        		map= new MapGenerator(i2,j2);
        		
        	}
        }
    }

    public void moveRight()
    {
        play = true;
        playerX+=20;
    }
    public void moveLeft()
    {
        play = true;
        playerX-=20;
    }
    
   
   
}
