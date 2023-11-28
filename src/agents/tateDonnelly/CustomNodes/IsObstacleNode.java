package agents.tateDonnelly.CustomNodes;

import agents.tateDonnelly.Agent;
import agents.tateDonnelly.StandardNodes.Node;
import agents.tateDonnelly.StandardNodes.NodeState;
import engine.core.MarioForwardModel;

public class IsObstacleNode extends Node {
	private int distance;
	private Agent agent;
	private MarioForwardModel model;
	private int[][] scene;
	
	public IsObstacleNode(Agent agent,MarioForwardModel model, int[][] scene,int distance) {
		this.agent = agent;
		this.model = model;
		this.scene = scene;
		this.distance=distance;
	}
	
	
	@Override
	public NodeState Evaluate() {
		for (int i = 0; i > -2; i--) {
			for (int j = 1; j < distance; j++) {
				int coord=agent.getCoords(j,i,scene);
				if(coord!=0){
					return NodeState.SUCCESS;
				}
			}
		}
		return NodeState.FAILURE;
	}
}
