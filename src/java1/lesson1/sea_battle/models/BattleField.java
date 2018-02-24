package java1.lesson1.sea_battle.models;

import java1.lesson1.sea_battle.components.Enums.FieldSymbol;
import java1.lesson1.sea_battle.configs.Config;

/**
 * Класс BattleField реализует игровое поле игрока
 */
public class BattleField {
    /**
     * Поле игрока с размещенной на нем эскадрой
     */
    private Field playerSeaArea;

    /**
     * Поле противника, по которому игрок производит выстрелы
     */
    private Field adversarySeaArea;


    public BattleField() {
        playerSeaArea = new Field();
        adversarySeaArea = new Field();

        for (int column = 0; column < Config.BATTLE_FIELD_COLUMNS_COUNT; column++) {
            for (int row = 0; row < Config.BATTLE_FIELD_ROWS_COUNT; row++) {
                playerSeaArea.setCell(column, row, FieldSymbol.SEA.getValue());
                adversarySeaArea.setCell(column, row, FieldSymbol.SEA.getValue());
            }
        }
    }

    public Field getPlayerSeaArea() {
        return playerSeaArea;
    }

    public Field getAdversarySeaArea() {
        return adversarySeaArea;
    }


    /**
     * Размещение эскадры на поле игрока
     *
     * @param squadron
     */
    public void initWithSquadron(Squadron squadron) {
        final char SHIP = '|';
        for (Ship ship : squadron.getShips()) {
            for (Coordinate deck : ship.getCoordinates()) {
                playerSeaArea.setCell(deck.getColumn(), deck.getRow(), SHIP);
            }
        }
    }
}
