package Views;

import javax.swing.*;
import java.awt.*;

/**
 * View of the moves counter to be integrated in the menu
 * @author erinb
 */

public class CounterView extends JPanel {
    private JLabel title, counter;

    public CounterView() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.title = new JLabel("Moves counter:");
        this.title.setFont(new Font("Helvetica", Font.PLAIN, 20));
        this.title.setPreferredSize(new Dimension(MenuView.BTN_WIDTH, 25));

        this.counter = new JLabel("0");
        this.counter.setFont(new Font("Helvetica", Font.BOLD, 30));
        this.counter.setPreferredSize(new Dimension(MenuView.BTN_WIDTH, 35));

        this.add(this.title);
        this.add(this.counter);
    }

    public void updateCounter(String txt) {
        if (this.counter != null)
            this.counter.setText(txt);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
