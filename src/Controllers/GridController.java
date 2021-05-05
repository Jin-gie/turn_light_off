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
    private Grid gridModel;

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
                this.configStep(xMouse, yMouse);
            else if (this.gridModel.getState() == State.GAME)
                this.playStep(xMouse, yMouse);
        }
    }

    private void playStep(int xMouse, int yMouse) {
        this.gridModel.switchAround(xMouse, yMouse);

        if (this.gridModel.testIfFinished())
            this.gridModel.winGame();
    }

    private void configStep(int xMouse, int yMouse) {
        this.gridModel.switchState(xMouse, yMouse);
    }
}

