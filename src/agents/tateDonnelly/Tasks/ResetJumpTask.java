package agents.tateDonnelly.Tasks;

import agents.tateDonnelly.Agent;
import agents.tateDonnelly.StandardNodes.Node;
import agents.tateDonnelly.StandardNodes.NodeState;

public class ResetJumpTask extends Node {
	Agent agent;
	
	public ResetJumpTask(Agent agent) {
		this.agent = agent;
	}
	
	@Override
	public NodeState Evaluate() {
		agent.setJump(false);
		return NodeState.SUCCESS;
	}
}
