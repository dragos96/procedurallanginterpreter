

public class NodeVariable extends AbstractNode{

	private Object nodeVariableValue;
	
	/**
	 * 
	 * @param nodeVariableName numele nodului (i.e. numele variabilei)
	 * @param nodeVariableValue valoarea stocata de nodul variabila
	 */
	public NodeVariable(Object nodeVariableName, Object nodeVariableValue) {
		super("VariableNode", nodeVariableName);
		this.nodeVariableValue = nodeVariableValue;
		
	}

	@Override
	public Type getType() {
		return Type.VARIABLE;
	}

}

