package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class LevelActivity extends AppCompatActivity implements View.OnClickListener {
    Bundle arguments;
    int level;
    String s;
    int idD = 100;
    int step = 0;
    int id_further = 0;
    int id;// Переменная, содержащая id, выбранной шашки.
    int turn = 1; // Очередность хода.

    public class Board {

        int[][] board = {{0, 0, 0, 0, 0, 0, 0, 0},
                         {0, 0, 0, 0, 0, 0, 0, 0},
                         {0, 0, 0, 0, 0, 0, 0, 0},
                         {0, 0, 0, 0, 0, 0, 0, 0},
                         {0, 0, 0, 0, 0, 0, 0, 0},
                         {0, 0, 0, 0, 0, 0, 0, 0},
                         {0, 0, 0, 0, 0, 0, 0, 0},
                         {0, 0, 0, 0, 0, 0, 0, 0}};

        /*
                int[][] board = {{0, 0, 0, 0, 0, 0, 0, 0},
                                 {0, 0, 0, 0, 0, 0, 0, 0},
                                 {0, 0, 0, 0, 0, 0, 0, 0},
                                 {0, 0, 0, 1, 0, 0, 0, 0},
                                 {0, 0, 0, 0, 22, 0, 0, 0},
                                 {0, 0, 0, 0, 0, 1, 0, 0},
                                 {0, 0, 0, 0, 0, 0, 0, 0},
                                 {0, 0, 0, 0, 0, 0, 0, 0}}; // Виртуальное поле.
        */
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
                            id = dr[i].id_draught;
                            for (int i2 = 1; i2 < 8; i2++) {
                                if ((dr[i].x + i2 > 7 || dr[i].y + i2 > 7) || b.board[dr[i].x + i2][dr[i].y + i2] != 0) {
                                    break;
                                }
                                b.board[dr[i].x + i2][dr[i].y + i2] = 3;
                            }

                            for (int i2 = 1; i2 < 8; i2++) {
                                if ((dr[i].x - i2 < 0 || dr[i].y + i2 > 7) || b.board[dr[i].x - i2][dr[i].y + i2] != 0) {
                                    break;
                                }
                                b.board[dr[i].x - i2][dr[i].y + i2] = 3;

                            }

                            for (int i2 = 1; i2 < 8; i2++) {
                                if ((dr[i].x - i2 < 0 || dr[i].y - i2 < 0) || b.board[dr[i].x - i2][dr[i].y - i2] != 0) {
                                    break;
                                }
                                b.board[dr[i].x - i2][dr[i].y - i2] = 3;
                            }

                            for (int i2 = 1; i2 < 8; i2++) {
                                if ((dr[i].x + i2 > 7 || dr[i].y - i2 < 0) || b.board[dr[i].x + i2][dr[i].y - i2] != 0) {
                                    break;
                                }
                                b.board[dr[i].x + i2][dr[i].y - i2] = 3;
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
                        int k;
                        k = turn == 1 ? 22 : 11;
                        int opponent;
                        opponent = turn == 1 ? 2 : 1;
                        if ((dr[i].x + 2 <= 7 && dr[i].y + 2 <= 7) && (b.board[dr[i].x + 1][dr[i].y + 1] == opponent || b.board[dr[i].x + 1][dr[i].y + 1] == k) && b.board[dr[i].x + 2][dr[i].y + 2] == 0) {
                            b.board[dr[i].x + 2][dr[i].y + 2] = 3;
                        }
                        if ((dr[i].x - 2 >= 0 && dr[i].y + 2 <= 7) && (b.board[dr[i].x - 1][dr[i].y + 1] == opponent || b.board[dr[i].x - 1][dr[i].y + 1] == k) && b.board[dr[i].x - 2][dr[i].y + 2] == 0) {
                            b.board[dr[i].x - 2][dr[i].y + 2] = 3;
                        }
                        if ((dr[i].x - 2 >= 0 && dr[i].y - 2 >= 0) && (b.board[dr[i].x - 1][dr[i].y - 1] == opponent || b.board[dr[i].x - 1][dr[i].y - 1] == k) && b.board[dr[i].x - 2][dr[i].y - 2] == 0) {
                            b.board[dr[i].x - 2][dr[i].y - 2] = 3;
                        }
                        if ((dr[i].x + 2 <= 7 && dr[i].y - 2 >= 0) && (b.board[dr[i].x + 1][dr[i].y - 1] == opponent || b.board[dr[i].x + 1][dr[i].y - 1] == k) && b.board[dr[i].x + 2][dr[i].y - 2] == 0) {
                            b.board[dr[i].x + 2][dr[i].y - 2] = 3;
                        }
                    } else {
                        id = dr[i].id_draught;
                        int k;
                        k = turn == 1 ? 22 : 11;
                        int opponent;
                        opponent = turn == 1 ? 2 : 1;
                        int[][] fights = dr[i].getPossibleFights();
                        if (fights[0][0] != -1) {
                            for (int i2 = 1; i2 < 8; i2++) {
                                if ((fights[0][0] + i2 > 7 || fights[0][1] + i2 > 7) || b.board[fights[0][0] + i2][fights[0][1] + i2] != 0) {
                                    break;
                                }
                                b.board[fights[0][0] + i2][fights[0][1] + i2] = 3;
                            }
                        }
                        if (fights[1][0] != -1) {
                            for (int i2 = 1; i2 < 8; i2++) {
                                if ((fights[1][0] - i2 < 0 || fights[1][1] + i2 > 7) || (b.board[fights[1][0] - i2][fights[1][1] + i2] != 0)) {
                                    break;
                                }
                                b.board[fights[1][0] - i2][fights[1][1] + i2] = 3;
                            }
                        }
                        if (fights[2][0] != -1) {
                            for (int i2 = 1; i2 < 8; i2++) {
                                if ((fights[2][0] - i2 < 0 || fights[2][1] - i2 < 0) || b.board[fights[2][0] - i2][fights[2][1] - i2] != 0) {
                                    break;
                                }
                                b.board[fights[2][0] - i2][fights[2][1] - i2] = 3;
                            }
                        }
                        if (fights[3][0] != -1) {
                            for (int i2 = 1; i2 < 8; i2++) {
                                if ((fights[3][0] + i2 > 7 || fights[3][1] - i2 < 0) || b.board[fights[3][0] + i2][fights[3][1] - i2] != 0) {
                                    break;
                                }
                                b.board[fights[3][0] + i2][fights[3][1] - i2] = 3;
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
                    if (dr[i].canFight() && b.board[dr[i].x][dr[i].y] != 0) {
                        s = Integer.toString(dr[i].x) + " " + Integer.toString(dr[i].y);
                        Log.d("f", s);
                        Log.d("f", Integer.toString(b.board[dr[i].x][dr[i].y]));
                        any_fight = true;
                        break;
                    }
                }
            return any_fight;
        }

        public void changeBoard() {
            int w = 0;
            int bl = 0;

            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (b.board[i][j] == 1) {
                        white[w].x = i;
                        white[w].y = j;
                        white[w].is_king = false;
                        w += 1;
                    } else if (b.board[i][j] == 2) {
                        black[bl].x = i;
                        black[bl].y = j;
                        black[bl].is_king = false;
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

        /*
        public canMove () {
        }*/

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
            pos_king = turn == 1 ? 7 : 0; // Указание гоиризонатли, на которой шашка станет дамкой.*/
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
            if (level == 2) {
                step = step + 1;
            }
            if (level == 1 || level == 2) {
                turn = 1;
            } else {
                turn = turn == 1 ? 2 : 1;
            }
            id = 0;
            b.updateboard();
        }

        public boolean canFight () {
            int k = 0;
            int opponent = 10;
            int teammate = 10;
            int kt = 10;
            boolean can_fight = false;
            if (!is_king) {
                if (x != -100 && y != -100) {
                    if (b.board[x][y] == 1 || b.board[x][y] == 11) {
                        opponent = 2;
                        k = 22;
                    } else if (b.board[x][y] == 2 || b.board[x][y] == 22) {
                        opponent = 1;
                        k = 11;
                    }
                    if ((x + 2 <= 7 && y + 2 <= 7) && (b.board[x + 1][y + 1] == opponent || b.board[x + 1][y + 1] == k) && b.board[x + 1][y + 1] != 10 && (b.board[x + 2][y + 2] == 0 || b.board[x + 2][y + 2] == 3)) {
                        s = Arrays.toString(b.board[3]);
                        Log.d("f", s);
                        can_fight = true;
                    }
                    if ((x - 2 >= 0 && y + 2 <= 7) && (b.board[x - 1][y + 1] == opponent || b.board[x - 1][y + 1] == k) && b.board[x - 1][y + 1] != 10 && (b.board[x - 2][y + 2] == 0 || b.board[x - 2][y + 2] == 3)) {
                        can_fight = true;
                    }
                    if ((x - 2 >= 0 && y - 2 >= 0) && (b.board[x - 1][y - 1] == opponent || b.board[x - 1][y - 1] == k) && b.board[x - 1][y - 1] != 10 && (b.board[x - 2][y - 2] == 0 || b.board[x - 2][y - 2] == 3)) {
                        can_fight = true;
                    }
                    if ((x + 2 <= 7 && y - 2 >= 0) && (b.board[x + 1][y - 1] == opponent || b.board[x + 1][y - 1] == k) && b.board[x + 1][y - 1] != 10 && (b.board[x + 2][y - 2] == 0 || b.board[x + 2][y - 2] == 3)) {
                        can_fight = true;
                    }
                }
            } else {
                if (x != -100 && y != -100) {
                    if (b.board[x][y] == 1 || b.board[x][y] == 11) {
                        teammate = 1;
                        kt = 11;
                        opponent = 2;
                        k = 22;
                    } else if (b.board[x][y] == 2 || b.board[x][y] == 22) {
                        teammate = 2;
                        kt = 22;
                        opponent = 1;
                        k = 11;
                    }
                    for (int i = 1; i < 8; i++) {
                        if (x + i + 1 <= 7 && y + i + 1 <= 7) {
                            if ((b.board[x + i + 1][y + i + 1] == opponent || b.board[x + i + 1][y + i + 1] == k || b.board[x + i + 1][y + i + 1] == kt || b.board[x + i + 1][y + i + 1] == teammate) && (b.board[x + i][y + i] == opponent || b.board[x + i][y + i] == k || b.board[x + i][y + i] == kt || b.board[x + i][y + i] == teammate)) {
                                Log.d("f", "aaaaaaa");
                                break;
                            }
                        }
                        if (x + i <= 7 && y + i <= 7) {
                            if (b.board[x + i][y + i] == 10) {
                                break;
                            }
                        }
                        if ((x + i + 1 <= 7 && y + i + 1 <= 7) && (b.board[x + i][y + i] == opponent || b.board[x + i][y + i] == k) && (b.board[x + i + 1][y + i + 1] == 0 || b.board[x + i + 1][y + i + 1] == 3)) {
                            can_fight = true;
                            break;
                        }
                    }

                    for (int i = 1; i < 8; i++) {
                        if (x - i >= 0 && y + i <= 7) {
                            if (b.board[x - i][y + i] == 10) {
                                Log.d("f", "aaaaaaab");
                                break;
                            }
                        }
                        if (x - i - 1 >= 0 && y + i + 1 <= 7) {
                            if ((b.board[x - i - 1][y + i + 1] == opponent || b.board[x - i - 1][y + i + 1] == k || b.board[x - i - 1][y + i + 1] == kt || b.board[x - i - 1][y + i + 1] == teammate) && (b.board[x - i][y + i] == opponent || b.board[x - i][y + i] == k || b.board[x - i][y + i] == kt || b.board[x - i][y + i] == teammate)) {
                                break;
                            }
                        }
                        if ((x - i - 1 >= 0 && y + i + 1 <= 7) && (b.board[x - i][y + i] == opponent || b.board[x - i][y + i] == k) && (b.board[x - i - 1][y + i + 1] == 0 || b.board[x - i - 1][y + i + 1] == 3)) {
                            can_fight = true;
                            break;
                        }
                    }
                    for (int i = 1; i < 8; i++) {
                        if (x - i >= 0 && y - i >= 0) {
                            if (b.board[x - i][y - i] == 10) {
                                break;
                            }
                        }
                        if (x - i - 1 >= 0 && y - i - 1 >= 0) {
                            if ((b.board[x - i - 1][y - i - 1] == opponent || b.board[x - i - 1][y - i - 1] == k || b.board[x - i - 1][y - i - 1] == kt || b.board[x - i - 1][y - i - 1] == teammate) && (b.board[x - i][y - i] == opponent || b.board[x - i][y - i] == k || b.board[x - i][y - i] == kt || b.board[x - i][y - i] == teammate)) {
                                Log.d("f", "aaaaaaabbbbbbbb");
                                break;
                            }
                        }
                        if ((x - i - 1 >= 0 && y - i - 1 >= 0) && (b.board[x - i][y - i] == opponent || b.board[x - i][y - i] == k) && (b.board[x - i - 1][y - i - 1] == 0 || b.board[x - i - 1][y - i - 1] == 3)) {
                            can_fight = true;
                            break;
                        }
                    }
                    for (int i = 1; i < 8; i++) {
                        if (x + i <= 7 && y - i >= 0) {
                            if (b.board[x + i][y - i] == 10) {
                                break;
                            }
                        }
                        if (x + i + 1 <= 7 && y - i - 1 >= 0) {
                            if ((b.board[x + i + 1][y - i - 1] == opponent || b.board[x + i + 1][y - i - 1] == k || b.board[x + i + 1][y - i - 1] == kt || b.board[x + i + 1][y - i - 1] == teammate) && (b.board[x + i][y - i] == opponent || b.board[x + i][y - i] == k || b.board[x + i][y - i] == kt || b.board[x + i][y - i] == teammate)) {
                                break;
                            }
                        }
                        if ((x + i + 1 <= 7 && y - i - 1 >= 0) && (b.board[x + i][y - i] == opponent || b.board[x + i][y - i] == k) && (b.board[x + i + 1][y - i - 1] == 0 || b.board[x + i + 1][y - i - 1] == 3)) {
                            can_fight = true;
                            break;
                        }
                    }
                }
            }
            return can_fight;
        }

        public int[][] getPossibleFights() {
            Log.d("f", "getPossibleFights draught");
            int k;
            int teammate = 0;
            int kt = 0;
            k = turn == 1 ? 22 : 11;
            int opponent;
            opponent = turn == 1 ? 2 : 1;
            if (b.board[x][y] == 1 || b.board[x][y] == 11) {
                teammate = 1;
                kt = 11;
                opponent = 2;
                k = 22;
            } else if (b.board[x][y] == 2 || b.board[x][y] == 22) {
                teammate = 2;
                kt = 22;
                opponent = 1;
                k = 11;
            }
            int[][] fights = {{-1, -1}, {-1, -1}, {-1, -1}, {-1, -1}};
            for (int i = 1; i < 8; i++) {
                if (x + i + 1 <= 7 && y + i + 1 <= 7) {
                    if ((b.board[x + i + 1][y + i + 1] == opponent || b.board[x + i + 1][y + i + 1] == k || b.board[x + i + 1][y + i + 1] == kt || b.board[x + i + 1][y + i + 1] == teammate) && (b.board[x + i][y + i] == opponent || b.board[x + i][y + i] == k || b.board[x + i][y + i] == kt || b.board[x + i][y + i] == teammate)) {
                        Log.d("f", "aaaaaaa");
                        break;
                    }
                }
                if (x + i <= 7 && y + i <= 7) {
                    if (b.board[x + i][y + i] == 10) {
                        break;
                    }
                }
                if ((x + i + 1 <= 7 && y + i + 1 <= 7) && (b.board[x + i][y + i] == opponent || b.board[x + i][y + i] == k) && (b.board[x + i + 1][y + i + 1] == 0 || b.board[x + i + 1][y + i + 1] == 3)) {
                    fights[0][0] = x + i;
                    fights[0][1] = y + i;
                    break;
                }
            }
            for (int i = 1; i < 8; i++) {
                if (x - i >= 0 && y + i <= 7) {
                    if (b.board[x - i][y + i] == 10) {
                        Log.d("f", "aaaaaaab");
                        break;
                    }
                }
                if (x - i - 1 >= 0 && y + i + 1 <= 7) {
                    if ((b.board[x - i - 1][y + i + 1] == opponent || b.board[x - i - 1][y + i + 1] == k || b.board[x - i - 1][y + i + 1] == kt || b.board[x - i - 1][y + i + 1] == teammate) && (b.board[x - i][y + i] == opponent || b.board[x - i][y + i] == k || b.board[x - i][y + i] == kt || b.board[x - i][y + i] == teammate)) {
                        break;
                    }
                }
                if ((x - i - 1 >= 0 && y + i + 1 <= 7) && (b.board[x - i][y + i] == opponent || b.board[x - i][y + i] == k) && (b.board[x - i - 1][y + i + 1] == 0 || b.board[x - i - 1][y + i + 1] == 3)) {
                    fights[1][0] = x - i;
                    fights[1][1] = y + i;
                    break;
                }
            }
            for (int i = 1; i < 8; i++) {
                if (x - i >= 0 && y - i >= 0) {
                    if (b.board[x - i][y - i] == 10) {
                        break;
                    }
                }
                if (x - i - 1 >= 0 && y - i - 1 >= 0) {
                    if ((b.board[x - i - 1][y - i - 1] == opponent || b.board[x - i - 1][y - i - 1] == k || b.board[x - i - 1][y - i - 1] == kt || b.board[x - i - 1][y - i - 1] == teammate) && (b.board[x - i][y - i] == opponent || b.board[x - i][y - i] == k || b.board[x - i][y - i] == kt || b.board[x - i][y - i] == teammate)) {
                        Log.d("f", "aaaaaaabbbbbbbb");
                        break;
                    }
                }
                if ((x - i - 1 >= 0 && y - i - 1 >= 0) && (b.board[x - i][y - i] == opponent || b.board[x - i][y - i] == k) && (b.board[x - i - 1][y - i - 1] == 0 || b.board[x - i - 1][y - i - 1] == 3)) {
                    fights[2][0] = x - i;
                    fights[2][1] = y - i;
                    break;
                }
            }
            for (int i = 1; i < 8; i++) {
                if (x + i <= 7 && y - i >= 0) {
                    if (b.board[x + i][y - i] == 10) {
                        break;
                    }
                }
                if (x + i + 1 <= 7 && y - i - 1 >= 0) {
                    if ((b.board[x + i + 1][y - i - 1] == opponent || b.board[x + i + 1][y - i - 1] == k || b.board[x + i + 1][y - i - 1] == kt || b.board[x + i + 1][y - i - 1] == teammate) && (b.board[x + i][y - i] == opponent || b.board[x + i][y - i] == k || b.board[x + i][y - i] == kt || b.board[x + i][y - i] == teammate)) {
                        break;
                    }
                }
                if ((x + i + 1 <= 7 && y - i - 1 >= 0) && (b.board[x + i][y - i] == opponent || b.board[x + i][y - i] == k) && (b.board[x + i + 1][y - i - 1] == 0 || b.board[x + i + 1][y - i - 1] == 3)) {
                    fights[3][0] = x + i;
                    fights[3][1] = y - i;
                    break;
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
                b.board[x + 1][y + 1] = 10;
                for (int i = 0; i < 12; i++) {
                    if (opponent[i].x == x + 1 && opponent[i].y == y + 1) {
                        opponent[i].x = -100;
                        opponent[i].y = -100;
                    }
                }
            }
            if (x1 < x && y1 > y) {
                b.board[x - 1][y + 1] = 10;
                for (int i = 0; i < 12; i++) {
                    if (opponent[i].x == x - 1 && opponent[i].y == y + 1) {
                        opponent[i].x = -100;
                        opponent[i].y = -100;
                    }
                }
            }
            if (x1 < x && y1 < y) {
                b.board[x - 1][y - 1] = 10;
                for (int i = 0; i < 12; i++) {
                    if (opponent[i].x == x - 1 && opponent[i].y == y - 1) {
                        opponent[i].x = -100;
                        opponent[i].y = -100;
                    }
                }
            }
            if (x1 > x && y1 < y) {
                b.board[x + 1][y - 1] = 10;
                for (int i = 0; i < 12; i++) {
                    if (opponent[i].x == x + 1 && opponent[i].y == y - 1) {
                        opponent[i].x = -100;
                        opponent[i].y = -100;
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
                clear3();
                clear10();
                b.updateboard();
            }
            step += 1;
        }

        public void kingFight(int x1, int y1, Draught[] opponent) {
            for (int[] i : b.board) {
                s = Arrays.toString(i);
                Log.d("f", s);
            }
            int dr = turn == 1 ? 11 : 22;
            int[][] fights = getPossibleFights();
            if (fights[0][0] > x && fights[0][1] > y && fights[0][0] != -1) {
                b.board[fights[0][0]][fights[0][1]] = 10;
                for (int j = 0; j < 12; j++) {
                    if(opponent[j].x == fights[0][0] && opponent[j].y == fights[0][1]){
                        opponent[j].x = -100;
                        opponent[j].y = -100;
                    }
                }
            } else if (fights[1][0] < x && fights[1][1] > y && fights[1][0] != -1) {
                b.board[fights[1][0]][fights[1][1]] = 10;
                for (int j = 0; j < 12; j++) {
                    if(opponent[j].x == fights[1][0] && opponent[j].y == fights[1][1]){
                        opponent[j].x = -100;
                        opponent[j].y = -100;
                    }
                }
            } else if (fights[2][0] < x && fights[2][1] < y && fights[2][0] != -1) {
                b.board[fights[2][0]][fights[2][1]] = 10;
                for (int j = 0; j < 12; j++) {
                    if(opponent[j].x == fights[2][0] && opponent[j].y == fights[2][1]){
                        opponent[j].x = -100;
                        opponent[j].y = -100;
                    }
                }
            } else if (fights[3][0] > x && fights[3][0] < y && fights[3][0] != -1) {
                b.board[fights[3][0]][fights[3][1]] = 10;
                for (int j = 0; j < 12; j++) {
                    if(opponent[j].x == fights[3][0] && opponent[j].y == fights[3][1]) {
                        opponent[j].x = -100;
                        opponent[j].y = -100;
                    }
                }
            }
            for (int[] i : b.board) {
                s = Arrays.toString(i);
                Log.d("f", s);
            }
            b.board[x][y] = 0;
            x = x1;
            y = y1;
            b.board[x1][y1] = dr;
            for (int[] i : b.board) {
                s = Arrays.toString(i);
                Log.d("f", s);
            }
            if (canFight()) {
                id_further = id_draught;
            } else {
                id_further = 0;
                turn = turn == 1 ? 2 : 1;
                id = 0;
                clear3();
                clear10();
                b.updateboard();
            }
        }
    }

    Draught[] white = new Draught[12];
    Draught[] black = new Draught[12];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);
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

        Button back = findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LevelActivity.this, ExActivity.class);
                startActivity(intent);
            }
        });

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                b.boardIm[i][j].setOnClickListener(this);
            }
        }

        arguments = getIntent().getExtras();
        level = Integer.parseInt(arguments.get("level").toString());

        showInfoAlert(level);
        switch (level) {
            case 1:
                b.board[0][0] = 1;
                break;
            case 2:
                b.board[5][5] = 11;
                break;
            case 3:
                b.board[3][3] = 1;
                b.board[4][4] = 2;
                b.board[4][6] = 2;
                b.board[2][6] = 2;
                break;
            case 4:
                b.board[7][1] = 11;
                b.board[5][3] = 2;
                b.board[3][3] = 2;
                b.board[3][5] = 2;
                b.board[2][6] = 2;
                break;
            case 5:
                b.board[0][2] = 1;
                b.board[1][3] = 1;
                b.board[6][0] = 2;
                b.board[3][1] = 2;
                b.board[3][5] = 2;
                b.board[5][5] = 2;
                break;
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
                    white[w].is_king = false;
                    w += 1;
                } else if (b.board[i][j] == 2) {
                    black[bl].x = i;
                    black[bl].y = j;
                    black[bl].is_king = false;
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

    private void showInfoAlert (int lvl) {
        AlertDialog.Builder builder = new AlertDialog.Builder(LevelActivity.this);
        String text = "";
        switch (lvl) {
            case 1:
                text = "Обычная шашка передвигается вперёд на одну клетку по диагонали. Доберитесь до противоположного края доски, используя эту информацию.";
                break;
            case 2:
                text = "Дамка ходит по диагонали на любое свободное поле как вперёд, так и назад, но не может перескакивать свои шашки или дамки. Сделайте несколько ходов!";
                break;
            case 3:
                text = "Взятие обязательно. Побитые шашки и дамки снимаются только после завершения хода.\n" +
                        "Простая шашка, находящаяся рядом с шашкой соперника, за которой имеется свободное поле, переносится через эту шашку на это свободное поле. Если есть возможность продолжить взятие других шашек соперника, то это взятие продолжается, пока бьющая шашка не достигнет положения, из которого бой невозможен. Взятие простой шашкой производится как вперёд, так и назад.";
                break;
            case 4:
                text = "Дамка бьёт по диагонали, как вперёд, так и назад, и становится на любое свободное поле после побитой шашки. Вперёд!";
                break;
            case 5:
                text = "В данной позиции всё может показаться безнадёжным. Но не стоит отчаиваться, просто нужно заметить \"решётчатость\" построения чёрных и воспользоваться этим. Дайте чёрным взять вашу шашку";
                break;
            case 0:
                text = "И вы прорываетесь в дамки!!!";
                break;
        }
            builder.setTitle("Задание" + " " + Integer.toString(level))
                    .setMessage(text);
                    if (level != 0) {
                        builder.setPositiveButton("Поехали", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                    }
        builder.setNegativeButton("Назад", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(LevelActivity.this, ExActivity.class);
                            startActivity(intent);
                        }
                    });
        if (level == 0) {
            builder.setTitle("Конец");
        }

        AlertDialog dialog = builder.create();
        dialog.show();
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
        virtualOnClick();
        result();
    }

    public void result () {
        switch (level) {
            case 1:
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        if (b.board[i][j] == 11) {
                            b.board[i][j] = 0;
                            b.board[5][5] = 11;
                            b.changeBoard();
                            b.updateboard();
                            level = 2;
                            showInfoAlert(level);
                        }
                    }
                }
                break;
            case 2:
                if (step >= 7) {
                    for (int i = 0; i < 8; i++) {
                        for (int j = 0; j < 8; j++) {
                            if (b.board[i][j] == 11) {
                                b.board[i][j] = 0;
                                b.board[3][3] = 1;
                                b.board[4][4] = 2;
                                b.board[4][6] = 2;
                                b.board[2][6] = 2;
                                b.changeBoard();
                                b.updateboard();
                                level = 3;
                                showInfoAlert(level);
                            }
                        }
                    }
                    step = 0;
                }
                break;
            case 3:
                if (step == 1) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Продалжайте взятие", Toast.LENGTH_SHORT);
                    toast.show();
                    step += 1;
                } else if (step == 4) {
                    clearAll();
                    b.board[7][1] = 11;
                    b.board[5][3] = 2;
                    b.board[3][3] = 2;
                    b.board[3][5] = 2;
                    b.board[2][6] = 2;
                    b.changeBoard();
                    b.updateboard();
                    turn = 1;
                    level = 4;
                    step = 0;
                    showInfoAlert(level);
                }
                break;
            case 4:
                int bl = 0;
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        if (b.board[i][j] == 2) {
                            bl = bl + 1;
                        }
                    }
                }
                if (bl == 2) {
                    clearAll();
                    b.board[0][2] = 1;
                    b.board[1][3] = 1;
                    b.board[6][0] = 2;
                    b.board[3][1] = 2;
                    b.board[3][5] = 2;
                    b.board[5][5] = 2;
                    turn = 1;
                    level = 5;
                    b.changeBoard();
                    b.updateboard();
                    showInfoAlert(level);
                }
                break;
            case 5:
                bl = 0;
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        if (b.board[i][j] == 2) {
                            bl = bl + 1;
                        }
                    }
                }
                if (bl == 1) {
                    level = 0;
                    showInfoAlert(level);
                }
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

    public void clear10 () {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (b.board[i][j] == 10) {
                    b.board[i][j] = 0;
                }
            }
        }
        b.updateboard();
    }

    public void clearAll () {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                b.board[i][j] = 0;
            }
        }
        b.updateboard();
    }

    public void virtualOnClick() {
        if (turn == 2) {
            for (int i = 0; i < 12; i++) {
                if (black[i].canFight()) {
                    b.setFights(black[i].x, black[i].y, black);
                    for (int i1 = 0; i1 < 8; i1++) {
                        for (int j1 = 0; j1 < 8; j1++) {
                            if (b.board[i1][j1] == 3) {
                                b.fight(i1, j1, black);
                            }
                        }
                    }
                }
            }
        }
            if (b.board[3][5] == 0 && b.board[1][3] == 2) {
                Toast toast = Toast.makeText(getApplicationContext(), "Неправильно", Toast.LENGTH_SHORT);
                toast.show();
                Intent intent = new Intent(LevelActivity.this, ExActivity.class);
                startActivity(intent);
            }
    }

    // Добавление в массив элемента.
    private static int[][] addElement (int[][] myArray, int[] element){
        int[][] array = Arrays.copyOf(myArray, myArray.length + 1);
        array[myArray.length] = element;
        return array;
    }
}