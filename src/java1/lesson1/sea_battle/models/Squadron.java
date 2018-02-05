package java1.lesson1.sea_battle.models;

import java1.lesson1.sea_battle.components.Enums.ShipState;

import java.util.ArrayList;

public class Squadron {
    public static ShipState shotResult = ShipState.UNHARMED;

    private ArrayList<Ship> ships;

    public Squadron() {
        ships = new ArrayList<>();
    }

    public void addShip(Ship ship) {
        ships.add(ship);
    }

    ArrayList<Ship> getShips() {
        return ships;
    }

    /**
     * Получить результат по выстрелу
     * @param shot
     * @return
     */
    public ShipState getResult(Shot shot) {
        for (Ship ship : ships) {
            Squadron.shotResult = ship.getResult(shot);
            if (Squadron.shotResult.equals(ShipState.WOUNDED) || Squadron.shotResult.equals(ShipState.SUNK)) {
                return Squadron.shotResult;
            }
        }
        return Squadron.shotResult;
    }

    /**
     * Проверяет, все ли корабли потопили в эскадре
     * @return
     */
    public boolean isLosing() {
        int sunkenShipsCount = 0;
        for (Ship ship : ships) {
            if (ship.getState().equals(ShipState.SUNK)) {
                sunkenShipsCount++;
            }
        }

        return sunkenShipsCount == ships.size();
    }
}
