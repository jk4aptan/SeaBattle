package java1.lesson1.sea_battle.components.Strategies;

import java1.lesson1.sea_battle.components.Inputs.ApplicationInput;
import java1.lesson1.sea_battle.models.*;
import java1.lesson1.sea_battle.views.ApplicationView;

/**
 * Реализует стратегию стрельбы в ручном режиме. Пользователе сами задают координаты выстрелов.
 */
public class HandMakeShotStrategy implements IMakeShotStrategy{
    @Override
    public Coordinate makeShotCoordinate() {
        final int ROW_STEP = 10;
        final char ZERO_SYMBOL = '0';
        int value = 0;
        boolean isShotValid = false;

        while (!isShotValid) {
            ApplicationView.getInstance().getView().renderMakeShotHandInput();
            String shot = ApplicationInput.getInstance().getInput().getString();

            int column = -1;
            for (int i = 0; i < Field.columnsSymbols.length; i++) {
                if (Field.columnsSymbols[i] == shot.charAt(0) || Field.columnsSymbolsLow[i] == shot.charAt(0)) {
                    column = i;
                    isShotValid = true;
                }
            }

            int row = shot.charAt(1) - ZERO_SYMBOL;

            value = row * ROW_STEP + column;

            if (!isShotValid) {
                ApplicationView.getInstance().getView().renderMakeShotHandError();
            }
        }
        return new Coordinate(value);
    }
}
