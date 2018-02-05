package java1.lesson1.sea_battle.models;

import java1.lesson1.sea_battle.views.ApplicationView;

public class Player implements IMakeShot {
    private String name;
    private final IMakeShotStrategy makeShotStrategy;

    public Player(String name, IMakeShotStrategy makeShotStrategy) {
        this.name = name;
        this.makeShotStrategy = makeShotStrategy;
    }

    @Override
    public Shot makeShot() {
        ApplicationView.getInstance().getView().renderMakeShotHandStart(name);
        Coordinate shotCoordinate = makeShotStrategy.makeShotCoordinate();
        ApplicationView.getInstance().getView().renderMakeShotHandCoordinate(shotCoordinate.getValue());

        return new Shot(shotCoordinate);
    }

    public String getName() {
        return name;
    }
}
