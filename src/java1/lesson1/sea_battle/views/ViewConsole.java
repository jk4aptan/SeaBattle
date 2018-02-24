package java1.lesson1.sea_battle.views;

public class ViewConsole {
    private static ViewConsole instance;

    public static ViewConsole getInstance() {
        if (instance == null) {
            instance = new ViewConsole();
        }
        return instance;
    }


    private ViewConsole() {
    }

    public void renderMakeShotHandStart(String name) {
        System.out.println(name + ", ваш выстрел");
    }

    public void renderMakeShotHandCoordinate(int value) {
        System.out.println(value);
    }
}
