import java.awt.*;
import java.awt.geom.*;
import java.util.*;
import java.awt.image.BufferedImage;

public class Canvas extends TimerTask {

  static Timer t;
  //The window in which everything is shown.
  private BufferedImageDrawer buffid_;

  //The background image
  private BufferedImage bg;
  int height_;
  int width_;
  int borderSpace_;

  public static Snake snake = new Snake(500, 700);
//    public static Snake snake = new Snake(600, 600);

  /**
   * Constructor
   *
   * @param bid The buffered image to be drawn
   * @param backGround The background (buffered) image
   * @param height Width of the window
   * @param delay Defines after how many milliseconds the image/frame is is
   * updated (needed for the synchronisation of the clock).
   */
  Canvas(BufferedImageDrawer bid, BufferedImage backGround, int height,int width, int delay) {
      height_ = height;
      width_ = width;
      buffid_ = bid;
      buffid_.addKeyListener(bid);
      //The lines should have a thickness of 3.0 instead of 1.0.
      buffid_.g2dbi.setStroke(new BasicStroke(3.0f));

      // the space between the begin windown and the border
      borderSpace_ = 20;

      //Use of antialiasing to have nicer lines.
      buffid_.g2dbi.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

      /*yUp defines a translation allowing the specification of objects in "real"
       coordinates where the y-axis points upwards and the origin of the coordinate
       system is located in the lower left corner of the window.
       */
      AffineTransform yUp = new AffineTransform();
      yUp.setToScale(1, -1);
      AffineTransform translate = new AffineTransform();
      translate.setToTranslation(0, height);
      yUp.preConcatenate(translate);

      //Apply the transformation to the Graphics2D object to draw everything
      //in "real" coordinates.
      buffid_.g2dbi.transform(yUp);

      //buffid_.g2dbi.setPaint(Color.black);
      buffid_.g2dbi.setPaint(Color.green);
      bg = backGround;

  }

  //This method is called in regular intervals. This method computes
  //the updated image/frame and calls the repaint method to draw the
  //updated image on the window
  @Override
  public void run() {
      snake.ableChangeDirection();

      //Draw the background.
      buffid_.g2dbi.drawImage(bg, 0, 0, null);

      //TODO
      //Fazer disso parte do cenário de fundo, para não precisar ser redesenhado a todo momento.
      buffid_.g2dbi.setPaint(Color.white);
      buffid_.g2dbi.setStroke(new BasicStroke(3.0f));
      Rectangle2D.Double field = new Rectangle2D.Double(borderSpace_, borderSpace_, width_ - 2 * borderSpace_, height_ - (2 * borderSpace_ + 20));
      buffid_.g2dbi.draw(field);

      buffid_.g2dbi.setStroke(new BasicStroke(3.0f));
      buffid_.g2dbi.setPaint(Color.green);

      for (int i = 0; i < snake.getSize(); i++) {
          Rectangle2D.Double quad = new Rectangle2D.Double(snake.getPoint(i).getX(), snake.getPoint(i).getY(),4,4);
          buffid_.g2dbi.draw(quad);
      }

      snake.move();
      buffid_.repaint();

      // if snake isn't alive stop the threads
      if (snake.isAlive(borderSpace_) == false){
          t.cancel();
      }

  }
  public static void main(String[] argv) {

      //Width of the window
      int width = 500;
      //Height of the window
      int height = 700;

      //Specifies (in milliseconds) when the frame should be updated.
      int delay = 400;

      //The BufferedImage to be drawn in the window.
      BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

      //The background.
      BufferedImage backGround = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
      Graphics2D g2dBackGround = backGround.createGraphics();

      //The lines should have a thickness of 3.0 instead of 1.0.
      g2dBackGround.setStroke(new BasicStroke(7.0f));

      //The background is painted white first.
      g2dBackGround.setPaint(Color.black);
      g2dBackGround.fill(new Rectangle(0, 0, width, height));

      g2dBackGround.setPaint(Color.green);

      //The window in which everything is drawn.
      BufferedImageDrawer bid = new BufferedImageDrawer(bi, width, height);

      //The TimerTask in which the repeated computations drawing take place.
      Canvas dbce = new Canvas(bid, backGround, height, width, delay);

      t = new Timer();
      t.scheduleAtFixedRate(dbce, 0, delay);


  }

}
