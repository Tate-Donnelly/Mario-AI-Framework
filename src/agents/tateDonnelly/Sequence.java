package agents.tateDonnelly;

import java.util.List;

public class Sequence extends Node{
	public Sequence(List<Node> children)  {
		super(children);
	}
	
	@Override
	public NodeState Evaluate()
	{
		boolean anyChildIsRunning = false;
		
		for(Node node : children)
		{
			switch (node.Evaluate())
			{
				case NodeState.FAILURE:
					state = NodeState.FAILURE;
					return state;
				case NodeState.SUCCESS:
					continue;
				case NodeState.RUNNING:
					anyChildIsRunning = true;
					continue;
				default:
					state = NodeState.SUCCESS;
					return state;
			}
		}
		
		state = anyChildIsRunning ? NodeState.RUNNING : NodeState.SUCCESS;
		return state;
	}
}
