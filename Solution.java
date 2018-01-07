package com.lukasz;

import java.util.List;
import java.util.Map;

public class Solution {
    private Map<String, String> rules;
    private Square currentSquare;
    private int solution;

    public Solution(Map<String, String> rules, int iterations) {
        this.rules = rules;
        currentSquare = new Square(".#./..#/###");

        currentSquare = transformSquare(currentSquare);
        for(int i = 2; i <= iterations; i++) {
            currentSquare = convertSquare(currentSquare);
        }
        solution = currentSquare.countOnPixels();
    }

    private Square transformSquare(Square square) {
        while(!rules.containsKey(square.getSquareAsString())) {
            square.transform();
        }

        return new Square(rules.get(square.getSquareAsString()));
    }

    private Square convertSquare(Square square) {
        List<Square> squares = divideSquare(square);
        squares = transformParts(squares);
        return mergeSquares(squares);
    }

    private Square mergeSquares(List<Square> squares) {
        StringBuilder stringBuilder = new StringBuilder();
        int partSize = squares.get(0).getSize();
        int newSize = partSize * (int) Math.sqrt(squares.size());
        int columns = (int) Math.sqrt(squares.size());
        for(int i = 0; i < newSize; i++) {
            int verticalPosition = i % partSize;
            int currentSquare = (i / partSize) * columns;
            for(int j = currentSquare; j < currentSquare + columns; j++) {
                stringBuilder.append(squares.get(j).getSquareAsList().get(verticalPosition));
            }
            if(i != newSize - 1) stringBuilder.append("/");
        }

        return new Square(stringBuilder.toString());
    }

    private List<Square> transformParts(List<Square> squares) {
        for(int i = 0; i < squares.size(); i++) {
            squares.set(i, transformSquare(squares.get(i)));
        }

        return squares;
    }

    private List<Square> divideSquare(Square square) {
        List<Square> squares = square.divideSquare();

        return squares;
    }

    public int getSolution() {
        return solution;
    }
}
