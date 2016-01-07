package fr.unicaen.IAFighter.model.strategy;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.*;
import org.xml.sax.SAXException;
/**
 * la classe StrategyReader permet de lire un fichier XML 
 * et d'en extraitre les informations necessaires à la création de nos strategies
 *
 */
public class StrategyReader {
	private final String FILE = "strategies.xml";
	
	private final String QUESTION = "question";
	private final String STRATEGY = "strategy";
	private final String TEXTE = "texte";
	private final String OUI = "oui";
	private final String NON = "non";

	private Document doc;
	
	private NoeudAbstrait strategie;
	private Node strategyNode;
	private HashMap<String, Node> nodes;
	
	/**
	 * Constructeur logique du StrategyReader
	 * Il ouvre un fichier xml et filtre les erreurs possibles (try/catch)
	 */
	public StrategyReader() {
		try{
			File fXmlFile = new File(FILE);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(fXmlFile);
		}
		catch(FileNotFoundException e){
			System.err.println(e.getMessage());
			System.exit(1);
		} 
		catch (ParserConfigurationException e) {
			System.err.println(e.getMessage());
			System.exit(1);
		} 
		catch (SAXException e) {
			System.err.println(e.getMessage());
			System.exit(1);
		} 
		catch (IOException e) {
			System.err.println(e.getMessage());
			System.exit(1);
		}
		
		this.nodes = new HashMap<String, Node>();
	}
	/**
	 * Construit la strategie a partir du nom passe en parametre
	 * @param name
	 */
	public void construireStrategie(String name){
		NodeList nodeList = doc.getElementsByTagName(STRATEGY);
		
		for (int i = 0 ; i < nodeList.getLength() ; i++) {
			if(nodeList.item(i).getNodeType() == Node.ELEMENT_NODE)
				if(nodeList.item(i).getAttributes().getNamedItem("name").getTextContent().equals(name))
					this.strategyNode = nodeList.item(i);
		}
		
		nodeList = this.strategyNode.getChildNodes();
		
		for (int i = 0 ; i < nodeList.getLength() ; i++) {
			if(nodeList.item(i).getNodeType() == Node.ELEMENT_NODE){
				if(nodeList.item(i).getNodeName().equals(QUESTION)){
					this.nodes.put(nodeList.item(i).getAttributes().getNamedItem("id").getTextContent(), nodeList.item(i));
				}
			}
		}
		this.strategie = construireStructureComposite("1");
	}
	
	/**
	 * Recupere les differentes questions a partir de leurs identifiants respectifs.
	 * ainsi que leurs reponses oui et non.
	 * @param identifiant
	 * @return un nouveau noeud question.
	 */
	public NoeudAbstrait construireStructureComposite(String identifiant){
		Node node = this.nodes.get(identifiant);
		NodeList nodeList = node.getChildNodes();
		
		String texte = "", oui = "", non = "";
		NoeudAbstrait noeudOui = null, noeudNon = null;
		
		for (int i = 0 ; i < nodeList.getLength() ; i++) {
			if(nodeList.item(i).getNodeType() == Node.ELEMENT_NODE){
				if(nodeList.item(i).getNodeName().equals(TEXTE)){
					texte = nodeList.item(i).getTextContent();
				}
				if(nodeList.item(i).getNodeName().equals(OUI)){
					oui = nodeList.item(i).getTextContent();
					noeudOui = recupererNoeud(oui);
				}
				if(nodeList.item(i).getNodeName().equals(NON)){
					non = nodeList.item(i).getTextContent();
					noeudNon = recupererNoeud(non);
				}
			}
		}
		return new Noeud(noeudOui,texte, noeudNon);
	}
	
	/**
	 * Coupe la chaine de caracteres en deux parties, si la premiere est egal à la chaine "question"
	 * on creer une nouvelle feuille avec la partie droite de la chaine (celle-ci correspond a l'identifiant 
	 * de la question suivante).
	 * @param content
	 * @return
	 */
	private NoeudAbstrait recupererNoeud(String content){
		String[] array = content.split(":");
		String typeNode = array[0];
		String value = array[1];
		
		if(typeNode.equals("question"))
			return this.construireStructureComposite(value);
		return new Feuille(value);
		
	}
	/**
	 * Retourne la strategie.
	 * @return this.strategie
	 */
	public NoeudAbstrait lireStrategie(){
		return this.strategie;
	}
	
	/**
	 * Permet de recuperer les strategies existantes dans le fichier
	 * @return  String[] les noms des Strategies
	 */
	public String[] lireNomStrategies(){
		NodeList nodeList = doc.getElementsByTagName(STRATEGY);
		
		ArrayList<String> nomStrategies = new ArrayList<String>();
		
		for (int i = 0 ; i < nodeList.getLength() ; i++){
			nomStrategies.add(nodeList.item(i).getAttributes().getNamedItem("name").getTextContent());
		}
		
		nomStrategies.add(new RandomStrategy().getName());
		
		String[] array = new String[nomStrategies.size()];
		
		return nomStrategies.toArray(array);
	}
}