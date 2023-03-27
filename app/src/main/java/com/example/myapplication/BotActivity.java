package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.Arrays;

public class BotActivity extends AppCompatActivity implements View.OnClickListener {

    int idD = 100;
    int id_further = 0;
    int id;// Переменная, содержащая id, выбранной шашки.
    int turn = 1; // Очередность хода.
    int is_fight; // Переменная, указывающая пуст или полон массив.

    public class Board {
        /*
        int[][] board = {{1, 0, 1, 0, 1, 0, 1, 0},
                         {0, 1, 0, 1, 0, 1, 0, 1},
                         {1, 0, 1, 0, 1, 0, 1, 0},
                         {0, 0, 0, 0, 0, 0, 0, 0},
                         {0, 0, 0, 0, 0, 0, 0, 0},
                         {0, 2, 0, 2, 0, 2, 0, 2},
                         {2, 0, 2, 0, 2, 0, 2, 0},
                         {0, 2, 0, 2, 0, 2, 0, 2}};
    */
        int[][] board = {{1, 0, 1, 0, 1, 0, 1, 0},
                {0, 1, 0, 1, 0, 1, 0, 1},
                {1, 0, 1, 0, 2, 0, 2, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 2, 0, 0, 0},
                {0, 2, 0, 0, 0, 0, 0, 0},
                {2, 0, 2, 0, 2, 0, 2, 0},
                {0, 2, 0, 2, 0, 2, 0, 2}}; // Виртуальное поле.

        ImageButton[][] boardIm = new ImageButton[8][8];

        public void setMoves(int x1, int y1, Draught[] dr) {
            Log.d("f", "setMoves board");
            clear3();
            int d;
            d = turn == 1 ? 1 : -1;
            for (int i = 0; i < 12; i++) {
                if (dr[i].x == x1 && dr[i].y == y1) {
                    for (int i1 = 0; i1 < 2; i1++) {
                        if (!dr[i].is_king) {
                            if (dr[i].getPossibleMoves()[i1] != -1) {
                                id = dr[i].id_draught;
                                board[x1 + d][dr[i].getPossibleMoves()[i1]] = 3;
                            }
                        } else {
                            // dr[i].printPossibleMovesKing();
                            for (int i2 = 0; i2 < dr[i].getPossibleMovesKing().length; i2++) {
                                if (dr[i].getPossibleMovesKing()[i2][0] != -1) {
                                    id = dr[i].id_draught;
                                    board[dr[i].getPossibleMovesKing()[i2][0]][dr[i].getPossibleMovesKing()[i2][1]] = 3;
                                }
                            }
                        }
                    }
                    break;
                }
            }
            updateboard();
        }

