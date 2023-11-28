package agents.tateDonnelly;

public class ResetJumpTask extends Node{
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
