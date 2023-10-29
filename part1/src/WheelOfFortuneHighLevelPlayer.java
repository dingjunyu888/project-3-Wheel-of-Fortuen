import java.util.Random;

public class WheelOfFortuneHighLevelPlayer implements WheelOfFortunePlayer{
    private String alphabets;
    private int index;

    public WheelOfFortuneHighLevelPlayer(){
        alphabets = "etaoinsrhldcumfpgwybvkxjqz";
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
        return "HLPlayer";
    }
    //reset the player to start a new game
    @Override
    public void reset(){
        index = 0;
    }
}
