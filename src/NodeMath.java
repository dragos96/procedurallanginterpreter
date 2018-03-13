import java.util.List;

public abstract class NodeMath extends AbstractNode {

	protected AbstractNode rvalNodeLeft;
	protected AbstractNode rvalNodeRight;

	public NodeMath(String nodeName, Object value) {
		super(nodeName, value);

	}

	public AbstractNode getRvalNodeLeft() {
		return rvalNodeLeft;
	}

	public void setRvalNodeLeft(AbstractNode rvalNodeLeft) {
		this.rvalNodeLeft = rvalNodeLeft;
	}

	public AbstractNode getRvalNodeRight() {
		return rvalNodeRight;
	}

	public void setRvalNodeRight(AbstractNode rvalNodeRight) {
		this.rvalNodeRight = rvalNodeRight;
	}

	public static Integer evaluateSimpleNode(NodeMath nm, TreeBuilder.VariableStates varstates) {
		
		// TODO: evaluare fara variabile
		List<Object> valori1 = varstates.getVariabila(nm.getRvalNodeLeft().getNodeValue().toString()).getValoari();
		List<Object> valori2 = varstates.getVariabila(nm.getRvalNodeRight().getNodeValue().toString()).getValoari();
		System.out.println("v1: " + valori1 + " v2: " + valori2);

		Integer rval1Value = null;
		Integer rval2Value = null;

		for (int i = valori1.size() - 1; i >= 0; i--) {
			if (valori1.get(i) != null) {
				rval1Value = Integer.valueOf(valori1.get(i).toString());
				break;
			}
		}

		for (int i = valori2.size() - 1; i >= 0; i--) {
			if (valori2.get(i) != null) {
				rval2Value = Integer.valueOf(valori2.get(i).toString());
				break;
			}
		}

		if (nm.getType().equals(Type.SUM)) {

			return rval1Value + rval2Value;
		} else if (nm.getType().equals(Type.MULTIPLICATION)) {
			return rval1Value * rval2Value;
		}  else if (nm.getType().equals(Type.DIVISION)) {
			return rval1Value / rval2Value;
		}  else if (nm.getType().equals(Type.MODULO)) {
			return rval1Value % rval2Value;
		} else {
			return rval1Value - rval2Value;
		}
	}

	public static Integer evaluate(NodeMath nm, TreeBuilder.VariableStates varstates) {
		Integer result = 0;
		System.out.println("OPERATIE CURENTA: " + nm.getType());
		if (!nm.getRvalNodeLeft().getType().equals(Type.RVAL)) {

			if (nm.getRvalNodeLeft().getType().equals(Type.MULTIPLICATION)) {
				// complex node, evaluam subelementele
				System.out.println("INMULTIM " + result + " cu ceva");
				result *= evaluate((NodeMath) nm.getRvalNodeLeft(), varstates);
			} else if (nm.getRvalNodeLeft().getType().equals(Type.SUM)) {
				System.out.println("ADUNAM " + result + " cu ceva");
				result += evaluate((NodeMath) nm.getRvalNodeLeft(), varstates);
			}
		} else {

			int val = evaluateSimpleNode(nm, varstates);
			System.out.println("END POINT: " + result + " val: " + val);
			return val;
		}
		return result;

	}

}
