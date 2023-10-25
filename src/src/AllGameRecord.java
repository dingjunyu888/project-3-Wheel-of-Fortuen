import java.util.ArrayList;

public class AllGameRecord {
    private ArrayList<GameRecord> allGameRecords;
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
}
