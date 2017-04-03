package model;

/**
 * Created by staho on 03.04.2017.
 */
public class MonteCarloData {
    private double xBegin, xEnd, yBegin, yEnd, sum;
    private long noOfPoints;


    public long getNoOfPoints() {
        return noOfPoints;
    }

    public void setNoOfPoints(long noOfPoints) {
        this.noOfPoints = noOfPoints;
    }

    public double getyEnd() {
        return yEnd;
    }

    public void setyEnd(double yEnd) {
        this.yEnd = yEnd;
    }

    public double getyBegin() {
        return yBegin;
    }

    public void setyBegin(double yBegin) {
        this.yBegin = yBegin;
    }

    public double getxEnd() {
        return xEnd;
    }

    public void setxEnd(double xEnd) {
        this.xEnd = xEnd;
    }

    public double getxBegin() {
        return xBegin;
    }

    public void setxBegin(double xBegin) {
        this.xBegin = xBegin;
    }

    public MonteCarloData(){
        this.xBegin = -8.;
        this.xEnd = 8.;
        this.yBegin = -8.;
        this.yEnd = 8;

        this.noOfPoints = 10000;

        this.sum = 0;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public void addToSum(double x){
        sum += x;
    }
}
