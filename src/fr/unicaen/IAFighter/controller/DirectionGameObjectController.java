package fr.unicaen.IAFighter.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import fr.unicaen.IAFighter.model.Direction;
import fr.unicaen.IAFighter.model.GameObject;
/**
 * Controle la direction du GameObject lors du clique sur le choix de la direction
 *
 */
class DirectionGameObjectController implements ActionListener {
	private GameObject gameObject;
		
	/**
	 * instancie la classe DirectionGameObjectController
	 * le controleur a besoin du GameObject pour changer demander au model de changer
	 * sa direction
	 * @param gameObject
	 */
	public DirectionGameObjectController(GameObject gameObject) {
		this.gameObject = gameObject;
	}
	@Override
	public void actionPerformed(ActionEvent event) {
		JComboBox<Direction> source = (JComboBox) event.getSource();
		this.gameObject.setDirection((Direction)source.getSelectedItem());
	}

}
