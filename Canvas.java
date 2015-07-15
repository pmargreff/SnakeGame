import java.awt.*;
import java.awt.geom.*;
import java.util.*;
import java.awt.image.BufferedImage;

public class Canvas extends TimerTask {

    static Timer t;
    //The window in which everything is shown.
    private BufferedImageDrawer buffid;

    //The background image
    private BufferedImage bg;

    public static Snake snake = new Snake(600, 600);
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
    Canvas(BufferedImageDrawer bid, BufferedImage backGround,
            int height, int delay) {
        buffid = bid;
        buffid.addKeyListener(bid);
        //The lines should have a thickness of 3.0 instead of 1.0.
        buffid.g2dbi.setStroke(new BasicStroke(3.0f));

        
        //Use of antialiasing to have nicer lines.
        buffid.g2dbi.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

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
        buffid.g2dbi.transform(yUp);

//        buffid.g2dbi.setPaint(Color.black);
        buffid.g2dbi.setPaint(Color.green);
        bg = backGround;
        
    }

    //This method is called in regular intervals. This method computes
    //the updated image/frame and calls the repaint method to draw the
    //updated image on the window
    @Override
    public void run() {
        snake.ableChangeDirection();

        //Draw the background.
        buffid.g2dbi.drawImage(bg, 0, 0, null);

        for (int i = 0; i < snake.getSize(); i++) {
            Rectangle2D.Double quad = new Rectangle2D.Double(snake.getPoint(i).getX(), snake.getPoint(i).getY(),4,4);
            buffid.g2dbi.draw(quad);
        }

        snake.move();
        buffid.repaint();
        if (snake.isAlive() == false){
            t.cancel();
        }
        
    }

    public static void main(String[] argv) {

        //Width of the window
        int width = 600;
        //Height of the window
        int height = 600;

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
        Canvas dbce = new Canvas(bid,
                backGround,
                height,
                delay);

        t = new Timer();
        t.scheduleAtFixedRate(dbce, 0, delay);
        

    }

}
