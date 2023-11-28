package agents.tateDonnelly.CustomNodes;

import agents.tateDonnelly.Agent;
import agents.tateDonnelly.StandardNodes.Node;
import agents.tateDonnelly.StandardNodes.NodeState;
import engine.core.MarioForwardModel;

public class IsQuestionBlock extends Node {
	private Agent agent;
	private MarioForwardModel model;
	private int[][] scene;
	
	public IsQuestionBlock(Agent agent, MarioForwardModel model, int[][] scene) {
		this.agent=agent;
		this.model = model;
		this.scene = scene;
	}
	
	@Override
	public NodeState Evaluate() {
		for (int j = 0; j > -5; j--) {
			int coord = agent.getCoords(1, j, scene);
			
			if (coord == MarioForwardModel.OBS_QUESTION_BLOCK) {
				return NodeState.SUCCESS;
			}
		}
		return NodeState.FAILURE;
	}
}
