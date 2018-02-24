package java1.lesson1.sea_battle.models;

import java1.lesson1.sea_battle.configs.Config;
import java1.lesson1.sea_battle.controllers.GameController;

public class Player implements IMakeShot {
    protected String name;
    protected IMakeShotStrategy makeShotStrategy;

    private Coordinate shotCoordinate;
    private volatile boolean isSetShotCoordinate;


    public Player(String name) {
        this.name = name;
    }

    public Player(String name, IMakeShotStrategy makeShotStrategy) {
        this(name);
        this.makeShotStrategy = makeShotStrategy;
    }

    @Override
    public Shot makeShot() {
        if (name.equals(Config.DEFAULT_PLAYER_NAME)) {
            GameController.getInstance().doComputerShot();
            shotCoordinate = makeShotStrategy.makeShotCoordinate();
        } else {
            isSetShotCoordinate = false;
            GameController.getInstance().doPlayerShot();
            while (!isSetShotCoordinate) {};
        }
        return new Shot(shotCoordinate);
    }

    public String getName() {
        return name;
    }

    public void setShotCoordinate(Coordinate shotCoordinate) {
        this.shotCoordinate = shotCoordinate;
    }

    public void setIsSetShotCoordinate(boolean setShotCoordinate) {
        isSetShotCoordinate = setShotCoordinate;
    }
}
