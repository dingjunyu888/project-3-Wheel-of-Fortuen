import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

public abstract class WheelOfFortune extends Game{
    List<String> phraseList;
    String phrase = "";
    String previousGuess = "";
    String hiddenPhrase = "";
    int start = 1;
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
