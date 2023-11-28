package agents.tateDonnelly.CustomNodes;

import agents.tateDonnelly.Agent;
import agents.tateDonnelly.StandardNodes.Node;
import agents.tateDonnelly.StandardNodes.NodeState;
import engine.core.MarioForwardModel;

public class PastQuestionBlockNode  extends Node {
	
	private Agent agent;
	private MarioForwardModel model;
	private int[][] scene;
	private int distance;
	
	public PastQuestionBlockNode(Agent agent, MarioForwardModel model, int[][] scene, int distance) {
		this.agent = agent;
		this.model = model;
		this.scene = scene;
		this.distance=distance;
	}
	
	@Override
	public NodeState Evaluate() {
		for (int i = 0; i > -distance; i--) {
			for (int j = 0; j > -4; j--) {
				int coord=agent.getCoords(i, j, scene);
				if (coord == MarioForwardModel.OBS_QUESTION_BLOCK) {
					return NodeState.SUCCESS;
				}
			}
		}
		return NodeState.FAILURE;
	}
}
