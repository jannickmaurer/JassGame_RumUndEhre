package jass.commons;

import java.util.ArrayList;

public class WiisEvaluation {

	ArrayList<Wiis> wiis = new ArrayList<>();
	
	
	public WiisEvaluation() {
		super();
	}


	public ArrayList<Wiis> getCards() {
		return wiis;
	}

	public void setCards(ArrayList<Wiis> wiis) {
		this.wiis = wiis;
	}
	
	public boolean hasWiis () {
		return wiis.size() != 0;
	}
	
	//Klasse Account getCurrentPlayerCards respektive set für Player Cards für wiis evaluation
	
	
	
	
	
	
}
