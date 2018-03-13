
public class NodeDeclare extends AbstractNode{

	private NodeLvl lvalNode;
	private NodeConstant constantNode;
	
	
	
	public NodeDeclare(NodeLvl lvalNode, NodeConstant constantNode) {
		super("DeclareNode", null);
		
		this.lvalNode = lvalNode;
		this.constantNode = constantNode;
		
	}



	@Override
	public Type getType() {
		return Type.DECLARE;
	}



	public NodeLvl getLvalNode() {
		return lvalNode;
	}



	public void setLvalNode(NodeLvl lvalNode) {
		this.lvalNode = lvalNode;
	}



	public NodeConstant getConstantNode() {
		return constantNode;
	}



	public void setConstantNode(NodeConstant constantNode) {
		this.constantNode = constantNode;
	}


	@Override
	public String toString() {
		return "NodeDeclare [lvalNode=" + lvalNode + ", constantNode=" + constantNode + "]";
	}

	
	
	
	
}
