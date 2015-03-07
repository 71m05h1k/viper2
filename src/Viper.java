import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Viper {

    Viper() {
        JFrame frame = new JFrame("Viper");
        frame.setVisible(true);
        frame.setSize(820, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final Draw object = new Draw();
        object.init();
        object.setFocusable(true);
        object.grabFocus();
        frame.add(object);

        //JLabel l = new JLabel("Score:\nLevel:\nLifes:\n");
        //l.setFont(l.getFont().deriveFont(10f));
        //object.add(l);

        object.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
//                System.out.println(e);

                if (e.getKeyChar() == 'w') {
                    object.changesnakedirect(object.vverh, object.vniz);
                }
                if (e.getKeyChar() == 's') {
                    object.changesnakedirect(object.vniz, object.vverh);
                }
                if (e.getKeyChar() == 'a') {
                    object.changesnakedirect(object.vlevo, object.vpravo);
                }
                if (e.getKeyChar() == 'd') {
                    object.changesnakedirect(object.vpravo, object.vlevo);
                }
                object.updateUI();
            }

        });

        new Thread(new MainLoop(object)).start();

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Viper();
            }
        });
    }
}