package java1.lesson1.sea_battle.models;

import java1.lesson1.sea_battle.components.Enums.FieldSymbol;
import java1.lesson1.sea_battle.components.Enums.OutputMessage;
import java1.lesson1.sea_battle.components.Enums.ShipState;
import java1.lesson1.sea_battle.components.Factories.PlayerFactory;
import java1.lesson1.sea_battle.components.Factories.SquadronFactory;
import java1.lesson1.sea_battle.controllers.GameController;

import java.util.HashMap;
import java.util.Map;

public class Game {
    private static Game instance;

    private Map<Player, Squadron> squadrons;
    private Map<Player, BattleField> battleFields;
    private Player player;
    private Player adversary;
    private GameController gameController;


    public Game() {
        player = PlayerFactory.getInstance().createPlayer();
        adversary = PlayerFactory.getInstance().createAdversary();

        squadrons = new HashMap<>();
        Squadron squadron1 = SquadronFactory.getInstance().createSquadron();
        Squadron squadron2 = SquadronFactory.getInstance().createSquadron();
        squadrons.put(player, squadron1);
        squadrons.put(adversary, squadron2);

        battleFields = new HashMap<>();
        BattleField battleField1 = new BattleField();
        battleField1.initWithSquadron(squadron1);
        BattleField battleField2 = new BattleField();
        battleField2.initWithSquadron(squadron2);
        battleFields.put(player, battleField1);
        battleFields.put(adversary, battleField2);

        gameController = GameController.getInstance();
        gameController.setPlayer(player);
        gameController.greetingPlayer();
        gameController.updateBattleField(battleFields.get(player));
    }

    /**
     * Игровой цикл
     */
    public void start() {
        Player currentPlayer = player;
        Player currentAdversary;
        boolean isSuccess = false;

        while (true) {
            // Установить текущего игрока
            if (!isSuccess) {
                currentPlayer = currentPlayer == player ? adversary : player;
            }
            currentAdversary = currentPlayer == player ? adversary : player;

            // Сделать выстрел
            Shot shot = currentPlayer.makeShot();

            // Обработать выстрел
            ShipState result = squadrons.get(currentAdversary).getResult(shot);
            switch (result) {
                case UNHARMED:
                    battleFields.get(currentPlayer).getAdversarySeaArea().setCell(shot.getCoordinate().getColumn(), shot.getCoordinate().getRow(), FieldSymbol.PAST.getValue());
                    battleFields.get(currentAdversary).getPlayerSeaArea().setCell(shot.getCoordinate().getColumn(), shot.getCoordinate().getRow(), FieldSymbol.PAST.getValue());
                    isSuccess = false;
                    break;
                case WOUNDED:
                    battleFields.get(currentPlayer).getAdversarySeaArea().setCell(shot.getCoordinate().getColumn(), shot.getCoordinate().getRow(), FieldSymbol.WOUNDED.getValue());
                    battleFields.get(currentAdversary).getPlayerSeaArea().setCell(shot.getCoordinate().getColumn(), shot.getCoordinate().getRow(), FieldSymbol.WOUNDED.getValue());
                    isSuccess = true;
                    break;
                case SUNK:
                    battleFields.get(currentPlayer).getAdversarySeaArea().setCell(shot.getCoordinate().getColumn(), shot.getCoordinate().getRow(), FieldSymbol.WOUNDED.getValue());
                    battleFields.get(currentAdversary).getPlayerSeaArea().setCell(shot.getCoordinate().getColumn(), shot.getCoordinate().getRow(), FieldSymbol.WOUNDED.getValue());
                    isSuccess = true;
                    break;
            }

            // Показать игровое поле
            if (currentPlayer.getName().equals(player.getName())) {
                gameController.updateBattleField(battleFields.get(player));
                gameController.showResult(player, result);
            } else {
                gameController.showResult(adversary, result);
            }

            // Проверить на окончание игры
            if (squadrons.get(currentAdversary).isLosing()) {
                gameController.gameIsOver(currentPlayer);
                break;
            }
        }
    }
}
