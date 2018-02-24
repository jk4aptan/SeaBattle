package java1.lesson1.sea_battle.components.Factories;

import java1.lesson1.sea_battle.components.Strategies.ImproveAutoMakeShotStrategy;
import java1.lesson1.sea_battle.components.Strategies.SimpleAutoMakeShotStrategy;
import java1.lesson1.sea_battle.configs.Config;
import java1.lesson1.sea_battle.controllers.GameController;
import java1.lesson1.sea_battle.models.Computer;
import java1.lesson1.sea_battle.models.IMakeShotStrategy;
import java1.lesson1.sea_battle.models.Player;

public class PlayerFactory {
    private static PlayerFactory instance;

    public static PlayerFactory getInstance() {
        if (instance == null) {
            instance = new PlayerFactory();
        }
        return instance;
    }


    private String playerName;
    private volatile boolean isSetPlayerName;

    private PlayerFactory() {
    }


    public Player createPlayer() {
        isSetPlayerName = false;
        GameController.getInstance().getPlayerName();
        while (!isSetPlayerName) {};
        return new Player(playerName);
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setIsSetPlayerName(boolean value) {
        this.isSetPlayerName = value;
    }


    public Player createAdversary() {
        Player player = null;

        switch (Config.MAKE_SHOT_STRATEGY) {
            case SIMPLE:
                player = new Computer(Config.DEFAULT_PLAYER_NAME, new SimpleAutoMakeShotStrategy());
                break;
            case IMPROVE:
                player = new Computer(Config.DEFAULT_PLAYER_NAME, new ImproveAutoMakeShotStrategy());
                break;
        }

        return player;
    }

    public Player createPlayer(String name, IMakeShotStrategy makeShotStrategy) {
        return new Computer(name, makeShotStrategy);
    }
}
