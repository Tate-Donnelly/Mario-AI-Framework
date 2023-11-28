package agents.tateDonnelly.CustomNodes;

import agents.tateDonnelly.Agent;
import agents.tateDonnelly.StandardNodes.Node;
import agents.tateDonnelly.StandardNodes.NodeState;
import engine.core.MarioForwardModel;

public class IsEnemyNode extends Node {
	private Agent agent;
	private MarioForwardModel model;
	int[][] enemies;
	int distance;
	
	public IsEnemyNode(Agent agent,MarioForwardModel model, int[][] enemies,int distance) {
		this.agent=agent;
		this.model = model;
		this.enemies=enemies;
		this.distance=distance;
	}
	
	
	@Override
	public NodeState Evaluate() {
		for (int j = 1; j < distance; j++) {
			int coords=agent.getCoords(j,0,enemies);
			if(coords>MarioForwardModel.OBS_ENEMY){
				return NodeState.SUCCESS;
			}
		}
		return NodeState.FAILURE;
	}
}
