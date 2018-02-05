package java1.lesson1.sea_battle.components.Enums;

public enum FieldSymbol {
    PAST('*'), WOUNDED('X'), SEA('_'), EMPTY(' '), START_COLUMN('A'), START_COLUMN_LOW('a'), START_ROW('0');

    private char value;

    FieldSymbol(char c) {
        value = c;
    }

    public char getValue() {
        return value;
    }
}