        public void setFights(int x1, int y1, Draught[] dr) {
            clear3();
            for (int i = 0; i < 12; i++) {
                if (dr[i].x == x1 && dr[i].y == y1 && dr[i].canFight() && (dr[i].id_draught == id_further || 0 == id_further)) {
                    Log.d("f", "setFights board");
                    id = dr[i].id_draught;
                    if (!dr[i].is_king) {
                        for (int j = 0; j < dr[i].getPossibleFights().length; j++) {
                            String s = Integer.toString(dr[i].getPossibleFights()[j][0]);
                            Log.d("f", s);
                            s = Integer.toString(dr[i].getPossibleFights()[j][1]);
                            Log.d("f", s);
                            b.board[dr[i].getPossibleFights()[j][0]][dr[i].getPossibleFights()[j][1]] = 3;
                        }
                    } else {
                        for (int j = 0; j < dr[i].getPossibleFights().length; j++) {
                            String s = Integer.toString(dr[i].getPossibleFights()[j][0]) + Integer.toString(dr[i].getPossibleFights()[j][1]);
                            Log.d("f", s);
                            s = Integer.toString(dr[i].x);
                            Log.d("f", s);
                            s = Integer.toString(dr[i].y);
                            Log.d("f", s);
                            if (dr[i].getPossibleFights()[j][0] > dr[i].x && dr[i].getPossibleFights()[j][1] > dr[i].y) {
                                for (int i1 = 1; i1 < 8; i1++) {
                                    s = Integer.toString(i1);
                                    Log.d("f", s);
                                    if ((dr[i].getPossibleFights()[j][0] + i1 <= 7 && dr[i].getPossibleFights()[j][1] + i1 <= 7) && (b.board[dr[i].getPossibleFights()[j][0] + i1][dr[i].getPossibleFights()[j][1] + i1] == 0 || b.board[dr[i].getPossibleFights()[j][0] + i1][dr[i].getPossibleFights()[j][1] + i1] == 3)) {
                                        b.board[dr[i].getPossibleFights()[j][0] + i1][dr[i].getPossibleFights()[j][1] + i1] = 3;
                                    } else {
                                        break;
                                    }
                                }
                            }
                            if (dr[i].getPossibleFights()[j][0] < dr[i].x && dr[i].getPossibleFights()[j][1] > dr[i].y) {
                                for (int i1 = 1; i1 < 8; i1++) {
                                    if ((dr[i].getPossibleFights()[j][0] - i1 >= 0 && dr[i].getPossibleFights()[j][1] + i1 <= 7) && (b.board[dr[i].getPossibleFights()[j][0] - i1][dr[i].getPossibleFights()[j][1] + i1] == 0 || b.board[dr[i].getPossibleFights()[j][0] - i1][dr[i].getPossibleFights()[j][1] + i1] == 3)) {
                                        b.board[dr[i].getPossibleFights()[j][0] - i1][dr[i].getPossibleFights()[j][1] + i1] = 3;
                                    } else {
                                        break;
                                    }
                                }
                            }
                            if (dr[i].getPossibleFights()[j][0] < dr[i].x && dr[i].getPossibleFights()[j][1] < dr[i].y) {
                                for (int i1 = 1; i1 < 8; i1++) {
                                    if ((dr[i].getPossibleFights()[j][0] - i1 >= 0 && dr[i].getPossibleFights()[j][1] - i1 >= 0) && (b.board[dr[i].getPossibleFights()[j][0] - i1][dr[i].getPossibleFights()[j][1] - i1] == 0 || b.board[dr[i].getPossibleFights()[j][0] - i1][dr[i].getPossibleFights()[j][1] - i1] == 3)) {
                                        b.board[dr[i].getPossibleFights()[j][0] - i1][dr[i].getPossibleFights()[j][1] - i1] = 3;
                                    } else {
                                        break;
                                    }
                                }
                            }
                            if (dr[i].getPossibleFights()[j][0] > dr[i].x && dr[i].getPossibleFights()[j][1] < dr[i].y) {
                                for (int i1 = 1; i1 < 8; i1++) {
                                    if ((dr[i].getPossibleFights()[j][0] + i1 <= 7 && dr[i].getPossibleFights()[j][1] - i1 >= 0) && (b.board[dr[i].getPossibleFights()[j][0] + i1][dr[i].getPossibleFights()[j][1] - i1] == 0 || b.board[dr[i].getPossibleFights()[j][0] + i1][dr[i].getPossibleFights()[j][1] - i1] == 3)) {
                                        b.board[dr[i].getPossibleFights()[j][0] + i1][dr[i].getPossibleFights()[j][1] - i1] = 3;
                                    } else {
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    break;
                }
            }
            updateboard();
        }

        public void move(int x1, int y1, Draught[] dr) {
            for (int i = 0; i < 12; i++) {
                if (dr[i].id_draught == id) {
                    Log.d("f", "move board");
                    dr[i].move(x1, y1);
                    break;
                }
            }
            clear3();
        }

        public void fight(int x1, int y1, Draught[] dr) {
            for (int i = 0; i < 12; i++) {
                if (dr[i].id_draught == id) {
                    Log.d("f", "fight board");
                    if (turn == 1) {
                        if (!dr[i].is_king) {
                            dr[i].Fight(x1, y1, black);
                        } else {
                            dr[i].kingFight(x1, y1, black);
                        }
                    } else {
                        if (!dr[i].is_king) {
                            dr[i].Fight(x1, y1, white);
                        } else {
                            dr[i].kingFight(x1, y1, white);
                        }
                    }
                    break;
                }
            }
            clear3();
        }

        public boolean anyFight (Draught[] dr) {
            boolean any_fight = false;
            for (int i = 0; i < 12; i++) {
                if (dr[i].canFight()) {
                    any_fight = true;
                    break;
                }
            }
            return any_fight;
        }

        // Обновление доски.
        public void updateboard() {
            /*
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    System.out.print(" " + b.board[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println();
            System.out.println();
             */
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (b.board[i][j] == 0) {
                        b.boardIm[i][j].setImageResource(R.drawable.draught_empty);
                    } else if (b.board[i][j] == 1) {
                        b.boardIm[i][j].setImageResource(R.drawable.draught_white);
                    } else if (b.board[i][j] == 2) {
                        b.boardIm[i][j].setImageResource(R.drawable.draught_black);
                    } else if (b.board[i][j] == 3) {
                        b.boardIm[i][j].setImageResource(R.drawable.dot_choice);
                    } else if (b.board[i][j] == 11) {
                        b.boardIm[i][j].setImageResource(R.drawable.draught_white_king);
                    } else if (b.board[i][j] == 22) {
                        b.boardIm[i][j].setImageResource(R.drawable.draught_black_king);
                    }
                }
            }
        }
    }

    Board b = new Board();

    public class Draught {
        int id_draught;
        boolean is_king;
        int x;
        int y;
        // id шашек, которые под боем.
        boolean furtherFight;

        public Draught() {
            this.id_draught = idD + 1;
            idD += 1;
        }

        public int[] getPossibleMoves() {
            int[] moves = new int[2];
            int el;
            int d;
            d = turn == 1 ? 1 : -1;
            if (y + 1 <= 7 && b.board[x + d][y + 1] == 0) {
                el = y + 1;
            } else {
                el = -1;
            }
            moves[0] = el;
            if (y - 1 >= 0 && b.board[x + d][y - 1] == 0) {
                el = y - 1;
            } else {
                el = -1;
            }
            moves[1] = el;
            return moves;
        }

        public int[][] getPossibleMovesKing () {
            /*
            int[] el = new int[2];
            for (int i = 0; i < 13; i++) {
                for (int j = 0; j < 2; j++) {
                    moves[i][j] = -1;
                }
            }*/
            int[] el = new int[2];
            int[][] moves = new int[0][2];
            for (int i = 1; i < 8; i++) {
                if ((x + i > 7 || y + i > 7) || b.board[x + i][y + i] != 0) {
                    break;
                }
                if ((x + i <= 7 || y + i <= 7) || b.board[x + i][y + i] == 0) {
                    el[0] = x + i;
                    el[1] = y + i;
                    moves = addElement(moves, el);
                }
            }

            for (int i = 1; i < 8; i++) {
                if ((x - i < 0 || y + i > 7) || b.board[x - i][y + i] != 0) {
                    break;
                }
                el[0] = x - i;
                el[1] = y + i;
                moves = addElement(moves, el);
            }

            for (int i = 1; i < 8; i++) {
                if ((x - i < 0 || y - i < 0) || b.board[x - i][y - i] != 0) {
                    break;
                }
                el[0] = x - i;
                el[1] = y - i;
                moves = addElement(moves, el);
            }

            for (int i = 1; i < 8; i++) {
                if ((x + i > 7 || y - i < 0) || b.board[x + i][y - i] != 0) {
                    break;
                }
                el[0] = x + i;
                el[1] = y - i;
                moves = addElement(moves, el);
            }

            return moves;
            /*
            int[][] moves = new int[13][2];
            for (int i = 0; i < 13; i++ ) {
                moves[i][0] = -1;
                moves[i][1] = -1;
            }

            for (int i = 1; i < 8; i++) {
                if ((x + i > 7 || y + i > 7) || b.board[x + i][y + i] != 0) {
                    break;
                }
                for (int j = 1; j < 8; j++) {
                    if (moves[j][0] == -1) {
                        moves[j][0] = x + i;
                        moves[j][1] = y + i;
                        break;
                    }
                }
            }

            for (int i = 1; i < 8; i++) {
                if ((x - i < 0 || y + i > 7) || b.board[x - i][y + i] != 0) {
                    break;
                }
                for (int j = 1; j < 8; j++) {
                    if (moves[j][0] == -1) {
                        moves[j][0] = x - i;
                        moves[j][1] = y + i;
                        break;
                    }
                }
            }

            for (int i = 1; i < 8; i++) {
                if ((x - i < 0 || y - i < 0) || b.board[x - i][y - i] != 0) {
                    break;
                }
                for (int j = 1; j < 8; j++) {
                    if (moves[j][0] == -1) {
                        moves[j][0] = x - i;
                        moves[j][1] = y - i;
                        break;
                    }
                }
            }

            for (int i = 1; i < 8; i++) {
                if ((x + i > 7 || y - i < 0) || b.board[x + i][y - i] != 0) {
                    break;
                }
                for (int j = 1; j < 8; j++) {
                    if (moves[j][0] == -1) {
                        moves[j][0] = x + i;
                        moves[j][1] = y - i;
                        break;
                    }
                }
            }
            return moves;
            */
        }

        public void move(int x1, int y1) {
            Log.d("f", "move draught");
            int dr;
            if (turn == 1) {
                dr = is_king ? 11 : 1;
            } else {
                dr = is_king ? 22 : 2;
            }
            int king;
            king = turn == 1 ? 11 : 22; // Указание кодировки дамки, в которую может превратиться шашка, в зависимости от цвета.
            int pos_king;
            pos_king = turn == 1 ? 7 : 0; // Указание гоиризонатли, на которой шашка станет дамкой.
            b.board[x][y] = 0;
            x = x1;
            y = y1;
            if (x1 == pos_king) {
                b.board[x1][y1] = king;
                is_king = true;
            } else {
                b.board[x1][y1] = dr;
            }
            // Смена хода.
            turn = turn == 1 ? 2 : 1;
            id = 0;
            b.updateboard();
        }

        public boolean canFight () {
            int k;
            k = turn == 1 ? 22 : 11;
            int opponent;
            opponent = turn == 1 ? 2 : 1;
            boolean can_fight = false;
            if (!is_king) {
                can_fight = ((x + 2 <= 7 && y + 2 <= 7) && (b.board[x + 1][y + 1] == opponent || b.board[x + 1][y + 1] == k) && (b.board[x + 2][y + 2] == 0 || b.board[x + 2][y + 2] == 3));
                if ((x - 2 >= 0 && y + 2 <= 7) && (b.board[x - 1][y + 1] == opponent || b.board[x - 1][y + 1] == k) && (b.board[x - 2][y + 2] == 0 || b.board[x - 2][y + 2] == 3)) {
                    can_fight = true;
                }
                if ((x - 2 >= 0 && y - 2 >= 0) && (b.board[x - 1][y - 1] == opponent || b.board[x - 1][y - 1] == k) && (b.board[x - 2][y - 2] == 0 || b.board[x - 2][y - 2] == 3)) {
                    can_fight = true;
                }
                if ((x + 2 <= 7 && y - 2 >= 0) && (b.board[x + 1][y - 1] == opponent || b.board[x + 1][y - 1] == k) && (b.board[x + 2][y - 2] == 0 || b.board[x + 2][y - 2] == 3)) {
                    can_fight = true;
                }
            } else {
                for (int i = 1; i < 8; i++) {
                    if ((x + i + 1 <= 7 && y + i + 1 <= 7) && (b.board[x + i][y + i] == opponent || b.board[x + i][y + i] == k) && (b.board[x + i + 1][y + i + 1] == 0 || b.board[x + i + 1][y + i + 1] == 3)) {
                        can_fight = true;
                    }
                    if ((x - i - 1 >= 0 && y + i + 1 <= 7) && (b.board[x - i][y + i] == opponent || b.board[x - i][y + i] == k) && (b.board[x - i - 1][y + i + 1] == 0 || b.board[x - i - 1][y + i + 1] == 3)) {
                        can_fight = true;
                    }
                    if ((x - i - 1 >= 0 && y - i - 1 >= 0) && (b.board[x - i][y - i] == opponent || b.board[x - i][y - i] == k) && (b.board[x - i - 1][y - i - 1] == 0 || b.board[x - i - 1][y - i - 1] == 3)) {
                        can_fight = true;
                    }
                    if ((x + i + 1 <= 7 && y - i - 1 >= 0) && (b.board[x + i][y - i] == opponent || b.board[x + i][y - i] == k) && (b.board[x + i + 1][y - i - 1] == 0 || b.board[x + i + 1][y - i - 1] == 3)) {
                        can_fight = true;
                    }
                }
            }
            return can_fight;
        }

        public int[][] getPossibleFights() {
            Log.d("f", "getPossibleFights draught");
            int k;
            k = turn == 1 ? 22 : 11;
            int opponent;
            opponent = turn == 1 ? 2 : 1;
            int[][] fights = new int[0][2];
            int[] el = new int[2];
            if (!is_king) {
                if ((x + 2 <= 7 && y + 2 <= 7) && (b.board[x + 1][y + 1] == opponent || b.board[x + 1][y + 1] == k) && b.board[x + 2][y + 2] == 0) {
                    el[0] = x + 2;
                    el[1] = y + 2;
                    fights = addElement(fights, el);
                }
                if ((x - 2 >= 0 && y + 2 <= 7) && (b.board[x - 1][y + 1] == opponent || b.board[x - 1][y + 1] == k) && b.board[x - 2][y + 2] == 0) {
                    el[0] = x - 2;
                    el[1] = y + 2;
                    fights = addElement(fights, el);
                }
                if ((x - 2 >= 0 && y - 2 >= 0) && (b.board[x - 1][y - 1] == opponent || b.board[x - 1][y - 1] == k) && b.board[x - 2][y - 2] == 0) {
                    el[0] = x - 2;
                    el[1] = y - 2;
                    fights = addElement(fights, el);
                }
                if ((x + 2 <= 7 && y - 2 >= 0) && (b.board[x + 1][y - 1] == opponent || b.board[x + 1][y - 1] == k) && b.board[x + 2][y - 2] == 0) {
                    el[0] = x + 2;
                    el[1] = y - 2;
                    fights = addElement(fights, el);
                }
            } else {
                for (int i = 1; i < 8; i++) {
                    if ((x + i + 1 <= 7 && y + i + 1 <= 7) && (b.board[x + i][y + i] == opponent || b.board[x + i][y + i] == k) && (b.board[x + i + 1][y + i + 1] == 0 || b.board[x + i + 1][y + i + 1] == 3)) {
                        el[0] = x + i;
                        el[1] = y + i;
                        fights = addElement(fights, el);
                        break;
                    }
                }
                for (int i = 1; i < 8; i++) {
                    if ((x - i - 1 >= 0 && y + i + 1 <= 7) && (b.board[x - i][y + i] == opponent || b.board[x - i][y + i] == k) && (b.board[x - i - 1][y + i + 1] == 0 || b.board[x - i - 1][y + i + 1] == 3)) {
                        el[0] = x - i;
                        el[1] = y + i;
                        fights = addElement(fights, el);
                        break;
                    }
                }
                for (int i = 1; i < 8; i++) {
                    if ((x - i - 1 >= 0 && y - i - 1 >= 0) && (b.board[x - i][y - i] == opponent || b.board[x - i][y - i] == k) && (b.board[x - i - 1][y - i - 1] == 0 || b.board[x - i - 1][y - i - 1] == 3)) {
                        el[0] = x - i;
                        el[1] = y - i;
                        fights = addElement(fights, el);
                        break;
                    }
                }
                for (int i = 1; i < 8; i++) {
                    if ((x + i + 1 <= 7 && y - i - 1 >= 0) && (b.board[x + i][y - i] == opponent || b.board[x + i][y - i] == k) && (b.board[x + i + 1][y - i - 1] == 0 || b.board[x + i + 1][y - i - 1] == 3)) {
                        el[0] = x + i;
                        el[1] = y - i;
                        fights = addElement(fights, el);
                        break;
                    }
                }
            }
            return fights;
        }

        public void Fight(int x1, int y1, Draught[] opponent) {
            int dr;
            if (turn == 1) {
                dr = is_king ? 11 : 1;
            } else {
                dr = is_king ? 22 : 2;
            }
            int king;
            king = turn == 1 ? 11 : 22;
            int pos_king;
            pos_king = turn == 1 ? 7 : 0; // Указание гоиризонатли, на которой шашка станет дамкой.
            b.board[x][y] = 0;
            if (x1 > x && y1 > y) {
                b.board[x + 1][y + 1] = 0;
                for (int i = 0; i < 12; i++) {
                    if (opponent[i].x == x + 1 && opponent[i].y == y + 1) {
                        opponent[i].x = -1;
                        opponent[i].y = -1;
                    }
                }
            }
            if (x1 < x && y1 > y) {
                b.board[x - 1][y + 1] = 0;
                for (int i = 0; i < 12; i++) {
                    if (opponent[i].x == x - 1 && opponent[i].y == y + 1) {
                        opponent[i].x = -1;
                        opponent[i].y = -1;
                    }
                }
            }
            if (x1 < x && y1 < y) {
                b.board[x - 1][y - 1] = 0;
                for (int i = 0; i < 12; i++) {
                    if (opponent[i].x == x - 1 && opponent[i].y == y - 1) {
                        opponent[i].x = -1;
                        opponent[i].y = -1;
                    }
                }
            }
            if (x1 > x && y1 < y) {
                b.board[x + 1][y - 1] = 0;
                for (int i = 0; i < 12; i++) {
                    if (opponent[i].x == x + 1 && opponent[i].y == y - 1) {
                        opponent[i].x = -1;
                        opponent[i].y = -1;
                    }
                }
            }
            x = x1;
            y = y1;
            if (x1 == pos_king) {
                b.board[x1][y1] = king;
                is_king = true;
            } else {
                b.board[x1][y1] = dr;
            }
            if (canFight()) {
                id_further = id;
            } else {
                id_further = 0;
                turn = turn == 1 ? 2 : 1;
                id = 0;
                b.updateboard();
            }
        }

        public void kingFight(int x1, int y1, Draught[] opponent) {
            int dr = turn == 1 ? 11 : 22;
            if (x1 > x && y1 > y) {
                for (int j = 0; j < 12; j++) {
                    if(opponent[j].x > x && opponent[j].x < x1 && opponent[j].y > y && opponent[j].y < y1) {
                        b.board[opponent[j].x][opponent[j].y] = 0;
                        opponent[j].x = -1;
                        opponent[j].y = -1;
                    }
                }
            }
            if (x1 < x && y1 > y) {
                for (int j = 0; j < 12; j++) {
                    if(opponent[j].x < x && opponent[j].x > x1 && opponent[j].y > y && opponent[j].y < y1) {
                        b.board[opponent[j].x][opponent[j].y] = 0;
                        opponent[j].x = -1;
                        opponent[j].y = -1;
                    }
                }
            }
            if (x1 < x && y1 < y) {
                for (int j = 0; j < 12; j++) {
                    if(opponent[j].x < x && opponent[j].x > x1 && opponent[j].y < y && opponent[j].y > y1) {
                        b.board[opponent[j].x][opponent[j].y] = 0;
                        opponent[j].x = -1;
                        opponent[j].y = -1;
                    }
                }
            }
            if (x1 > x && y1 < y) {
                for (int j = 0; j < 12; j++) {
                    if(opponent[j].x > x && opponent[j].x < x1 && opponent[j].y < y && opponent[j].y > y1) {
                        b.board[opponent[j].x][opponent[j].y] = 0;
                        opponent[j].x = -1;
                        opponent[j].y = -1;
                    }
                }
            }
            b.board[x][y] = 0;
            b.board[x1][y1] = dr;
            if (canFight()) {
                id_further = id_draught;
            } else {
                id_further = 0;
                turn = turn == 1 ? 2 : 1;
                id = 0;
                b.updateboard();
            }
        }
    }

    Draught[] white = new Draught[12];
    Draught[] black = new Draught[12];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bot);

        b.boardIm[0][0] = findViewById(R.id.check11);
        b.boardIm[0][1] = findViewById(R.id.check12);
        b.boardIm[0][2] = findViewById(R.id.check13);
        b.boardIm[0][3] = findViewById(R.id.check14);
        b.boardIm[0][4] = findViewById(R.id.check15);
        b.boardIm[0][5] = findViewById(R.id.check16);
        b.boardIm[0][6] = findViewById(R.id.check17);
        b.boardIm[0][7] = findViewById(R.id.check18);
        b.boardIm[1][0] = findViewById(R.id.check21);
        b.boardIm[1][1] = findViewById(R.id.check22);
        b.boardIm[1][2] = findViewById(R.id.check23);
        b.boardIm[1][3] = findViewById(R.id.check24);
        b.boardIm[1][4] = findViewById(R.id.check25);
        b.boardIm[1][5] = findViewById(R.id.check26);
        b.boardIm[1][6] = findViewById(R.id.check27);
        b.boardIm[1][7] = findViewById(R.id.check28);
        b.boardIm[2][0] = findViewById(R.id.check31);
        b.boardIm[2][1] = findViewById(R.id.check32);
        b.boardIm[2][2] = findViewById(R.id.check33);
        b.boardIm[2][3] = findViewById(R.id.check34);
        b.boardIm[2][4] = findViewById(R.id.check35);
        b.boardIm[2][5] = findViewById(R.id.check36);
        b.boardIm[2][6] = findViewById(R.id.check37);
        b.boardIm[2][7] = findViewById(R.id.check38);
        b.boardIm[3][0] = findViewById(R.id.check41);
        b.boardIm[3][1] = findViewById(R.id.check42);
        b.boardIm[3][2] = findViewById(R.id.check43);
        b.boardIm[3][3] = findViewById(R.id.check44);
        b.boardIm[3][4] = findViewById(R.id.check45);
        b.boardIm[3][5] = findViewById(R.id.check46);
        b.boardIm[3][6] = findViewById(R.id.check47);
        b.boardIm[3][7] = findViewById(R.id.check48);
        b.boardIm[4][0] = findViewById(R.id.check51);
        b.boardIm[4][1] = findViewById(R.id.check52);
        b.boardIm[4][2] = findViewById(R.id.check53);
        b.boardIm[4][3] = findViewById(R.id.check54);
        b.boardIm[4][4] = findViewById(R.id.check55);
        b.boardIm[4][5] = findViewById(R.id.check56);
        b.boardIm[4][6] = findViewById(R.id.check57);
        b.boardIm[4][7] = findViewById(R.id.check58);
        b.boardIm[5][0] = findViewById(R.id.check61);
        b.boardIm[5][1] = findViewById(R.id.check62);
        b.boardIm[5][2] = findViewById(R.id.check63);
        b.boardIm[5][3] = findViewById(R.id.check64);
        b.boardIm[5][4] = findViewById(R.id.check65);
        b.boardIm[5][5] = findViewById(R.id.check66);
        b.boardIm[5][6] = findViewById(R.id.check67);
        b.boardIm[5][7] = findViewById(R.id.check68);
        b.boardIm[6][0] = findViewById(R.id.check71);
        b.boardIm[6][1] = findViewById(R.id.check72);
        b.boardIm[6][2] = findViewById(R.id.check73);
        b.boardIm[6][3] = findViewById(R.id.check74);
        b.boardIm[6][4] = findViewById(R.id.check75);
        b.boardIm[6][5] = findViewById(R.id.check76);
        b.boardIm[6][6] = findViewById(R.id.check77);
        b.boardIm[6][7] = findViewById(R.id.check78);
        b.boardIm[7][0] = findViewById(R.id.check81);
        b.boardIm[7][1] = findViewById(R.id.check82);
        b.boardIm[7][2] = findViewById(R.id.check83);
        b.boardIm[7][3] = findViewById(R.id.check84);
        b.boardIm[7][4] = findViewById(R.id.check85);
        b.boardIm[7][5] = findViewById(R.id.check86);
        b.boardIm[7][6] = findViewById(R.id.check87);
        b.boardIm[7][7] = findViewById(R.id.check88);

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                b.boardIm[i][j].setOnClickListener(this);
            }
        }
        b.updateboard();

