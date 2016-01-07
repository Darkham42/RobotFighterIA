package fr.unicaen.IAFighter.view;


import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import fr.unicaen.IAFighter.model.GameBoard;
import fr.unicaen.IAFighter.model.TypeEvent;

@SuppressWarnings("serial")
public class Gui extends JFrame implements Observer{
	private GameBoard model;
	protected static ViewState viewState;
	private JScrollPane pane;
	public Gui(GameBoard model){
		this.model = model;
		
		Gui.viewState = new ViewState(this);
		Gui.viewState.setState(new MainState());
		
		this.setVisible(true);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.model.addObserver(this);
		this.setMinimumSize(new Dimension(680, 480));
	}
	
	public void constructView(){
		if(this.pane != null)
			this.remove(this.pane);
		
		this.setTitle(viewState.titleView());
		this.pane = new JScrollPane(viewState.view(this.model));
		this.add(this.pane);
		
		this.revalidate();
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		if((TypeEvent)arg1 == TypeEvent.sizeEvent){
			this.getContentPane().revalidate();
		}
	}
}
