package java1.lesson1.sea_battle.components.Inputs;

import java1.lesson1.sea_battle.configs.Config;
import java1.lesson1.sea_battle.models.IInputable;

public class ApplicationInput {
    private static ApplicationInput instance;

    public static ApplicationInput getInstance() {
        if (instance == null) {
            instance = new ApplicationInput();
        }
        return instance;
    }


    private IInputable input;

    private ApplicationInput() {
        switch (Config.APPLICATION_TYPE) {
            case CONSOLE:
                input = InputConsole.getInstance();
                break;
            case GUI:
                // заглушка
                break;
        }
    }

    public IInputable getInput() {
        return input;
    }
}
