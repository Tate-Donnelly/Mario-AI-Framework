package agents.tateDonnelly.CustomNodes;

import agents.tateDonnelly.Agent;
import agents.tateDonnelly.StandardNodes.Node;
import agents.tateDonnelly.StandardNodes.NodeState;
import engine.core.MarioForwardModel;

public class IsGapNode extends Node {
	
	private Agent agent;
	private MarioForwardModel model;
	private int[][] scene;
	private int distance;
	
	public IsGapNode(Agent agent, MarioForwardModel model, int[][] scene, int distance) {
		this.agent = agent;
		this.model = model;
		this.scene = scene;
		this.distance=distance;
	}
	
	@Override
	public NodeState Evaluate() {
		for (int i = 1; i < distance; i++) {
			for (int j = 0; j < model.obsGridHeight/2; j++) {
				if (agent.getCoords(i, j, scene) != MarioForwardModel.OBS_NONE) {
					return NodeState.FAILURE;
				}
			}
		}
		return NodeState.SUCCESS;
	}
}
