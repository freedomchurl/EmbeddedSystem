package embedded.cse.cau.ac.kr.embedded;

/**
 * Created by caucse on 2017-11-08.
 */

public class RankingData {
    int score;
    String name;
    int rank;

    RankingData(int score,String name, int rank)
    {
        this.score = score;
        this.name = name;
        this.rank = rank;
    }

    public int getRank() {
        return rank;
    }

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
