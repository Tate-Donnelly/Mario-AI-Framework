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
		for (int i = 0; i > -4; i--) {
			for (int j = 1; j < distance; j++) {
				if(agent.getCoords(j,i,enemies)!=0){
					return NodeState.SUCCESS;
				}
			}
		}
		return NodeState.FAILURE;
	}
}
