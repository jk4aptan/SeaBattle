package java1.lesson1.sea_battle.models;

import java.util.ArrayList;

public interface IViewable {
    void renderBattleField(Field playerSeaArea, Field adversarySeaArea);
    void renderGreetingPlayers(ArrayList<Player> players);
    void renderShotingResult(String value);
    void renderCongratulations(String name);
    void renderInputName();

    void renderMakeShotHandStart(String name);
    void renderMakeShotHandInput();
    void renderMakeShotHandError();
    void renderMakeShotHandCoordinate(int value);
}
