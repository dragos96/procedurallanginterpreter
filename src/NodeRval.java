
public class NodeRval extends AbstractNode{

	public NodeRval(Object value) {
		super("RvalNode", value);
		
	}

	@Override
	public Type getType() {
		return Type.RVAL;
	}

}
