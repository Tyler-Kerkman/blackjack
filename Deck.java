package realGame;

import java.util.ArrayList;

public class Deck {

	private Card[] deck;
	private ArrayList<Card> usedCards = new ArrayList<Card>();
	
	public Deck(Card[] deck) {
		// TODO Auto-generated constructor stub
		this.deck = deck;
	}
	
	public Card[] getDeck() {
		return this.deck;
	}
	
	
	public Card getRandomCard() {
		Card c;
		do {
			c  = deck[getRandomInt()];
		}while(usedCards.contains(c));
		usedCards.add(c);
		return c;
	}

	private int getRandomInt() {
		return (int) (Math.random() * 52);
	}
}
