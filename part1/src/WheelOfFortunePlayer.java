public interface WheelOfFortunePlayer {
    //get the next guess from the player
    char nextGuess();
    //an id for player
    String playerId();
    //reset the player to start a new game
    void reset();

}
