package decision_trees;

public class TerminalNode implements DecisionNode {
	private String value;

	public TerminalNode(String value){
		this.value = value;
	}
}
