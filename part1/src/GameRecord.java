public class GameRecord implements Comparable<GameRecord>{
    private int score;
    private String playerId;

    GameRecord(int score, String playerId){
        this.score = score;
        this.playerId = playerId;
    }

    public int getScore() {
        return score;
    }

    public String getPlayerId() {
        return playerId;
    }

    @Override
    public String toString() {
        return "GameRecord{" +
                "score=" + score +
                ", playerId='" + playerId + '\'' +
                '}';
    }

    @Override
    public int compareTo(GameRecord other){
        if(this.score > other.score){
            return -1;
        }else if(this.score < other.score){
            return 1;
        }else{
            return 0;
        }
    }
}
