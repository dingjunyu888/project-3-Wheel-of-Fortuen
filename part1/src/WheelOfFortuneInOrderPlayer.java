import java.util.Random;

public class WheelOfFortuneInOrderPlayer implements WheelOfFortunePlayer{
    private String alphabets;
    private int index;

    public WheelOfFortuneInOrderPlayer(){
        alphabets="abcdefghijklmnopqrstuvwxyz";
    }
    @Override
    //get the next guess from the player
    public char nextGuess(){
        char letter = alphabets.charAt(index);
        index++;
        return letter;
    }
    //an id for player
    @Override
    public String playerId(){
        return "IOPlayer";
    }
    //reset the player to start a new game
    @Override
    public void reset(){
        index = 0;
    }
}
