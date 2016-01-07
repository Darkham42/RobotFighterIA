package fr.unicaen.IAFighter.model.strategy;

import fr.unicaen.IAFighter.model.Robot;
/**
 * Classe representant une question dans nos strategies
 *
 */
public class Noeud extends NoeudAbstrait {
	private NoeudAbstrait oui, non;
	private Question question;
	/**
	 * Constructeur logique
	 * @param oui : NoeudAbstrait
	 * @param question: String
	 * @param non: NoeudAbstrait
	 */
	public Noeud(NoeudAbstrait oui, String question, NoeudAbstrait non) {
		this.question = Question.valueOf(question);
		this.oui = oui;
		this.non = non;
	}
	
	/**
	 * Redefinition de la methode executer de la classe mere
	 * Cette methode recupere la reponse a la question, oui ou non, et appelle les autres questions de facons recursive jusqu'a tombe
	 * sur une feuille, donc une action a effectuer
	 */
	@Override
	public Action executer(Robot r) {
		NoeudAbstrait action = null;
		if(this.verifierQuestion(r))
			action = oui;
		else
			action = non;
		
		return action.executer(r);
	}
	/**
	 * appelle la méthode verifierQuestion définie dans le type enumere Question.d
	 * @param Robot : r
	 * @return
	 */
	public boolean verifierQuestion(Robot r){
		return this.question.verifierQuestion(r);
	}

}
