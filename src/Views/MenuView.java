package Views;

import Controllers.MenuController;
import Models.Grid;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * View for the menu
 * @author erinb
 */

public class MenuView extends JPanel implements Observer {
    public static final int BTN_WIDTH = 200;
    private MenuController mc;
    private Grid gridModel;
    private final JButton[] buttons;

    public MenuView(MenuController mc) {
        this.mc = mc;

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

        this.add(buttons[0]);
        this.add(buttons[1]);
        this.add(buttons[2]);
        this.add(buttons[3]);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Grid)
            this.gridModel = (Grid) o;

        switch (this.gridModel.getState()) {
            case CONFIG -> {
                System.out.println("config");
                this.buttons[0].setEnabled(true);
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
                System.out.println("game");
                this.buttons[0].setEnabled(false);
                this.buttons[1].setEnabled(false);
                this.buttons[2].setEnabled(false);
                this.buttons[3].setEnabled(true);
            }
            case FINISH -> {
                System.out.println("finish");
                this.buttons[0].setEnabled(true);
                this.buttons[1].setEnabled(true);
                this.buttons[2].setEnabled(false);
                this.buttons[3].setEnabled(false);
            }
        }

        repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

}
