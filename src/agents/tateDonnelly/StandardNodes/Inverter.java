package agents.tateDonnelly.StandardNodes;

public class Inverter extends Node{
	Node node;
	
	public Inverter(Node node) {
		this.node = node;
	}
	
	@Override
	public NodeState Evaluate()
	{
		switch (node.Evaluate())
		{
			case NodeState.RUNNING:
				state = NodeState.RUNNING;
				break;
			case NodeState.SUCCESS:
				state = NodeState.FAILURE;
				break;
			case NodeState.FAILURE:
				state = NodeState.SUCCESS;
				return state;
			default:
				break;
		}
		return state;
	}
}
