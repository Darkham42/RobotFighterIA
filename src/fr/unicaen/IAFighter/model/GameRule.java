package fr.unicaen.IAFighter.model;

/**
 *	Represente les regles du jeux
 *  La seule condition qui est fixee est qu'un robot ne peux pas se retrouver sur une case ou un robot est deja present
 *  @see Cell#setContent(GameObject) Cette methode utilise les regles du jeu
 */
public class GameRule {
	/**
	 * Si vrai alors l'energie des robots est infinies
	 */
	public static boolean infiniteEnergy = false;
	/**
	 * Si Vrai alors les missiles sont destructibles entre eux
	 */
	public static boolean MissileDestroyableTogether = true;
	/**
	 * Si Vrai alors les missiles sont destructible par les lasers
	 */
	public static boolean MissileDestroyableByLaser = true;
	
	/**
	 * Applique les regles en rapport avec les lasers
	 * Supprime les Objets sur le rayon du laser;
	 * @param model
	 */
	public static void apply(GameBoard model){
		
		for(GameObject gameObject : model.listGameObjects()){
			if(gameObject.isDead())
				continue;
			
			if(gameObject.getTypeObject() == TypeObject.missile && !GameRule.MissileDestroyableByLaser)
				continue;
			
			Cell c = model.getBoard()[gameObject.getRow()][gameObject.getCol()];
			if(c.getLaserOccured()){
				gameObject.die();
			}
		}
	}
}
