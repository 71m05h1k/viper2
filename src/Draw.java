import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Draw extends JPanel {

//    private static final Font SMALL_FONT = new Font("Tahoma", Font.BOLD, 12);
//    private static final Font MEDIUM_FONT = new Font("Tahoma", Font.BOLD, 16);
//    private static final Font MEDIUM_FONT = new Font("Tahoma", Font.BOLD, 16);

    public int FIELD_X_SIZE = 51;
    public int FIELD_Y_SIZE = 23;
    public byte SNAKE_FIELD[][] = new byte[FIELD_Y_SIZE][FIELD_X_SIZE];


    public int RECT_MARGIN = 1;
    public int RECT_X_SIZE = 12;
    public int RECT_Y_SIZE = 12;


    public int CURRENT_SNAKE_SIZE;
    public int SNAKE_BODY[][] = new int[2][FIELD_X_SIZE * FIELD_Y_SIZE];


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

    //Color  kkolor = new Color();
    public Color FIELD_COLOR = new Color(0x000000); //eeeeee
    public Color APFEL_COLOR = new Color(0xff0000); //00ff00
    public Color SNAKE_BODY_COLOR = new Color(0x888888); //ff0000
    public Color block_color00 = new Color(0x202c38);
    public Color block_color01 = new Color(0x4d6d8a);
    public Color block_color02 = new Color(0x82baeb);
    public Color block_color03 = new Color(0xffffff);
    public Color block_color04 = new Color(0x000000);
    public Color block_color05 = new Color(0x000000);
    public Color block_color06 = new Color(0x000000);
    public Color block_color07 = new Color(0x000000);
    public Color block_color08 = new Color(0x000000);
    public Color block_color09 = new Color(0x000000);
    public Color block_color0a = new Color(0x000000);
    public Color block_color0b = new Color(0x000000);
    public Color block_color0c = new Color(0x000000);
    public Color block_color0d = new Color(0x000000);
    public Color block_color0e = new Color(0x000000);
    public Color block_color0f = new Color(0x000000);

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

//vivodim pole

        for (y = 0; y < FIELD_Y_SIZE; y++) {
            for (x = 0; x < FIELD_X_SIZE; x++) {
                //getBrickColor();
                g.setColor(getBrickColor());
                g.fillRect(RECT_MARGIN + x * (RECT_X_SIZE), RECT_MARGIN + y * (RECT_Y_SIZE), RECT_X_SIZE, RECT_Y_SIZE);
            }
        }

//        g.setColor(Color.YELLOW);
//        g.setFont(SMALL_FONT);
//        g.drawString("ABRAKADABRA",100,100);
    }


//vibor cveta kvadrata
private Color getBrickColor() {
        switch ((SNAKE_FIELD[y][x])) {
            case unfillingblock:
            case emptyblock:
                return FIELD_COLOR;
            case snakeblock:
                return SNAKE_BODY_COLOR;
            case apfelblock:
                return APFEL_COLOR;
            case block00:
                return block_color00;
            case block01:
                return block_color01;
            case block02:
                return block_color02;
            case block03:
                return block_color03;
            case block04:
                return block_color04;
            case block05:
                return block_color05;
            case block06:
                return block_color06;
            case block07:
                return block_color07;
            case block08:
                return block_color08;
            case block09:
                return block_color09;
            case block0a:
                return block_color0a;
            case block0b:
                return block_color0b;
            case block0c:
                return block_color0c;
            case block0d:
                return block_color0d;
            case block0e:
                return block_color0e;
            case block0f:
                return block_color0f;
            default:
                return FIELD_COLOR;
        }
    }

    public void init() {
        CURRENT_SNAKE_SIZE = 8;
        SNAKE_DIRECT = direct_right;

        BufferedReader fajleg = null;
        try {
            fajleg = new BufferedReader(new FileReader("./levelset/ft2/1.lvl"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String stroka;

        int xx, yy;

        for (yy = 0; yy < FIELD_Y_SIZE; yy++) {

            try {
                assert fajleg != null;
                stroka = fajleg.readLine();

                for (xx = 0; xx < FIELD_X_SIZE; xx++) {
                    SNAKE_FIELD[yy][xx] = (byte) stroka.charAt(xx);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

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
            loc_x = (int) (Math.random() * FIELD_X_SIZE);
            loc_y = (int) (Math.random() * FIELD_Y_SIZE);
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