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
public class ClubList {

	private Club[] clubs;

	public static ClubList withName(String name) throws IOException {

		String sezon=Controller.getting();
		Gson g = new Gson();
		URL u = new URL(Main.MAIN_URL + sezon+"/"+name+".clubs.json");

		HttpURLConnection c = (HttpURLConnection) u.openConnection();
		InputStreamReader r = new InputStreamReader(c.getInputStream());

		ClubList l = g.fromJson(r, ClubList.class);

		r.close();

		return l;
	}

	public Club[] getClubs() {
		return clubs;
	}



}
