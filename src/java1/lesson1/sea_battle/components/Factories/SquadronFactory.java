package java1.lesson1.sea_battle.components.Factories;

import java1.lesson1.sea_battle.configs.Config;
import java1.lesson1.sea_battle.models.Coordinate;
import java1.lesson1.sea_battle.models.Ship;
import java1.lesson1.sea_battle.models.Squadron;

import java.util.ArrayList;

public class SquadronFactory {
    private static SquadronFactory instance;

    public static SquadronFactory getInstance() {
        if (instance == null) {
            instance = new SquadronFactory();
        }
        return instance;
    }


    private ArrayList<Integer> busySells;

    private SquadronFactory() {
        busySells = new ArrayList<>();
    }

    /**
     * Создание эскадры кораблей
     *
     * @return Squadron
     */
    public Squadron createSquadron() {
        Squadron squadron = new Squadron();

        // для всех типов кораблей входящих в состав эскадры
        for (int shipType : Config.SQUADRON_CONFIG) {
            //создать корабль соответсвующего типа и проверить валидность его координат
            Ship ship = null;
            do {
                switch (Config.CREATING_SHIP_MODE) {
                    case AUTO:
                        ship = ShipFactory.getInstance().createShipAuto(shipType);
                        break;
                    case HAND:
                        ship = ShipFactory.getInstance().createShipHand(shipType);
                        break;
                }
            } while (!isShipValid(ship));

            // добавить корабль в эскадру
            squadron.addShip(ship);

            // запомнить занятые ячейки
            addBusySells(ship.getCoordinates());
        }
        busySells.clear();
        return squadron;
    }

    /**
     * Проверка валидности размещения корабля в эскадре
     *
     * @param ship
     * @return
     */
    private boolean isShipValid(Ship ship) {
        for (Coordinate deck : ship.getCoordinates()) {
            // координаты корабля попадают на занятые ячейки поля
            if (busySells.contains(deck.getValue())) {
                return false;
            }
        }
        return true;
    }

    /**
     * Сохранить ячейки занятые кораблем и ячейки вокруг корабля.
     * Ячейки занятые кораблем и вокруг него немогут быть заняты другим кораблем.
     *
     * @param shipCoordinates
     */
    private void addBusySells(Coordinate[] shipCoordinates) {
        final int COLUMN = 1;
        final int ROW = 10;

        for (Coordinate deck : shipCoordinates) {
            busySells.add(deck.getValue());

            if (deck.getColumn() == 0) {
                switch (deck.getRow()) {
                    case 0:
                        busySells.add(deck.getValue() + COLUMN);
                        busySells.add(deck.getValue() + ROW);
                        busySells.add(deck.getValue() + ROW + COLUMN);
                        break;
                    case 9:
                        busySells.add(deck.getValue() + COLUMN);
                        busySells.add(deck.getValue() - ROW);
                        busySells.add(deck.getValue() - ROW + COLUMN);
                        break;
                    default:
                        busySells.add(deck.getValue() + COLUMN);
                        busySells.add(deck.getValue() - ROW);
                        busySells.add(deck.getValue() - ROW + COLUMN);
                        busySells.add(deck.getValue() + ROW);
                        busySells.add(deck.getValue() + ROW + COLUMN);
                }
            }

            if (deck.getColumn() == 9) {
                switch (deck.getRow()) {
                    case 0:
                        busySells.add(deck.getValue() - COLUMN);
                        busySells.add(deck.getValue() + ROW);
                        busySells.add(deck.getValue() + ROW - COLUMN);
                        break;
                    case 9:
                        busySells.add(deck.getValue() - COLUMN);
                        busySells.add(deck.getValue() - ROW);
                        busySells.add(deck.getValue() - ROW - COLUMN);
                        break;
                    default:
                        busySells.add(deck.getValue() - COLUMN);
                        busySells.add(deck.getValue() - ROW);
                        busySells.add(deck.getValue() - ROW - COLUMN);
                        busySells.add(deck.getValue() + ROW);
                        busySells.add(deck.getValue() + ROW - COLUMN);
                }
            }

            if (deck.getRow() == 0) {
                busySells.add(deck.getValue() - COLUMN);
                busySells.add(deck.getValue() + COLUMN);
                busySells.add(deck.getValue() + ROW);
                busySells.add(deck.getValue() + ROW - COLUMN);
                busySells.add(deck.getValue() + ROW + COLUMN);
            }

            if (deck.getRow() == 9) {
                busySells.add(deck.getValue() - COLUMN);
                busySells.add(deck.getValue() + COLUMN);
                busySells.add(deck.getValue() - ROW);
                busySells.add(deck.getValue() - ROW - COLUMN);
                busySells.add(deck.getValue() - ROW + COLUMN);
            }

            if (deck.getColumn() != 0 && deck.getColumn() != 9 && deck.getRow() != 0 && deck.getRow() != 9) {
                busySells.add(deck.getValue() - COLUMN);
                busySells.add(deck.getValue() + COLUMN);
                busySells.add(deck.getValue() - ROW);
                busySells.add(deck.getValue() - ROW - COLUMN);
                busySells.add(deck.getValue() - ROW + COLUMN);
                busySells.add(deck.getValue() + ROW);
                busySells.add(deck.getValue() + ROW - COLUMN);
                busySells.add(deck.getValue() + ROW + COLUMN);
            }
        }
    }
}
