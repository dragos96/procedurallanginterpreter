import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Program {

	public static TreeBuilder tb = null;

	public static void main(String[] args) throws Exception {


		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(args[0])));
		List<String> comenzi = new ArrayList<>();
		String linie = null;
		while ((linie = br.readLine()) != null) {
			comenzi.add(linie);
		}
		
		comenzi = FiltrareInput.filtrare(comenzi);

		tb = new TreeBuilder();
		tb.build(comenzi, 0, null, null);
		System.out.println("END BUILD");
		MainNode main = tb.getMain();
		System.out.println("-------------APPLICATION TREE-----------");
		
		VisitorDisplay displayVisitor = new VisitorDisplay();
		// REZULTAT ARBORE
		String treeResult = displayVisitor.visit(main).text.toString();
		System.out.println(treeResult);
		System.out.println("-------------STATES---------------");
		EvalSolution eval = new EvalSolution(tb.getStates());
//		eval.displaySolution();
		System.out.println("----------------------------");
		System.out.println("END");
		VisitorEvaluate ve = new VisitorEvaluate(tb.getStates());
		AbstractResult ar = ve.visit(main);
		System.out.println("-----------------SOLUTION-----------------------");
		// REZULTAT APLICATIE
		System.out.println(ar.getResult());
	}

}
