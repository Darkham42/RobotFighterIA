package fr.unicaen.IAFighter.model.strategy;

import java.util.HashMap;

import fr.unicaen.IAFighter.model.*;
/**
 * Type ennumere correspondant aux questions posees par nos strategies
 *
 */
public enum Question {
	
	Energy 	//le robot a-t-il de l'energie ?
	{
		/**
		 * Verifie si le robot a de l'energie ou non.
		 */
		@Override
		public boolean verifierQuestion(Robot robot) {
			return robot.getEnergie()!=0;
		}
	},

	EnnemyForward //Y a t-il un ennemi devant le robot ?
	{
		/**
		 * Verifie si un ennemi est devant le robot
		 */
		@Override
		public boolean verifierQuestion(Robot robot) {
			return Question.rechercheRecursive(1, robot, robot.getDirection(), TypeObject.robot);
		}
	},
	
	Ennemy	//Y a t'il un ennemi autour du robot ?
	{
		/**
		 * verifie si il y a un ennemi autour du robot, plus precisement sur les lignes et colonnes ou il se situe.
		 */
		@Override
		public boolean verifierQuestion(Robot robot) {
			for(Direction direction : Direction.values()){
				if(Question.rechercheRecursive(1, robot, direction, TypeObject.robot))
					return true;
			}
			
			return false;
		}
	},

	Missile	//Y a t-il un missile autour du robot ?
	{
		/**
		 * Idem pour un missile
		 */
		@Override
		public boolean verifierQuestion(Robot robot) {
			for(Direction direction : Direction.values()){
				if(Question.rechercheRecursive(1, robot, direction, TypeObject.missile))
					return true;
			}
			
			return false;
		}
	},
	
	FullEnergy	//L'energie du robot est-elle pleine ?
	{
		/**
		 * Verifie si l'energie du robot est pleine
		 */
		@Override
		public boolean verifierQuestion(Robot robot) {
			return robot.getEnergie()== robot.getMaxEnergie();
		}
	},

	MissileForward	//Le missile est-il devant le robot ?
	{	
		/**
		 * verifie si un ennemi se situe devant le robot
		 */
		@Override
		public boolean verifierQuestion(Robot robot) {
			return Question.rechercheRecursive(1, robot, robot.getDirection(), TypeObject.missile);
		}
	},
	MissileBackward	//Le missile est-il derriere le robot ?
	{
		/**
		 * Verifie si le missile se situe derriere le robot
		 */
		@Override
		public boolean verifierQuestion(Robot robot) {
			return Question.rechercheRecursive(1, robot, Direction.values()[(2 + robot.getDirection().ordinal()) % 4] , TypeObject.missile);
		}
	},
	MissileAvoid	//Le robot peut-il eviter le missile ?
	{	
		/**
		 * Verifie si le robot a la possibilite d'eviter le missile
		 */
		@Override
		public boolean verifierQuestion(Robot robot) {
			HashMap<Direction, Cell> voisins = Question.voisinIterateur(robot);
			
			for(Direction d : Direction.values()){
				Direction directionOpposee = Direction.values()[(d.ordinal() + 2) % 4];
				
				if(voisins.containsKey(d)){
					if(voisins.get(d).numberOf(TypeObject.missile) == 1){
						if(voisins.get(d).getContent()[0].getDirection() == directionOpposee)
							return Question.celluleVide(robot, robot.getDirection()) || 
									Question.celluleVide(robot,  Direction.values()[(robot.getDirection().ordinal() + 2) % 4]);
					}
				}
			}
			return true;
		}
	},
	MissileBeamBoom		//Le missile est-il a portee du laser ?
	{	
		/**
		 * Verifie si le missile est a portee du laser, dans ce cas, il peut le detruire
		 */
		@Override
		public boolean verifierQuestion(Robot robot) {
			int porteeLaser = robot.getLaserSize();
			return Question.aPortee(porteeLaser, robot, TypeObject.missile);	
		}
	},
	EnnemyBeamBoom	//L'ennemi est-il a portee du laser ?
	{	
		/**
		 * Verifie si un ennemi est a portee du laser
		 */
		@Override
		public boolean verifierQuestion(Robot robot) {
			int porteeLaser = robot.getLaserSize();
			return Question.aPortee(porteeLaser, robot, TypeObject.robot);	
		}
	};
	/**
	 * Methode abstraite permettant de savoir si la question doit repondre oui ou non
	 * @param robot
	 * @return
	 */
	public abstract boolean verifierQuestion(Robot robot);
	
	/**
	 * Effectue une recherche d'un objet de facon recursive dans le plateau 
	 * @param iteration
	 * @param robot
	 * @param direction
	 * @param type
	 * @return true: l'objet a ete trouve</br>
	 * false : l'objet n'a pas ete trouve </br>
	 */
	private static boolean rechercheRecursive(int iteration, Robot robot, Direction direction, TypeObject type){
		boolean returnValue = false;
		if(!robot.getModel().inGrid(robot.getRow() + (iteration * direction.getRow()), robot.getCol() + (iteration * direction.getCol()))){
			return false;
		}
		
		if(robot.getModel().getBoard()[robot.getRow() + (iteration * direction.getRow())][robot.getCol() + (iteration * direction.getCol())].numberOf(type) != 1){
			returnValue = true;
		}
		
		return returnValue || Question.rechercheRecursive(iteration+1, robot, direction, type);
		
	}
	/**
	 * Test qui permet de savoir si le robot ou missile ennemi se trouve à portee du laser ou non
	 * @param iteration
	 * @param robot
	 * @param type
	 * @return true : l'objet est a portee du laser</br>
	 * false : l'objet n'est pas a portee
	 */
	private static boolean aPortee(int iteration, Robot robot, TypeObject type){
		boolean returnValue = false;
		
		if(iteration == 0)
			return false;
		
		if(!robot.getModel().inGrid(robot.getRow() + (iteration * robot.getDirection().getRow()), robot.getCol() + (iteration * robot.getDirection().getCol()))){
			return false;
		}
		
		if(robot.getModel().getBoard()[robot.getRow() + (iteration * robot.getDirection().getRow())][robot.getCol() + (iteration * robot.getDirection().getCol())].numberOf(type) != 1){
			returnValue = true;
		}
		
		return returnValue || Question.aPortee(iteration-1, robot, type);
		
	}
	
	/**
	 * Iterateur qui permet de recuperer les cellules voisines
	 * @param robot
	 * @return liste des cellules voisines (remplit avec null si en dehors du tableau)
	 */
	private static HashMap<Direction, Cell> voisinIterateur(Robot robot){
		HashMap<Direction, Cell> voisins = new HashMap<Direction, Cell>();
		
		for (Direction direction : Direction.values()) {
			int row = robot.getRow() + direction.getRow();
			int col = robot.getCol() + direction.getCol();
			
			if(robot.getModel().inGrid(row, col))
				voisins.put(direction, robot.getModel().getBoard()[row][col]);
		}
		
		return voisins;
	}
	
	/**
	 * Fonction permettant de savoir si la cellule du tableau est vide ou non.
	 * @param robot
	 * @param direction
	 * @return true : la cellule est vide </br>
	 * false: la cellule contient un objet
	 */
	private static boolean celluleVide(Robot robot, Direction direction){
		int row = robot.getRow() + direction.getRow();
		int col = robot.getCol() + direction.getCol();
		
		if(robot.getModel().inGrid(row, col)){
			if(robot.getModel().getBoard()[row][col].getContent().length == 0)
				return true;
		}
		return false;
	}
}
