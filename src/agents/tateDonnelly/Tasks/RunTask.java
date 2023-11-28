package agents.tateDonnelly.Tasks;

import agents.tateDonnelly.Agent;
import agents.tateDonnelly.StandardNodes.Node;
import agents.tateDonnelly.StandardNodes.NodeState;

public class RunTask extends Node {
	
	private Agent agent;
	private boolean faceRight;
	
	public RunTask(Agent agent, boolean faceRight) {
		this.agent = agent;
		this.faceRight=faceRight;
	}
	
	
	@Override
	public NodeState Evaluate() {
		agent.setRun(true,faceRight);
		return NodeState.SUCCESS;
	}
}
