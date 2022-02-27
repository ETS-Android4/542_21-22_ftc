package org.whitneyrobotics.ftc.teamcode.visionImpl;

import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvWebcam;
import org.whitneyrobotics.ftc.teamcode.framework.DashboardOpMode;
import org.whitneyrobotics.ftc.teamcode.visionImpl.ScannerCamSid;
@TeleOp(name="Vision Test", group="New Tests")
public class CameraAutoSid extends DashboardOpMode {
    OpenCvWebcam webcam;
    ScannerCamSid scanner = new ScannerCamSid();
    double scanLevel = 2;

    @Override
    public void init() {
        initializeDashboardTelemetry(50);
        //int cameraMoniterViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMoniterViewId");
        webcam = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "pycam"));

        webcam.setPipeline(scanner);

        webcam.setMillisecondsPermissionTimeout(2500);

        webcam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            @Override
            public void onOpened() {

                webcam.startStreaming(320, 240, OpenCvCameraRotation.UPRIGHT);
                startDriverStationWebcamStream(webcam);
<<<<<<< HEAD
                FtcDashboard.getInstance().startCameraStream(webcam, webcam.getCurrentPipelineMaxFps());
=======
                dashboard.startCameraStream(webcam, webcam.getCurrentPipelineMaxFps());
>>>>>>> master
            }

            @Override
            public void onError(int errorCode) {
                telemetry.addLine(String.valueOf(errorCode));
            }
        });
    }

    @Override
    public void init_loop(){
        ScannerCamSid.Barcode result = scanner.getResult();
        if(result != null){
            scanLevel = result.ordinal();
        }
    }

    @Override
    public void loop() {
<<<<<<< HEAD
        telemetry.addData("scanned in init",scanLevel);
        telemetry.addData("detecting",(scanner.getResult()!=null?scanner.getResult().name():"none"));
=======
        telemetry.addData("scanned",scanLevel);
        telemetry.addData("Currently seeing",scanner.getResult().name());
>>>>>>> master

    }
}