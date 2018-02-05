package java1.lesson1.sea_battle.components.Factories;

import java1.lesson1.sea_battle.components.Inputs.ApplicationInput;
import java1.lesson1.sea_battle.components.Strategies.HandMakeShotStrategy;
import java1.lesson1.sea_battle.components.Strategies.ImproveAutoMakeShotStrategy;
import java1.lesson1.sea_battle.components.Strategies.SimpleAutoMakeShotStrategy;
import java1.lesson1.sea_battle.configs.Config;
import java1.lesson1.sea_battle.models.IMakeShotStrategy;
import java1.lesson1.sea_battle.models.Player;
import java1.lesson1.sea_battle.views.ApplicationView;

public class PlayerFactory {
    private static PlayerFactory instance;

    public static PlayerFactory getInstance() {
        if (instance == null) {
            instance = new PlayerFactory();
        }
        return instance;
    }


    private PlayerFactory() {
    }

    public Player createPlayer() {
        // define the name
        ApplicationView.getInstance().getView().renderInputName();
        String name = ApplicationInput.getInstance().getInput().getString();
        // define the makeShotStrategy
        IMakeShotStrategy makeShotStrategy = new HandMakeShotStrategy();

        return new Player(name, makeShotStrategy);
    }

    public Player createDefaultPlayer() {
        Player player = null;
        switch (Config.SHOTING_MODE) {
            case HAND:
                player = new Player(Config.DEFAULT_PLAYER_NAME, new HandMakeShotStrategy());
                break;
            case AUTO:
                switch (Config.MAKE_SHOT_STRATEGY) {
                    case SIMPLE:
                        player = new Player(Config.DEFAULT_PLAYER_NAME, new SimpleAutoMakeShotStrategy());
                        break;
                    case IMPROVE:
                        player = new Player(Config.DEFAULT_PLAYER_NAME, new ImproveAutoMakeShotStrategy());
                        break;
                }
                break;
        }

        return player;
    }

    public Player createPlayer(String name, IMakeShotStrategy makeShotStrategy) {
        return new Player(name, makeShotStrategy);
    }
}
