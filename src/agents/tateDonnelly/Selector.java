package agents.tateDonnelly;

import java.util.List;

public class Selector extends Node{
	public Selector(List<Node> children)  {
		super(children);
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
