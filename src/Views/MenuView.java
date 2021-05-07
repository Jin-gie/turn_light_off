package Views;

import Controllers.GridController;
import Controllers.MenuController;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * View for the menu
 * @author erinb
 */

@SuppressWarnings("deprecation")
public class MenuView extends JPanel implements Observer {
    public static final int BTN_WIDTH = 200;
    private final MenuController mc;
    private final GridController gc;
    private final JButton[] buttons;
    private final CounterView ctrPanel;

    // TODO create view for the buttons
    public MenuView(MenuController mc, GridController gc) {
        this.mc = mc;
        this.gc = gc;
        this.gc.getGrid().addObserver(this);

        this.buttons = new JButton[4];
        buttons[0] = new JButton("Configure");
        buttons[1] = new JButton("Random");
        buttons[2] = new JButton("Play");
        buttons[3] = new JButton("Quit");

        for (JButton btn : buttons) {
            btn.setPreferredSize(new Dimension(BTN_WIDTH, 50));
            btn.setBackground(Color.WHITE);
            btn.setBorder(new EtchedBorder());
            btn.addActionListener(mc);
        }

        this.buttons[2].setEnabled(false);
        this.buttons[3].setEnabled(false);

        this.ctrPanel = new CounterView();

        this.add(buttons[0]);
        this.add(buttons[1]);
        this.add(this.ctrPanel);
        this.add(buttons[2]);
        this.add(buttons[3]);
    }

    @Override
    public void update(Observable o, Object arg) {
        this.ctrPanel.updateCounter(String.valueOf(this.gc.getGrid().getCounter()));

        switch (this.gc.getGrid().getState()) {
            case CONFIG, FINISH -> {
                this.buttons[0].setEnabled(true); // TODO disable configure if all lights off
                this.buttons[1].setEnabled(true);
                this.buttons[2].setEnabled(false);
                this.buttons[3].setEnabled(false);
            }
            case PLAYABLE -> {
                this.buttons[0].setEnabled(true);
                this.buttons[1].setEnabled(true);
                this.buttons[2].setEnabled(true);
                this.buttons[3].setEnabled(false);
            }
            case GAME -> {
                this.buttons[0].setEnabled(false);
                this.buttons[1].setEnabled(false);
                this.buttons[2].setEnabled(false);
                this.buttons[3].setEnabled(true);
            }
        }

        repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

}
