package Views;

import Controllers.GridController;
import Models.Grid;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * View for the game grid
 * @author erinb
 */

public class GridView extends JPanel implements Observer {

    private Grid gridModel;
    private GridController gc;
    private final Color green = new Color(0, 121, 41);
    private final Color ligtGreen = new Color(138, 246, 138);

    public GridView(GridController gc) {
        this.gc = gc;
        this.addMouseListener(gc);
    }

    @Override
    public void update(Observable o, Object arg) {
        gridModel = (Grid) o;
        repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(green);
        for (int x = 0; x < Grid.CELL_SIZE * Grid.GRID_SIZE; x += Grid.CELL_SIZE) {
            for (int y = 0; y < Grid.CELL_SIZE * Grid.GRID_SIZE; y += Grid.CELL_SIZE) {
                if (this.gridModel != null) {
                    if (this.gridModel.isOn(x / Grid.CELL_SIZE, y / Grid.CELL_SIZE))
                        g.setColor(ligtGreen);
                    else
                        g.setColor(green);
                }
                g.fillRect(x + 1, y + 1, Grid.CELL_SIZE - 1, Grid.CELL_SIZE - 1);
            }
        }
    }
}
