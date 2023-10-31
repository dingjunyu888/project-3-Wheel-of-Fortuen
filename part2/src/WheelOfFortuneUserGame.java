import java.util.ArrayList;
import java.util.Scanner;

public class WheelOfFortuneUserGame extends WheelOfFortune{

    @Override
    public GameRecord play(){
        super.randomPhrase();
        super.generateHiddenPhrase();
        super.chance = 10;
        phraseList.remove(phrase);
        return super.play();
    }

    @Override
    public String takeId(){
        System.out.println("Please enter your ID:");
        Scanner id = new Scanner(System.in);
        String userId = id.nextLine();
        return userId;
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

    public static void main(String [] args) {
        WheelOfFortuneUserGame games = new WheelOfFortuneUserGame();
        AllGameRecord record = games.playAll();
        System.out.println(record);  // or call specific functions of record
    }
}
