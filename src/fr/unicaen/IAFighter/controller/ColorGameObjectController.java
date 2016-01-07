package fr.unicaen.IAFighter.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JColorChooser;

import fr.unicaen.IAFighter.model.GameBoard;
import fr.unicaen.IAFighter.model.GameObject;
/**
 * Controle la selection de la couleur par l'utilisateur sur la vue pour que le modele
 * l'utilise
 *
 */
class ColorGameObjectController extends BaseController implements ActionListener {
	private GameObject gameObject;
	/**
	 * Constructeur logique
	 * @param model
	 * @param gameObject
	 */
	ColorGameObjectController(GameBoard model, GameObject gameObject) {
		super(model);
		this.gameObject = gameObject;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		gameObject.setColor(JColorChooser.showDialog(null, "GameObject - Choose a Color", gameObject.getColor()));
	}

}
