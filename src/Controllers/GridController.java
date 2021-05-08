package Controllers;

import Models.Grid;
import Models.State;

import javax.swing.*;
import java.awt.event.*;

/**
 * Controller of the cells of the grid
 * @author erinb
 */

public class GridController extends MouseAdapter {
    private final Grid gridModel;

    public GridController(Grid grid) {
        super();
        this.gridModel = grid;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e)) {
            int xMouse = this.gridModel.reduce(e.getX());
            int yMouse = this.gridModel.reduce(e.getY());

            if (this.gridModel.getState() == State.CONFIG ||
                    this.gridModel.getState() == State.PLAYABLE)
                this.gridModel.configStep(xMouse, yMouse);
            else if (this.gridModel.getState() == State.GAME)
                this.gridModel.playStep(xMouse, yMouse);
        }
    }

    public Grid getGrid() {
        return this.gridModel;
    }
}

