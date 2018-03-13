
public class NodeAssignment extends AbstractNode {

	private NodeLvl lvalue;
	private AbstractNode rval;
	
	
	
	public NodeAssignment(NodeLvl lvalue, AbstractNode rval) {
		super("AssignmentNode", null);
		this.lvalue = lvalue;
		this.rval = rval;
	}

	@Override
	public Type getType() {
		return Type.ASSIGNMENT;
	}

	public NodeLvl getLvalue() {
		return lvalue;
	}

	public void setLvalue(NodeLvl lvalue) {
		this.lvalue = lvalue;
	}

	public AbstractNode getRval() {
		return rval;
	}

	public void setRval(AbstractNode rval) {
		this.rval = rval;
	}
	
	
	
	
}
