package Game;

import Cards.Card;
import Cards.Glyph;
import Cards.Glyphs.GlyphEarth;
import Cards.Spell;
import Cards.Spells.Bolt;
import Cards.Spells.ReturnFromGrave;
import Cards.Troop;
import Cards.Troops.Slime;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by acurr on 5/30/2016.
 */
public class Game {
    private final Scanner kb = new Scanner(System.in);
    private Field battlefield;
    private Player player1;
    private Player player2;
    private Player activePlayer;
    private int c = -1;

    public Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.battlefield = new Field(player1, player2);
        generateDeck();

    }

    public void generateDeck() {
        for (int i = 0; i < 20; i++) {
            player1.addCardDeck(new Slime());
            player2.addCardDeck(new Slime());
        }
        player1.addCardDeck(new Bolt());
        player1.addCardDeck(new GlyphEarth());
        player2.addCardDeck(new ReturnFromGrave());

    }

    public void startGame() {
        player1.drawCard(5);
        player2.drawCard(5);
        Random random = new Random();
        int num = random.nextInt(1);
        battlefield.update();
        if (num == 0) {
            activePlayer = player1;
            startTurn(player1);

        } else {
            activePlayer = player2;
            startTurn(player2);

        }
    }

    public void startTurn(Player p) {
        for (Troop t : battlefield.getPlayersTroops(p)) {
            t.setExhausted(false);

        }
        p.setMana(p.getMaxMana());
        System.out.println();
        System.out.println("It's " + p.getName() + "'s turn!");
        p.drawCard(1);

        while (c != 0) {
            if (!(player1.getHealth() > 0 && player2.getHealth() > 0)) {
                System.out.println("Game Over!!!!");
            }
            System.out.println("What would you like to do?");
            System.out.println("1. Use Card     2. Attack with Troop    3. End Turn");
            if (p.getHand().isEmpty()) {
                System.out.println("Your hand is empty");
            }
            c = kb.nextInt();
            if (c == 1 && !p.getHand().isEmpty()) {
                System.out.println("You have " + p.getMana() + " mana which card would you like to use?");
                System.out.println();
                int n = 0;
                for (Card c : p.getHand()) {
                    System.out.print(++n + ":  " + c.toString() + " ");
                }
                int d = kb.nextInt() - 1;
                if (d >= 0 && d < n && p.getHand().get(d).getCost() <= p.getMana() && p.getGlyphs().containsAll(p.getHand().get(d).getThresholds())) {
                    Card card = p.getHand().get(d);
                    p.setMana(p.getMana() - card.getCost());
                    useCard(p, card);

                } else if (p.getHand().get(d).getCost() > p.getMana()) {
                    System.out.println("You don't have enough Mana!");
                } else if (!p.getGlyphs().containsAll(p.getHand().get(d).getThresholds())) {
                    System.out.println("You don't the correct Glyphs to play that!");
                }

            }
            if (c == 2 && !battlefield.getPlayersTroops(p).isEmpty()) {
                attackWithTroop(p);

            }
            if (c == 3) {
                if (activePlayer == player1) {
                    activePlayer = player2;
                } else
                    activePlayer = player1;
                if (player1.getHealth() > 0 && player2.getHealth() > 0) {
                    startTurn(activePlayer);
                } else
                    System.out.println("Game over");
            }

        }

    }

    private void attackWithTroop(Player p) {
        System.out.println("Who would you like to attack with?");
        int listed = 0;
        for (Troop troop : battlefield.getPlayersTroops(p)) {
            System.out.print(++listed + ":  " + troop.toString() + " ");
        }
        int d = kb.nextInt() - 1;
        if (battlefield.getPlayersTroops(p).get(d).isExhausted()) {
            System.out.println("That troop is exhausted and cant fight!");
            return;
        }
        if (d >= 0 && d < listed) {
            System.out.println("Who would you like to attack?");
            List<Object> targets = new ArrayList<Object>();
            int enemies = 0;
            targets.add(battlefield.getOpposingPlayer(p));
            System.out.print(++enemies + ":  " + battlefield.getOpposingPlayer(p).getName() + " ");
            for (Troop troop : battlefield.getOpposingTroops(p)) {

                targets.add(troop);
                System.out.print(++enemies + ":  " + troop.toString() + " ");
            }
            int e = kb.nextInt() - 1;
            if (e >= 0 && e < enemies) {
                Object target = targets.get(e);
                if (target instanceof Player) {
                    battlefield.getPlayersTroops(p).get(d).attack((Player) target);
                } else {
                    battlefield.getPlayersTroops(p).get(d).attack((Troop) target);
                }
                battlefield.getPlayersTroops(p).get(d).setExhausted(true);
            }
        }
        battlefield.update();
    }

    private void useCard(Player p, Card card) {
        if (card instanceof Glyph) {
            Glyph glyph = (Glyph) card;
            for (int i = 0; i < glyph.getAmount(); i++) {
                glyph.effect(p);
            }
            p.getHand().remove(card);
        } else if (card instanceof Troop) {
            p.putCardField((Troop) card, battlefield);
            battlefield.update();
        } else if (card instanceof Spell) {
            Spell spell = (Spell) card;
            if (spell.getTarget() == Target.ANY) {
                for (int i = 0; i < spell.getAmount(); i++) {
                    System.out.println("Who would you like to target?");
                    List<Object> targets = new ArrayList<Object>();
                    int listed = 0;
                    for (Player player : battlefield.getPlayers()) {

                        targets.add(player);
                        System.out.print(++listed + ":  " + player.getName() + " ");
                    }

                    for (Troop t : battlefield.getPlayer1Troops()) {

                        targets.add(t);
                        System.out.print(++listed + ":  " + t.toString() + " ");
                    }
                    for (Troop t : battlefield.getPlayer2Troops()) {

                        targets.add(t);
                        System.out.print(++listed + ":  " + t.toString() + " ");
                    }
                    int d = kb.nextInt() - 1;
                    if (d >= 0 && d < listed) {
                        Object target = targets.get(d);
                        if (target instanceof Player) {
                            spell.effect((Player) target);
                        } else {
                            spell.effect((Troop) target);
                        }

                    }
                    battlefield.update();
                }

            } else if (spell.getTarget() == Target.SELF) {

                for (int i = 0; i < spell.getAmount(); i++) {
                    spell.effect(p);
                }
            } else if (spell.getTarget() == Target.OTHER) {
                for (int i = 0; i < spell.getAmount(); i++) {
                    System.out.println("Who would you like to target?");
                    List<Object> targets = new ArrayList<Object>();
                    int listed = 0;
                    targets.add(battlefield.getOpposingPlayer(p));
                    System.out.print(++listed + ":  " + battlefield.getOpposingPlayer(p).getName() + " ");

                    for (Troop t : battlefield.getPlayer1Troops()) {

                        targets.add(t);
                        System.out.print(++listed + ":  " + t.toString() + " ");
                    }
                    for (Troop t : battlefield.getPlayer2Troops()) {

                        targets.add(t);
                        System.out.print(++listed + ":  " + t.toString() + " ");
                    }
                    int d = kb.nextInt() - 1;
                    if (d >= 0 && d < listed) {
                        Object target = targets.get(d);
                        if (target instanceof Player) {
                            spell.effect((Player) target);
                        } else {
                            spell.effect((Troop) target);
                        }

                    }
                    battlefield.update();
                }
            } else if (spell.getTarget() == Target.PLAYER) {
                for (int i = 0; i < spell.getAmount(); i++) {
                    System.out.println("Who would you like to target?");
                    List<Object> targets = new ArrayList<Object>();
                    int listed = 0;
                    for (Player player : battlefield.getPlayers()) {

                        targets.add(player);
                        System.out.print(++listed + ":  " + player.getName() + " ");
                    }
                    int d = kb.nextInt() - 1;
                    if (d >= 0 && d < listed) {
                        Object target = targets.get(d);
                        if (target instanceof Player) {
                            spell.effect((Player) target);
                        } else {
                            spell.effect((Troop) target);
                        }

                    }
                    battlefield.update();

                }
            } else if (spell.getTarget() == Target.TROOP) {
                for (int i = 0; i < spell.getAmount(); i++) {
                    System.out.println("Who would you like to target?");
                    List<Object> targets = new ArrayList<Object>();
                    int listed = 0;
                    for (Troop t : battlefield.getPlayer1Troops()) {

                        targets.add(t);
                        System.out.print(++listed + ":  " + t.toString() + " ");
                    }
                    for (Troop t : battlefield.getPlayer2Troops()) {

                        targets.add(t);
                        System.out.print(++listed + ":  " + t.toString() + " ");
                    }
                    int d = kb.nextInt() - 1;
                    if (d >= 0 && d < listed) {
                        Object target = targets.get(d);
                        if (target instanceof Player) {
                            spell.effect((Player) target);
                        } else {
                            spell.effect((Troop) target);
                        }

                    }
                    battlefield.update();
                }

            }
            p.getHand().remove(card);
        }
    }

    public Field getBattlefield() {
        return battlefield;
    }

    public void setBattlefield(Field battlefield) {
        this.battlefield = battlefield;
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }
}
