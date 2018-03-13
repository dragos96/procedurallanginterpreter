
public class NodeString extends AbstractNode{

	public NodeString(Object value) {
		super("StringNode", value);
		
	}

	@Override
	public Type getType() {
		return Type.STRING;
	}

}
