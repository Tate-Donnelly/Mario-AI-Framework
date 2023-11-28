package agents.tateDonnelly;

import engine.core.MarioForwardModel;

public class JumpTask extends Node{
	private Agent agent;
	private MarioForwardModel model;
	
	public JumpTask(Agent agent, MarioForwardModel model) {
		this.agent = agent;
		this.model = model;
		System.out.println(model==null);
	}
	
	@Override
	public NodeState Evaluate() {
		agent.setJump(true);
		return NodeState.SUCCESS;
	}
}