        for (int i = 0; i < 12; i++) {
            white[i] = new Draught();
            black[i] = new Draught();
        }

        int w = 0;
        int bl = 0;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(b.board[i][j] == 1) {
                    white[w].x = i;
                    white[w].y = j;
                    w += 1;
                } else if (b.board[i][j] == 2) {
                    black[bl].x = i;
                    black[bl].y = j;
                    bl += 1;
                } else if (b.board[i][j] == 11) {
                    white[w].x = i;
                    white[w].y = j;
                    white[w].is_king = true;
                    w += 1;
                } else if (b.board[i][j] == 22) {
                    black[bl].x = i;
                    black[bl].y = j;
                    black[bl].is_king = true;
                    bl += 1;
                }
            }
        }
    }



    @Override
    public void onClick(View v) {
        int k;
        k = turn == 1 ? 11 : 22;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (b.boardIm[i][j].getId() == v.getId() && (b.board[i][j] == turn || b.board[i][j] == k)) {
                    if (turn == 1) {
                        if (b.anyFight(white)) {
                            Log.d("f", "setFights onClick white");
                            b.setFights(i, j, white);
                        } else {
                            Log.d("f", "setMoves onClick white");
                            b.setMoves(i, j, white);
                        }
                    } else {
                        if (b.anyFight(black)) {
                            Log.d("f", "setFights onClick black");
                            b.setFights(i, j, black);
                        } else {
                            Log.d("f", "setMoves onClick black");
                            b.setMoves(i, j, black);
                        }
                    }
                } else if (b.boardIm[i][j].getId() == v.getId() && b.board[i][j] == 3) {
                    if (turn == 1) {
                        if (b.anyFight(white)) {
                            Log.d("f", "fight onClick white");
                            b.fight(i, j, white);
                        } else {
                            Log.d("f", "move onClick white");
                            b.move(i, j, white);
                        }
                    } else {
                        if (b.anyFight(black)) {
                            Log.d("f", "fight onClick black");
                            b.fight(i, j, black);
                        } else {
                            Log.d("f", "move onClick black");
                            b.move(i, j, black);
                        }
                    }
                }
            }
        }
        result();
    }

    public void result () {
        int w = 0;
        int bl = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (b.board[i][j] == 1 || b.board[i][j] == 11) {
                    w += 1;
                } else if (b.board[i][j] == 2 || b.board[i][j] == 22) {
                    bl += 1;
                }
            }
        }
        if (w == 0) {
            Toast toast = Toast.makeText(getApplicationContext(), "Чёрные выиграли!!!", Toast.LENGTH_LONG);
            toast.show();
        } else if (bl == 0) {
            Toast toast = Toast.makeText(getApplicationContext(), "Белые выиграли!!!", Toast.LENGTH_LONG);
            toast.show();
        }
    }

    // Очистка доски от кнопок с выбором.
    public void clear3 () {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (b.board[i][j] == 3) {
                    b.board[i][j] = 0;
                }
            }
        }
        b.updateboard();
    }

    // Добавление в массив элемента.
    private static int[][] addElement (int[][] myArray, int[] element){
        int[][] array = Arrays.copyOf(myArray, myArray.length + 1);
        array[myArray.length] = element;
        return array;
    }

    public static int[][] join(int[][] a, int[][] b) {
        int[][] result = Arrays.copyOf(a, a.length + b.length);
        System.arraycopy(b, 0, result, a.length, b.length);
        return result;
    }


}