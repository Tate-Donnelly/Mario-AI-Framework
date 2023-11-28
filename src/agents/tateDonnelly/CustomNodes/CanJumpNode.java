package agents.tateDonnelly.CustomNodes;

import agents.tateDonnelly.StandardNodes.Node;
import agents.tateDonnelly.StandardNodes.NodeState;
import engine.core.MarioForwardModel;

public class CanJumpNode extends Node {
	private MarioForwardModel model;
	
	public CanJumpNode(MarioForwardModel model) {
		this.model = model;
	}
	@Override
	public NodeState Evaluate() {
		if(model.isMarioOnGround() && model.mayMarioJump()){
			return NodeState.SUCCESS;
		}
		return NodeState.FAILURE;
	}
}
