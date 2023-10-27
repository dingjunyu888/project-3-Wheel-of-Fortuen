import java.util.Random;

public class WheelOfFortuneInOrderPlayer implements WheelOfFortunePlayer{
    @Override
    //get the next guess from the player
    public char nextGuess(){
        String order = "abcdefghijklmnopqrstuvwxyz";
        Character letter;
        for(int i = 0; i < order.length(); i++){
            letter = order.charAt(i);
            if(!previousGuess.toLowerCase().contains(letter.toString())){
                return letter.toString();
            }
        }
    }
    //an id for player
    @Override
    public String playerId(){
        return "IOPlayer";
    }
    //reset the player to start a new game
    @Override
    public void reset(){

    }
}
