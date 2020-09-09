package footballclubs.api.apk;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import footballclubs.Controller;
import footballclubs.Main;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class RoundList {

    private Round[] rounds;

    public static RoundList withName(String name) throws IOException {

        String sezon=Controller.getting();

        Gson g = new Gson();
        URL u = new URL(Main.MAIN_URL + sezon+"/"+name+".json");
        HttpURLConnection c = (HttpURLConnection) u.openConnection();
        InputStreamReader r = new InputStreamReader(c.getInputStream());

        RoundList l = g.fromJson(r, RoundList.class);

        //System.out.println(g.toJson(l));

        r.close();

        return l;

    }

    public Round[] getRounds() {
        return rounds;
    }



}
