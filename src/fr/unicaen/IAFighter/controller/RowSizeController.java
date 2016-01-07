package fr.unicaen.IAFighter.controller;

import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import fr.unicaen.IAFighter.model.GameBoard;

/**
 *
 */
class RowSizeController extends BaseController implements ChangeListener {

	/**
	 * Constructeur logique
	 * @param model
	 */
	public RowSizeController(GameBoard model) {
		super(model);
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		JSpinner spinner = (JSpinner) e.getSource();
		this.model.setRow((int) spinner.getValue());
	}

}
