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

        object.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
//                System.out.println(e);

                if (e.getKeyChar() == 'w') {
                    object.to_vverh();
                }
                if (e.getKeyChar() == 's') {
                    object.to_vniz();
                }
                if (e.getKeyChar() == 'a') {
                    object.to_vlevo();
                }
                if (e.getKeyChar() == 'd') {
                    object.to_vpravo();
                }
                object.updateUI();
            }

        });

        //второй вариант
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
