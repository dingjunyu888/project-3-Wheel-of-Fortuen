import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public abstract class GuessingGame extends Game{
    protected List<String> phraseList;
    protected String phrase = "";
    protected ArrayList<String> previousGuess;
    protected String hiddenPhrase = "";

    protected String fileName = "";

    int chance = 10;

    public void readPhrase(){
        try {
            phraseList = Files.readAllLines(Paths.get(fileName));
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public String randomPhrase(){
        readPhrase();

        Random rand = new Random();
        int r= rand.nextInt(phraseList.size()); // gets 0, 1, or 2
        this.phrase = phraseList.get(r);
        System.out.println(phrase);
        return phrase;
    }


    public String generateHiddenPhrase(){
        previousGuess = new ArrayList<>();
        return hiddenPhrase = this.phrase.replaceAll("[A-Za-z]", "*");
    }



}
