import java.util.ArrayList;
import java.util.List;

public abstract class AbstractNode {

	private String nodeName;
	private Object nodeValue;
	
	private AbstractNode parentNode = null;
	private List<AbstractNode> subnodes = null;
	
	public void accept(IVisitor visitor){
		visitor.visit(this);
	}
	
	
	
	public AbstractNode getParentNode() {
		return parentNode;
	}



	public void setParentNode(AbstractNode parentNode) {
		this.parentNode = parentNode;
	}



	public void addSubnode(AbstractNode node){
		if(subnodes == null){
			subnodes = new ArrayList<>();
		}
		subnodes.add(node);
	}

	public AbstractNode(String nodeName, Object value){
		this.nodeName = nodeName;
		this.nodeValue = value;
	}

	public abstract Type getType();



	public String getNodeName() {
		return nodeName;
	}



	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}



	public Object getNodeValue() {
		return nodeValue;
	}



	public void setNodeValue(Object nodeValue) {
		this.nodeValue = nodeValue;
	}



	public List<AbstractNode> getSubnodes() {
		return subnodes;
	}



	public void setSubnodes(List<AbstractNode> subnodes) {
		this.subnodes = subnodes;
	}
	
	
	
}
