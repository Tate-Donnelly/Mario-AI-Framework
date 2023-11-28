package agents.tateDonnelly;

import engine.core.MarioForwardModel;

public class IsGapNode extends Node{
	
	private Agent agent;
	private MarioForwardModel model;
	private int[][] scene;
	
	public IsGapNode(Agent agent, MarioForwardModel model, int[][] scene) {
		this.agent = agent;
		this.model = model;
		this.scene = scene;
	}
	
	@Override
	public NodeState Evaluate() {
		for (int i = 1; i < 3; i++) {
			for (int j = 2; j < 8; j++) {
				if (agent.getCoords(i, j, scene) != 0) {
					return NodeState.FAILURE;
				}
			}
		}
		return NodeState.SUCCESS;
	}
}
