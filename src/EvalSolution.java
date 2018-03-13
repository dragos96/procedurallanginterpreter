
import java.util.Map;

public class EvalSolution {

	private TreeBuilder.VariableStates varstates;

	public EvalSolution(TreeBuilder.VariableStates varstates) {
		this.varstates = varstates;
	}

	public void displaySolution() {
		System.out.println("STADII COMPLETE VARIABILE");
		TreeBuilder.VariableStates states = varstates;
		for (Map.Entry<Variabila, Integer> entry : states.getStari().entrySet()) {
			System.out.println(entry.getKey().getNume() + " / " + entry.getValue());
			System.out.println(entry.getKey().getValoari());
		}
		System.out.println("STADII PRINT VARIABILE");
		System.out.println(states.getPrintStates());

		for (Map.Entry<Variabila, Integer> entry : states.getStari().entrySet()) {
			int printStare = -1;
			for (Map<String, Integer> ps : varstates.getPrintStates()) {
				if (ps.containsKey(entry.getKey().getNume())) {
					printStare = ps.get(entry.getKey().getNume());
					System.out.println(
							"OK: " + entry.getKey().getValoari().get(printStare) + " - " + entry.getKey().getNume());
				}
			}
		}
	}

	public static String printOrNode(NodeOr or, String soFar, int level) {

		System.out.println("LEVEL: " + level);
		// System.out.println(or.getNodeName());
		for(int i=0; i<level; i++){
			soFar += "\t";
		}
		if(!or.getRval1().getType().equals(Type.OR)){
			soFar += or.getNodeName() + "x\n";
		}else{
			soFar += "TEST: " + or.getNodeName() + "\n";
		}
		if (!or.getRval1().getType().equals(Type.OR)) {
			
			for(int i=0; i<level; i++){
				soFar += "\t";
			}
			soFar += "\tRvalNode <" + or.getRval1().getNodeValue() + ">\n";
			for(int i=0; i<level; i++){
				soFar += "\t";
			}
			soFar +=  "\tRvalNode <"+ or.getRval2().getNodeValue() + ">\n";
			return soFar;
		}

		if (or.getRval1().getType().equals(Type.OR)) {
			soFar += printOrNode((NodeOr) or.getRval1(), soFar, level+1);
		}
		for(int i=0; i<level+1; i++){
			soFar += "\t";
		}
		soFar += "RvalNode <"+or.getRval2().getNodeValue() + ">\n";
		return soFar;
	}

}
