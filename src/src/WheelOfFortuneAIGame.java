import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class WheelOfFortuneAIGame extends WheelOfFortune{
    ArrayList<WheelOfFortunePlayer> players;
    WheelOfFortunePlayer currentPlayer;

    public WheelOfFortuneAIGame(){
        WheelOfFortuneInOrderPlayer wheelOfFortuneInOrderPlayer = new WheelOfFortuneInOrderPlayer();
        this.players.add(wheelOfFortuneInOrderPlayer);
    }
    public WheelOfFortuneAIGame(WheelOfFortunePlayer wheelOfFortunePlayer){
        this.players.add(wheelOfFortunePlayer);
    }
    public WheelOfFortuneAIGame(ArrayList<WheelOfFortunePlayer> wheelOfFortunePlayers){
        this.players = wheelOfFortunePlayers;
    }

    @Override
    public GameRecord play(){
        super.randomPhrase();
        super.generateHiddenPhrase();
        //
        //take user id
        System.out.println("Please enter your ID:");
        Scanner id = new Scanner(System.in);
        String userId = id.nextLine()
                ;
        StringBuilder updatedPhrase = new StringBuilder(hiddenPhrase);
        boolean notOver = true;
        int chance = 10;

        System.out.println(hiddenPhrase);
        while(notOver && chance > 0){
            System.out.println("Please enter a letter:");
            String userInput = getGuess(previousGuess);
            char guessInput = userInput.charAt(0);
            if(phrase.toLowerCase().contains(userInput) && !previousGuess.contains(userInput)){
                updatedPhrase = processGuess(guessInput, updatedPhrase);
                previousGuess += userInput;
            }else if(!Character.isLetter(guessInput)){
                System.out.println("Only letter is accepted. You have " + chance + " chances left");
            }else if(previousGuess.contains(userInput)){
                System.out.println("This letter has been guessed previously. Please try another letter. You have " + chance + " chances left");
            }else{
                chance--;
                previousGuess += userInput;
                System.out.println("Wrong guess, please guess another letter.");
                System.out.println("You have " + chance + " chances left.");
            }
            System.out.println(updatedPhrase);
            notOver = updatedPhrase.toString().contains("*");
        }

        if(chance > 0) {
            System.out.println("You Win");
        }else{
            System.out.println("You Lose");
        }
        GameRecord record = new GameRecord((10-chance)*10, userId);
        return record;
    }

    @Override
    public boolean playNext(){
        System.out.println("Do you want to play another game? Y/N");
        Scanner myObj = new Scanner(System.in);
        String answer = myObj.nextLine().toLowerCase();
        if(answer == "y"){
            return true;
        }else if(answer == "n"){
            return false;
        }else{
            System.out.println("Please enter Y for yes or N for no!");
            return playNext();
        }
    }

    @Override
    public String getGuess(String previousGuess){
        Random r = new Random();
        Character c = (char)(r.nextInt(26) + 'a');
        return c.toString();
    }
}
