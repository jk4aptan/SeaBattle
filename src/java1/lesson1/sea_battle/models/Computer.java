package java1.lesson1.sea_battle.models;

import java1.lesson1.sea_battle.views.ViewConsole;

public class Computer extends Player{

    public Computer(String name, IMakeShotStrategy makeShotStrategy) {
        super(name, makeShotStrategy);
    }

    @Override
    public Shot makeShot() {
        ViewConsole.getInstance().renderMakeShotHandStart(name);
        Coordinate shotCoordinate = makeShotStrategy.makeShotCoordinate();
        ViewConsole.getInstance().renderMakeShotHandCoordinate(shotCoordinate.getValue());

        return new Shot(shotCoordinate);
    }
}
