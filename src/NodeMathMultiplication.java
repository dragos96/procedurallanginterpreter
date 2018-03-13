
public class NodeMathMultiplication extends NodeMath{

	public NodeMathMultiplication() {
		super("MultiplicationNode", null);
	}

	@Override
	public Type getType() {
		return Type.MULTIPLICATION;
	}

}
