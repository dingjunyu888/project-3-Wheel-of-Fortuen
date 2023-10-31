import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class MasterMind extends GuessingGame{

    @Override
    public GameRecord play(){

        super.randomPhrase();
        super.generateHiddenPhrase();
        //
        //take user id
        String userId = takeId();
        int partials = 0;
        int exacts = 0;

        System.out.println(hiddenPhrase);
        while(chance > 0 && exacts!=4){
            System.out.println("Please enter a pattern:");
            String userInput = getGuess(previousGuess);
            if(userInput.matches("[a-zA-Z]+") && !previousGuess.contains(userInput)){
                partials = checkPartials(phrase.toLowerCase(), userInput.toLowerCase());
                exacts = checkExacts(phrase.toLowerCase(), userInput.toLowerCase());
                previousGuess.add(userInput);
                System.out.println("Partial Correct: " + partials + " Exact Correct: " + exacts);
                chance--;
                System.out.println("You have " + chance + " chances left.");
            }else if(!userInput.matches("[a-zA-Z]+")){
                System.out.println("Only letter is accepted. You have " + chance + " chances left");
            }else{
                System.out.println("This pattern has been guessed previously. Please try another letter. You have " + chance + " chances left");
            }
        }

        if(chance > 0) {
            System.out.println("You Win");
        }else{
            System.out.println("You Lose");
        }
        GameRecord record = new GameRecord((chance)*10, userId);
        return record;
    }
    @Override
    public void readPhrase(){
        fileName = "./phrases2.txt";
        try {
            phraseList = Files.readAllLines(Paths.get(fileName));
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    @Override
    public boolean playNext(){
        if(phraseList.size() != 0) {
            System.out.println("Do you want to play another game? Y/N");
            Scanner myObj = new Scanner(System.in);
            String answer = myObj.nextLine().toLowerCase();
            if (answer.equals("y")) {
                return true;
            } else if (answer.equals("n")){
                return false;
            } else {
                return false;
            }
        }else{
            System.out.println("We ran out phrase! Game Over!");
            return false;
        }
    }

    public int checkPartials(String secretSB, String guessSB) {
        // compare secret to guess
        int i=0;
        int partials=0;
        while (i<4) {
            int j=0;
            while (j<4) {
                if (guessSB.charAt(i) == secretSB.charAt(j)) {
                    partials += 1;
                    break;
                }
                j++;
            }
            i++;
        }
        return partials;
    }

    public int checkExacts(String guessSB,String secretSB) {
        // compare secret to guess
        int i=0;
        int exacts=0;
        while (i<4) {
            if (secretSB.charAt(i) == guessSB.charAt(i)) {
                exacts = exacts + 1;
            }
            i++;
        }
        return exacts;
    }


    public String takeId(){
        System.out.println("Please enter your ID:");
        Scanner id = new Scanner(System.in);
        String userId = id.nextLine();
        return userId;
    }

    public String getGuess(ArrayList<String> previousGuess){
        Scanner myObj = new Scanner(System.in);
        return myObj.nextLine();
    }
    public static void main(String [] args) {
        MasterMind games = new MasterMind();
        AllGameRecord record = games.playAll();
        System.out.println(record);  // or call specific functions of record
    }

}
