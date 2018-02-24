package java1.lesson1.sea_battle.views;

import java1.lesson1.sea_battle.configs.Config;
import java1.lesson1.sea_battle.controllers.GameController;
import java1.lesson1.sea_battle.models.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GameWindow extends JFrame {

    private static final Integer BATTLE_FIELDS = 1;
    private static final Integer FIELDS = 2;
    private static final Integer ROW_STEP = 10;

    private JButton[][] playerSels;
    private JButton[][] adversarySels;
    private GameController gameController;
    private boolean isPlayerShot;
    private JLabel message;


    public GameWindow() {
        setSize(1000, 500);
        setTitle("Sea Battle Game");
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel battleField = new JPanel(new GridLayout(BATTLE_FIELDS, FIELDS));
        JPanel adversarySeaArea = new JPanel(new GridLayout(Config.BATTLE_FIELD_ROWS_COUNT, Config.BATTLE_FIELD_COLUMNS_COUNT));
        adversarySels = new JButton[Config.BATTLE_FIELD_ROWS_COUNT][Config.BATTLE_FIELD_COLUMNS_COUNT];
        for (int i = 0; i < Config.BATTLE_FIELD_ROWS_COUNT; i++) {
            for (int j = 0; j < Config.BATTLE_FIELD_COLUMNS_COUNT; j++) {
                JButton jButton = new JButton("");
                int finalI = i;
                int finalJ = j;
                jButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (isPlayerShot) {
                            gameController.setShotCoordinate(new Coordinate(finalI * ROW_STEP + finalJ));
                        }
                    }
                });

                adversarySels[i][j] = jButton;
                adversarySeaArea.add(jButton);
            }
        }
        battleField.add(adversarySeaArea);

        JPanel playerSeaArea = new JPanel(new GridLayout(Config.BATTLE_FIELD_ROWS_COUNT, Config.BATTLE_FIELD_COLUMNS_COUNT));
        playerSels = new JButton[Config.BATTLE_FIELD_ROWS_COUNT][Config.BATTLE_FIELD_COLUMNS_COUNT];
        for (int i = 0; i < Config.BATTLE_FIELD_ROWS_COUNT; i++) {
            for (int j = 0; j < Config.BATTLE_FIELD_COLUMNS_COUNT; j++) {
                JButton jButton = new JButton("");
                playerSels[i][j] = jButton;
                playerSeaArea.add(jButton);
            }
        }
        battleField.add(playerSeaArea);
        add(battleField, BorderLayout.CENTER);

        message = new JLabel("Sea Battle Game");
        message.setHorizontalAlignment(SwingConstants.CENTER);
        add(message, BorderLayout.NORTH);
        setVisible(true);

        gameController = GameController.getInstance();
        gameController.setGameWindow(this);
        isPlayerShot = false;
    }

    public void updateBattleField(BattleField battleField) {
        Field playerSeaArea = battleField.getPlayerSeaArea();
        Field adversarySeaArea = battleField.getAdversarySeaArea();

        for (int i = 0; i < Config.BATTLE_FIELD_ROWS_COUNT; i++) {
            for (int j = 0; j < Config.BATTLE_FIELD_COLUMNS_COUNT; j++) {
                playerSels[i][j].setText(Character.toString(playerSeaArea.getCell(j, i)));
                adversarySels[i][j].setText(Character.toString(adversarySeaArea.getCell(j, i)));
            }
        }
    }

    public void getPlayerName() {
        String name = JOptionPane.showInputDialog(this, "Enter Your Name");
        gameController.setPlayerName(name);
    }

    public void setIsPlayerShot(boolean playerShot) {
        isPlayerShot = playerShot;
    }

    public void showMessage(String message) {
        this.message.setText(message);
    }

    public void gameIsOver(String currentPlayerName) {
        JOptionPane.showMessageDialog(this, "Победа!!! " + currentPlayerName + ", вы выиграли!");
        System.exit(0);
    }
}
