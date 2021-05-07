package Views;

import Controllers.GridController;
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
    private GridController gc;
    private Grid gridModel;
    private final JButton[] buttons;
    private JLabel counter;

    public MenuView(MenuController mc, GridController gc) {
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
        this.add(this.createCounter());
        this.add(buttons[2]);
        this.add(buttons[3]);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Grid) {
            this.gridModel = (Grid) o;
            this.counter.setText(String.valueOf(this.gridModel.getCounter()));
        }

        switch (this.gridModel.getState()) {
            case CONFIG, FINISH -> {
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

    public JPanel createCounter() {
        JPanel ctr = new JPanel();
        ctr.setLayout(new BoxLayout(ctr, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Moves counter:");
        title.setFont(new Font("Helvetica", Font.PLAIN, 20));
        title.setPreferredSize(new Dimension(BTN_WIDTH, 25));
//        title.setHorizontalAlignment(SwingConstants.CENTER);

        this.counter = new JLabel("0");
        this.counter.setFont(new Font("Helvetica", Font.BOLD, 30));
        this.counter.setPreferredSize(new Dimension(BTN_WIDTH, 35));
//        this.counter.setHorizontalAlignment(SwingConstants.CENTER);

        ctr.add(title);
        ctr.add(this.counter);

        return (ctr);
    }

}
