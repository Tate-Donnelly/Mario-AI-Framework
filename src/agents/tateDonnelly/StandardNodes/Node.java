package agents.tateDonnelly.StandardNodes;

import java.util.ArrayList;
import java.util.List;

public abstract class Node {
	
	public NodeState state;
	public Node parent;
	public List<Node> children=new ArrayList<>();
	
	public Node(List<Node> nodes){
		children=nodes;
	}
	
	protected Node(){
		parent=null;
	}
	
	public abstract NodeState Evaluate();
}
