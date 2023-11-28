package agents.tateDonnelly;

import engine.core.MarioForwardModel;

import java.util.List;

public class RunTask extends Node{
	
	private Agent agent;
	private boolean faceRight;
	
	public RunTask(Agent agent, boolean faceRight) {
		this.agent = agent;
		this.faceRight=faceRight;
	}
	
	
	@Override
	public NodeState Evaluate() {
		agent.setRun(true,faceRight);
		//agent.setJump(false);
		return NodeState.SUCCESS;
	}
}
