package fr.unicaen.IAFighter.controller;


import fr.unicaen.IAFighter.model.GameBoard;

abstract class BaseController {
	protected GameBoard model;
	
	/**
	 * Constructeur logique
	 * @param model
	 */
	public BaseController(GameBoard model) {
		this.model = model;
	}
}
