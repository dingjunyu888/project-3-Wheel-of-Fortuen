import java.util.Random;

public class WheelOfFortuneRandomPlayer implements WheelOfFortunePlayer{
    @Override
    //get the next guess from the player
    public char nextGuess(){
        Random r = new Random();
        Character c = (char)(r.nextInt(26) + 'a');
        return c;
    }
    //an id for player
    @Override
    public String playerId(){
        return "RDPlayer";
    }
    //reset the player to start a new game
    @Override
    public void reset(){

    }
}
