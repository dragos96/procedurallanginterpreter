
public class NodeLvl extends AbstractNode{

	
	
	public NodeLvl(Object value) {
		super("LvalNode", value);
	}

	@Override
	public Type getType() {
		return Type.LVL;
	}

	
	
}
