package java1.lesson1.sea_battle.views;

import java1.lesson1.sea_battle.configs.Config;
import java1.lesson1.sea_battle.models.IViewable;

public class ApplicationView {
    private static ApplicationView instance;

    public static ApplicationView getInstance() {
        if (instance == null) {
            instance = new ApplicationView();
        }
        return instance;
    }


    private IViewable view;

    private ApplicationView() {
        switch (Config.APPLICATION_TYPE) {
            case CONSOLE:
                view = ViewConsole.getInstance();
                break;
            case GUI:
                // заглушка
                break;
        }
    }

    public IViewable getView() {
        return view;
    }
}
