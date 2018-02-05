package java1.lesson1.sea_battle.configs;

import java1.lesson1.sea_battle.components.Enums.ApplicationType;
import java1.lesson1.sea_battle.components.Enums.CreatingShipMode;
import java1.lesson1.sea_battle.components.Enums.MakeShotStrategy;
import java1.lesson1.sea_battle.components.Enums.ShotingMode;

import java.util.ArrayList;

public class Config {
    public static final int ONE_DECK_SHIP = 1;
    public static final int TWO_DECK_SHIP = 2;
    public static final int THREE_DECK_SHIP = 3;
    public static final int FOUR_DECK_SHIP = 4;
    public static final int BATTLE_FIELD_COLUMNS_COUNT;
    public static final int BATTLE_FIELD_ROWS_COUNT;
    public static final int MIN_COORDINATE = 0;
    public static final int MAX_COORDINATE;
    public static final String DEFAULT_PLAYER_NAME;
    public static final ArrayList<Integer> SQUADRON_CONFIG;
    public static final ShotingMode SHOTING_MODE;
    public static final MakeShotStrategy MAKE_SHOT_STRATEGY;
    public static final CreatingShipMode CREATING_SHIP_MODE;
    public static final ApplicationType APPLICATION_TYPE;

    static {
        BATTLE_FIELD_COLUMNS_COUNT = 10;
        BATTLE_FIELD_ROWS_COUNT = 10;
        MAX_COORDINATE = BATTLE_FIELD_COLUMNS_COUNT * BATTLE_FIELD_ROWS_COUNT;

        DEFAULT_PLAYER_NAME = "computer";

        // APPLICATION_TYPE's values - CONSOLE, GUI
        APPLICATION_TYPE = ApplicationType.CONSOLE;

        // CREATING_SHIP_MODE's values - AUTO, HAND
        CREATING_SHIP_MODE = CreatingShipMode.AUTO;

        // SHOTING_MODE's values - AUTO, HAND
        SHOTING_MODE = ShotingMode.AUTO;

        // MakeShotStrategy's values - SIMPLE, IMPROVE
        MAKE_SHOT_STRATEGY = MakeShotStrategy.SIMPLE;

        SQUADRON_CONFIG = new ArrayList<>();
        SQUADRON_CONFIG.add(FOUR_DECK_SHIP);
        SQUADRON_CONFIG.add(THREE_DECK_SHIP);
        SQUADRON_CONFIG.add(THREE_DECK_SHIP);
        SQUADRON_CONFIG.add(TWO_DECK_SHIP);
        SQUADRON_CONFIG.add(TWO_DECK_SHIP);
        SQUADRON_CONFIG.add(TWO_DECK_SHIP);
        SQUADRON_CONFIG.add(ONE_DECK_SHIP);
        SQUADRON_CONFIG.add(ONE_DECK_SHIP);
        SQUADRON_CONFIG.add(ONE_DECK_SHIP);
        SQUADRON_CONFIG.add(ONE_DECK_SHIP);
    }
}
