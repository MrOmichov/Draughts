package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    int idD = 100;
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
        int[][] board = {{1, 0, 0, 0, 0, 0, 1, 0},
                         {0, 0, 0, 2, 0, 0, 0, 0},
                         {0, 0, 0, 0, 0, 0, 2, 0},
                         {0, 0, 0, 1, 0, 0, 0, 0},
                         {0, 0, 0, 0, 0, 0, 2, 0},
                         {0, 0, 0, 0, 0, 0, 0, 0},
                         {0, 0, 0, 0, 0, 0, 0, 0},
                         {0, 0, 0, 0, 0, 0, 0, 0}}; // Виртуальное поле.

        ImageButton[][] boardIm = new ImageButton[8][8];

        public void setMoves(int x, int y, Draught[] dr) {
            clear3();
            int d;
            d = turn == 1 ? 1 : -1;
            for (int i = 0; i < 12; i++) {
                if (dr[i].x == x && dr[i].y == y) {
                    for (int i1 = 0; i1 < 2; i1++) {
                        if (dr[i].getPossibleMoves()[i1] != -1) {
                            board[x + d][dr[i].getPossibleMoves()[i1]] = 3;
                        }
                    }
                    break;
                }
            }
            updateboard();
        }

        // Обновление доски.
        public void updateboard() {
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
                    } else if (b.board[i][j] == 10) {
                        b.boardIm[i][j].setImageResource(R.drawable.draught_white_king);
                    } else if (b.board[i][j] == 20) {
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

        public Draught() {
            this.id_draught = idD + 1;
            idD += 1;
        }

        public int[] getPossibleMoves() {
            int[] moves = new int[2];
            int el;
            int d;
            d = turn == 1 ? 1 : -1;
            if (!is_king) {
                id = id_draught;
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
            }
            return moves;
        }

        public void move(int x, int y) {

        }
    }

    Draught[] white = new Draught[12];
    Draught[] black = new Draught[12];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        for (int i = 0; i < 8; i++) {
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
                }
            }
        }
    }



    @Override
    public void onClick(View v) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (b.boardIm[i][j].getId() == v.getId() && b.board[i][j] != 0) {
                    if (turn == 1) {
                        b.setMoves(i, j, white);
                    } else {
                        b.setMoves(i, j, black);
                    }
                    break;
                }
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

        // Добавление в массив элемента.
        private static int[][] addElement (int[][] myArray, int[] element){
            int[][] array = Arrays.copyOf(myArray, myArray.length + 1);
            array[myArray.length] = element;
            return array;
        }
}