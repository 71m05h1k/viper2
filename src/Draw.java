import javax.swing.*;
import java.awt.*;

public class Draw extends JPanel {
    public int FIELD_X_SIZE = 51;
    public int FIELD_Y_SIZE = 23;
    public int SNAKE_FIELD[][] = new int[FIELD_Y_SIZE][FIELD_X_SIZE];

    public int RECT_MARGIN = 1;
    public int RECT_X_SIZE = 15;
    public int RECT_Y_SIZE = 15;
    public int GRID_TICKNESS = 1;

    public int FIELD_COLOR = 0x000000; //eeeeee

    public int APFEL_COLOR = 0xffffff; //00ff00

    public int CURRENT_SNAKE_SIZE;
    public int SNAKE_BODY[][] = new int[2][FIELD_X_SIZE * FIELD_Y_SIZE];

    public int SNAKE_BODY_COLOR = 0x888888; //ff0000

    public int x, y; //, z;

    public int direct_right = 0;
    public int direct_down = 1;
    public int direct_left = 2;
    public int direct_up = 3;
    public int SNAKE_DIRECT;

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

//vivodim pole
        for (y = 0; y < FIELD_Y_SIZE; y++)
            for (x = 0; x < FIELD_X_SIZE; x++) {
                g.setColor(new Color(SNAKE_FIELD[y][x]));
                g.fillRect(RECT_MARGIN + x * (RECT_X_SIZE + GRID_TICKNESS), RECT_MARGIN + y * (RECT_Y_SIZE + GRID_TICKNESS), RECT_X_SIZE, RECT_Y_SIZE);
            }
    }

    public void init() {
        CURRENT_SNAKE_SIZE = 8;
        SNAKE_DIRECT = direct_right;
//zapolnyaem pole
        for (y = 0; y < FIELD_Y_SIZE; y++) {
            for (x = 0; x < FIELD_X_SIZE; x++) {
                SNAKE_FIELD[y][x] = FIELD_COLOR;
            }
        }

//risuem zmejku
        for (int loc_counter = 0; loc_counter < CURRENT_SNAKE_SIZE; loc_counter++) {
            SNAKE_FIELD[SNAKE_BODY[1][loc_counter] = (FIELD_Y_SIZE / 2)][SNAKE_BODY[0][loc_counter] = (loc_counter + 5)] = SNAKE_BODY_COLOR;
        }
        putapfel();
    }

    void dvizhenie() {

        SNAKE_BODY[0][CURRENT_SNAKE_SIZE] = SNAKE_BODY[0][CURRENT_SNAKE_SIZE - 1];
        SNAKE_BODY[1][CURRENT_SNAKE_SIZE] = SNAKE_BODY[1][CURRENT_SNAKE_SIZE - 1];
        if (SNAKE_DIRECT == direct_right) {
            SNAKE_BODY[0][CURRENT_SNAKE_SIZE]++;
        }
        if (SNAKE_DIRECT == direct_left) {
            SNAKE_BODY[0][CURRENT_SNAKE_SIZE]--;
        }
        if (SNAKE_DIRECT == direct_up) {
            SNAKE_BODY[1][CURRENT_SNAKE_SIZE]--;
        }
        if (SNAKE_DIRECT == direct_down) {
            SNAKE_BODY[1][CURRENT_SNAKE_SIZE]++;
        }

        if ((SNAKE_BODY[0][CURRENT_SNAKE_SIZE] < 0) || (SNAKE_BODY[1][CURRENT_SNAKE_SIZE] < 0) || (SNAKE_BODY[0][CURRENT_SNAKE_SIZE] == FIELD_X_SIZE) || (SNAKE_BODY[1][CURRENT_SNAKE_SIZE] == FIELD_Y_SIZE)) {
            init();
        } else if (headcolor() == FIELD_COLOR) {

            SNAKE_FIELD[SNAKE_BODY[1][0]][SNAKE_BODY[0][0]] = FIELD_COLOR;
            for (int loc_counter= 0; loc_counter < CURRENT_SNAKE_SIZE; loc_counter++) {
                SNAKE_BODY[0][loc_counter] = SNAKE_BODY[0][loc_counter + 1];
                SNAKE_BODY[1][loc_counter] = SNAKE_BODY[1][loc_counter + 1];
            }
            setheadcolor(SNAKE_BODY_COLOR);
        } else if (headcolor() == APFEL_COLOR) {
            setheadcolor(SNAKE_BODY_COLOR);
            CURRENT_SNAKE_SIZE++;
            putapfel();
        } else {
            init();
        }
    }

    int headcolor() {
        return SNAKE_FIELD[SNAKE_BODY[1][CURRENT_SNAKE_SIZE]][SNAKE_BODY[0][CURRENT_SNAKE_SIZE]];
    }

    void setheadcolor(int color) {
        SNAKE_FIELD[SNAKE_BODY[1][CURRENT_SNAKE_SIZE]][SNAKE_BODY[0][CURRENT_SNAKE_SIZE]] = color;
    }

    void putapfel() {
        int loc_x, loc_y;
        do {
            loc_x = (int) (Math.random() * FIELD_X_SIZE - 1);
            loc_y = (int) (Math.random() * FIELD_Y_SIZE - 1);
        }
        while (SNAKE_FIELD[loc_y][loc_x] != FIELD_COLOR);

        SNAKE_FIELD[loc_y][loc_x] = APFEL_COLOR;
    }

    public void changesnakedirect(int newdirect, int incorrectdirect) {
        if (SNAKE_DIRECT != incorrectdirect) {
            SNAKE_DIRECT = newdirect;
        }
    }
}