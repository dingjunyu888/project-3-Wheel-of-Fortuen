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
        if(phraseList.size() != 0) {
            System.out.println("Do you want to play another game? Y/N");
            Scanner myObj = new Scanner(System.in);
            String answer = myObj.nextLine().toLowerCase();
            if (answer == "y") {
                return true;
            } else if (answer == "n") {
                return false;
            } else {
                System.out.println("Please enter Y for yes or N for no!");
                return playNext();
            }
        }else{
            return false;
        }
    }

    @Override
    public String getGuess(String previousGuess){
        Scanner myObj = new Scanner(System.in);
        return myObj.nextLine();
    }
}
