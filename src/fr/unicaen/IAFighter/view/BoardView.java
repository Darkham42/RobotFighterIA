package fr.unicaen.IAFighter.view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Observable;

import fr.unicaen.IAFighter.model.GameBoard;
import fr.unicaen.IAFighter.model.TypeEvent;

@SuppressWarnings("serial")
class BoardView extends BaseView{
	private int width, height;
	private final int cellSize;
	
	BoardView(GameBoard model) {
		super(model);
		this.cellSize = 40;
		
		this.width = this.model.getCol() * cellSize;
		this.height = this.model.getRow() * cellSize;
		this.setPreferredSize(new Dimension(this.width + 1 , this.height + 1));
		
		this.model.addObserver(this);
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		if((TypeEvent)arg1 == TypeEvent.gameMove){
			this.repaint();
		}
		else {
			if(this.width != this.model.getCol() * cellSize || this.height != this.model.getRow() * cellSize) {
				this.width = this.model.getCol() * cellSize;
				this.height = this.model.getRow() * cellSize;
				this.setPreferredSize(new Dimension(this.width + 1, this.height + 1));
			}
		}
	}
	
	public void paintComponent(Graphics g){
		
		//vertical
		for (int i = 0; i <= this.model.getCol(); i++) {
			g.drawLine(i * this.cellSize, 0, i * this.cellSize, this.height);
		}
		//horizontal
		for (int i = 0; i <= this.model.getRow(); i++) {
			g.drawLine(0, i * this.cellSize, this.width, i * this.cellSize);
		}
		
		if(this.model.listGameObjects().size() != 0){
			this.drawCells(g);
		}
			
	}
	
	public void drawCells(Graphics g){
		for (int row = 0; row < this.model.getRow(); row++) {
			for (int col = 0; col < this.model.getCol(); col++) {
				CellView cellView = new CellView(this.model.getBoard()[row][col], this.cellSize - 2); 
				cellView.setBounds(col * this.cellSize + 1, row * this.cellSize + 1, this.cellSize - 1, this.cellSize - 1);
				this.add(cellView);
			}
		}
	}
}
