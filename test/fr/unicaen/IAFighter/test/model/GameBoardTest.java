package fr.unicaen.IAFighter.test.model;

import java.util.Random;

import fr.unicaen.IAFighter.model.Cell;
import fr.unicaen.IAFighter.model.GameBoard;
import fr.unicaen.IAFighter.model.Robot;
import junit.framework.*;
/**
 * Classe de test de la classe GameBoard
 *
 */
public class GameBoardTest extends TestCase {

	/**
	 * Test si le gameboard est bien genere
	 * @throws Exception
	 */
	public void testGenerateBoard() throws Exception {
		GameBoard gameBoard = new GameBoard();
		gameBoard.setRow(5);
		Cell[][] board = gameBoard.getBoard();
		assertFalse("Le tableau n'est pas cree.",
				board[gameBoard.getCol() - 1][gameBoard.getRow() - 1] == null);
	}
	
	/**
	 * Test la fin d'une partie
	 */
	public void testTerminate() throws Exception {
	
		int min = 2, max = 10;
		GameBoard gb = new GameBoard();
		gb.setRow(5);
		Random random = new Random();
		int rnd = random.nextInt((max - min) + 1) + min;
		System.out.println("Lancement du test avec " + rnd + " joueurs.");
		MockGameObject[] objects = new MockGameObject[rnd];
		for (int i=0; i < rnd; i++) {
			objects[i] = new MockGameObject(gb);
			gb.listGameObjects().add(objects[i]);
		}
		//System.out.println("Liste de joueurs avant : " + gb.listGameObjects().size());
		for (int j = 0; j < rnd - 1; j++) {
			assertFalse(gb.terminal());
			gb.listGameObjects().remove(objects[j]);
		}
		//System.out.println("Liste de joueurs apres : " + gb.listGameObjects().size());
		assertTrue(gb.terminal());
		
	}

	public class MockGameObject extends Robot{
		public MockGameObject(GameBoard model) {
			super(model);
		}
	}

}