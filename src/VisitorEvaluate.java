import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class VisitorEvaluate implements IVisitor {

	private TreeBuilder.VariableStates varstates = null;

	// private String result = null;
	AbstractResult result = new AbstractResult();
	
	public VisitorEvaluate(TreeBuilder.VariableStates varstates) {
		this.varstates = varstates;

	}

	@Override
	public AbstractResult visit(AbstractNode node) {
		

		if (node.getType().equals(Type.PRINT)) {
			NodePrint np = (NodePrint) node;
			if (np.getToPrint().getType().equals(Type.VARIABLE)) {
				String variableToPrint = (String) np.getToPrint().getNodeValue();
				List<Map<String, Integer>> printStates = varstates.getPrintStates();
				
				Iterator<Map<String, Integer>> elements = printStates.iterator();
				while(elements.hasNext()){
					Map<String, Integer> touple = elements.next();
					if(touple.containsKey(variableToPrint)){
						Variabila var = varstates.getVariabila(variableToPrint);
						System.out.println("VARIABLE TO PRINT: " + variableToPrint + " VAL: " + touple.get(variableToPrint) + " = " + var.getValoari().get(touple.get(variableToPrint)));
						result.addString(var.getValoari().get(touple.get(variableToPrint))+"\n");
						elements.remove();
						break;
					}
				}
				
			} else if (np.getToPrint().getType().equals(Type.STRING)) {
				String stringToPrint = np.getToPrint().getNodeValue().toString();
				result.addString(stringToPrint+"\n");
				System.out.println(stringToPrint);
			}

		}
		
		if (node.getType().equals(Type.IF)) {
			NodeIf nif = (NodeIf) node;
			NodePrint ifBody = nif.getIfBodyNode();
			NodePrint elseBody = nif.getElseBodyNode();
			NodeIf elseIfBody = nif.getElseBodyNodeIf();
			

			Variabila var = varstates.getVariabila(nif.getNodeValue().toString());
			System.out.println("VALORI: " + var.getValoari());
			Object value = var.getValoari().get(var.getValoari().size()-1);
			System.out.println("VALUE : " + value);
			if(value instanceof Integer){
				int v = (int)value;
				if(v != 0){
					System.out.println(ifBody.getToPrint().getNodeValue());
					result.addString(ifBody.getToPrint().getNodeValue().toString());
				}else{
					if(elseBody != null){
						System.out.println(elseBody.getToPrint().getNodeValue());
						result.addString(elseBody.getToPrint().getNodeValue().toString());
					}
					if(elseIfBody != null){
						visit(elseIfBody);
					}
				}
			}
		}
		
		if (node.getSubnodes() != null)
			for (AbstractNode child : node.getSubnodes()) {
				visit(child);
			}
		return result;
	}

}
