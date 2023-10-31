import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public abstract class WheelOfFortune extends GuessingGame{
    int start = 1;
    @Override
    public GameRecord play(){
        //
        //take user id
        String userId = takeId();
        StringBuilder updatedPhrase = new StringBuilder(hiddenPhrase);
        boolean notOver = true;

        System.out.println(hiddenPhrase);
        while(notOver && chance > 0){
            System.out.println("Please enter a letter:");
            char userInput = getGuess(previousGuess);

            if(phrase.toLowerCase().contains(String.valueOf(userInput)) && !previousGuess.contains(String.valueOf(userInput))){
                updatedPhrase = processGuess(userInput, updatedPhrase);
                previousGuess.add(String.valueOf(userInput));
            }else if(!Character.isLetter(userInput)){
                System.out.println("Only letter is accepted. You have " + chance + " chances left");
            }else if(previousGuess.contains(String.valueOf(userInput))){
                System.out.println("This letter has been guessed previously. Please try another letter. You have " + chance + " chances left");
            }else{
                chance--;
                previousGuess.add(String.valueOf(userInput));
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
        GameRecord record = new GameRecord((chance)*10, userId);
        return record;
    }

    public abstract String takeId();

    @Override
    public void readPhrase(){
        // Get the phrase from a file of phrases
        fileName = "./phrases1.txt";
        super.readPhrase();
    }

    @Override
    public String randomPhrase(){
        if(start == 1) {
            readPhrase();
        }
        super.randomPhrase();
        return phrase;
    }

    public StringBuilder processGuess(char guessInput, StringBuilder updatedPhrase){
        for(int i = 0; i < updatedPhrase.length(); i++){
            if(phrase.toLowerCase().charAt(i) == guessInput){
                updatedPhrase.setCharAt(i, phrase.charAt(i));
            }
        }
        return updatedPhrase;
    }

    public char getGuess(ArrayList<String> previousGuess){
        Scanner myObj = new Scanner(System.in);
        return myObj.nextLine().charAt(0);
    }
}
