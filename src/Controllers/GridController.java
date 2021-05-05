package Controllers;

import Models.Grid;

import javax.swing.*;
import java.awt.event.*;

/**
 * Controller of the cells of the grid
 * @author erinb
 */

public class GridController extends MouseAdapter {
    private Grid gridModel;

    public GridController(Grid grid) {
        super();
        this.gridModel = grid;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e)) {
            int x = this.gridModel.reduce(e.getX());
            int y = this.gridModel.reduce(e.getY());

            this.gridModel.switchAround(x, y);

            this.gridModel.isFinished();
        }
    }
}

