package battleship;

public class Player {
    private String name;

    private Battlefield battlefield;

    public Player(Battlefield battlefield, String name) {
        this.battlefield = battlefield;
        this.name = name;
    }

    public Battlefield getBattlefield() {
        return battlefield;
    }

    public String getName() {
        return name;
    }
}
