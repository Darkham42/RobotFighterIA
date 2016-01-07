package fr.unicaen.IAFighter.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import fr.unicaen.IAFighter.model.GameBoard;
import fr.unicaen.IAFighter.model.GameObject;

/**
 * lors du clique sur le bouton delete supprime le gameobject associe dans le model
 *
 */
class DeleteGameObjectController extends BaseController implements ActionListener {
	private GameObject gameObject;
	
	/**
	 * instancie la classe DeleteGameObjectController
	 * le controleur a besoin du modele pour repercuter les actions sur
	 * le GameObject souhaite
	 * @param model
	 * @param gameObject
	 */
	public DeleteGameObjectController(GameBoard model, GameObject gameObject) {
		super(model);
		this.gameObject = gameObject;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		model.removeGameObject(gameObject);
	}

}
