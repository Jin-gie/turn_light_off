package Views;

import Controllers.GridController;
import Controllers.MenuController;
import Controllers.RandomController;
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

@SuppressWarnings("deprecation")
public class MenuView extends JPanel implements Observer {
    public static final int BTN_WIDTH = 200;
    private final MenuController mc;
    private final RandomController rc;
    private final GridController gc;
    private final JButton[] buttons;
    private final CounterView ctrPanel;
    private JSpinner nbRandom = new JSpinner();

    public MenuView(MenuController mc, GridController gc, RandomController rc) {
        this.mc = mc;
        this.gc = gc;
        this.rc = rc;
        this.gc.getGrid().addObserver(this);

        this.buttons = new JButton[5];
        buttons[0] = new JButton("Configure");
        buttons[1] = new JButton("Random");
        buttons[2] = new JButton("Play");
        buttons[3] = new JButton("Quit");
        buttons[4] = new JButton("Restart");

        for (JButton btn : buttons) {
            btn.setFont(new Font("Helvetica", Font.PLAIN, 15));
            btn.setPreferredSize(new Dimension(BTN_WIDTH, 50));
            btn.setBackground(Color.WHITE);
            btn.setBorder(new EtchedBorder());
            btn.addActionListener(mc);
        }

        this.buttons[0].setEnabled(false);
        this.buttons[2].setEnabled(false);
        this.buttons[3].setEnabled(false);
        this.buttons[4].setEnabled(false);

        this.ctrPanel = new CounterView();

        this.add(buttons[0]);
        this.add(buttons[1]);
        this.add(this.createRandomChangerView());
        this.add(buttons[2]);
        this.add(buttons[3]);
        this.add(buttons[4]);
        this.add(this.ctrPanel);
    }

    @Override
    public void update(Observable o, Object arg) {
        this.ctrPanel.updateCounter(String.valueOf(this.gc.getGrid().getCounter()));

        switch (this.gc.getGrid().getState()) {
            case CONFIG -> {
                this.buttons[0].setEnabled(!this.gc.getGrid().testIfFinished());
                this.buttons[1].setEnabled(true);
                this.buttons[2].setEnabled(false);
                this.buttons[3].setEnabled(false);
                this.buttons[4].setEnabled(true);
            }
            case PLAYABLE -> {
                this.buttons[0].setEnabled(!this.gc.getGrid().testIfFinished());
                this.buttons[1].setEnabled(true);
                this.buttons[2].setEnabled(true);
                this.buttons[3].setEnabled(false);
                this.buttons[4].setEnabled(true);
            }
            case GAME -> {
                this.buttons[0].setEnabled(false);
                this.buttons[1].setEnabled(false);
                this.buttons[2].setEnabled(false);
                this.buttons[3].setEnabled(true);
                this.buttons[4].setEnabled(true);
            }
            case FINISH -> {
                for (JButton btn : this.buttons)
                    btn.setEnabled(false);
                this.buttons[4].setEnabled(true);
            }
        }

        repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    public JPanel createRandomChangerView() {
        JPanel randomView = new JPanel();

        JLabel expl = new JLabel("Nb of random cells: ");

        SpinnerModel spinnerModel = new SpinnerNumberModel(8, 1, (Grid.GRID_SIZE * Grid.GRID_SIZE) - 1, 1);
        nbRandom.setModel(spinnerModel);
        nbRandom.setFont(new Font("Helvetica", Font.PLAIN, 15));
        nbRandom.setPreferredSize(new Dimension(50, 25));
        ((JSpinner.DefaultEditor) nbRandom.getEditor()).getTextField().setEditable(false);
        nbRandom.addChangeListener(rc);

//        JButton validateRandom = new JButton( "Validate");
//        validateRandom.setFont(new Font("Helvetica", Font.PLAIN, 15));
//        validateRandom.setPreferredSize(new Dimension(75, 25));
//        validateRandom.setBackground(Color.WHITE);
//        validateRandom.setBorder(new EtchedBorder());
//        validateRandom.addActionListener(mc);

        randomView.add(expl);
        randomView.add(nbRandom);
//        randomView.add(validateRandom);

        return randomView;
    }

    public int getRandomVal() {
        return Integer.parseInt(((JSpinner.DefaultEditor) this.nbRandom.getEditor()).getTextField().getText());
    }
}
