package fr.unicaen.IAFighter.controller;

import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import fr.unicaen.IAFighter.model.GameBoard;
/**
 * Controle la taille du plateau de jeu (taille largeur)
 *
 */
class ColSizeController extends BaseController implements ChangeListener {
	
	/**
	 * Constructeur logique
	 * @param model
	 */
	public ColSizeController(GameBoard model) {
		super(model);
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		JSpinner spinner = (JSpinner) e.getSource();
		this.model.setCol((int) spinner.getValue());
	}

}
