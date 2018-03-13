public class MainNode extends AbstractNode{

	public MainNode() {
		super("MainNode", null);
	}


	@Override
	public void accept(IVisitor visitor) {
		
		for(AbstractNode node : getSubnodes()){
			visitor.visit(node);
		}
	}

	@Override
	public Type getType() {
		return Type.MAIN;
	}

	@Override
	public String toString() {
		return "MainNode [ops=" + getSubnodes() + "]";
	}
	
	
	
}
