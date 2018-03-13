
public class NodeMathAnd extends NodeMath{

	public NodeMathAnd() {
		super("AndNode", null);
	}

	@Override
	public Type getType() {
		return Type.AND;
	}

	
	
}
