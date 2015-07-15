import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.*;

/**
* A class for drawing a BufferedImage object. It can be used in 
* connection with double buffering.
*
* @author Frank Klawonn
* Last change 27.05.2005
*/
public class BufferedImageDrawer extends Frame implements KeyListener
{
  //These image is drawn when the paint method is called.
  public BufferedImage bi;

  //This Graphics2D object can be used to draw on bi.
  public Graphics2D g2dbi;

  //The Graphics2D object used in the paint method.
  private Graphics2D g2d;

 public BufferedImageDrawer(BufferedImage buffIm, int width, int height)
  {
    addWindowListener(new MyFinishWindow());
    
    bi = buffIm;
    g2dbi = bi.createGraphics();
    this.setTitle("Snake Game");
    this.setSize(width,height);
    this.setVisible(true);
    this.setFocusable(true);
    
  }

  @Override
  public void paint(Graphics g)
  {
    update(g);
  }


  @Override
  public void update(Graphics g)
  {
    g2d = (Graphics2D) g;
    g2d.drawImage(bi,0,0,null);
  }

    @Override
    public void keyTyped(KeyEvent e) {
    
    }

    @Override
    public void keyPressed(KeyEvent e) {
    
    }

    @Override
    public void keyReleased(KeyEvent e) {
       Snake.changeDirection(e);
       
    }


}


