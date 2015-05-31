
import java.awt.Point;
import java.util.ArrayList;

/**
 * @version 0.1
 * @author pmargreff
 */
public class Snake {
    private final int width_, height_;
    private final int cellSize_; //tamanho de cada unidade que compõe a Snake
    ArrayList<Point> body_;

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

        this.body_ = new ArrayList<>();

        for (int i = 0; i < 11; i++) {
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
}
