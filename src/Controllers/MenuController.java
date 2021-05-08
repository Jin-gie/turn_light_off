package Controllers;

import Models.Grid;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

/**
 * Controller of the buttons of the menu
 * @author erinb
 */

@SuppressWarnings("deprecation")
public class MenuController
        extends Observable
        implements ActionListener {

    Grid gridModel;
    Grid starter;

    public MenuController(Grid g) {
        this.gridModel = g;
        this.starter = new Grid();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String btnText = ((JButton) e.getSource()).getText();

        switch (btnText) {
            case "Configure" -> this.gridModel.playableGame();
            case "Random" -> this.gridModel.randomizeGrid();
            case "Play" -> {
                this.gridModel.launchGame();
                this.gridModel.setGrid(this.starter); // TODO Load last saved grid
            }
            case "Quit" -> this.gridModel.configGame();
            case "Restart" -> {
                this.gridModel.configGame();
                this.gridModel.emptyGrid();
                this.gridModel.resetCounter();
            }
        }

        setChanged();
        notifyObservers();
    }
}
