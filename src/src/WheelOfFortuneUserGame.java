import java.util.Scanner;

public class WheelOfFortuneUserGame extends WheelOfFortune{
    @Override
    public GameRecord play(){
        super.randomPhrase();
        super.generateHiddenPhrase();
        //
        //take user id
        System.out.println("Please enter your ID:");
        Scanner id = new Scanner(System.in);
        String userId = id.nextLine();
        StringBuilder updatedPhrase = new StringBuilder(hiddenPhrase);
        boolean notOver = true;
        int chance = 10;

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
            return false;
        }
    }

    @Override
    public char getGuess(String previousGuess){
        Scanner myObj = new Scanner(System.in);
        return myObj.nextLine().charAt(0);
    }

    public static void main(String [] args) {
        WheelOfFortuneUserGame games = new WheelOfFortuneUserGame();
        AllGameRecord record = games.playAll();
        System.out.println(record);  // or call specific functions of record
    }
}
