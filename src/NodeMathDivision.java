
public class NodeMathDivision extends NodeMath{

	public NodeMathDivision() {
		super("DivisionNode", null);
	}

	@Override
	public Type getType() {
		return Type.DIVISION;
	}
}