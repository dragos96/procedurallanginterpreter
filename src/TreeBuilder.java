import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TreeBuilder {

	private MainNode main = null;
	private VariableStates states = null;

	public VariableStates getStates() {
		return states;
	}

	public TreeBuilder() {
		states = new VariableStates();
	}

	public static class VariableStates {

		private Map<Variabila, Integer> stari = new HashMap<>();

		private List<Map<String, Integer>> printStates = new ArrayList<>();

		public void incrementStare(Variabila var, Object valoare) {
			if (!stari.containsKey(var)) {
				stari.put(var, 0);
			} else {
				stari.put(var, stari.get(var) + 1);
			}

			this.adaugaValoare(var.getNume(), valoare);
		}

		public void addPrintState(String varName, int stare) {
			Map<String, Integer> st = new HashMap<>();
			st.put(varName, stare);
			printStates.add(st);
		}

		public Variabila getVariabila(String nume) {

			for (Map.Entry<Variabila, Integer> entry : stari.entrySet()) {
				// System.out.println(entry.getKey() + "/" + entry.getValue());
				if (entry.getKey().getNume().equals(nume)) {
					return entry.getKey();
				}
			}
			return null;
		}

		public void adaugaValoare(String numeVariabila, Object valoare) {
			Variabila var = getVariabila(numeVariabila);
			var.addValoare(valoare);
		}

		public Map<Variabila, Integer> getStari() {
			return stari;
		}

		public List<Map<String, Integer>> getPrintStates() {
			return printStates;
		}

	}

	public static String getLastElement(String str) {
		String[] elems = str.split(" ");
		return elems[elems.length - 1];
	}

	public static NodeOr construct(NodeRval rval1, NodeRval rval2) {
		NodeOr or = new NodeOr();
		or.setRval1(rval1);
		or.setRval2(rval2);
		return or;
	}

	public static NodeOr construct(NodeOr rval1, NodeRval rval2) {
		NodeOr or = new NodeOr();
		or.setRval1(rval1);
		or.setRval2(rval2);
		return or;
	}

	public static NodeOr buildOr(List<String> variableNames) {

		List<NodeOr> ors = new ArrayList<>();
		int indexOrs = 0;
		for (int i = 0; i < variableNames.size(); i++) {
			System.out.println("Luam variabila: " + variableNames.get(i));
			System.out.println("ORS: " + ors.size());
			if (i == 0) {
				System.out.println("R R");
				NodeOr nRR = construct(new NodeRval(variableNames.get(0)), new NodeRval(variableNames.get(1)));
				i += 1;
				ors.add(nRR);
			} else {
				System.out.println("O R " + i);
				System.out.println("DE LA INDEX: " + indexOrs);
				System.out.println("NUME VAR: " + variableNames.get(i));
				NodeOr nOR = construct(ors.get(ors.size() - 1), new NodeRval(variableNames.get(i)));
				ors.add(nOR);
			}
			indexOrs++;
		}

		return ors.get(ors.size() - 1);
	}

	// XXX 1
	public static NodeMath buildMathSingleOperation(String variableNameHMI, List<String> comenzi, int position) {
		System.out.println("SO");
		List<String> variables = new ArrayList<>();
		int endIndex = position;
		while (!comenzi.get(endIndex).contains("ENOUGH TALK")) {
			endIndex++;
		}
		for (int i = endIndex - 1; i >= position; i--) {
			variables.add(getLastElement(comenzi.get(i)));
		}

		String numeComanda = comenzi.get(position);
		System.out.println("NUME COMANDA: " + numeComanda);
		NodeMath nm = null;
		if (numeComanda.contains("GET UP")) {
			nm = new NodeMathSum();
			String var1Name = getLastElement(comenzi.get(position - 1));
			String var2Name = getLastElement(comenzi.get(position));

			System.out.println("VAR 1 name: " + var1Name + " VAR 2 name: " + var2Name);
			nm.setRvalNodeLeft(new NodeRval(var1Name));
			nm.setRvalNodeRight(new NodeRval(var2Name));
		} else if (numeComanda.contains("YOU'RE FIRED")) {
			nm = new NodeMathMultiplication();
			String var1Name = getLastElement(comenzi.get(position - 1));
			String var2Name = getLastElement(comenzi.get(position));

			System.out.println("VAR 1 name: " + var1Name + " VAR 2 name: " + var2Name);
			nm.setRvalNodeLeft(new NodeRval(var1Name));
			nm.setRvalNodeRight(new NodeRval(var2Name));
		} else if (numeComanda.contains("GET DOWN")) {
			nm = new NodeMathDiff();
			String var1Name = getLastElement(comenzi.get(position - 1));
			String var2Name = getLastElement(comenzi.get(position));

			System.out.println("VAR 1 name: " + var1Name + " VAR 2 name: " + var2Name);
			nm.setRvalNodeLeft(new NodeRval(var1Name));
			nm.setRvalNodeRight(new NodeRval(var2Name));
		} else if (numeComanda.contains("HE HAD TO SPLIT")) {
			nm = new NodeMathDivision();
			String var1Name = getLastElement(comenzi.get(position - 1));
			String var2Name = getLastElement(comenzi.get(position));

			System.out.println("DIVISION VAR 1 name: " + var1Name + " VAR 2 name: " + var2Name);
			nm.setRvalNodeLeft(new NodeRval(var1Name));
			nm.setRvalNodeRight(new NodeRval(var2Name));
		} else if (numeComanda.contains("I LET HIM GO")) {
			nm = new NodeMathModulo();
			String var1Name = getLastElement(comenzi.get(position - 1));
			String var2Name = getLastElement(comenzi.get(position));

			System.out.println("VAR 1 name: " + var1Name + " VAR 2 name: " + var2Name);
			nm.setRvalNodeLeft(new NodeRval(var1Name));
			nm.setRvalNodeRight(new NodeRval(var2Name));

		}
		/* LOGICAL OPS */

		else if (numeComanda.contains("YOU ARE NOT YOU YOU ARE ME")) {
			nm = new NodeMathEqualTo();
			String var1Name = getLastElement(comenzi.get(position - 1));
			String var2Name = getLastElement(comenzi.get(position));

			System.out.println("EQUAL TO >>  VAR 1 name: " + var1Name + " VAR 2 name: " + var2Name);
			nm.setRvalNodeLeft(new NodeRval(var1Name));
			nm.setRvalNodeRight(new NodeRval(var2Name));
		} else if (numeComanda.contains("LET OFF SOME STEAM BENNET")) {
			nm = new NodeMathGT();
			String var1Name = getLastElement(comenzi.get(position - 1));
			String var2Name = getLastElement(comenzi.get(position));

			System.out.println("GREATER THAN >> VAR 1 name: " + var1Name + " VAR 2 name: " + var2Name);
			nm.setRvalNodeLeft(new NodeRval(var1Name));
			nm.setRvalNodeRight(new NodeRval(var2Name));
		} else if (numeComanda.contains("KNOCK KNOCK")) {
			nm = new NodeMathAnd();
			String var1Name = getLastElement(comenzi.get(position - 1));
			String var2Name = getLastElement(comenzi.get(position));

			System.out.println("AND >> VAR 1 name: " + var1Name + " VAR 2 name: " + var2Name);
			nm.setRvalNodeLeft(new NodeRval(var1Name));
			nm.setRvalNodeRight(new NodeRval(var2Name));
		} else {
			System.out.println("UNRECOGNIZED COMMAND: " + numeComanda);
		}

		// TODO: increment state
		// TODO:

		return nm;
	}

	public static NodeMath buildMathOpsTwoOperations(String variableNameHMI, List<String> comenzi, int position) {

		List<String> variables = new ArrayList<>();
		int endIndex = position;
		while (!comenzi.get(endIndex).contains("ENOUGH TALK")) {
			endIndex++;
		}
		for (int i = endIndex - 1; i >= position; i--) {
			variables.add(getLastElement(comenzi.get(i)));

		}

		System.out.println("VARIABILE OPERATII: " + variables);
		NodeMathSum nodeSum = new NodeMathSum();
		nodeSum.setRvalNodeLeft(new NodeRval(variableNameHMI));
		nodeSum.setRvalNodeRight(new NodeRval(variables.get(variables.size() - 1)));

		NodeMathMultiplication multiplication = new NodeMathMultiplication();
		multiplication.setRvalNodeLeft(nodeSum);
		multiplication.setRvalNodeRight(new NodeConstant(variables.get(0)));
		return multiplication;
	}

	public static NodeIf buildIf(List<String> comenzi, int pozitieCurenta) {

		String varName = getLastElement(comenzi.get(pozitieCurenta));
		NodeIf nodNou = new NodeIf(varName);
		nodNou.setNodeValue(varName);

		if (comenzi.get(pozitieCurenta + 1).contains("\"")) {

			// String Node
			NodePrint np = new NodePrint();
			String comanda = comenzi.get(pozitieCurenta + 1);
			String sir = comanda.substring(comanda.indexOf('"') + 1, comanda.length() - 1);

			NodeString ns = new NodeString(sir);
			np.setToPrint(ns);
			nodNou.setIfBodyNode(np);
		}

		if (comenzi.get(pozitieCurenta + 2).contains("BULLSHIT")) {
			if (comenzi.get(pozitieCurenta + 3).contains("\"")) {
				NodePrint np2 = new NodePrint();
				String comanda2 = comenzi.get(pozitieCurenta + 3);
				String sir2 = comanda2.substring(comanda2.indexOf('"') + 1, comanda2.length() - 1);
				np2.setToPrint(new NodeString(sir2));
				nodNou.setElseBodyNode(np2);
			} else if (comenzi.get(pozitieCurenta + 3).contains("BECAUSE I'M GOING TO SAY PLEASE")) {
				nodNou.setElseBodyNodeIf(buildIf(comenzi, pozitieCurenta + 3));
			}
		}
		return nodNou;
	}

	public static void main(String[] args) {
		List<String> vars = new ArrayList<>();
		vars.addAll(Arrays.asList(new String[] { "a", "b", "c" }));
		NodeOr nor = buildOr(vars);
		System.out.println(nor);
	}

	public boolean build(List<String> comenzi, int pozitie, AbstractNode nodCurent, AbstractNode previousNode) {

		System.out.println("POZITIE curenta: " + pozitie);

		if (pozitie >= comenzi.size()) {
			return true;
		}

		if (comenzi.get(pozitie).contains("ENOUGH TALK")) {
			System.out.println("SHOULD STOP AT POZITIE: " + pozitie);
			System.out.println("NOD CURENT: " + nodCurent);
			build(comenzi, pozitie + 1, nodCurent, null);
			return true;
		}

		if (comenzi.get(pozitie).contains("IT'S SHOWTIME")) {

			main = new MainNode();
			System.out.println("MAIN NODE");
			build(comenzi, pozitie + 1, main, null);
		} else if (comenzi.get(pozitie).contains("HEY CHRISTMAS TREE")) {
			String elems[] = comenzi.get(pozitie).split(" ");
			String varName = elems[elems.length - 1];
			NodeLvl lvl = new NodeLvl(varName);

			// adaugam variabila la cele existente deja
			NodeConstant constant = null;
			// variabile.add(new NodeVariable(null, varName));

			String youSetUsUp = comenzi.get(pozitie + 1);

			if (youSetUsUp.contains("@")) { // could be a @MACRO
				String value = youSetUsUp.split("@")[1];
				constant = new NodeConstant(value.equals("I LIED") ? 0 : 1);
			} else {
				String nextComElements[] = youSetUsUp.split(" ");
				constant = new NodeConstant(nextComElements[nextComElements.length - 1]);

			}

			NodeDeclare nodeDeclare = new NodeDeclare(lvl, constant);
			Variabila var1 = new Variabila();
			var1.setNume(varName);
			states.incrementStare(var1, constant.getNodeValue());

			if (nodCurent != null) {
				nodCurent.addSubnode(nodeDeclare);
				nodeDeclare.setParentNode(nodCurent);
			} else {
				// main.ops.add(nodeDeclare);
				// nodeDeclare.setParentNode(main);
				main.addSubnode(nodeDeclare);
				nodeDeclare.setParentNode(main);
			}
			build(comenzi, pozitie + 2, nodCurent, null);
		} else if (comenzi.get(pozitie).contains("GET TO THE CHOPPER")) {
			String varName = getLastElement(comenzi.get(pozitie));
			NodeLvl lv = new NodeLvl(varName);
			NodeAssignment na = new NodeAssignment(lv, null);

			Variabila var1 = new Variabila();
			var1.setNume(varName);
			states.incrementStare(var1, null);

			nodCurent.addSubnode(na);
			na.setParentNode(nodCurent);

			boolean shouldStop = false;
			int pozitiiSarite = 0;
			do {
				pozitiiSarite++;
				shouldStop = build(comenzi, pozitie + 1, na, null);
				System.out.println("GC SHOULD STOP = " + shouldStop);
			} while (!shouldStop);
			System.out.println("GC NOD CURENT: " + nodCurent);
			System.out.println("GC POZITII SARITE: " + pozitiiSarite);
			System.out.println("CONTINUAM BUILDul DE LA " + pozitiiSarite + 2);
			build(comenzi, pozitie + pozitiiSarite + 2, nodCurent, null);

		} else if (comenzi.get(pozitie).contains("GET UP")) {
			// TODO: VARSTATE LAST

		} else if (comenzi.get(pozitie).contains("CONSIDER THAT A DIVORCE")) {

			System.out.println("CONSIDER THAT A DIVORCE ********************************");

			NodeOr or = new NodeOr();

			switch (nodCurent.getType()) {
			case ASSIGNMENT:
				NodeAssignment naParent = (NodeAssignment) nodCurent;
				naParent.addSubnode(or);
				or.setParentNode(naParent);
				break;
			case OR:
				NodeOr orParent = (NodeOr) nodCurent;
				orParent.setRval1(or);
				break;
			default:
				break;
			}

			boolean shouldStop = false;
			int pozitiiSarite = 0;
			do {
				pozitiiSarite++;
				shouldStop = build(comenzi, pozitie + 1, or, null);
				System.out.println("OR SHOULD STOP = " + shouldStop);
			} while (!shouldStop);
			System.out.println("OR NOD CURENT: " + nodCurent);
			System.out.println("OR POZITII SARITE: " + pozitiiSarite);
			return build(comenzi, pozitie + pozitiiSarite, nodCurent, null);
			// return true;

		} else if (comenzi.get(pozitie).contains("TALK TO THE HAND")) {
			System.out.println("TALK TO");
			NodePrint np = new NodePrint();
			if (comenzi.get(pozitie).contains("\"")) {
				// String Node
				String comanda = comenzi.get(pozitie);
				String sir = comanda.substring(comanda.indexOf('"') + 1, comanda.length() - 1);

				NodeString ns = new NodeString(sir);
				np.setToPrint(ns);

			} else {
				// Variable Node
				String varName = getLastElement(comenzi.get(pozitie));
				NodeVariable nv = new NodeVariable(varName, null);

				int starePrint = states.getStari().get(states.getVariabila(varName));
				// states.incrementPrintState(states.getVariabila(varName));
				states.addPrintState(varName, starePrint);
				np.setToPrint(nv);

			}
			if (nodCurent != null) {
				np.setParentNode(nodCurent);
				nodCurent.addSubnode(np);
			}
			build(comenzi, pozitie + 1, nodCurent, null);
		} else if (comenzi.get(pozitie).contains("HERE IS MY INVITATION")) {
			System.out.println("HMA: " + comenzi.get(pozitie + 1));
			String last = getLastElement(comenzi.get(pozitie));

			boolean esteNumar = true;
			int numar = -1;
			try {
				numar = Integer.valueOf(last);
			} catch (NumberFormatException e) {
				esteNumar = false;
				// nu avem un numar, avem o variabila => pot sa fie alte
				// subcomenzi sub "HERE IS MY INVITATION"
			}

			if (esteNumar) {
				System.out.println("HMA 2: " + numar);
				NodeAssignment parinte = (NodeAssignment) nodCurent;
				Variabila var1 = new Variabila(parinte.getLvalue().getNodeValue().toString());
				// nu incrementam starea pana nu aflam daca exista alte operatii
				if (!comenzi.get(pozitie + 1).contains("ENOUGH TALK")) {
					System.out.println("DIVORCE TEST !" + comenzi.get(pozitie + 1));
					// mai aplicam o operatie (et, gt, ...)
					if (comenzi.get(pozitie + 1).contains("YOU ARE NOT YOU YOU ARE ME")
							|| comenzi.get(pozitie + 1).contains("KNOCK KNOCK")
							|| comenzi.get(pozitie + 1).contains("LET OFF SOME STEAM BENNET")
							|| comenzi.get(pozitie + 1).contains("CONSIDER THAT A DIVORCE")

							|| comenzi.get(pozitie + 1).contains("GET UP")
							|| comenzi.get(pozitie + 1).contains("GET DOWN")
							|| comenzi.get(pozitie + 1).contains("YOU'RE FIRED")
							|| comenzi.get(pozitie + 1).contains("HE HAD TO SPLIT")
							|| comenzi.get(pozitie + 1).contains("I LET HIM GO")) {
						NodeMath nm = null;
						System.out.println("HMA 3: ");
						if (comenzi.get(pozitie + 1).contains("YOU ARE NOT YOU YOU ARE ME")) {
							nm = new NodeMathEqualTo();
						} else if (comenzi.get(pozitie + 1).contains("KNOCK KNOCK")) {
							nm = new NodeMathAnd();
						}
						if (comenzi.get(pozitie + 1).contains("LET OFF SOME STEAM BENNET")) {
							nm = new NodeMathGT();
						}
						if (comenzi.get(pozitie + 1).contains("CONSIDER THAT A DIVORCE")) {
							nm = new NodeMathOr();

						}
						if (comenzi.get(pozitie + 1).contains("GET UP")) {
							nm = new NodeMathSum();
						}
						if (comenzi.get(pozitie + 1).contains("GET DOWN")) {
							nm = new NodeMathDiff();
						}
						if (comenzi.get(pozitie + 1).contains("YOU'RE FIRED")) {
							nm = new NodeMathMultiplication();
						}
						if (comenzi.get(pozitie + 1).contains("HE HAD TO SPLIT")) {
							nm = new NodeMathDivision();
						}
						if (comenzi.get(pozitie + 1).contains("I LET HIM GO")) {
							nm = new NodeMathModulo();
						}

						NodeConstant nc1 = new NodeConstant("" + numar);
						NodeConstant nc2 = new NodeConstant(
								"" + Integer.valueOf(getLastElement(comenzi.get(pozitie + 1))));

						System.out.println("EQUAL TO >>  VAR 1 name: " + nc1 + " VAR 2 name: " + nc2);
						nm.setRvalNodeLeft(nc1);
						nm.setRvalNodeRight(nc2);

						if (comenzi.get(pozitie + 1).contains("YOU ARE NOT YOU YOU ARE ME")) {
							if (numar == Integer.valueOf(getLastElement(comenzi.get(pozitie + 1)))) {
								states.incrementStare(var1, 1);
							} else {
								states.incrementStare(var1, 0);
							}
						} else if (comenzi.get(pozitie + 1).contains("KNOCK KNOCK")) {
							if (numar > 0 && Integer.valueOf(getLastElement(comenzi.get(pozitie + 1))) > 0) {
								states.incrementStare(var1, 1);
							} else {
								states.incrementStare(var1, 0);
							}
						} else if (comenzi.get(pozitie + 1).contains("CONSIDER THAT A DIVORCE")) {
							if (numar > 0 || Integer.valueOf(getLastElement(comenzi.get(pozitie + 1))) > 0) {
								states.incrementStare(var1, 1);
							} else {
								states.incrementStare(var1, 0);
							}
						} else if (comenzi.get(pozitie + 1).contains("LET OFF SOME STEAM BENNET")) {
							if (numar > Integer.valueOf(getLastElement(comenzi.get(pozitie + 1)))) {
								states.incrementStare(var1, 1);
							} else {
								states.incrementStare(var1, 0);
							}

						} else if (comenzi.get(pozitie + 1).contains("GET UP")) {
							if (numar > Integer.valueOf(getLastElement(comenzi.get(pozitie + 1)))) {
								states.incrementStare(var1, 1);
							} else {
								states.incrementStare(var1, 0);
							}
						} else if (comenzi.get(pozitie + 1).contains("GET DOWN")) {
							if (numar > Integer.valueOf(getLastElement(comenzi.get(pozitie + 1)))) {
								states.incrementStare(var1, 1);
							} else {
								states.incrementStare(var1, 0);
							}
						} else if (comenzi.get(pozitie + 1).contains("YOU'RE FIRED")) {
							if (numar > Integer.valueOf(getLastElement(comenzi.get(pozitie + 1)))) {
								states.incrementStare(var1, 1);
							} else {
								states.incrementStare(var1, 0);
							}
						} else if (comenzi.get(pozitie + 1).contains("HE HAD TO SPLIT")) {
							if (numar > Integer.valueOf(getLastElement(comenzi.get(pozitie + 1)))) {
								states.incrementStare(var1, 1);
							} else {
								states.incrementStare(var1, 0);
							}
						} else if (comenzi.get(pozitie + 1).contains("I LET HIM GO")) {
							if (numar > Integer.valueOf(getLastElement(comenzi.get(pozitie + 1)))) {
								states.incrementStare(var1, 1);
							} else {
								states.incrementStare(var1, 0);
							}
						}

						parinte.addSubnode(nm);
					}
					if (comenzi.get(pozitie + 1).contains("LET OFF SOME STEAM BENNET")) {

					}
					if (comenzi.get(pozitie + 1).contains("KNOCK KNOCK")) {

					}
				} else {

					NodeConstant nc = new NodeConstant("" + numar);
					System.out.println("NUME VARIABILA: " + parinte.getLvalue().getNodeValue());

					states.incrementStare(var1, numar);
					parinte.setRval(nc);
				}

				return true;
			}

			else { // putem avea o expresie GET TO THE CHOPPER d
					// HERE IS MY INVITATION a
					// CONSIDER THAT A DIVORCE b
					// CONSIDER THAT A DIVORCE c
					// ENOUGH TALK

				Variabila valoare = states.getVariabila(last); // vh.getVariabileValue(last);

				NodeAssignment parinte = (NodeAssignment) nodCurent;
				System.out.println("VALOARE: " + valoare.getNume());
				Variabila var1 = new Variabila();
				var1.setNume(parinte.getLvalue().getNodeValue().toString());
				states.incrementStare(var1, valoare.getValoari().get(valoare.getValoari().size() - 1)); // TODO:
																										// CHECK!!!!

				// VERIFICARE multipla CTAD
				List<String> vars = new ArrayList<>();
				vars.add(getLastElement(comenzi.get(pozitie)));
				int idx = pozitie + 1;
				boolean or = false;
				boolean math = false;
				while (comenzi.get(idx).contains("CONSIDER THAT A DIVORCE")) {
					vars.add(getLastElement(comenzi.get(idx)));
					idx++;
					or = true;
				}

				if (or) {
					NodeOr nor = buildOr(vars);
					parinte.addSubnode(nor);
					return true;

				}

				// XXX 2
				while (comenzi.get(idx).contains("GET UP") || comenzi.get(idx).contains("YOU'RE FIRED")
						|| comenzi.get(idx).contains("GET DOWN") || comenzi.get(idx).contains("HE HAD TO SPLIT")
						|| comenzi.get(idx).contains("I LET HIM GO")

						|| comenzi.get(idx).contains("YOU ARE NOT YOU YOU ARE ME")
						|| comenzi.get(idx).contains("LET OFF SOME STEAM BENNET")
						|| comenzi.get(idx).contains("KNOCK KNOCK")

				) {
					idx++;
					math = true;
				}

				System.out.println("LUNGIME INDEX: " + (idx - pozitie - 1));

				NodeMath op = null;
				if ((idx - pozitie - 1) == 0) {
					System.out.println("LAST ELEMENT HERE: " + comenzi.get(pozitie));
					// NodeConstant nc = new NodeConstant(val)
					boolean isNr = true;
					Integer nr = null;
					try {
						nr = Integer.valueOf(getLastElement(comenzi.get(pozitie)));
					} catch (Exception e) {
						isNr = false;
					}
					Integer result = null;
					if(isNr){
						System.out.println("IS NR");
						NodeConstant nc = new NodeConstant(getLastElement(comenzi.get(pozitie)));
						result = nr;
						nc.setParentNode(parinte);
						parinte.addSubnode(nc);
					}else{
						System.out.println("IS NOT NR");
						System.out.println("NOD PARINTE: " + parinte);
						NodeRval nrv = new NodeRval(getLastElement(comenzi.get(pozitie)));
						List<Object> valori1 = states.getVariabila(getLastElement(comenzi.get(pozitie))).getValoari();
						for (int i = valori1.size() - 1; i >= 0; i--) {
							if (valori1.get(i) != null) {
								result = Integer.valueOf(valori1.get(i).toString());
								break;
							}
						}
						NodeAssignment nas = (NodeAssignment)parinte;
						parinte.setRval(nrv);
						
//						nrv.setParentNode(parinte);
//						parinte.addSubnode(nrv);
					}
					
					Variabila lastVar = states.getVariabila(getLastElement(comenzi.get(pozitie - 1)));
					states.incrementStare(lastVar, result); // TODO: CHECK!!!!
					return true;
					
				}else
				if ((idx - pozitie - 1) == 1) { // o singura operatie: a = (b +
												// c) -> 40
					System.out.println("CALLING SINGLE OP: " + comenzi.get(pozitie));
					op = buildMathSingleOperation(getLastElement(comenzi.get(pozitie)), comenzi, pozitie + 1);
				} else { // operatii imbricate: myVar = (myVar + myVar) * 5 ->
							// 30
					op = buildMathOpsTwoOperations(getLastElement(comenzi.get(pozitie)), comenzi, pozitie + 1);
				}

				System.out.println("OP: " + op);
				// TODO: add state
				System.out.println("OP LEFT: " + op.getRvalNodeLeft().getType());

				Integer result = null;

				if (op.getRvalNodeLeft().getType().equals(Type.SUM)
						|| op.getRvalNodeLeft().getType().equals(Type.MULTIPLICATION)
						|| op.getRvalNodeLeft().getType().equals(Type.MINUS)
						|| op.getRvalNodeLeft().getType().equals(Type.AND)
						|| op.getRvalNodeLeft().getType().equals(Type.GT)
						|| op.getRvalNodeLeft().getType().equals(Type.MODULO)
						|| op.getRvalNodeLeft().getType().equals(Type.DIVISION)
						|| op.getRvalNodeLeft().getType().equals(Type.LT)
						|| op.getRvalNodeLeft().getType().equals(Type.EQUAL_TO)) {
					// complex op
					Integer valOperatieSubelemente = NodeMath.evaluate(op, states); // subnodes

					System.out.println("VAL OPERATIE: " + valOperatieSubelemente + " PENTRU: " + op.getNodeName());
					System.out.println("POZITIE CURENTA xx: " + pozitie);
					AbstractNode operand2 = op.getRvalNodeRight();
					Integer valoareOperand2 = null;
					if (operand2.getType().equals(Type.CONSTANT)) {
						// valoare cu care inmultim sau adunam
						System.out.println("OP 2: " + operand2.getNodeName() + " " + operand2.getNodeValue());
						boolean variabila = false;
						try {
							Integer.valueOf("" + operand2.getNodeValue());
						} catch (Exception e) {
							// e.printStackTrace();
							variabila = true;
						}

						if (!variabila) {
							valoareOperand2 = Integer.valueOf("" + operand2.getNodeValue());
						} else {
							// laum ultima valoare a variabilei
							String varname = operand2.getNodeValue().toString();
							List<Object> valori1 = states.getVariabila(varname).getValoari();
							for (int i = valori1.size() - 1; i >= 0; i--) {
								if (valori1.get(i) != null) {
									valoareOperand2 = Integer.valueOf(valori1.get(i).toString());
									break;
								}
							}
						}
						// XXX valoare variabila
					} else {

						String varname = operand2.getNodeValue().toString();
						List<Object> valori1 = states.getVariabila(varname).getValoari();
						for (int i = valori1.size() - 1; i >= 0; i--) {
							if (valori1.get(i) != null) {
								valoareOperand2 = Integer.valueOf(valori1.get(i).toString());
								break;
							}
						}
					}

					if (op.getType().equals(Type.SUM)) {
						result = valOperatieSubelemente + valoareOperand2;
					} else if (op.getType().equals(Type.MULTIPLICATION)) {
						result = valOperatieSubelemente * valoareOperand2;
					} else if (op.getType().equals(Type.DIVISION)) {
						result = valOperatieSubelemente / valoareOperand2;
					} else if (op.getType().equals(Type.MODULO)) {
						result = valOperatieSubelemente % valoareOperand2;
					}

				} else {
					System.out.println("EEE: " + op.getRvalNodeLeft().getType());
					// simple op => rval1 + rval2 /// rval1 * rval2

					result = NodeMath.evaluateSimpleNode(op, states);
					System.out.println("EVALUARE X2 PENTRU: " + op.getNodeName() + " "
							+ op.getRvalNodeLeft().getNodeValue() + " " + op.getRvalNodeRight().getNodeValue());
					System.out.println("REZULTAT: " + result);
				}

				// TODO: de updatat valoare variabilei in cazul in care avem o
				// variabila setata
				System.out.println("REZULTAT (trebuie pus in variabila): " + result + " IN " + valoare.getNume());
				Variabila lastVar = states.getVariabila(getLastElement(comenzi.get(pozitie - 1)));
				states.incrementStare(lastVar, result); // TODO: CHECK!!!!

				op.setParentNode(parinte);
				parinte.addSubnode(op);
				return true;

			}
		} else if (comenzi.get(pozitie).contains("BECAUSE I'M GOING TO SAY PLEASE")) {
			NodeIf nif = buildIf(comenzi, pozitie);

			nif.setParentNode(nodCurent);
			nodCurent.addSubnode(nif);

			// YOU HAVE NO RESPECT FOR LOGIC
			// build(comenzi, pozitie + (bullshit ? 5 : 3), nodCurent, null);
			// TODO: call BUILD!
		}

		else {
			build(comenzi, pozitie + 1, nodCurent, null);
		}
		System.out.println("STEP 2");
		return false;

	}

	public MainNode getMain() {
		return main;
	}

}
