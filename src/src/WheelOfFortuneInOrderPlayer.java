public class WheelOfFortuneInOrderPlayer implements WheelOfFortunePlayer{
    @Override
    //get the next guess from the player
    public char nextGuess(){
        return "".charAt(1);
    }
    //an id for player
    @Override
    public String playerId(){
        return "";
    }
    //reset the player to start a new game
    @Override
    public void reset(){

    }
}
