package jass.commons;

import java.util.ArrayList;

public enum Wyss { 
	Stöck, Dreiblatt, Vierblatt, Fünfblatt, Sechsblatt, Siebenblatt, Achtblatt, Neunblatt, Viergliichi, Vierneun, Vierbuebe;
	
	public static Wyss evaluateWyss(ArrayList<Card> cards){
		Wyss currentEval = null;
		ArrayList<Wyss> wyss = new ArrayList<>();
		
		if (hasStöck(cards)) currentEval = Stöck; wyss.add(currentEval);
		if (hasDreiblatt(cards)) currentEval = Dreiblatt; wyss.add(currentEval);
		if (hasVierblatt(cards)) currentEval = Vierblatt; wyss.add(currentEval);
		if (hasFünfblatt(cards)) currentEval = Fünfblatt; wyss.add(currentEval);
		if (hasSechsblatt(cards)) currentEval = Sechsblatt; wyss.add(currentEval);
		if (hasSiebenblatt(cards)) currentEval = Siebenblatt; wyss.add(currentEval);//Nur ein Weiss Plus Stöcke
		if (hasAchtblatt(cards)) currentEval = Achtblatt; wyss.add(currentEval);//Nur ein Weiss Plus Stöcke evtl.
		if (hasNeunblatt(cards)) currentEval = Neunblatt; wyss.add(currentEval);//Nur ein Weiss Plus Stöcke evtl.
		if (hasViergliichi(cards)) currentEval = Viergliichi; wyss.add(currentEval);
		if (hasVierneun(cards)) currentEval = Vierneun; wyss.add(currentEval);//150
		if (hasVierbuebe(cards)) currentEval = Vierbuebe; wyss.add(currentEval);//200
		
	return null;
	}
//    public static boolean isStraight(ArrayList<Card> cards) {
//    	ArrayList<Card> clonedCards = (ArrayList<Card>) cards.clone();
//    	boolean straightFound = false;
//    	int successfulTries = 0;
//    	boolean next = true;
//    	
//    	Collections.sort(clonedCards);
//
//    		for(int i = 0; i < clonedCards.size() && next; i++) {
//    			if(clonedCards.get(i).getRank().compareTo(clonedCards.get(i+1).getRank()) == -1) {
//    				successfulTries++;
//    			} else {
//    				next = false;
//    			}
//    			if(successfulTries == 4) {
//    				next = false;
//    				straightFound = true;
//    			}	
//    		}
//    	 
//    	return straightFound;
//    }
//	
	
//Achtung: Evaluation mit mehreren dingen möglich zb drei Mal Dreiblatt....
//und bei Sechsblatt, wäres dann auch fünfblatt, vierblatt, dreiblatt....

	private static boolean hasStöck(ArrayList<Card> cards) {
		if(Trummpf != Undeufe || Tr)
		
		return false;
	}

	private static boolean hasDreiblatt(ArrayList<Card> cards) {
		// TODO Auto-generated method stub
		return false;
	}

	private static boolean hasVierblatt(ArrayList<Card> cards) {
		// TODO Auto-generated method stub
		return false;
	}

	private static boolean hasFünfblatt(ArrayList<Card> cards) {
		// TODO Auto-generated method stub
		return false;
	}

	private static boolean hasSechsblatt(ArrayList<Card> cards) {
		// TODO Auto-generated method stub
		return false;
	}

	private static boolean hasSiebenblatt(ArrayList<Card> cards) {
		// TODO Auto-generated method stub
		return false;
	}

	private static boolean hasAchtblatt(ArrayList<Card> cards) {
		// TODO Auto-generated method stub
		return false;
	}

	private static boolean hasNeunblatt(ArrayList<Card> cards) {
		// TODO Auto-generated method stub
		return false;
	}

	private static boolean hasViergliichi(ArrayList<Card> cards) {
		// TODO Auto-generated method stub
		return false;
	}

	private static boolean hasVierneun(ArrayList<Card> cards) {
		// TODO Auto-generated method stub
		return false;
	}

	private static boolean hasVierbuebe(ArrayList<Card> cards) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	

}
