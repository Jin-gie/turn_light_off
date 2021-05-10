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
    boolean[][] starter;

    public MenuController(Grid g) {
        this.gridModel = g;
        this.starter = new boolean[Grid.GRID_SIZE][Grid.GRID_SIZE];
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String btnText = ((JButton) e.getSource()).getText();

        switch (btnText) {
            case "Configure" -> {
                for (int x = 0; x < Grid.GRID_SIZE; x++)
                    for (int y = 0; y < Grid.GRID_SIZE; y++)
                        this.starter[y][x] = this.gridModel.getElement(x, y);
                this.gridModel.playableGame();
            }
            case "Random" -> this.gridModel.randomizeGrid();
            case "Play" -> {
                this.gridModel.launchGame();
                this.gridModel.setGrid(starter);
            }
            case "Quit" -> this.gridModel.configGame();
            case "Restart" -> {
                this.gridModel.configGame();
                this.gridModel.emptyGrid();
                this.gridModel.resetCounter();
            }
            case "Validate" -> {
                System.out.println();
            }
        }

        setChanged();
        notifyObservers();
    }
}
