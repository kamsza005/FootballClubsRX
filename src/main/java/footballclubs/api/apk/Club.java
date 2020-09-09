package footballclubs.api.apk;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Club {

	private String name;
	private String code;
	private String country;

	private String[] platforms;



	@Override
	public String toString() {

		return name;
	}


}
