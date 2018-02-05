package java1.lesson1.sea_battle.components.Inputs;

import java1.lesson1.sea_battle.models.IInputable;
import java.util.Scanner;

/**
 * Реализует ввод пользовательских данных в консольном режиме работы программы
 */
public class InputConsole implements IInputable {
    private static InputConsole instance;

    public static InputConsole getInstance() {
        if (instance == null) {
            instance = new InputConsole();
        }
        return instance;
    }


    private Scanner scanner;

    private InputConsole() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public String getString() {
        return scanner.nextLine();
    }
}
