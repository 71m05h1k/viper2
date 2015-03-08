import javax.swing.*;
import java.awt.*;

public class Draw extends JPanel {

    public int FIELD_X_SIZE = 51;
    public int FIELD_Y_SIZE = 23;
    //public byte SNAKE_FIELD[][] = new byte[FIELD_Y_SIZE][FIELD_X_SIZE];
    public byte SNAKE_FIELD[][] =
            {
                    {'-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-'},
                    {'-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-'},
                    {'-', '-', '-', '0', '1', '2', '3', '2', '1', '0', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '0', '1', '2', '3', '2', '1', '0', '-', '-', '-'},
                    {'-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '0', '1', '2', '3', '3', '2', '1', '0', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '0', '1', '2', '3', '3', '2', '1', '0', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-'},
                    {'-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '2', '3', '3', '-', '-', '3', '3', '2', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-'},
                    {'-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '2', '3', '3', '-', '-', '3', '3', '2', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-'},
                    {'-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '1', '2', '3', '3', '1', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '1', '3', '3', '2', '1', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-'},
                    {'-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '1', '2', '2', '2', '2', '2', '2', '1', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '1', '2', '2', '2', '2', '2', '2', '1', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-'},
                    {'-', '-', '-', '-', '-', '-', '-', '-', '-', '1', '2', '2', '1', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '1', '2', '2', '1', '-', '-', '-', '-', '-', '-', '-', '-', '-'},
                    {'-', '-', '-', '-', '-', '-', '-', '1', '2', '2', '3', '2', '1', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '1', '2', '3', '2', '2', '1', '-', '-', '-', '-', '-', '-', '-'},
                    {'-', '-', '-', '-', '-', '-', '2', '2', '3', '3', '3', '2', '1', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '1', '2', '3', '3', '3', '2', '2', '-', '-', '-', '-', '-', '-'},
                    {'-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-'},
                    {'-', '-', '-', '-', '-', '-', '2', '2', '3', '3', '3', '2', '1', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '1', '2', '3', '3', '3', '2', '2', '-', '-', '-', '-', '-', '-'},
                    {'-', '-', '-', '-', '-', '-', '-', '1', '2', '2', '3', '2', '1', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '1', '2', '3', '2', '2', '1', '-', '-', '-', '-', '-', '-', '-'},
                    {'-', '-', '-', '-', '-', '-', '-', '-', '-', '1', '2', '2', '1', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '1', '2', '2', '1', '-', '-', '-', '-', '-', '-', '-', '-', '-'},
                    {'-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '1', '2', '2', '2', '2', '2', '2', '1', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '1', '2', '2', '2', '2', '2', '2', '1', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-'},
                    {'-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '1', '2', '3', '3', '1', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '1', '3', '3', '2', '1', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-'},
                    {'-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '2', '3', '3', '-', '-', '3', '3', '2', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-'},
                    {'-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '2', '3', '3', '-', '-', '3', '3', '2', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-'},
                    {'-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '0', '1', '2', '3', '3', '2', '1', '0', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '0', '1', '2', '3', '3', '2', '1', '0', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-'},
                    {'-', '-', '-', '0', '1', '2', '3', '2', '1', '0', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '0', '1', '2', '3', '2', '1', '0', '-', '-', '-'},
                    {'-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-'},
                    {'-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-'}
            };
    public int kkolor;

    public int RECT_MARGIN = 1;
    public int RECT_X_SIZE = 15;
    public int RECT_Y_SIZE = 15;
    public int GRID_TICKNESS = 1;

    public int FIELD_COLOR = 0x000000; //eeeeee

    public int APFEL_COLOR = 0xff0000; //00ff00

    public int CURRENT_SNAKE_SIZE;
    public int SNAKE_BODY[][] = new int[2][FIELD_X_SIZE * FIELD_Y_SIZE];

    public int SNAKE_BODY_COLOR = 0x888888; //ff0000

    public int x, y;

    public int direct_right = 0;
    public int direct_down = 1;
    public int direct_left = 2;
    public int direct_up = 3;
    public int SNAKE_DIRECT;

    public final byte emptyblock = '-';
    public final byte unfillingblock = '#';
    public final byte block00 = '0';
    public final byte block01 = '1';
    public final byte block02 = '2';
    public final byte block03 = '3';
    public final byte block04 = '4';
    public final byte block05 = '5';
    public final byte block06 = '6';
    public final byte block07 = '7';
    public final byte block08 = '8';
    public final byte block09 = '9';
    public final byte block0a = 'A';
    public final byte block0b = 'B';
    public final byte block0c = 'C';
    public final byte block0d = 'D';
    public final byte block0e = 'E';
    public final byte block0f = 'F';
    public final byte snakeblock = '*';
    public final byte apfelblock = '@';

