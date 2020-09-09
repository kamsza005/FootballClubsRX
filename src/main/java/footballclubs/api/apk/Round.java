package footballclubs.api.apk;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Round extends Match {

    public String name;
    public List<Match> matches;

    private static class RoundData {
        Round data;
    }

    public String toString() {
        return String.format("name:%s,matches:%s", name,matches);
    }

    public String getName() { return name; }
    public List<Match> getMatches() { return matches; }

    public void setName(String title) { this.name = title; }
    public void setMathes(List<Match> groups) { this.matches = groups; }

}
