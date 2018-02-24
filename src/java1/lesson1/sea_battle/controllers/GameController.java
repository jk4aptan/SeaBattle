package java1.lesson1.sea_battle.controllers;

import java1.lesson1.sea_battle.components.Enums.OutputMessage;
import java1.lesson1.sea_battle.components.Enums.ShipState;
import java1.lesson1.sea_battle.components.Factories.PlayerFactory;
import java1.lesson1.sea_battle.configs.Config;
import java1.lesson1.sea_battle.models.*;
import java1.lesson1.sea_battle.views.GameWindow;

public class GameController {
    private static GameController instance;
    public static GameController getInstance() {
        if (instance == null) {
            instance = new GameController();
        }
        return instance;
    }

    private Player player;
    private GameWindow gameWindow;


    private GameController() {
    }

    public void setGameWindow(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void getPlayerName() {
        gameWindow.getPlayerName();
    }

    public void setPlayerName(String name) {
        PlayerFactory playerFactory = PlayerFactory.getInstance();
        playerFactory.setPlayerName(name);
        playerFactory.setIsSetPlayerName(true);
    }

    public void setShotCoordinate(Coordinate shotCoordinate) {
        gameWindow.setIsPlayerShot(false);
        player.setShotCoordinate(shotCoordinate);
        player.setIsSetShotCoordinate(true);
    }

    public void updateBattleField(BattleField battleField) {
        gameWindow.updateBattleField(battleField);
    }

    public void greetingPlayer() {
        gameWindow.showMessage("Привет " + player.getName() + "!");
    }

    public void doPlayerShot() {
        gameWindow.setIsPlayerShot(true);
        gameWindow.showMessage(player.getName() + ", ваш выстрел");
    }

    public void doComputerShot() {
        gameWindow.showMessage(Config.DEFAULT_PLAYER_NAME + ", ваш выстрел");
    }

    public void showResult(Player player, ShipState result) {
        String message = null;
        switch (result) {
            case UNHARMED:
                message = OutputMessage.PAST.toString();
                break;
            case WOUNDED:
                message = OutputMessage.WOUNDED.toString();
                break;
            case SUNK:
                message = OutputMessage.SUNK.toString();
                break;
        }
        gameWindow.showMessage(player.getName() + ", ваш результат: " + message);
    }

    public void gameIsOver(Player currentPlayer) {
        gameWindow.gameIsOver(currentPlayer.getName());
    }
}
