
public class NodeConstant extends AbstractNode{

	public NodeConstant(Object val) {
		super("ConstantNode", val);
	}

	@Override
	public Type getType() {
		return Type.CONSTANT;
	}

	
	
	
}
