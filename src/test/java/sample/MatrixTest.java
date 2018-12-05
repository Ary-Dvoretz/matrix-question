package sample;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MatrixTest {

    Matrix matrix;

    @Before
    public void setup() {
        Character start = 'A';
        matrix = new Matrix(5, 4);
        for (int i = 0; i < matrix.getRowsLimit(); i++) {
            for (int j = 0; j < matrix.getColsLimit(); j++) {
                matrix.setDataAt(start++, i, j);
            }
        }
    }

    @Test
    public void check_ToString() {
        String x = matrix.toString();
        System.out.println("Original:");
        System.out.println(x);
        Assert.assertThat(x, CoreMatchers.equalTo("A\tB\tC\tD\nE\tF\tG\tH\nI\tJ\tK\tL\nM\tN\tO\tP\nQ\tR\tS\tT\n"));
    }

    @Test
    public void checkRotateClockwise() {
        matrix.rotateClockwise();
        String x = matrix.toString();
        System.out.println("Rotated 90:");
        System.out.println(x);
        Assert.assertThat(x, CoreMatchers.equalTo("Q\tM\tI\tE\tA\nR\tN\tJ\tF\tB\nS\tO\tK\tG\tC\nT\tP\tL\tH\tD\n"));
    }

    @Test
    public void checkRotateCounterClockwise() {
        matrix.rotateCounterClockwise();
        String x = matrix.toString();
        System.out.println("Rotated -90:");
        System.out.println(x);
        Assert.assertThat(x, CoreMatchers.equalTo("D\tH\tL\tP\tT\nC\tG\tK\tO\tS\nB\tF\tJ\tN\tR\nA\tE\tI\tM\tQ\n"));
    }

    @Test
    public void checkRotateToOriginal() {
        matrix.rotateClockwise();
        matrix.rotateCounterClockwise();
        String x = matrix.toString();
        System.out.println("Rotated 90 and then -90:");
        System.out.println(x);
        Assert.assertThat(x, CoreMatchers.equalTo("A\tB\tC\tD\nE\tF\tG\tH\nI\tJ\tK\tL\nM\tN\tO\tP\nQ\tR\tS\tT\n"));
    }

    @Test
    public void checkRotateTo180() {
        matrix.rotateClockwise();
        matrix.rotateClockwise();
        String x = matrix.toString();
        System.out.println("Rotated 180:");
        System.out.println(x);
        Assert.assertThat(x, CoreMatchers.equalTo("T\tS\tR\tQ\nP\tO\tN\tM\nL\tK\tJ\tI\nH\tG\tF\tE\nD\tC\tB\tA\n"));
    }

    @Test
    public void checkPutWhileRotated90() {
        matrix.rotateClockwise();
        for (int i = 0; i < matrix.getRowsLimit(); i ++) {
            matrix.setDataAt((char) ('W' + i), i,2);
        }
        String x = matrix.toString();
        System.out.println("Rotate 90 then change 3rd column to W,X,Y,Z: ");
        System.out.println(x);
        Assert.assertThat(x, CoreMatchers.equalTo("Q\tM\tW\tE\tA\nR\tN\tX\tF\tB\nS\tO\tY\tG\tC\nT\tP\tZ\tH\tD\n"));
    }

}
