package realGame;

import java.util.ArrayList;

public class Hand {

	private ArrayList<Card> hand = new ArrayList<Card>();
	private Player player;
	private boolean bust = false;
	private boolean stand = false;
	private String name;
	private boolean visual;
	
	public Hand(Player player, String name, boolean visual) {
		// TODO Auto-generated constructor stub
		this.player = player;
		this.name = name;
		this.visual = visual;
	}

	public ArrayList<Card> getHand() {
		return hand;
	}

	public void addToHand(Card c) {
		hand.add(c);
	}

	public boolean isBusted() {
		return this.bust;
	}
	
	public void bust() {
		bust = true;
	}
	
	public boolean isStanding() {
		return this.stand;
	}
	
	public void stand() {
		stand = true;
	}
	
	public int sumHand() {
		int sum = 0;
		int numAces = 0;
		for (int i = 0; i < hand.size(); i++) {

			if (hand.get(i).getCard() != Cards.ace) {

				switch (hand.get(i).getCard()) {

				case king:
				case queen:
				case jack:
				case ten:
					sum += 10;
					break;
				case nine:
					sum += 9;
					break;
				case eight:
					sum += 8;
					break;
				case seven:
					sum += 7;
					break;
				case six:
					sum += 6;
					break;
				case five:
					sum += 5;
					break;
				case four:
					sum += 4;
					break;
				case three:
					sum += 3;
					break;
				case two:
					sum += 2;
					break;
				default:
					break;
				}

			} else {

				numAces++;

			}

		}
		
		for(int i = 0; i < numAces; i++) {
			
			if(sum + 11 > 21) {
				sum += 1;
			}else {
				sum += 11;
			}
			
		}

		return sum;
	}
	
	public void printHand() {
		
		System.out.println(" " + player.name() + "'s hand " + this.name);
		System.out.println();
		
		
		if(visual) {
			for(int i = 0; i < hand.size(); i++) {
				System.out.print(" ---    ");
			}
			System.out.println();
			for(int i = 0; i < 3; i++) {
				for(int j = 0; j < hand.size(); j++) {
					
					if(i == 1) {
						System.out.print("| " + hand.get(j).getSymbol() + " |   ");
					}else {
						System.out.print("|   |   ");
					}
					
				}
				System.out.println();

			}
			for(int i = 0; i < hand.size(); i++) {
				System.out.print(" ---    ");
			}
			System.out.println();
		}else {
			
			
			for(int i = 0; i < hand.size(); i++) {
				System.out.print(" " + hand.get(i).getSymbol());
			}
			System.out.println();
			System.out.println();
		}
		


	}
	
	public void printDealerHand() {
		
		System.out.println(" " + player.name() + "'s hand ");
		System.out.println();
		
		if(visual) {
			
			for(int i = 0; i < hand.size(); i++) {
				System.out.print(" ---    ");
			}
			System.out.println();
			for(int i = 0; i < 3; i++) {
				for(int j = 0; j < hand.size(); j++) {
					
					if(i == 1 && j != 1) {
						System.out.print("| " + hand.get(j).getSymbol() + " |   ");
					}else {
						System.out.print("|   |   ");
					}
					
				}
				System.out.println();

			}
			for(int i = 0; i < hand.size(); i++) {
				System.out.print(" ---    ");
			}
		}else {
			System.out.println(" " + hand.get(0).getSymbol() + " ?");
		}
		
		
		System.out.println();
	}

}
