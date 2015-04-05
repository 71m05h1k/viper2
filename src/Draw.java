import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Draw extends JPanel {

    public Color brickcolor = null;

    public int rastishka;
    public int startrastishka = 3;
    public int apfelz;

    public int OSD_TEXT_X_OFFSET = 4;
    public int OSD_TEXT_Y_STRIDE = 12;
    public int OSD_TEXT_Y_OFFSET = 8;

    private static final Font SMALL_FONT = new Font("Tahoma", Font.BOLD, 12);
//    private static final Font MEDIUM_FONT = new Font("Tahoma", Font.BOLD, 16);
//    private static final Font BIG_FONT = new Font("Tahoma", Font.BOLD, 20);

    public String LEVEL_SET = "ft2";
    public int CURRENT_LEVEL;

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

    public Color empty_block_color = new Color(0x000000); //eeeeee
    public Color apfel_block_color = new Color(0xff0000); //00ff00
    public Color snake_block_color = new Color(0x888888); //ff0000
    public Color block_color00 = new Color(0x82baeb);
    public Color block_color01 = new Color(0x4d6d8a);
    public Color block_color02 = new Color(0x202c38);
    public Color block_color03 = new Color(0x3c4551);
    public Color block_color04 = new Color(0xa6b6db);
    public Color block_color05 = new Color(0xffffff);
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
        g.setColor(empty_block_color);
        g.fillRect(RECT_MARGIN, RECT_MARGIN, RECT_X_SIZE * FIELD_X_SIZE, RECT_Y_SIZE * FIELD_Y_SIZE);
        for (y = 0; y < FIELD_Y_SIZE; y++) {
            for (x = 0; x < FIELD_X_SIZE; x++) {

                if ((brickcolor = getBrickColor()) != empty_block_color) {
                    g.setColor(brickcolor);
                    g.fillRect(RECT_MARGIN + x * (RECT_X_SIZE), RECT_MARGIN + y * (RECT_Y_SIZE), RECT_X_SIZE, RECT_Y_SIZE);
                }
            }
        }

        int drawY = OSD_TEXT_Y_OFFSET;
        g.setColor(Color.BLUE);
        g.setFont(SMALL_FONT);
        g.drawString("Levelset: " + LEVEL_SET, OSD_TEXT_X_OFFSET, drawY += OSD_TEXT_Y_OFFSET);
        g.drawString("Level: " + CURRENT_LEVEL, OSD_TEXT_X_OFFSET, drawY += OSD_TEXT_Y_STRIDE);
        g.drawString("Apples: " + apfelz, OSD_TEXT_X_OFFSET, drawY += OSD_TEXT_Y_STRIDE);
        g.drawString("Lives: ", OSD_TEXT_X_OFFSET, drawY += OSD_TEXT_Y_STRIDE);
        g.drawString("Score: ", OSD_TEXT_X_OFFSET, drawY += OSD_TEXT_Y_STRIDE);
        g.drawString("HiScore: ", OSD_TEXT_X_OFFSET, drawY += OSD_TEXT_Y_STRIDE);
    }

    //vibor cveta kvadrata
    private Color getBrickColor() {
        switch ((SNAKE_FIELD[y][x])) {
            case unfillingblock:
            case emptyblock:
                return empty_block_color;
            case snakeblock:
                return snake_block_color;
            case apfelblock:
                return apfel_block_color;
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
                return empty_block_color;
        }
    }

    public void newGame() {
        CURRENT_LEVEL = -1;
        nextLevel();
    }

    private void putNewSnake() {
        //risuem zmejku i zanosim koordinati golovi, vernee naoborot
        SNAKE_BODY[1][0] = 0;
        SNAKE_BODY[0][0] = 0;
        SNAKE_FIELD[SNAKE_BODY[1][0]][SNAKE_BODY[0][0]] = snakeblock;
    }

    private void loadLevel() {
        BufferedReader fajleg = null;
        try {
            fajleg = new BufferedReader(new FileReader("./levelset/" + LEVEL_SET + "/" + CURRENT_LEVEL + ".lvl"));
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
    }

    void nextLevel() {
        apfelz = 9;
        rastishka = startrastishka;
        CURRENT_SNAKE_SIZE = 1;
        SNAKE_DIRECT = direct_right;
        CURRENT_LEVEL++;
        loadLevel();
        putNewSnake();
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
    }

    void moveCheck() {
        if ((SNAKE_BODY[0][CURRENT_SNAKE_SIZE] < 0) || (SNAKE_BODY[1][CURRENT_SNAKE_SIZE] < 0) || (SNAKE_BODY[0][CURRENT_SNAKE_SIZE] == FIELD_X_SIZE) || (SNAKE_BODY[1][CURRENT_SNAKE_SIZE] == FIELD_Y_SIZE)) {
            newGame();
            return;
        } else if (headcolor() == apfelblock) {
            setHeadBlock(emptyblock);
            rastishka += apfelz--;
            if (apfelz == 0) {
                nextLevel();
                return;
            }
            putapfel();
        } else if (headcolor() == emptyblock) {
            setHeadBlock(snakeblock);
            if (rastishka != 0) {
                CURRENT_SNAKE_SIZE++;
                rastishka--;
            } else {
                bodymove();
            }
        } else {
            newGame();
        }
    }

    void setHeadBlock(byte putableblock) {
        SNAKE_FIELD[SNAKE_BODY[1][CURRENT_SNAKE_SIZE]][SNAKE_BODY[0][CURRENT_SNAKE_SIZE]] = putableblock;
    }

    void bodymove() {
        SNAKE_FIELD[SNAKE_BODY[1][0]][SNAKE_BODY[0][0]] = emptyblock;
        for (int loc_counter = 0; loc_counter < CURRENT_SNAKE_SIZE; loc_counter++) {
            SNAKE_BODY[0][loc_counter] = SNAKE_BODY[0][loc_counter + 1];
            SNAKE_BODY[1][loc_counter] = SNAKE_BODY[1][loc_counter + 1];
        }
    }

    byte headcolor() {
        return SNAKE_FIELD[SNAKE_BODY[1][CURRENT_SNAKE_SIZE]][SNAKE_BODY[0][CURRENT_SNAKE_SIZE]];
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
