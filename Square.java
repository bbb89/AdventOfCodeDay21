package com.lukasz;

import java.util.ArrayList;
import java.util.List;

public class Square {
    private List<String> squareAsList;
    private String squareAsString;
    private int size;
    private int numberOfTransforms;

    public Square(List<String> squareAsList) {
        this.squareAsList = squareAsList;
        this.squareAsString = convertToString(squareAsList);
        setup();
    }

    public Square(String squareAsString) {
        this.squareAsString = squareAsString;
        this.squareAsList = convertToSquare(squareAsString);
        setup();
    }

    private void setup() {
        this.size = squareAsList.size();
        this.numberOfTransforms = 0;
    }

    public List<Square> divideSquare() {
        int divideBy = 3;

        if( size % 2 == 0 ) {
            divideBy = 2;
        }

        List<Square> squares = new ArrayList<>();
        int columns = size / divideBy;
        int newSize = size / columns;

        for(int i = 0; i < columns * columns; i++) {
            StringBuilder stringBuilder = new StringBuilder();
            int verticalStartPosition = (i / columns) * newSize;

            for (int j = verticalStartPosition; j < verticalStartPosition + newSize; j++) {

                int horizontalStartPosition = (i % columns) * newSize;

                for (int k = horizontalStartPosition; k < horizontalStartPosition + newSize; k++) {
                    stringBuilder.append(squareAsList.get(j).charAt(k));
                }
                if(j % newSize != newSize - 1) {
                    stringBuilder.append("/");
                }
            }

            squares.add(new Square(stringBuilder.toString()));
        }

        return squares;
    }

    public void transform() {
        if(numberOfTransforms == 4) {
            flipHorizontally();
        }else {
            rotate();
        }
        numberOfTransforms++;
    }

    public int countOnPixels() {
        int sum = 0;
        for(int i = 0; i < squareAsString.length(); i++) {
            if(squareAsString.charAt(i) == '#') sum++;
        }

        return sum;
    }

    private List<String> convertToSquare(String squareAsString) {
        List<String> square = new ArrayList<>();
        String[] lines = squareAsString.split("/");
        for(int i = 0; i < lines.length; i++) {
            square.add(lines[i]);
        }

        return square;
    }

    private String convertToString(List<String> squareAsList) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < squareAsList.size(); i++) {
            sb.append(squareAsList.get(i));
            if(i < squareAsList.size() - 1) sb.append("/");
        }

        return sb.toString();
    }

    private void rotate() {
        List<String> newSquare = new ArrayList<>();
        for(int i = 0; i < squareAsList.get(0).length(); i++) {
            StringBuilder stringBuilder = new StringBuilder();
            for(int j = 0; j < squareAsList.size(); j++) {
                stringBuilder.append(squareAsList.get(j).charAt(i));
            }
            stringBuilder.reverse();
            newSquare.add(stringBuilder.toString());
        }

        this.squareAsList = newSquare;
        this.squareAsString = convertToString(squareAsList);
    }

    private void flipHorizontally() {
        for(int i = 0; i < squareAsList.size(); i++) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(squareAsList.get(i));
            stringBuilder.reverse();
            squareAsList.set(i, stringBuilder.toString());
        }

        this.squareAsString = convertToString(squareAsList);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < squareAsList.size(); i++) {
            sb.append(squareAsList.get(i));
            if(i < squareAsList.size() - 1) sb.append("\n");
        }
        return sb.toString();
    }

    public List<String> getSquareAsList() {
        return squareAsList;
    }

    public String getSquareAsString() {
        return squareAsString;
    }

    public int getSize() {
        return size;
    }

    @Override
    public int hashCode() {
        return squareAsString.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return squareAsString.equals(((Square) obj).getSquareAsString());
    }
}
