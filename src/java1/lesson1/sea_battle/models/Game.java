package java1.lesson1.sea_battle.models;

import java1.lesson1.sea_battle.components.Enums.FieldSymbol;
import java1.lesson1.sea_battle.components.Enums.OutputMessage;
import java1.lesson1.sea_battle.components.Enums.ShipState;
import java1.lesson1.sea_battle.components.Factories.PlayerFactory;
import java1.lesson1.sea_battle.components.Factories.SquadronFactory;
import java1.lesson1.sea_battle.components.Strategies.ImproveAutoMakeShotStrategy;
import java1.lesson1.sea_battle.components.Strategies.SimpleAutoMakeShotStrategy;
import java1.lesson1.sea_battle.views.ApplicationView;

import java.util.ArrayList;

public class Game {
    private static Game instance;

    public static Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }


    private ArrayList<Squadron> squadrons;
    private ArrayList<BattleField> battleFields;
    private ArrayList<Player> players;

    private Game() {
        squadrons = new ArrayList<>();
        Squadron squadron1 = SquadronFactory.getInstance().createSquadron();
        Squadron squadron2 = SquadronFactory.getInstance().createSquadron();
        squadrons.add(squadron1);
        squadrons.add(squadron2);

        battleFields = new ArrayList<>();
        BattleField battleField1 = new BattleField();
        battleField1.initWithSquadron(squadron1);
        BattleField battleField2 = new BattleField();
        battleField2.initWithSquadron(squadron2);
        battleFields.add(battleField1);
        battleFields.add(battleField2);

        players = new ArrayList<>();
    }

    /**
     * Инициализация игры по умолчанию
     */
    public void initDefault() {
//        players.add(PlayerFactory.getInstance().createDefaultPlayer());
//        players.add(PlayerFactory.getInstance().createPlayer());
        Player pl1 = PlayerFactory.getInstance().createPlayer("John", new SimpleAutoMakeShotStrategy());
        Player pl2 = PlayerFactory.getInstance().createPlayer("Nik", new ImproveAutoMakeShotStrategy());
        players.add(pl1);
        players.add(pl2);
    }

    /**
     * Игровой цикл
     */
    public void start() {
        int currentPlayer = 0;
        int adversary;
        boolean isSuccess = false;

        // Приветствие игрокам
        ApplicationView.getInstance().getView().renderGreetingPlayers(players);

        while (true) {
            // Установить текущего игрока
            if (!isSuccess) {
                currentPlayer = currentPlayer == 0 ? 1 : 0;
            }
            adversary = currentPlayer == 0 ? 1 : 0;

            // Показать игровое поле
            battleFields.get(currentPlayer).render();

            // Сделать выстрел
            Shot shot = players.get(currentPlayer).makeShot();

            // Обработать выстрел
            ShipState result = squadrons.get(adversary).getResult(shot);
            switch (result) {
                case UNHARMED:
                    battleFields.get(currentPlayer).getAdversarySeaArea().setCell(shot.getCoordinate().getColumn(), shot.getCoordinate().getRow(), FieldSymbol.PAST.getValue());
                    battleFields.get(adversary).getPlayerSeaArea().setCell(shot.getCoordinate().getColumn(), shot.getCoordinate().getRow(), FieldSymbol.PAST.getValue());
                    ApplicationView.getInstance().getView().renderShotingResult(OutputMessage.PAST.getMessage());
                    isSuccess = false;
                    break;
                case WOUNDED:
                    battleFields.get(currentPlayer).getAdversarySeaArea().setCell(shot.getCoordinate().getColumn(), shot.getCoordinate().getRow(), FieldSymbol.WOUNDED.getValue());
                    battleFields.get(adversary).getPlayerSeaArea().setCell(shot.getCoordinate().getColumn(), shot.getCoordinate().getRow(), FieldSymbol.WOUNDED.getValue());
                    ApplicationView.getInstance().getView().renderShotingResult(OutputMessage.WOUNDED.getMessage());
                    isSuccess = true;
                    break;
                case SUNK:
                    battleFields.get(currentPlayer).getAdversarySeaArea().setCell(shot.getCoordinate().getColumn(), shot.getCoordinate().getRow(), FieldSymbol.WOUNDED.getValue());
                    battleFields.get(adversary).getPlayerSeaArea().setCell(shot.getCoordinate().getColumn(), shot.getCoordinate().getRow(), FieldSymbol.WOUNDED.getValue());
                    ApplicationView.getInstance().getView().renderShotingResult(OutputMessage.SUNK.getMessage());
                    isSuccess = true;
                    break;
            }
            System.out.println();

            // Проверить на окончание игры
            if (squadrons.get(adversary).isLosing()) {
                battleFields.get(currentPlayer).render();
                ApplicationView.getInstance().getView().renderCongratulations(players.get(currentPlayer).getName());
                break;
            }
        }
    }
}
