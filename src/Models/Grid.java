package Models;

import java.util.Observable;
import java.util.Random;

/**
 * Model of a grid
 * Each cell is either turned off (=false) or turned on (=true)
 * @author erinb
 */


@SuppressWarnings("deprecation")
public class Grid
        extends Observable {
    public static final int GRID_SIZE = 5;
    public static final int CELL_SIZE = 100;
    public static final int RANDOM_CELLS = 8;
    public boolean[][] grid;
    private State state;
    private int counter;

    public Grid() {
        this.grid = new boolean[GRID_SIZE][GRID_SIZE];
        this.state = State.CONFIG;

        // Initialize all cells to false (= lights off)
        for (int y = 0; y < GRID_SIZE; y++)
            for (int x = 0; x < GRID_SIZE; x++)
                grid[x][y] = false;

        this.counter = 0;
    }

    public boolean isOn(int x, int y) {
        return this.grid[y][x];
    }

    public void emptyGrid() {
        for (int xG = 0; xG < GRID_SIZE; xG++)
            for (int yG = 0; yG < GRID_SIZE; yG++)
                this.grid[yG][xG] = false;
    }

    private void switchState(int x, int y) {
        if (this.grid[y][x])
            this.turnOff(x, y);
        else
            this.turnOn(x, y);

        setChanged();
        notifyObservers();
    }

    private void switchAround(int x, int y) {
        int[][] steps = {{0, 0}, {-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int[] item : steps) {
            int x1 = x + item[0];
            int y1 = y + item[1];
            if (x1 >= 0 && x1 < GRID_SIZE && y1 >= 0 && y1 < GRID_SIZE)
                this.switchState(x + item[0], y + item[1]);
        }
    }

    public void playStep(int x, int y) {
        this.switchAround(x, y);
        this.increaseCounter();
        if (this.testIfFinished())
            this.finishGame();

        setChanged();
        notifyObservers();
    }

    public void configStep(int x, int y) {
        this.switchState(x, y);
        setChanged();
        notifyObservers();
    }

    public boolean testIfFinished() {
        for (int x = 0; x < GRID_SIZE; x++) {
            for (int y = 0; y < GRID_SIZE; y++)
                if (isOn(x, y))
                    return false;
        }
        return true;
    }

    public void randomizeGrid() {
        Random r = new Random();
        int[][] cells = new int[RANDOM_CELLS][RANDOM_CELLS];
        int x, y;
        boolean found;

        for (int i = 0; i < RANDOM_CELLS; i++) {
            x = r.nextInt(GRID_SIZE);
            y = r.nextInt(GRID_SIZE);
            found = false;

            for (int[] item : cells) {
                if (item[0] == x && item[1] == y) {
                    found = true;
                    break;
                }
            }

            if (!found)
                cells[i] = new int[]{x, y};
        }

        this.emptyGrid();

        for (int[] item : cells)
            this.grid[item[1]][item[0]] = true;

    }

    public void launchGame() {
        this.counter = 0;
        this.state = State.GAME;
    }

    public void finishGame() {
        this.state = State.FINISH;
    }

    public void configGame() {
        this.state = State.CONFIG;
    }

    public void playableGame() {
        this.state = State.PLAYABLE;
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

    public boolean[][] getGrid() {
        return grid;
    }

    public boolean getElement(int x, int y) {
        return this.grid[y][x];
    }

    public void setGrid(boolean[][] g) {
        for (int x = 0; x < GRID_SIZE; x++)
            for (int y = 0; y < GRID_SIZE; y++)
                this.grid[y][x] = g[y][x];
    }

    public int getCounter() {
        return this.counter;
    }

    public void increaseCounter() {
        this.counter++;
    }

    public void resetCounter() {
        this.counter = 0;
    }
}
