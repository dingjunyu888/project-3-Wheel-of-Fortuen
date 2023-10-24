import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class HangmanMain {
    public static void main(String[] args) {

        WheelOfFortuneMain wheelOfFortune = new WheelOfFortuneMain();

        List<String> phraseList=null;
        // Get the phrase from a file of phrases
        try {
            phraseList = Files.readAllLines(Paths.get("./phrases.txt"));
        } catch (IOException e) {
            System.out.println(e);
        }

        // Get a random phrase from the list
        Random rand = new Random();
        int r= rand.nextInt(3); // gets 0, 1, or 2
        String phrase = phraseList.get(r);

        String newPhrase = phrase.replaceAll("[A-Za-z]", "*");
        StringBuilder updatedPhrase = new StringBuilder(newPhrase);
        String previousGuess = "";
        boolean notOver = true;
        int chance = 10;
        Scanner myObj = new Scanner(System.in);
        System.out.println(newPhrase);

        while(notOver && chance>0){
            System.out.println("Please enter a letter:");
            String userInput = myObj.nextLine();
            char guessInput = userInput.charAt(0);
            if(phrase.toLowerCase().contains(userInput) && !previousGuess.contains(userInput)){
                for(int i = 0; i < updatedPhrase.length(); i++){
                    if(phrase.toLowerCase().charAt(i) == guessInput){
                        updatedPhrase.setCharAt(i, phrase.charAt(i));
                        previousGuess += userInput;
                    }
                }
            }else if(Character.isLetter(guessInput) != true){
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
    }
}