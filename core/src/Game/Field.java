package Game;

import Cards.Troop;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by acurr on 5/27/2016.
 */
public class Field {
    private List<Troop> player1Troops = new ArrayList<Troop>();
    private List<Troop> player2Troops = new ArrayList<Troop>();
    private List<Player> players = new ArrayList<Player>();
    private List<Troop> grave = new ArrayList<Troop>();
    private Player p1;
    private Player p2;

    public Field(Player p1, Player p2) {
        this.p1 = p1;
        this.p2 = p2;
        players.add(p1);
        players.add(p2);
        p1.setBattlefield(this);
        p2.setBattlefield(this);
    }

    public Player getP1() {
        return p1;
    }

    public void setP1(Player p1) {
        this.p1 = p1;
    }

    public Player getP2() {
        return p2;
    }

    public void setP2(Player p2) {
        this.p2 = p2;
    }

    public void update() {
        List<Troop> toRemove = new ArrayList<Troop>();
        for (Troop t : player1Troops) {
            if (t.getCurrentHealth() <= 0) {
                toRemove.add(t);
            }
        }

        for (Troop t : player2Troops) {
            if (t.getCurrentHealth() <= 0) {

                toRemove.add(t);
            }
        }
        player1Troops.removeAll(toRemove);
        player2Troops.removeAll(toRemove);
        grave.addAll(toRemove);
        printField();
    }

    public void printField() {
        System.out.println("========");
        System.out.println(p1.getName() + ": Health: " + p1.getHealth() + " Card in hand: " + p1.getHand().size());
        System.out.println("========");
        System.out.println(player1Troops);
        System.out.println("========");
        System.out.println(player2Troops);
        System.out.println("========");
        System.out.println(p2.getName() + ": Health: " + p2.getHealth() + " Card in hand: " + p2.getHand().size());
    }

    public List<Troop> getGrave() {
        return grave;
    }

    public void setGrave(ArrayList<Troop> grave) {
        this.grave = grave;
    }

    public void addTroop(Player p, Troop troop) {
        if (p == p1)
            player1Troops.add(troop);
        if (p == p2)
            player2Troops.add(troop);
    }

    public void removeTroop(Troop troop) {
        player1Troops.remove(troop);
        player2Troops.remove(troop);
        grave.add(troop);
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public List<Troop> getPlayersTroops(Player player) {
        if (player == p1)
            return player1Troops;
        else
            return player2Troops;
    }

    public List<Troop> getOpposingTroops(Player player) {
        if (player == p1)
            return player2Troops;
        else
            return player1Troops;
    }

    public Player getOpposingPlayer(Player player) {
        if (player == p1)
            return p2;
        else
            return p1;

    }

    public List<Troop> getPlayer1Troops() {
        return player1Troops;
    }

    public void setPlayer1Troops(ArrayList<Troop> player1Troops) {
        this.player1Troops = player1Troops;
    }

    public List<Troop> getPlayer2Troops() {
        return player2Troops;
    }

    public void setPlayer2Troops(ArrayList<Troop> player2Troops) {
        this.player2Troops = player2Troops;
    }
}
