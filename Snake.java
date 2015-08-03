import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * @version 0.8
 * @author pmargreff
 */
public class Snake{

    private static boolean directionFlag_;
    private final int width_, height_;
    private final int cellSize_; //tamanho de cada unidade que compõe a Snake
    private ArrayList<Point> body_;
    private static int direction_;

    /**
     * Snake é sempre construída no meio da tela
     * e inicia se movimentando para a direita
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

        this.cellSize_ = 8;
        this.direction_ = 39;
        this.body_ = new ArrayList<>();
        this.directionFlag_ = false;

        for (int i = 0; i < 10; i++) {
            Point tmp = new Point();
            tmp.setLocation((width / 2) - i * cellSize_, (height / 2));


            body_.add(i, tmp);
//            System.out.println(body_.get(i).getX() +" "+ body_.get(i).getY());

        }
    }

    /**
     * Confere se a snake não se movimentou através dos limites do tamanho da
     * janela e se não tenteu colidir com o próprio corpo e
     * partir  atribui como viva ou morta
     * @return live - false para morta, true para viva
     */
    public boolean isAlive(int borderSpace) {
        boolean live = true;
        Point tmp = new Point(body_.get(1));

        if (tmp.getX() >= this.width_ - (borderSpace + 10)) {
            return false;
        }

        if (tmp.getY() >= this.height_ - (2 * borderSpace + 10)) {
            return false;
        }

        if (tmp.getX() <= 7 + borderSpace) {
            return false;
        }

        if (tmp.getY() <= 7 + borderSpace) {
            return false;
        }

        for (int i = 1; i < getSize(); i++){
            if (body_.get(0).equals(body_.get(i))){
                return false;
            }
        }

        return true;
    }

    /**
     * Move snake in the current direction
     */
    public void move() {

        for (int i = body_.size() - 1; i > 0; i--) {
            Point tmp = new Point(body_.get(i - 1));
            body_.set(i, tmp);

        }

        Point tmp = new Point(body_.get(0));

        if (direction_ == 37) { //move down
            tmp.setLocation(tmp.getX() - cellSize_, tmp.getY());
        } else if (direction_ == 38) { //move right
            tmp.setLocation(tmp.getX() , tmp.getY() + cellSize_);
        } else if (direction_ == 39) { //move left
            tmp.setLocation(tmp.getX() + cellSize_, tmp.getY());
        } else if (direction_ == 40) { //move up
            tmp.setLocation(tmp.getX() , tmp.getY()- cellSize_);
        }
        body_.set(0, tmp);
    }

    public int getSize() {
        return body_.size();
    }

    public Point getPoint(int i){
        return body_.get(i);

    }

    public Point getFirst(){
        return body_.get(0);
    }

    /**
     * Static method that test if the changing to new direction is valid
     * @param e KeyEvent get from keyboard
     */
    public static void changeDirection(KeyEvent e){
        System.out.print(directionFlag_ + " ");
        if (((direction_ ==  37) || (direction_ ==  39)) && (directionFlag_ == true)){
            if ((e.getKeyCode() ==  38) || (e.getKeyCode() ==  40)){
                direction_ = e.getKeyCode();
                directionFlag_ = false;
            }

        } else if (((direction_ ==  38) || (direction_ ==  40) && (directionFlag_ == true))){
            if ((e.getKeyCode() ==  37) || (e.getKeyCode() ==  39)){
                direction_ = e.getKeyCode();
                directionFlag_ = false;
            }
        }

    }

    public void ableChangeDirection(){
        directionFlag_ = true;
    }


}
