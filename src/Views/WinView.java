package Views;

import javax.swing.*;
import java.awt.*;

public class WinView extends JPanel {
    public WinView(int w, int h, String txt) {
        this.setPreferredSize(new Dimension(w, h));
        this.setBackground(new Color(139, 139, 139, 124));
        this.setLayout(new GridBagLayout());
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setOpaque(false);

        this.add(textPanel);

        JLabel wTitle = new JLabel("You won!");
        wTitle.setFont(new Font("Helvetica", Font.BOLD, 50));
        wTitle.setForeground(Color.BLACK);

        JLabel wMoves = new JLabel("Total moves: " + txt);
        wMoves.setFont(new Font("Helvetica", Font.PLAIN, 25));
        wMoves.setForeground(Color.BLACK);

        textPanel.add(wTitle);
        textPanel.add(wMoves);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
