import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

/**
 * @version 0.1
 * @author pmargreff
 */
public class Snake implements KeyListener {

    private final int width_, height_;
    private final int cellSize_; //tamanho de cada unidade que compõe a Snake
    private ArrayList<Point> body_;
    private int direction_;

    /**
     * Snake é sempre construída no meio da tela
     *
     * @param width - largura
     * @param height - altura
     */
    Snake(int width, int height) {

        if (width < 500) {
            this.width_ = 500;
        } else {
            this.width_ = width;
        }

        if (height < 500) {
            this.height_ = 500;
        } else {
            this.height_ = height;
        }

        this.cellSize_ = 5;
        this.direction_ = 39;
        this.body_ = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Point tmp = new Point();
            tmp.setLocation((width / 2) - i * cellSize_, (height / 2));

            body_.add(i, tmp);

        }
    }

    /**
     * Confere se a snake não se movimentou através dos limites do tamanha da
     * janela e a partir disto atribui como viva ou morta
     *
     * @return live - false para morta, true para viva
     */
    public boolean isAlive() {
        boolean live = true;
        Point tmp = new Point(body_.get(1));

        if (tmp.getX() >= this.width_) {
            live = false;
        }

        if ((tmp.getY() >= this.height_)) {
            live = false;
        }

        if (tmp.getX() <= this.cellSize_) {
            live = false;
        }

        if ((tmp.getY() < this.cellSize_)) {
            live = false;
        }

        return live;
    }

    public void move() {

        for (int i = body_.size(); i > 0; i--) {
            body_.get(i - 1);

        }

        Point tmp = new Point(body_.get(0));

        if (direction_ == 37) {         //left
            tmp.setLocation(tmp.getX(), tmp.getY() - cellSize_);
        } else if (direction_ == 38) {  //up
            tmp.setLocation(tmp.getX() + cellSize_, tmp.getY());
        } else if (direction_ == 39) {  //rigth
            tmp.setLocation(tmp.getX(), tmp.getY() + cellSize_);
        } else if (direction_ == 40) {  //down
            tmp.setLocation(tmp.getX() - cellSize_, tmp.getY());
        }
        body_.set(0, tmp);
    }

    public int getSize() {
        return body_.size();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (((e.getKeyCode() == 37) || (e.getKeyCode() == 39)) && ((direction_ == 38) || (direction_ == 40))) {

            direction_ = e.getKeyCode();

        }

        if (((e.getKeyCode() == 38) || (e.getKeyCode() == 40)) && ((direction_ == 37) || (direction_ == 39))) {

            direction_ = e.getKeyCode();

        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //não utilizado
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //não utilizado 
    }
}
