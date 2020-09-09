package footballclubs.api.apk;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Match {

    private String date;
    private String team1;
    private String team2;
    private Score score;

    public String getDatee() { return date; }
    public String getTeam1() { return team1; }
    public String getTeam2() { return team2; }
    public Score getScore() { return score; }

    public void setDate(String date) { this.date = date; }
    public void setTeam1(String team1) { this.team1 = team1; }
    public void setTeam2(String team2) { this.team2 = team2; }
    public void setScore(Score score) { this.score = score; }

    //public String toString() {return String.format("date:%s,team1:%s,team2:%s,score:%s", date,team1,team2,score);}

    public String toString() {
        return String.format("date:%s,team1:%s,team2:%s,score%s", date,team1,team2,score);
    }

}
