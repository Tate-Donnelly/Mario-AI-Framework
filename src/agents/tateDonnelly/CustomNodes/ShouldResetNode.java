package agents.tateDonnelly.CustomNodes;

import agents.tateDonnelly.StandardNodes.Node;
import agents.tateDonnelly.StandardNodes.NodeState;
import engine.core.MarioForwardModel;

public class ShouldResetNode extends Node {
	MarioForwardModel model;
	
	public ShouldResetNode(MarioForwardModel model) {
		this.model = model;
	}
	
	@Override
	public NodeState Evaluate() {
		if(model.isMarioOnGround()){
			return NodeState.SUCCESS;
		}
		return NodeState.FAILURE;
	}
}
