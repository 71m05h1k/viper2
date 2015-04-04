import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class Viper2 {

    Viper2() throws IOException {
        JFrame frame = new JFrame("Viper2");
        frame.setVisible(true);
        frame.setSize(820, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final Draw snakeField = new Draw();
        snakeField.init();
        snakeField.setFocusable(true);
        snakeField.grabFocus();
        frame.add(snakeField);

        snakeField.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
//                System.out.println(e);

                if (e.getKeyChar() == 'w') {
                    snakeField.changesnakedirect(snakeField.direct_up, snakeField.direct_down);
                }
                if (e.getKeyChar() == 's') {
                    snakeField.changesnakedirect(snakeField.direct_down, snakeField.direct_up);
                }
                if (e.getKeyChar() == 'a') {
                    snakeField.changesnakedirect(snakeField.direct_left, snakeField.direct_right);
                }
                if (e.getKeyChar() == 'd') {
                    snakeField.changesnakedirect(snakeField.direct_right, snakeField.direct_left);
                }
                snakeField.updateUI();
            }
        });

        new Thread(new MainLoop(snakeField)).start();
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Viper2();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}