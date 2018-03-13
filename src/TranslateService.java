import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class TranslateService {

	private Map<String, String> wordsA2C = new HashMap<>();
	private Map<String, String> wordsC2A = new HashMap<>();

	public TranslateService() {
		wordsA2C.put("False", "I LIED");
		wordsA2C.put("True", "NO PROBLEMO");
		wordsA2C.put("If", "BECAUSE I'M GOING TO SAY PLEASE");
		wordsA2C.put("Else", "BULLSHIT");
		wordsA2C.put("EndIf", "YOU HAVE NO RESPECT FOR LOGIC");
		wordsA2C.put("While", "STICK AROUND");
		wordsA2C.put("EndWhile", "CHILL");
		wordsA2C.put("PlusOperator", "GET UP");
		wordsA2C.put("MinusOperator", "GET DOWN");
		wordsA2C.put("MultiplicationOperator", "YOU'RE FIRED");
		wordsA2C.put("DivisionOperator", "HE HAD TO SPLIT");
		wordsA2C.put("ModuloOperator", "I LET HIM GO");
		wordsA2C.put("EqualTo", "YOU ARE NOT YOU YOU ARE ME");
		wordsA2C.put("GreaterThan", "LET OFF SOME STEAM BENNET");
		wordsA2C.put("Or", "CONSIDER THAT A DIVORCE");
		wordsA2C.put("And", "KNOCK KNOCK");
		wordsA2C.put("DeclareInt", "HEY CHRISTMAS TREE");
		wordsA2C.put("SetInitialValue", "YOU SET US UP");
		wordsA2C.put("BeginMain", "IT'S SHOWTIME");
		wordsA2C.put("EndMain", "YOU HAVE BEEN TERMINATED");
		wordsA2C.put("Print", "TALK TO THE HAND");
		wordsA2C.put("AssignVariable", "GET TO THE CHOPPER");
		wordsA2C.put("SetValue", "HERE IS MY INVITATION");
		wordsA2C.put("EndAssignVariable", "ENOUGH TALK");

		Iterator<Map.Entry<String, String>> it = wordsA2C.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, String> pair = it.next();
			wordsC2A.put(pair.getValue(), pair.getKey());
		}
		
		System.out.println(wordsC2A);
	}

	public String getTranslationA2C(String word) {
		return wordsC2A.get(word);

	}

	public String getTranslationC2A(String word) {
		return wordsA2C.get(word);

	}

	public static void main(String[] args) {

		TranslateService service = new TranslateService();
		System.out.println(service.getTranslationA2C("IT'S SHOWTIME"));
	}

}
