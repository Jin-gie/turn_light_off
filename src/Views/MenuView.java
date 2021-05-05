package Views;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.plaf.basic.BasicBorders;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * View for the menu
 * @author erinb
 */

public class MenuView extends JPanel implements Observer {
    public static final int BTN_WIDTH = 200;
    private final Dimension btnSize = new Dimension(BTN_WIDTH, 50);

    @Override
    public void update(Observable o, Object arg) {

    }

    public MenuView() {
        JButton[] buttons = new JButton[4];
        buttons[0] = new JButton("Configure");
        buttons[1] = new JButton("Random");
        buttons[2] = new JButton("Play");
        buttons[3] = new JButton("Quit");

        for (JButton btn : buttons) {
            btn.setPreferredSize(this.btnSize);
            btn.setBackground(Color.WHITE);
            btn.setBorder(new EtchedBorder());
        }

        buttons[2].setEnabled(false);
        buttons[3].setEnabled(false);

        this.add(buttons[0]);
        this.add(buttons[1]);
        this.add(buttons[2]);
        this.add(buttons[3]);
    }

}
