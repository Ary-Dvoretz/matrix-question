package sample;

import javafx.util.Pair;

public class Matrix {

    private Character[][] data;

    private final int dataX, dataY;

    private Angle angle = Angle._0;

    private enum Angle {
        _0,_90,_180,_270;
    }

    public Matrix(int dataX, int dataY) {
        this.dataX = dataX;
        this.dataY = dataY;
        this.data = new Character[dataX][dataY];
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < getRowsLimit(); i++) {
            for (int j = 0; j < getColsLimit(); j++ ) {
                if (j > 0) {
                    result.append("\t");
                }
                result.append(getDataAt(i, j));
            }
            result.append("\n");
        }
        return result.toString();
    }

    private int getRowsLimit() {
        return getLimit(false);
    }

    private int getColsLimit() {
        return getLimit(true);
    }

    private int getLimit(boolean colums) {
        switch (angle) {
            case _0:
            case _180:
                return colums ? dataY : dataX;
            case _90:
            case _270:
                return colums ? dataX : dataY;
        }
        throw new IllegalStateException("not supported angle");
    }

    // TODO: check bounds per angle
    private Pair<Integer, Integer> convertCordinates(int i, int j) {
        switch (angle) {
            case _0:
                return new Pair<>(i,j);
            case _90:
                return new Pair<>(dataX-1-j,i);
            case _180:
                return new Pair<>(dataX-1-i,dataY-1-j);
            case _270:
                return new Pair<>(j,dataY-1-i);
        }
        throw new IllegalStateException("not supported angle");
    }

    public Character getDataAt(int i, int j) {
        Pair<Integer, Integer> dataCorrdinates = convertCordinates(i, j);
        return data[dataCorrdinates.getKey()][dataCorrdinates.getValue()];
    }

    public void setDataAt(Character newData, int i, int j) {
        Pair<Integer, Integer> dataCorrdinates = convertCordinates(i, j);
        data[dataCorrdinates.getKey()][dataCorrdinates.getValue()] = newData;
    }

    public void rotateClockwise() {
        if (angle.ordinal() + 1 > Angle.values().length) {
            angle = Angle._0;
        } else {
            angle = Angle.values()[angle.ordinal() + 1];
        }
    }

    public void rotateCounterClockwise() {
        if (angle.ordinal() == 0) {
            angle = Angle._270;
        } else {
            angle = Angle.values()[angle.ordinal() - 1];
        }
    }

}
