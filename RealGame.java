package realGame;

import java.util.Scanner;

public class RealGame {

	public static Deck d;
	public static boolean bust = false;
	public static boolean stand = false;
	public static Hand computerHand;
	public static boolean visual = false;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		playVisualGame();
		simGame();
	}

	public static int simGame() {

		// Initialize Deck
		initializeDeck();

		// Initialize Hands
		Hand playerHand = new Hand(Player.USER, "", visual);
		computerHand = new Hand(Player.COMPUTER, "", visual);

		// Deal
		deal(playerHand, computerHand);
		
		Moves pm = null;
		while (!playerHand.isBusted() && !playerHand.isStanding() && pm != Moves.SPLIT) {
			pm = getBestMove(playerHand);
			makeSimMove(playerHand, pm);
		}

		if (pm != Moves.SPLIT) {

			if (playerHand.isBusted()) {

			} else {
				dealComputerAfterPlayersPlay(computerHand);
			}

			testWinner(playerHand, computerHand);
		}
		
		

		return 0;
	}

	public static Moves getBestMove(Hand hand) {
		
		if(hand.sumHand() > 10) {
			return Moves.HIT;
		}else {
			return Moves.STAND;
		}
		
		
	}
	
	public static void playVisualGame() {

		// Initialize Deck
		initializeDeck();

		// Initialize Hands
		Hand playerHand = new Hand(Player.USER, "", visual);
		computerHand = new Hand(Player.COMPUTER, "", visual);

		// Deal
		deal(playerHand, computerHand);

		// Print hands
		playerHand.printHand();
		computerHand.printDealerHand();

		// Get users move and make move
		Scanner s = new Scanner(System.in);
		Moves pm = null;
		while (!playerHand.isBusted() && !playerHand.isStanding() && pm != Moves.SPLIT) {
			System.out.print("Hit, stand, split, or double down(dd): ");
			pm = getPlayersMove(s);
			System.out.println();
			makeMove(playerHand, pm, s);
		}

		if (pm != Moves.SPLIT) {

			if (playerHand.isBusted()) {
				System.out.println("BUST");
			} else {
				dealComputerAfterPlayersPlay(computerHand);
			}

			testWinner(playerHand, computerHand);
		}

		s.close();

	}

	public static void dealComputerAfterPlayersPlay(Hand computerHand) {

		while (computerHand.sumHand() < 17) {
			computerHand.printHand();
			computerHand.addToHand(d.getRandomCard());
		}
		computerHand.printHand();

	}

	public static void testWinner(Hand playerHand, Hand computerHand) {

		playerHand.printHand();

		System.out.println("USER: " + playerHand.sumHand());
		System.out.println("COMPUTER: " + computerHand.sumHand());

		if (computerHand.sumHand() > 21) {
			System.out.println("COMPUTER BUST");
			System.out.println("User wins with a " + playerHand.sumHand());
		} else {

			if (computerHand.sumHand() > playerHand.sumHand()) {
				System.out.println("Computer Wins with " + computerHand.sumHand());
			} else if (playerHand.sumHand() > computerHand.sumHand()) {
				System.out.println("User Wins with " + playerHand.sumHand());
			} else {
				System.out.println("Push");
			}

		}

	}
	
	public static void makeSimMove(Hand hand, Moves move) {
		switch (move) {
		case STAND:
			hand.stand();
			break;
		case HIT:
			hand.addToHand(d.getRandomCard());
			hand.printHand();
			if (hand.sumHand() > 21) {
				hand.bust();
			}
			break;
		case SPLIT:
			Hand phand1 = new Hand(Player.USER, "1", visual);
			Hand phand2 = new Hand(Player.USER, "2", visual);
			phand1.addToHand(hand.getHand().get(0));
			phand2.addToHand(hand.getHand().get(1));
			phand1.addToHand(d.getRandomCard());
			phand2.addToHand(d.getRandomCard());
			phand1.printHand();
			phand2.printHand();

			Moves pm = null;
			while (!phand1.isBusted() && !phand1.isStanding()) {
				pm = getBestMove(phand1);
				if (pm != Moves.SPLIT) {
					makeSimMove(phand1, pm);
				}
			}
			phand2.printHand();

			while (!phand2.isBusted() && !phand2.isStanding()) {
				pm = getBestMove(phand2);
				if (pm != Moves.SPLIT) {
					makeSimMove(phand2, pm);
				}
			}

			if (phand1.isBusted() && phand2.isBusted()) {
				System.out.println("Computer Wins");
			} else if (phand1.isBusted()) {
				dealComputerAfterPlayersPlay(computerHand);
				testWinner(phand2, computerHand);
			} else if (phand2.isBusted()) {
				dealComputerAfterPlayersPlay(computerHand);
				testWinner(phand1, computerHand);
			} else {
				dealComputerAfterPlayersPlay(computerHand);
				testWinner(phand1, computerHand);
				dealComputerAfterPlayersPlay(computerHand);
				testWinner(phand2, computerHand);
			}

		case DOUBLE_DOWN:
			hand.addToHand(d.getRandomCard());
			hand.stand();
			hand.printHand();
			break;
		}
	}

	public static void makeMove(Hand hand, Moves move, Scanner s) {
		switch (move) {
		case STAND:
			hand.stand();
			break;
		case HIT:
			hand.addToHand(d.getRandomCard());
			hand.printHand();
			if (hand.sumHand() > 21) {
				hand.bust();
			}
			break;
		case SPLIT:
			Hand phand1 = new Hand(Player.USER, "1", visual);
			Hand phand2 = new Hand(Player.USER, "2", visual);
			phand1.addToHand(hand.getHand().get(0));
			phand2.addToHand(hand.getHand().get(1));
			phand1.addToHand(d.getRandomCard());
			phand2.addToHand(d.getRandomCard());
			phand1.printHand();
			phand2.printHand();

			Moves pm = null;
			while (!phand1.isBusted() && !phand1.isStanding()) {
				System.out.print("HAND 1 - Hit, stand, split, or double down(dd): ");
				pm = getPlayersMove(s);
				if (pm != Moves.SPLIT) {
					makeMove(phand1, pm, s);
				}
			}
			if (phand1.isBusted()) {
				System.out.println("Hand 1 - BUST");
			} else if (phand1.isStanding()) {
				System.out.println("Hand 1 - STAND");
			}
			System.out.println();
			phand2.printHand();

			while (!phand2.isBusted() && !phand2.isStanding()) {
				System.out.print("HAND 2 - Hit, stand, split, or double down(dd): ");
				pm = getPlayersMove(s);
				if (pm != Moves.SPLIT) {
					makeMove(phand2, pm, s);
				}
			}

			if (phand2.isBusted()) {
				System.out.println("Hand 2 - BUST");
			} else if (phand2.isStanding()) {
				System.out.println("Hand 2 - STAND");
			}

			if (phand1.isBusted() && phand2.isBusted()) {
				System.out.println("Computer Wins");
			} else if (phand1.isBusted()) {
				dealComputerAfterPlayersPlay(computerHand);
				testWinner(phand2, computerHand);
			} else if (phand2.isBusted()) {
				dealComputerAfterPlayersPlay(computerHand);
				testWinner(phand1, computerHand);
			} else {
				dealComputerAfterPlayersPlay(computerHand);
				testWinner(phand1, computerHand);
				System.out.println();
				dealComputerAfterPlayersPlay(computerHand);
				testWinner(phand2, computerHand);
			}

		case DOUBLE_DOWN:
			hand.addToHand(d.getRandomCard());
			hand.stand();
			hand.printHand();
			break;
		}

	}

	public static Moves getPlayersMove(Scanner s) {
		String input;
		do {
			input = s.nextLine().toUpperCase();
		} while (!(input.equals("HIT") || input.equals("STAND") || input.equals("SPLIT") || input.equals("DD")));

		if (input.equals("DD")) {
			return Moves.DOUBLE_DOWN;
		}
		return Moves.valueOf(input);
	}

	public static void deal(Hand playerHand, Hand computerHand) {

		for (int i = 0; i < 4; i++) {
			Card c = d.getRandomCard();
			if (i % 2 == 0) {
				playerHand.addToHand(c);
			} else {
				computerHand.addToHand(c);
			}
		}

	}

	public static void initializeDeck() {
		// Initialize deck
		Card[] deck = new Card[52];
		for (int i = 0; i < Cards.values().length; i++) {
			for (int j = 0; j < Suits.values().length; j++) {

				Card c = new Card(Suits.values()[j], Cards.values()[i]);
				deck[(i * 4) + j] = c;

			}
		}
		d = new Deck(deck);
	}

}
