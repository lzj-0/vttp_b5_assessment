package vttp.batch5.sdf.task02;

import java.io.*;
import java.util.*;

public class TicTacToe {
    private String[][] board;

    public TicTacToe(String filename) {
        System.out.printf("Processing: %s\n\n", filename);
        File file = new File(filename);
        try (FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);) {
                String[][] board = new String[3][3];
                for (int i = 0; i < board.length; i++) {
                    String[] row = br.readLine().split("");
                    for (int j = 0; j < board[0].length; j++) {
                        board[i][j] = row[j];
                    }
                }
                this.board = board;
                } catch (IOException e) {
                    System.out.println("Invalid configuration file");
                    System.exit(-1);
                }
    }

    public String[][] getBoard() {
        return this.board;
    }

    public void setBoard(String[][] board) {
        this.board = board;
    }

    public String printBoard() {
        StringBuilder sb = new StringBuilder();
        sb.append("Board:\n");
        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board[0].length; j++) {
                sb.append("%s".formatted(board[i][j]));
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public String getUtility() {
        ArrayList<int[]> emptyPos = new ArrayList<>();
        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board[0].length; j++) {
                if (this.board[i][j].equals(".")) {
                    emptyPos.add(new int[] {i, j});
                }
            }
        }
        HashMap<int[], Integer> utility = new HashMap<>();
        for (int[] pos: emptyPos) {
            this.board[pos[0]][pos[1]] = "X";
            List<String> evaluateRows = Arrays.asList(this.board[0][0] + this.board[0][1] + this.board[0][2],
                                    this.board[1][0] + this.board[1][1] + this.board[1][2],
                                    this.board[2][0] + this.board[2][1] + this.board[2][2],
                                    this.board[0][0] + this.board[1][0] + this.board[2][0],
                                    this.board[0][1] + this.board[1][1] + this.board[2][1],
                                    this.board[0][2] + this.board[1][2] + this.board[2][2],
                                    this.board[0][0] + this.board[1][1] + this.board[2][2],
                                    this.board[0][2] + this.board[1][1] + this.board[2][0]);
            Boolean evaluated = false;
            if (evaluateRows.contains("XXX")) {
                utility.put(pos, 1);
            } else {
                for (String row: evaluateRows) {
                    //System.out.println(row);
                    if (row.length() - row.replace("O", "").length() == 2 && !row.contains("X")) {
                        //System.out.println("true");
                        utility.put(pos, -1);
                        evaluated = true;
                        break;
                    }
                }
                if (!evaluated) {
                    utility.put(pos, 0);
                }
            }
            this.board[pos[0]][pos[1]] = ".";

        }
        StringBuilder sb = new StringBuilder();
        
        utility.keySet().forEach(pos -> sb.append("y=%d, x=%d, utility=%d\n".formatted(pos[0], pos[1], utility.get(pos))));
        return sb.toString();

    }





}
