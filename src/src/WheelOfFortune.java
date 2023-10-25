import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

public abstract class WheelOfFortune extends Game{
    List<String> phraseList = null;
    String phrase = "";
    String previousGuess = "";
    String hiddenPhrase = "";

    public void readPhrase(){
        // Get the phrase from a file of phrases
        try {
            phraseList = Files.readAllLines(Paths.get("./phrases.txt"));
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public String randomPhrase(){
        readPhrase();
        Random rand = new Random();
        int r= rand.nextInt(3); // gets 0, 1, or 2
        this.phrase = phraseList.get(r);
        System.out.println(phrase);
        return phrase;
    }

    public String generateHiddenPhrase(){
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



    public abstract String getGuess(String previousGuess);
}
