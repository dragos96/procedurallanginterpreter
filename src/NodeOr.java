

public class NodeOr extends AbstractNode{

	private AbstractNode rval1;
	private AbstractNode rval2;
	
	public NodeOr() {
		super("OrNode", null);
	}

	@Override
	public Type getType() {
		return Type.OR;
	}

	public AbstractNode getRval1() {
		return rval1;
	}

	public void setRval1(AbstractNode rval) {
		this.rval1 = rval;
	}

	public AbstractNode getRval2() {
		return rval2;
	}

	public void setRval2(AbstractNode rval2) {
		this.rval2 = rval2;
	}

	@Override
	public String toString() {
		return "NodeOr [rval1=" + rval1 + ", rval2=" + rval2 + "]";
	}

	
	
}
