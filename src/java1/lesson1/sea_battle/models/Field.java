package java1.lesson1.sea_battle.models;

import java1.lesson1.sea_battle.components.Enums.FieldSymbol;
import java1.lesson1.sea_battle.configs.Config;

public class Field {
    public static final char[] columnsSymbols = init(Config.BATTLE_FIELD_COLUMNS_COUNT, FieldSymbol.START_COLUMN.getValue());
    public static final char[] columnsSymbolsLow = init(Config.BATTLE_FIELD_COLUMNS_COUNT, FieldSymbol.START_COLUMN_LOW.getValue());
    public static final char[] rowsSymbols = init(Config.BATTLE_FIELD_ROWS_COUNT, FieldSymbol.START_ROW.getValue());

    private static char[] init(int count, char currentSymbol) {
        char[] symbols = new char[count];
        for (int i = 0; i < count; i++) {
            symbols[i] = currentSymbol++;
        }
        return symbols;
    }


    private char[][] fieldArea;

    public Field() {
        fieldArea = new char[Config.BATTLE_FIELD_COLUMNS_COUNT][Config.BATTLE_FIELD_ROWS_COUNT];
    }

    public char getCell(int column, int row) {
        return fieldArea[column][row];
    }

    public void setCell(int column, int row, char symbol) {
        fieldArea[column][row] = symbol;
    }
}
