public abstract class Game {
    public AllGameRecord playAll(){
        AllGameRecord gamePlayRecord = new AllGameRecord();
        while(playNext()){
            gamePlayRecord.add(play());
        }
        return gamePlayRecord;
    }

    public abstract GameRecord play();

    public abstract boolean playNext();
}
