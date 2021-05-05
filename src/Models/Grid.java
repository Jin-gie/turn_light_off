package Models;

import java.util.Observable;

/**
 * Model of a grid
 * Each cell is either turned off (=false) or turned on (=true)
 * @author erinb
 */

public class Grid
        extends Observable {
    public static final int GRID_SIZE = 5;
    public static final int CELL_SIZE = 100;
    private boolean[][] grid;
    private State state;

    public Grid() {
        this.grid = new boolean[GRID_SIZE][GRID_SIZE];
        this.state = State.CONFIG;
        // Initialize all cells to false (= lights off)
        for (int y = 0; y < GRID_SIZE; y++)
            for (int x = 0; x < GRID_SIZE; x++)
                grid[x][y] = false;
    }

    public boolean isOn(int x, int y) {
        return this.grid[y][x];
    }

    public void switchState(int x, int y) {
        if (this.grid[y][x])
            this.turnOff(x, y);
        else
            this.turnOn(x, y);

        setChanged();
        notifyObservers();
    }

    public void switchAround(int x, int y) {
        int steps[][] = {{0, 0}, {-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int item[] : steps) {
            int x1 = x + item[0];
            int y1 = y + item[1];
            if (x1 >= 0 && x1 < GRID_SIZE && y1 >= 0 && y1 < GRID_SIZE)
                this.switchState(x + item[0], y + item[1]);
        }

        // To test winning panel
//        for (int a = 0; a < GRID_SIZE; a++)
//            for (int b = 0; b < GRID_SIZE; b++)
//                switchState(a, b);
    }

    public boolean isFinished() {
        for (int x = 0; x < GRID_SIZE; x++) {
            for (int y = 0; y < GRID_SIZE; y++)
                if (!isOn(x, y))
                    return false;
        }
        this.state = State.FINISH;
        return true;
    }

    public void launchGame() {
        this.state = State.GAME;
    }

    public void finishGame() {
        this.state = State.FINISH;
    }

    public void configGame() {
        this.state = State.CONFIG;
    }

    private void turnOn(int x, int y) {
        this.grid[y][x] = true;
    }

    private void turnOff(int x, int y) {
        this.grid[y][x] = false;
    }

    public int reduce(int i) {
        return (i / CELL_SIZE);
    }

    public State getState() {
        return this.state;
    }
}
