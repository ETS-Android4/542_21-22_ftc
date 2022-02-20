package org.whitneyrobotics.ftc.teamcode.visionImpl;


import org.opencv.core.Rect;
import org.opencv.core.Point;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

public class ScannerCamSid extends OpenCvPipeline {

    public double width;
    public double height;

    public ScannerCamSid(double width, double height){
        this.width = width;
        this.height = height;
    }

    public enum Barcode {
        LEFT,
        MIDDLE,
        RIGHT
    }


    // Mat is color matrix
    Mat mat = new Mat();
    Mat leftMat, midMat, rightMat = new Mat();
    /* coordinate system
    origin ---------------> x+
    |
    |
    |
    |
    |
    v
    y+
     */
    Rect leftROI = new Rect(new Point(0,0), new Point(width / 3.0, height));
    Rect midROI = new Rect(new Point(width/3.0,0), new Point(2 * (width / 3.0), height));
    Rect rightROI = new Rect(new Point(2 * (width / 3.0),0), new Point(width, height));

    // Could pass in telemetry into constructor for use in class.

    private Barcode result;
    @Override
    public Mat processFrame(Mat input) {
        Imgproc.cvtColor(input, input, Imgproc.COLOR_RGBA2RGB);
        Imgproc.cvtColor(input, mat, Imgproc.COLOR_RGB2HSV);
        // CHANGE THESE ACCORDINGLY
        // Division by two since only 180 degrees allowed
        Scalar upperBound = new Scalar(20 / 2.0, 110, 65);
        Scalar lowerBound = new Scalar(0, 75, 45);
        Core.inRange(mat, lowerBound, upperBound, mat);

        leftMat = mat.submat(leftROI);
        midMat = mat.submat(midROI);
        rightMat = mat.submat(rightROI);

        double leftValue = Core.sumElems(leftMat).val[0];
        double midValue = Core.sumElems(midMat).val[0];
        double rightValue = Core.sumElems(rightMat).val[0];

        double maxValue = Math.max(leftValue, Math.max(midValue, rightValue));
        Scalar matchColor = new Scalar(0,255,0);
        Scalar mismatchColor = new Scalar(255,0,0);

        if (maxValue == leftValue) {
            //result = Barcode.LEFT;
            Imgproc.rectangle(input, leftROI, matchColor,3);
            Imgproc.rectangle(input, midROI, mismatchColor,3);
            Imgproc.rectangle(input, rightROI, mismatchColor,3);
        } else if (maxValue == midValue) {
            //result = Barcode.MIDDLE;
            Imgproc.rectangle(input, leftROI, mismatchColor,3);
            Imgproc.rectangle(input, midROI, matchColor,3);
            Imgproc.rectangle(input, rightROI, mismatchColor,3);
        } else if (maxValue == rightValue) {
            //result = Barcode.RIGHT;
            Imgproc.rectangle(input, leftROI, mismatchColor,3);
            Imgproc.rectangle(input, midROI, mismatchColor,3);
            Imgproc.rectangle(input, rightROI, matchColor,3);
        } else {
            Imgproc.rectangle(input, leftROI, mismatchColor);
            Imgproc.rectangle(input, midROI, mismatchColor);
            Imgproc.rectangle(input, rightROI, mismatchColor);
        }
        Imgproc.putText(input,String.format("Max Value: %d", maxValue),new Point(10,10),0,12,new Scalar(58,100,42),4);

        return input;
    }

    public Barcode getResult() {
        // CountDownLatch could be used?
        return result;
    }
}
