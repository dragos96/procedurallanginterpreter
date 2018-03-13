
public class NodeMathOr extends NodeMath{
	
	public NodeMathOr() {
		super("OrNode", null);
	}

	@Override
	public Type getType() {
		return Type.OR_MATH;
	}
}
