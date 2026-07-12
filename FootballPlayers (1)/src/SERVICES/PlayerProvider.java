package SERVICES;

import MODEL.Player;

/**
 * Interface for providing player information
 * Allows loose coupling between TrainingSession and player data sources
 */
public interface PlayerProvider {
    /**
     * Retrieve a player by their ID
     * @param id the player's ID
     * @return the Player object if found, null otherwise
     */
    Player getPlayerById(String id);
}
