import javax.swing.*;
import java.awt.*;

public class Draw extends JPanel {
    public int FIELD_X_SIZE = 51;
    public int FIELD_Y_SIZE = 23;
    public int bubblecolor[][] = new int[FIELD_Y_SIZE][FIELD_X_SIZE];

    public int FIELD_COLOR = 0x000000; //eeeeee
    //public int BORDER_COLOR = 0x000000; //000000
    public int BODY_COLOR = 0x888888; //ff0000
    public int APFEL_COLOR = 0xffffff; //00ff00

    public int SNAKE_SIZE;
    public int MAX_SNAKE_SIZE = 1000;
    public int SnakeBody[][] = new int[2][MAX_SNAKE_SIZE];

    public int x, y, z, zed;

    public int vpravo = 0;
    public int vniz = 1;
    public int vlevo = 2;
    public int vverh = 3;
    public int kuda;

    public void paintComponent(Graphics g) {
 //       z = 0;
        super.paintComponent(g);

//vivodim pole
        for (y = 0; y < FIELD_Y_SIZE; y++)
            for (x = 0; x < FIELD_X_SIZE; x++) {
                g.setColor(new Color(bubblecolor[y][x]));
                g.fillRect(1+x * 16, 1 + y * 16, 15, 15);
//                z++;
            }
    }

    public void init() {
        SNAKE_SIZE = 8;
        kuda = vpravo;
//zapolnyaem pole
        for (y = 0; y < FIELD_Y_SIZE; y++) {
            for (x = 0; x < FIELD_X_SIZE; x++) {
//                if ((x == 0) | (y == 0) | (x == FIELD_X_SIZE - 1) | (y == FIELD_Y_SIZE - 1)) {
//                    bubblecolor[y][x] = BORDER_COLOR;
//                } else {
                    bubblecolor[y][x] = FIELD_COLOR;
//                }
            }
        }

//risuem zmejku
        for (z = 0; z < SNAKE_SIZE; z++) {
            bubblecolor[SnakeBody[1][z] = (FIELD_Y_SIZE / 2)][SnakeBody[0][z] = (z + 5)] = BODY_COLOR;
        }
        putapfel();
    }

    void dvizhenie() {

        SnakeBody[0][SNAKE_SIZE] = SnakeBody[0][SNAKE_SIZE - 1];
        SnakeBody[1][SNAKE_SIZE] = SnakeBody[1][SNAKE_SIZE - 1];
        if (kuda == vpravo) {SnakeBody[0][SNAKE_SIZE] = SnakeBody[0][SNAKE_SIZE] + 1;}
        if (kuda == vlevo)  {SnakeBody[0][SNAKE_SIZE] = SnakeBody[0][SNAKE_SIZE] - 1;}
        if (kuda == vverh)  {SnakeBody[1][SNAKE_SIZE] = SnakeBody[1][SNAKE_SIZE] - 1;}
        if (kuda == vniz)   {SnakeBody[1][SNAKE_SIZE] = SnakeBody[1][SNAKE_SIZE] + 1;}

//        bubblecolor[SnakeBody[1][SNAKE_SIZE - 1]][SnakeBody[0][SNAKE_SIZE - 1]] = BODY_COLOR;
        if ((SnakeBody[0][SNAKE_SIZE] < 0)|(SnakeBody[1][SNAKE_SIZE] < 0)|(SnakeBody[0][SNAKE_SIZE] == FIELD_X_SIZE)|(SnakeBody[1][SNAKE_SIZE] == FIELD_Y_SIZE)){
            init();
        }
        else if (headcolor() == FIELD_COLOR) {
            bubblecolor[SnakeBody[1][0]][SnakeBody[0][0]] = FIELD_COLOR;
        for (zed = 0; zed < SNAKE_SIZE; zed++) {
            SnakeBody[0][zed] = SnakeBody[0][zed + 1];
            SnakeBody[1][zed] = SnakeBody[1][zed + 1];
        }

            setheadcolor(BODY_COLOR);
        }
        else if (headcolor() == APFEL_COLOR) {
            setheadcolor(BODY_COLOR);
            SNAKE_SIZE++;
            putapfel();
        }
        else {init();}
    }

    int headcolor(){
        return bubblecolor[SnakeBody[1][SNAKE_SIZE]][SnakeBody[0][SNAKE_SIZE]];
    }

    void setheadcolor(int color){
        bubblecolor[SnakeBody[1][SNAKE_SIZE]][SnakeBody[0][SNAKE_SIZE]] = color;
    }


    void putapfel(){
//         bubblecolor[z] = (int) (Math.random()*0xffffff);
        x = 0;
        y = 0;
            do {
                x = (int) (Math.random()*FIELD_X_SIZE-1);
                y = (int) (Math.random()*FIELD_Y_SIZE-1);
            }
            while (bubblecolor[y][x]!=FIELD_COLOR);

        bubblecolor[y][x] = APFEL_COLOR;
    }

    public void to_vverh() {
        if (kuda != vniz) {
            kuda = vverh;
        }
    }

    public void to_vniz() {
        if (kuda != vverh) {
            kuda = vniz;
        }
    }

    public void to_vlevo() {
        if (kuda != vpravo) {
            kuda = vlevo;
        }
    }

    public void to_vpravo() {
        if (kuda != vlevo) {
            kuda = vpravo;
        }
    }
}