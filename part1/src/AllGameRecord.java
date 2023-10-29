import java.util.ArrayList;
import java.util.Collections;

public class AllGameRecord {
    private ArrayList<GameRecord> allGameRecords;

    public AllGameRecord(){
        allGameRecords = new ArrayList<>();
    }

    public void add (GameRecord record){
        allGameRecords.add(record);
    }

    //return average of allGameRecords scores
    public int average(){
        int average = 0;
        for(GameRecord record : allGameRecords){
            average += record.getScore();
        }
        average /= allGameRecords.size();
        return average;
    }

    //return average score for a specific player id;
    public int average(String playerId){
        int allScores = 0;
        int playerNum = 0;
        for(GameRecord record : allGameRecords){
            if(record.getPlayerId() == playerId){
                playerNum++;
                allScores += record.getScore();
            }
        }
        int average = allScores/playerNum;
        return average;
    }

    //returns a sorted list of the top n scores including player and score.
    public ArrayList<GameRecord> highGameList(int n){
        ArrayList<GameRecord> topList = new ArrayList<>();
        Collections.sort(allGameRecords);
        for(GameRecord gr: allGameRecords){
            if(n == 0){
                break;
            }else{
                topList.add(gr);
                n--;
            }
        }
        return topList;
    }

    //returns a sorted list of the top n scores for the specified player
    public ArrayList<GameRecord> highGameList(int n, String playerId){
        ArrayList<GameRecord> topList = new ArrayList<>();
        Collections.sort(allGameRecords);
        for(GameRecord gr: allGameRecords){
            if(n == 0){
                break;
            }else{
                if(gr.getPlayerId() == playerId){
                    topList.add(gr);
                    n--;
                }
            }
        }
        return topList;
    }
    @Override
    public String toString() {
        String recordPrint = "";
        for(GameRecord record : allGameRecords){
            recordPrint += "Player ID : " + record.getPlayerId() + " " + "Player score : " + record.getScore() + "\n";
        }
        return "Game records: " + "\n" + recordPrint;
    }
}
