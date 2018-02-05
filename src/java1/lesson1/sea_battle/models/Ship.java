package java1.lesson1.sea_battle.models;

import java1.lesson1.sea_battle.components.Enums.ShipState;

public class Ship {
    private Coordinate[] coordinates;
    private int decks;
    private ShipState[] decksState;
    private ShipState state;

    public Ship() {
    }

    public void setCoordinates(Coordinate[] coordinates) {
        this.coordinates = coordinates;
    }

    public Coordinate[] getCoordinates() {
        return coordinates;
    }

    public void setDecks(int decks) {
        this.decks = decks;
    }

    public void setDecksState(ShipState[] state) {
        decksState = state;
    }

    public void setState(ShipState state) {
        this.state = state;
    }

    ShipState getState() {
        return state;
    }

    ShipState getResult(Shot shot) {
        for (int deck = 0; deck < decks; deck++) {
            if (coordinates[deck].getValue() == shot.getCoordinate().getValue()) {
                decksState[deck] = ShipState.WOUNDED;
                int damagedDeckCount = 0;
                for (ShipState deckState : decksState) {
                    if (deckState.equals(ShipState.WOUNDED)) {
                        damagedDeckCount++;
                    }
                }
                if (damagedDeckCount == decks) {
                    state = ShipState.SUNK;
                    return ShipState.SUNK;
                } else {
                    state = ShipState.WOUNDED;
                    return ShipState.WOUNDED;
                }
            }
        }
        return ShipState.UNHARMED;
    }
}
