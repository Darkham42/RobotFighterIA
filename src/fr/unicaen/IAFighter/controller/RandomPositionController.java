package fr.unicaen.IAFighter.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import fr.unicaen.IAFighter.model.GameBoard;
import fr.unicaen.IAFighter.view.AddGameObjectView;
/**
 * Initialise la position ligne-colonne du nouveau GameObject au hasard sur la vue
 *
 */
class RandomPositionController extends BaseController implements ActionListener {
	private AddGameObjectView view;
	
	/**
	 * Constructeur logique
	 * @param model
	 * @param view
	 */
	public RandomPositionController(GameBoard model, AddGameObjectView view) {
		super(model);
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		int row = 0;
		int col = 0;
		
		row = (getRandomInt(this.model.getRow()));
		col = (getRandomInt(this.model.getCol()));
		
		this.view.setRow(row);
		this.view.setCol(col);
	}
	
	/**
	 * Obtenir une valeur aleatoire entre 1 et max
	 * @param max
	 * @return
	 */
	private int getRandomInt(int max){
		return new Random().nextInt(max) + 1;
	}

}
