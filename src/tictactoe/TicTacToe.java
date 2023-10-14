package tictactoe;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToe implements ActionListener {
    JFrame frame = new JFrame("Tic Tac Toe");
    JButton[] buttons = new JButton[9];
    boolean player1Turn = true;

    TicTacToe() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setLayout(new GridLayout(3, 3));
        

        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            buttons[i].setFont(new Font("Arial", Font.PLAIN, 120));
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);
            frame.add(buttons[i]);
        }

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 9; i++) {
            if (e.getSource() == buttons[i] && buttons[i].getText().equals("")) {
                if (player1Turn) {
                    buttons[i].setText("X");
                    buttons[i].setForeground(Color.BLACK);
                    buttons[i].setOpaque(false);
                } else {
                    buttons[i].setText("O");
                    buttons[i].setForeground(Color.BLACK);
                    buttons[i].setOpaque(false);
                }
                buttons[i].setEnabled(false);
                player1Turn = !player1Turn;

                if (checkForWin()) {
                    String winner = player1Turn ? "O" : "X";
                    JOptionPane.showMessageDialog(frame, "Player " + winner + " wins!");
                    resetGame();
                } else if (isBoardFull()) {
                    JOptionPane.showMessageDialog(frame, "It's a draw!");
                    resetGame();
                }
            }
        }
    }

    private boolean checkForWin() {
        String[] board = new String[9];
        for (int i = 0; i < 9; i++) {
            board[i] = buttons[i].getText();
        }


        // Check rows
        for (int i = 0; i < 9; i += 3) {
            if (board[i].equals(board[i + 1]) && board[i + 1].equals(board[i + 2]) && !board[i].equals("")) {
                return true;
            }
        }

        // Check columns
        for (int i = 0; i < 3; i++) {
            if (board[i].equals(board[i + 3]) && board[i + 3].equals(board[i + 6]) && !board[i].equals("")) {
                return true;
            }
        }

        // Check diagonals
        if (board[0].equals(board[4]) && board[4].equals(board[8]) && !board[0].equals("")) {
            return true;
        }
        if (board[2].equals(board[4]) && board[4].equals(board[6]) && !board[2].equals("")) {
            return true;
        }

        return false;
    }

    private boolean isBoardFull() {
        for (int i = 0; i < 9; i++) {
            if (buttons[i].getText().equals("")) {
                return false;
            }
        }
        return true;
    }

    private void resetGame() {
        for (int i = 0; i < 9; i++) {
            buttons[i].setText("");
            buttons[i].setEnabled(true);
        }
        player1Turn = true;
    }

    public static void main(String[] args) {
        new TicTacToe();
    }
}
