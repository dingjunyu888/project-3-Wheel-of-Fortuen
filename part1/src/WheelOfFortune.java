import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public abstract class WheelOfFortune extends Game{
    List<String> phraseList;
    String phrase = "";
    String previousGuess = "";
    String hiddenPhrase = "";

    int chance = 10;
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
                previousGuess += userInput;
            }else if(!Character.isLetter(userInput)){
                System.out.println("Only letter is accepted. You have " + chance + " chances left");
            }else if(previousGuess.contains(String.valueOf(userInput))){
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
        GameRecord record = new GameRecord((chance)*10, userId);
        return record;
    }

    public abstract String takeId();

    public void readPhrase(){
        // Get the phrase from a file of phrases
        try {
            phraseList = Files.readAllLines(Paths.get("./phrases.txt"));
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public String randomPhrase(){
        if(start == 1) {
            readPhrase();
        }
        Random rand = new Random();
        int r= rand.nextInt(phraseList.size()); // gets 0, 1, or 2
        this.phrase = phraseList.get(r);
        System.out.println(phrase);
        start++;
        return phrase;
    }

    public String generateHiddenPhrase(){
        previousGuess = "";
        return hiddenPhrase = this.phrase.replaceAll("[A-Za-z]", "*");
    }

    public StringBuilder processGuess(char guessInput, StringBuilder updatedPhrase){
        for(int i = 0; i < updatedPhrase.length(); i++){
            if(phrase.toLowerCase().charAt(i) == guessInput){
                updatedPhrase.setCharAt(i, phrase.charAt(i));
            }
        }
        return updatedPhrase;
    }



    public abstract char getGuess(String previousGuess);
}
