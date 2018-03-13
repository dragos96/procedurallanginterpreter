
public class NodePrint extends AbstractNode{

	private AbstractNode toPrint;
	
	public NodePrint() {
		super("PrintNode", null);
	}

	
	
	public AbstractNode getToPrint() {
		return toPrint;
	}



	public void setToPrint(AbstractNode toPrint) {
		this.toPrint = toPrint;
	}



	@Override
	public Type getType() {
		return Type.PRINT;
	}

}

