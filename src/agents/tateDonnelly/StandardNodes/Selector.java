package agents.tateDonnelly.StandardNodes;

import agents.tateDonnelly.StandardNodes.Node;
import agents.tateDonnelly.StandardNodes.NodeState;

import java.util.List;

public class Selector extends Node {
	boolean randomize;
	
	public Selector(List<Node> children, boolean randomize)  {
		super(children);
		this.randomize=randomize;
	}
	
	@Override
	public NodeState Evaluate()
	{
		for (Node node : children)
		{
			switch (node.Evaluate())
			{
				case NodeState.FAILURE:
					continue;
				case NodeState.SUCCESS:
					state = NodeState.SUCCESS;
					return state;
				case NodeState.RUNNING:
					state = NodeState.RUNNING;
					return state;
				default:
					continue;
			}
		}
		
		state = NodeState.FAILURE;
		return state;
	}
}
