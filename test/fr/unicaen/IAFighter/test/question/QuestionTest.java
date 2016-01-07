package fr.unicaen.IAFighter.test.question;

import fr.unicaen.IAFighter.model.Direction;
import fr.unicaen.IAFighter.model.GameBoard;
import fr.unicaen.IAFighter.model.Missile;
import fr.unicaen.IAFighter.model.Robot;
import fr.unicaen.IAFighter.model.strategy.Question;
import junit.framework.*;
/**
 * Classe de test de la classe Question
 *
 */
public class QuestionTest extends TestCase {
	
	/**
	 * Test la question verifiant que le robot n'a pas d'energie
	 * @throws Exception
	 */
	public void testVerifierQuestionEnergieNull() throws Exception {
		GameBoard gb = new GameBoard();
		gb.setRow(5);
		Robot tonio = new Robot(gb);
		tonio.setEnergie(0);
		gb.addGameObject(tonio);
		assertTrue("Le robot n'a plus d'energie.",
				Question.Energy.verifierQuestion(tonio) == false);
	}
	
	/**
	 * Test la question verifiant que le robot a son energie pleine
	 * @throws Exception
	 */
	public void testVerifierQuestionEnergieFull() throws Exception {
		GameBoard gb = new GameBoard();
		gb.setRow(5);
		Robot tonio = new Robot(gb);
		tonio.setEnergie(10);
		gb.addGameObject(tonio);
		assertTrue("Le robot a toute son energie.",
				Question.FullEnergy.verifierQuestion(tonio) == true);
	}
	
	/**
	 * Test la question verifiant que le robot a un robot ennemie devant lui
	 * @throws Exception
	 */
	public void testVerifierQuestionEnnemyForward() throws Exception {
		GameBoard gb = new GameBoard();
		gb.setRow(5);
		Robot tonio = new Robot(gb);
		Robot toto = new Robot(gb);
		tonio.updatePosition(1, 1);
		tonio.setDirection(Direction.top);
		toto.updatePosition(0, 1);
		toto.setDirection(Direction.bottom);
		gb.addGameObject(tonio);
		gb.addGameObject(toto);
		assertFalse("Un ennemi est devant le robot",
				Question.EnnemyForward.verifierQuestion(tonio) == true);
	}
	
	/**
	 * Test la question verifiant que le robot a un misile ennemie derriere lui
	 * @throws Exception
	 */
	public void testVerifierQuestionMissileBackward() throws Exception {
		GameBoard gb = new GameBoard();
		gb.setRow(5);
		Robot tonio = new Robot(gb);
		Robot toto = new Robot(gb);
		tonio.updatePosition(1, 1);
		tonio.setDirection(Direction.top);
		toto.updatePosition(2, 1);
		toto.setDirection(Direction.top);
		gb.addGameObject(tonio);
		gb.addGameObject(toto);
		Missile bomb = new Missile(gb, Direction.top, toto);
		gb.addGameObject(bomb);
		assertFalse("Un missile est derriere le robot",
				Question.MissileBackward.verifierQuestion(tonio) == false);
	}

}
