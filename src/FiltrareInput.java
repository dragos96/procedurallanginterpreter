
import java.util.ArrayList;
import java.util.List;

public class FiltrareInput {
	
	public static List<String> filtrare(List<String> linii) {
		List<String> liniiOK = new ArrayList<>();
		for (String linie : linii) {
			linie = linie.replaceAll("\\s+", " ");
			// sb.append(linie+"\n");
			liniiOK.add(linie);
		}

		return liniiOK;
	}

}

