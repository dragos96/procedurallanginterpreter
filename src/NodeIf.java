
public class NodeIf extends AbstractNode{

	private NodePrint ifBodyNode;
	private NodePrint elseBodyNode;
	private NodeIf elseBodyNodeIf = null;
	
	public NodeIf(Object value) {
		super("IfNode", value);
	}

	@Override
	public Type getType() {
		return Type.IF;
	}

	public NodePrint getIfBodyNode() {
		return ifBodyNode;
	}

	public void setIfBodyNode(NodePrint ifBodyNode) {
		this.ifBodyNode = ifBodyNode;
	}

	public NodePrint getElseBodyNode() {
		return elseBodyNode;
	}

	public void setElseBodyNode(NodePrint elseBodyNode) {
		this.elseBodyNode = elseBodyNode;
	}

	public NodeIf getElseBodyNodeIf() {
		return elseBodyNodeIf;
	}

	public void setElseBodyNodeIf(NodeIf elseBodyNodeIf) {
		this.elseBodyNodeIf = elseBodyNodeIf;
	}
	
	

}
