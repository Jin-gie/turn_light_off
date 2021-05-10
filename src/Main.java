import Controllers.GridController;
import Controllers.MenuController;
import Controllers.RandomController;
import Models.Grid;
import Views.*;

import javax.swing.*;
import java.awt.*;

/**
 * Main class of the program
 * @author erinb
 */

public class Main {
    public static void main(String[] args) {
        Grid g = new Grid();
        GridController gc = new GridController(g);
        MenuController mc = new MenuController(g);
        RandomController rc = new RandomController(g);

        GridView gv = new GridView(gc);
        MenuView mv = new MenuView(mc, gc, rc);

        g.addObserver(gv);
        g.addObserver(mv);

        mc.addObserver(mv);
        mc.addObserver(gv);


        JFrame f = new JFrame("Turn off the lights");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(new BorderLayout());
        f.setResizable(false);

        gv.setPreferredSize(new Dimension(Grid.CELL_SIZE * Grid.GRID_SIZE, Grid.CELL_SIZE * Grid.GRID_SIZE));
        mv.setPreferredSize(new Dimension(MenuView.BTN_WIDTH, Grid.CELL_SIZE * Grid.GRID_SIZE));

        f.getContentPane().add(gv, BorderLayout.EAST);
        f.getContentPane().add(mv, BorderLayout.WEST);

        f.pack();
        f.setVisible(true);
    }
}
