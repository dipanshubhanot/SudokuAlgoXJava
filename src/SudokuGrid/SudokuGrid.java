package SudokuGrid;

import java.io.*;
import java.util.HashSet;

public class SudokuGrid {
    private int size;
    private int[][] grid;
    private int[][] coverMatrix;
    private HashSet<Integer> validValues;
    private final int NUMCONSTRAINTS  = 4;
    public final int NULLVALUE = 0;
    private int boxsize;


    // Getters and Setters
    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int[][] getGrid() {
        return grid;
    }

    public void setGrid(int[][] grid) {
        this.grid = grid;
    }

    public int[][] getCoverMatrix() {
        return coverMatrix;
    }

    public void setCoverMatrix(int[][] coverMatrix) {
        this.coverMatrix = coverMatrix;
    }

    public HashSet<Integer> getValidValues() {
        return validValues;
    }

    public void setValidValues(HashSet<Integer> validValues) {
        this.validValues = validValues;
    }

    public int getNumConstraints() {
        return NUMCONSTRAINTS;
    }


    private SudokuGrid(String inputFile){
        readFromFile(inputFile);
        boxsize = (int) Math.sqrt(size);
        generateCoverMatrix();

    }

    private void generateCoverMatrix(){
        coverMatrix = new int[size*size*(validValues.size()-1)][size*size*NUMCONSTRAINTS];
        // initialising cover matrix
        for (int[] row:
             coverMatrix) {
            for (int cell:
                 row){
                cell= 0;
            }
        }
        // filling in the cover matrix

        // first setting row-col constraint
        // each cell can contain at most 1 value


    }

    private void readFromFile(String inputFile){
        // reading file and setting state variables
        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            String currLine  = reader.readLine();
            size = Integer.parseInt(currLine);
            currLine = reader.readLine();
            for (Character c:currLine.toCharArray()){
                if (c!=' '){
                    validValues.add(Integer.parseInt(c.toString()));
                }
            }
            validValues.add(NULLVALUE);

            // initialising the grid to null values
            grid = new int[size][size];
            for (int[] row: grid) {
                for (int cell:row) {
                    cell = NULLVALUE;
                }
            }

            while ((currLine = reader.readLine())!=  null){
                int x;
                int y;
                int val;
                x = Integer.parseInt(currLine.substring(0,currLine.indexOf(' ')));
                y = Integer.parseInt(currLine.substring(currLine.indexOf(' '), currLine.lastIndexOf(' ') ));
                val = Integer.parseInt(currLine.substring(currLine.lastIndexOf(' ')));
                if(!validValues.contains(val)){
                    throw new IllegalArgumentException(val + " is not a valid  Value. Please provide a valid value!");
                }
                else{
                    grid[x-1][y-1]= val;
                }

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public SudokuGrid generateGrid(String inputFile){
        return new SudokuGrid(inputFile);
    }
}
