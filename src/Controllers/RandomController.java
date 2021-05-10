package Controllers;

import Models.Grid;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class RandomController implements ChangeListener {
    Grid gridModel;

    public RandomController(Grid g) {
        this.gridModel = g;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        int val = (Integer) ((JSpinner) e.getSource()).getValue();
        this.gridModel.setNbRandom(val);
    }
}
