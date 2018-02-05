package java1.lesson1.sea_battle.components.Enums;

public enum OutputMessage {
    PAST("Мимо"), WOUNDED("Ранил"), SUNK("Потопил");

    private String message;

    OutputMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