    public int block_color00 = 0x202c38;
    public int block_color01 = 0x4d6d8a;
    public int block_color02 = 0x82baeb;
    public int block_color03 = 0xffffff;
    public int block_color04 = 0x000000;
    public int block_color05 = 0x000000;
    public int block_color06 = 0x000000;
    public int block_color07 = 0x000000;
    public int block_color08 = 0x000000;
    public int block_color09 = 0x000000;
    public int block_color0a = 0x000000;
    public int block_color0b = 0x000000;
    public int block_color0c = 0x000000;
    public int block_color0d = 0x000000;
    public int block_color0e = 0x000000;
    public int block_color0f = 0x000000;

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

//vivodim pole

        for (y = 0; y < FIELD_Y_SIZE; y++) {
            for (x = 0; x < FIELD_X_SIZE; x++) {
                casecolor();
                g.setColor(new Color(kkolor));
                g.fillRect(RECT_MARGIN + x * (RECT_X_SIZE + GRID_TICKNESS), RECT_MARGIN + y * (RECT_Y_SIZE + GRID_TICKNESS), RECT_X_SIZE, RECT_Y_SIZE);
            }
        }
    }


//vibor cveta kvadrata
    private void casecolor() {
        switch ((SNAKE_FIELD[y][x])) {
            case unfillingblock:
            case emptyblock:
                kkolor = FIELD_COLOR;
                break;
            case snakeblock:
                kkolor = SNAKE_BODY_COLOR;
                break;
            case apfelblock:
                kkolor = APFEL_COLOR;
                break;
            case block00:
                kkolor = block_color00;
                break;
            case block01:
                kkolor = block_color01;
                break;
            case block02:
                kkolor = block_color02;
                break;
            case block03:
                kkolor = block_color03;
                break;
            case block04:
                kkolor = block_color04;
                break;
            case block05:
                kkolor = block_color05;
                break;
            case block06:
                kkolor = block_color06;
                break;
            case block07:
                kkolor = block_color07;
                break;
            case block08:
                kkolor = block_color08;
                break;
            case block09:
                kkolor = block_color09;
                break;
            case block0a:
                kkolor = block_color0a;
                break;
            case block0b:
                kkolor = block_color0b;
                break;
            case block0c:
                kkolor = block_color0c;
                break;
            case block0d:
                kkolor = block_color0d;
                break;
            case block0e:
                kkolor = block_color0e;
                break;
            case block0f:
                kkolor = block_color0f;
                break;
            default:
                kkolor = emptyblock;
                break;
        }
    }

    public void init() {
        CURRENT_SNAKE_SIZE = 8;
        SNAKE_DIRECT = direct_right;
//zapolnyaem pole
//        for (y = 0; y < FIELD_Y_SIZE; y++) {
//            for (x = 0; x < FIELD_X_SIZE; x++) {
//                SNAKE_FIELD[y][x] = emptyblock;
//            }
//        }

//risuem zmejku
        for (int loc_counter = 0; loc_counter < CURRENT_SNAKE_SIZE; loc_counter++) {
            SNAKE_FIELD[SNAKE_BODY[1][loc_counter] = (FIELD_Y_SIZE / 2)][SNAKE_BODY[0][loc_counter] = (loc_counter + 5)] = snakeblock;
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
        } else if (headcolor() == emptyblock) {

            SNAKE_FIELD[SNAKE_BODY[1][0]][SNAKE_BODY[0][0]] = emptyblock;
            for (int loc_counter = 0; loc_counter < CURRENT_SNAKE_SIZE; loc_counter++) {
                SNAKE_BODY[0][loc_counter] = SNAKE_BODY[0][loc_counter + 1];
                SNAKE_BODY[1][loc_counter] = SNAKE_BODY[1][loc_counter + 1];
            }
            setnewhead();
        } else if (headcolor() == apfelblock) {
            setnewhead();
            CURRENT_SNAKE_SIZE++;
            putapfel();
        } else {
            init();
        }
    }

    byte headcolor() {
        return SNAKE_FIELD[SNAKE_BODY[1][CURRENT_SNAKE_SIZE]][SNAKE_BODY[0][CURRENT_SNAKE_SIZE]];
    }

    void setnewhead() {
        SNAKE_FIELD[SNAKE_BODY[1][CURRENT_SNAKE_SIZE]][SNAKE_BODY[0][CURRENT_SNAKE_SIZE]] = snakeblock;
    }

    void putapfel() {
        int loc_x, loc_y;
        do {
            loc_x = (int) (Math.random() * FIELD_X_SIZE - 1);
            loc_y = (int) (Math.random() * FIELD_Y_SIZE - 1);
        }
        while (SNAKE_FIELD[loc_y][loc_x] != emptyblock);

        SNAKE_FIELD[loc_y][loc_x] = apfelblock;
    }

    public void changesnakedirect(int newdirect, int incorrectdirect) {
        if (SNAKE_DIRECT != incorrectdirect) {
            SNAKE_DIRECT = newdirect;
        }
    }
}