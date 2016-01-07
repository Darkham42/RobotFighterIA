package fr.unicaen.IAFighter.model.strategy;

import java.util.Random;

import fr.unicaen.IAFighter.model.Robot;

/**
 * Type enumerer representant les actions possibles
 *
 */
public enum Action {
	//se reposer
	DoNothing{
		/**
		 * le robot se repose.
		 */
		@Override 
		public void execute(Robot r){
			r.doNothing();
		}
	},
	//seRéorienter (Random entre turnLeft et turnRight)
	Turn{
		/**
		 * le robot tourne a gauche ou a droite
		 */
		@Override 
		public void execute(Robot r){
			int nombreAleatoire = new Random().nextInt(1);
			switch(nombreAleatoire){
				case 0 : // Tourner a droite
					r.turnRight();
					break;
				case 1 : // Tourner a gauche
					r.turnLeft();
					break;
			}
		}
	},
	//SeDeplacer (Random entre forward et backward)
	Move{
		/**
		 * le robot avance ou recule.
		 */
		@Override 
		public void execute(Robot r){
			int nombreAleatoire = new Random().nextInt(1);
			switch(nombreAleatoire){
				case 0 : // Avancer 
					r.forward();
					break;
				case 1 : // Reculer 
					r.backward();
					break;
			}
		}
	},
	RandomAction{
		/**
		 * le robot effectue une action aleatoire.
		 */
		@Override 
		public void execute(Robot r){
			int nombreAleatoire = new Random().nextInt(6);
			switch(nombreAleatoire){
				case 0 : // Avancer 
					r.forward();
					break;
				case 1 : // Reculer 
					r.backward();
					break;
				case 2 : //aller a gauche 
					r.turnLeft();
					break;
				case 3 : //aller a droite 
					r.turnRight();
					break;
				case 4 : // tirer un missile 
					r.missileFire();
					break;
				case 5 : // tirer un laser 
					r.laserFire();
					break;
				case 6 : //Bouclier (se proteger) 
					r.shield();
					break;
				}
			}
	},
	TurnLeft{
		/**
		 * le robot tourne a gauche.
		 */
		@Override 
		public void execute(Robot r){
			r.turnLeft();
		}
	},
	TurnRight{
		/**
		 * le robot tourne a droite.
		 */
		@Override 
		public void execute(Robot r){
			r.turnRight();
		}
	},
	Shield{
		/**
		 * le robot active un bouclier pour se proteger.
		 */
		@Override 
		public void execute(Robot r){
			r.shield();
		}
	},
	MissileFire{
		/**
		 * le robot lance un missile.
		 */
		@Override 
		public void execute(Robot r){
			r.missileFire();
		}
	},
	LaserFire{
		/**
		 * le robot lance un laser.
		 */
		@Override 
		public void execute(Robot r){
			r.laserFire();
		}
	};
	/**
	 * Methode abstraite.
	 * Cette methode permet de dire au robot qu'elle action il doit effectuer.
	 * @param r
	 */
	public abstract void execute(Robot r);
}
