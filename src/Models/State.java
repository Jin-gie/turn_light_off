package Models;

/**
 * Enumeration of the states of a game
 * CONFIG: on configuration mode
 * PLAYABLE: configuration saved, game can be launched
 * GAME: when a game is launched
 * FINISH: when a game is finished
 * @author erinb
 */

public enum State {
    CONFIG, PLAYABLE, GAME, FINISH
}
