import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class WheelOfFortuneAIGame extends WheelOfFortune{
    ArrayList<WheelOfFortunePlayer> players;
//    WheelOfFortunePlayer currentPlayer;

    private int index;

    public WheelOfFortuneAIGame(){
        this.players = new ArrayList<>();
        WheelOfFortuneInOrderPlayer wheelOfFortuneInOrderPlayer = new WheelOfFortuneInOrderPlayer();
        this.players.add(wheelOfFortuneInOrderPlayer);
    }
    public WheelOfFortuneAIGame(WheelOfFortunePlayer wheelOfFortunePlayer){
        this.players.add(wheelOfFortunePlayer);
    }
    public WheelOfFortuneAIGame(ArrayList<WheelOfFortunePlayer> wheelOfFortunePlayers){
        this.players = wheelOfFortunePlayers;
    }

    @Override
    public GameRecord play(){
        super.chance = 12;
        if(index == 0) {
            super.randomPhrase();
            super.generateHiddenPhrase();
        }
        //take AI id
        WheelOfFortunePlayer currentPlayer = players.get(index);
        currentPlayer.reset();
        if(index >= players.size()-1){
            index = 0;
            previousGuess="";
            phraseList.remove(phrase);
        }else{
            index++;
            previousGuess="";
        }
        return super.play();
    }

    @Override
    public String takeId(){
        System.out.println("Please enter your ID:");
        WheelOfFortunePlayer currentPlayer = players.get(index);
        String aiId = currentPlayer.playerId();
        System.out.println(aiId);
        return aiId;
    }

    @Override
    public boolean playNext(){
        if(phraseList.size() != 0){
            return true;
        }else{
            System.out.println("We ran out phrase! Game Over!");
            return false;
        }
    }

    @Override
    public char getGuess(String previousGuess){
        return players.get(index).nextGuess();
    }

    public static void main(String [] args) {
        ArrayList<WheelOfFortunePlayer> allPlayers = new ArrayList<>();
        WheelOfFortuneInOrderPlayer player1 = new WheelOfFortuneInOrderPlayer();
        WheelOfFortuneRandomPlayer player2 = new WheelOfFortuneRandomPlayer();
        WheelOfFortuneHighLevelPlayer player3 = new WheelOfFortuneHighLevelPlayer();
        allPlayers.add(player1);
        allPlayers.add(player2);
        allPlayers.add(player3);
        WheelOfFortuneAIGame games1 = new WheelOfFortuneAIGame(allPlayers);
        WheelOfFortuneAIGame games2 = new WheelOfFortuneAIGame();
        AllGameRecord record = games1.playAll();
//        AllGameRecord record = games2.playAll();
        System.out.println(record);// or call specific functions of record
        System.out.println(record.average("HLPlayer"));
        System.out.println(record.highGameList(5));
        System.out.println(record.highGameList(2, "IOPlayer"));

    }
}
