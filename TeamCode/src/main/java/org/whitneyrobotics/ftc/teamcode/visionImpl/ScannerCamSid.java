package org.whitneyrobotics.ftc.teamcode.visionImpl;


import org.opencv.core.Rect;
import org.opencv.core.Point;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

public class ScannerCamSid extends OpenCvPipeline {

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
    Rect leftROI = new Rect(new Point(0,0), new Point(320 / 3.0, 240));
    Rect midROI = new Rect(new Point(320/3.0,0), new Point(2 * (320 / 3.0), 240));
    Rect rightROI = new Rect(new Point(2 * (320 / 3.0),0), new Point(320, 240));

    // Could pass in telemetry into constructor for use in class.

    private Barcode result;
    @Override
    public Mat processFrame(Mat input) {
        Imgproc.cvtColor(input, input, Imgproc.COLOR_RGBA2RGB);
        Imgproc.cvtColor(input, mat, Imgproc.COLOR_RGB2HSV);
        // CHANGE THESE ACCORDINGLY
        // Division by two since only 180 degrees allowed
        int[] color = {95, 85, 61};
        Scalar upperBound = new Scalar(color[0] / 2.0 + 30, color[1] + 30, color[2] + 30);
        Scalar lowerBound = new Scalar(color[0] / 2.0 - 30, color[1] - 30, color[2] - 30);
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
            result = Barcode.LEFT;
            Imgproc.rectangle(input, leftROI, matchColor, 3);
            Imgproc.rectangle(input, midROI, mismatchColor, 3);
            Imgproc.rectangle(input, rightROI, mismatchColor, 3);
        } else if (maxValue == midValue) {
            result = Barcode.MIDDLE;
            Imgproc.rectangle(input, leftROI, mismatchColor, 3);
            Imgproc.rectangle(input, midROI, matchColor, 3);
            Imgproc.rectangle(input, rightROI, mismatchColor, 3);
        } else if (maxValue == rightValue) {
            result = Barcode.RIGHT;
            Imgproc.rectangle(input, leftROI, mismatchColor, 3);
            Imgproc.rectangle(input, midROI, mismatchColor, 3);
            Imgproc.rectangle(input, rightROI, matchColor, 3);
        } else {
            result = Barcode.LEFT;
            Imgproc.rectangle(input, leftROI, mismatchColor, 3);
            Imgproc.rectangle(input, midROI, mismatchColor, 3);
            Imgproc.rectangle(input, rightROI, mismatchColor, 3);
        }

        return input;
    }

    public Barcode getResult() {
        // CountDownLatch could be used?
        return result;
    }
}