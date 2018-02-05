package java1.lesson1.sea_battle.views;

import java1.lesson1.sea_battle.components.Enums.FieldSymbol;
import java1.lesson1.sea_battle.models.Field;
import java1.lesson1.sea_battle.models.IViewable;
import java1.lesson1.sea_battle.models.Player;

import java.util.ArrayList;

public class ViewConsole implements IViewable {
    private static ViewConsole instance;

    public static ViewConsole getInstance() {
        if (instance == null) {
            instance = new ViewConsole();
        }
        return instance;
    }


    private ViewConsole() {
    }

    @Override
    public void renderGreetingPlayers(ArrayList<Player> players) {
        for (Player player : players) {
            System.out.printf("Здравствуйте %s!\n", player.getName());
        }
        System.out.println();
    }

    @Override
    public void renderShotingResult(String value) {
        System.out.println(value);
    }

    @Override
    public void renderCongratulations(String name) {
        System.out.println("Победа!!! " + name + ", вы выиграли!");
    }

    @Override
    public void renderInputName() {
        System.out.println("Введите ваше имя/nik: ");
    }

    @Override
    public void renderMakeShotHandStart(String name) {
        System.out.println(name + ", ваш выстрел");
    }

    @Override
    public void renderMakeShotHandInput() {
        System.out.println("Введите от А0 до J9: ");
    }

    @Override
    public void renderMakeShotHandError() {
        System.out.println("Неверные координаты выстрела");
    }

    @Override
    public void renderMakeShotHandCoordinate(int value) {
        System.out.println(value);
    }

    @Override
    public void renderBattleField(Field playerSeaArea, Field adversarySeaArea) {
        System.out.printf("%c%c", FieldSymbol.EMPTY.getValue(), FieldSymbol.EMPTY.getValue());
        for (int column = 0; column < Field.columnsSymbols.length; column++) {
            System.out.printf("%c%c", Field.columnsSymbols[column], FieldSymbol.EMPTY.getValue());
        }
        System.out.printf("\t%c%c",  FieldSymbol.EMPTY.getValue(), FieldSymbol.EMPTY.getValue());
        for (int column = 0; column < Field.columnsSymbols.length; column++) {
            System.out.printf("%c%c", Field.columnsSymbols[column], FieldSymbol.EMPTY.getValue());
        }
        System.out.println();

        for (int row = 0; row < Field.rowsSymbols.length; row++) {
            System.out.printf("%c%c", Field.rowsSymbols[row], FieldSymbol.EMPTY.getValue());
            for (int column = 0; column < Field.columnsSymbols.length; column++) {
                System.out.printf("%c%c", playerSeaArea.getCell(column, row), FieldSymbol.EMPTY.getValue());
            }
            System.out.printf("\t%c%c", Field.rowsSymbols[row], FieldSymbol.EMPTY.getValue());
            for (int column = 0; column < Field.columnsSymbols.length; column++) {
                System.out.printf("%c%c", adversarySeaArea.getCell(column, row), FieldSymbol.EMPTY.getValue());
            }
            System.out.println();
        }
    }
}
