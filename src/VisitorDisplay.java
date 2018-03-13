import java.util.Arrays;
import java.util.List;

public class VisitorDisplay implements IVisitor {

	private DisplayResult result = null;
	public static int currentLevel = 0;

	public VisitorDisplay() {

		result = new DisplayResult();
	}

	@Override
	public DisplayResult visit(AbstractNode node) {
		// String result = "";

		if (node.getType().equals(Type.MAIN)) {

			MainNode main = (MainNode) node;
			result.addString("MainNode", 0);

			for (AbstractNode an : main.getSubnodes()) {
				DisplayResult opResult = visit(an);

			}
		} else if (node.getType().equals(Type.DECLARE)) {
			currentLevel++;
			result.addString("DeclareNode", currentLevel);
			NodeDeclare nodeD = (NodeDeclare) node;
			result.addString(
					nodeD.getLvalNode().getNodeName() + " <" + (String) nodeD.getLvalNode().getNodeValue() + ">",
					currentLevel + 1);
			result.addString(
					nodeD.getConstantNode().getNodeName() + " <" + nodeD.getConstantNode().getNodeValue() + ">",
					currentLevel + 1);
			currentLevel--;
		} else if (node.getType().equals(Type.PRINT)) {
			currentLevel++;
			NodePrint nodeP = (NodePrint) node;
			result.addString("PrintNode", currentLevel);
			AbstractNode toPrint = nodeP.getToPrint();
			result.addString(toPrint.getNodeName() + " <" + toPrint.getNodeValue() + ">", currentLevel + 1);
			currentLevel--;
		} else if (node.getType().equals(Type.ASSIGNMENT)) {
			currentLevel++;
			NodeAssignment na = (NodeAssignment) node;
			result.addString(na.getNodeName(), currentLevel);
			result.addString(na.getLvalue().getNodeName() + " <" + (String) na.getLvalue().getNodeValue() + ">",
					currentLevel + 1);

			if (na.getRval() != null) {
				result.addString(na.getRval().getNodeName() + " <" + (String) na.getRval().getNodeValue() + ">",
						currentLevel + 1);

			}

			if (na.getSubnodes() != null) {
				for (AbstractNode subNode : na.getSubnodes()) {
					visit(subNode);
				}
			}
			currentLevel--;
		} else if (node.getType().equals(Type.OR)) {
			currentLevel++;
			AbstractNode no = node;

			String nodeAsString = EvalSolution.printOrNode((NodeOr) no, "", currentLevel);
			result.addString(nodeAsString, currentLevel);
			currentLevel--;
		} else if (node.getType().equals(Type.CONSTANT)) {
			currentLevel++;
			NodeConstant nc = (NodeConstant) node;
			result.addString(nc.getNodeName() + " <" + nc.getNodeValue() + ">", currentLevel + 1);
			currentLevel--;
		} else if (node.getType().equals(Type.IF)) {
			currentLevel++;
			NodeIf nif = (NodeIf) node;
			result.addString(nif.getNodeName(), currentLevel);
			result.addString("ConditionNode <" + nif.getNodeValue() + ">", currentLevel + 1);
			result.addString("IfBodyNode", currentLevel + 1);
			NodePrint np = nif.getIfBodyNode();
			result.addString("PrintNode", currentLevel + 2);
			result.addString(np.getToPrint().getNodeName() + " <" + np.getToPrint().getNodeValue().toString() + ">",
					currentLevel + 3);

			if (nif.getElseBodyNode() != null) {
				result.addString("ElseBodyNode", currentLevel + 1);
				NodePrint npelse = nif.getElseBodyNode();
				result.addString("PrintNode", currentLevel + 2);
				result.addString(
						npelse.getToPrint().getNodeName() + " <" + npelse.getToPrint().getNodeValue().toString() + ">",
						currentLevel + 3);
			}

			if (nif.getElseBodyNodeIf() != null) {
				result.addString("ElseBodyNode", currentLevel + 1);
				currentLevel++;
				visit(nif.getElseBodyNodeIf());
			}

			currentLevel--;
		}else if(node.getType().equals(Type.EQUAL_TO) || node.getType().equals(Type.GT)
				|| node.getType().equals(Type.LT)|| node.getType().equals(Type.AND) || node.getType().equals(Type.OR_MATH)){
			System.out.println("TEST x1");
			NodeMath nm = (NodeMath) node;
			currentLevel++;
			result.addString(nm.getNodeName(), currentLevel);
			
			result.addString(nm.getRvalNodeLeft().getNodeName() + " <" + nm.getRvalNodeLeft().getNodeValue() + ">",
					currentLevel + 1);
			result.addString(
					nm.getRvalNodeRight().getNodeName() + " <" + nm.getRvalNodeRight().getNodeValue() + ">",
					currentLevel + 1);
			
			currentLevel--;
			
		}
		else if (node.getType().equals(Type.SUM) || node.getType().equals(Type.MULTIPLICATION)
				|| node.getType().equals(Type.MINUS) || node.getType().equals(Type.DIVISION)
				|| node.getType().equals(Type.MODULO)
				) {
			NodeMath nm = (NodeMath) node;
			currentLevel++;
			result.addString(nm.getNodeName(), currentLevel);
			// rval1, rval2
			if (nm.getRvalNodeLeft().getType().equals(Type.SUM)
					|| nm.getRvalNodeLeft().getType().equals(Type.MULTIPLICATION)
					|| nm.getRvalNodeLeft().getType().equals(Type.MINUS)
					|| nm.getRvalNodeLeft().getType().equals(Type.DIVISION)
					|| nm.getRvalNodeLeft().getType().equals(Type.MODULO)) {
				// TODO: afisare left
				visit(nm.getRvalNodeLeft());
				result.addString(
						nm.getRvalNodeRight().getNodeName() + " <" + nm.getRvalNodeRight().getNodeValue() + ">",
						currentLevel + 1);
			} else {
				result.addString(nm.getRvalNodeLeft().getNodeName() + " <" + nm.getRvalNodeLeft().getNodeValue() + ">",
						currentLevel + 1);

//				List<Type> logicalTypes = Arrays.asList(new Type[] { Type.EQUAL_TO, Type.AND, Type.GT, Type.LT });

				result.addString(
						nm.getRvalNodeRight().getNodeName() + " <" + nm.getRvalNodeRight().getNodeValue() + ">",
						currentLevel + 1);
			}

			currentLevel--;
		}
		return result;
	}

}
