package agents.tateDonnelly.Tasks;

import agents.tateDonnelly.Agent;
import agents.tateDonnelly.StandardNodes.Node;
import agents.tateDonnelly.StandardNodes.NodeState;
import engine.core.MarioForwardModel;

public class QuestionBlockJumpTask extends Node {
	private Agent agent;
	private MarioForwardModel model;
	
	public QuestionBlockJumpTask(Agent agent, MarioForwardModel model) {
		this.agent = agent;
		this.model = model;
	}
	
	@Override
	public NodeState Evaluate() {
		agent.setJump(true);
		return NodeState.SUCCESS;
	}
}
