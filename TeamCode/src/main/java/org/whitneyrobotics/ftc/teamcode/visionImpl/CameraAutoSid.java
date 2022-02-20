package org.whitneyrobotics.ftc.teamcode.visionImpl;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.hardware.camera.controls.ExposureControl;
import org.firstinspires.ftc.robotcore.external.hardware.camera.controls.FocusControl;
import org.firstinspires.ftc.robotcore.external.hardware.camera.controls.GainControl;
import org.firstinspires.ftc.robotcore.external.hardware.camera.controls.PtzControl;
import org.firstinspires.ftc.robotcore.external.stream.CameraStreamServer;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvWebcam;
import org.whitneyrobotics.ftc.teamcode.framework.DashboardOpMode;
import org.whitneyrobotics.ftc.teamcode.lib.util.DataNotFoundException;
import org.whitneyrobotics.ftc.teamcode.lib.util.DataToolsLite;

@TeleOp(name="Vision Test", group="New Tests")
public class CameraAutoSid extends DashboardOpMode {
    OpenCvWebcam webcam;
    ScannerCamSid scanner = new ScannerCamSid(1920,1080);
    int timeout = 2500;
    ExposureControl ec;
    FocusControl fc;
    PtzControl panTiltZoom;
    GainControl gain;
    double scanLevel = 2;

    @Override
    public void init() {
        try {
            String[] configData = DataToolsLite.decode("visionConfig.txt");
            String[] resolution = configData[0].split("x");
            scanner = new ScannerCamSid(Integer.parseInt(resolution[0]),Integer.parseInt(resolution[1]));
            timeout = Integer.parseInt(configData[1]);
        } catch (Exception e){
            telemetry.addLine("Failed to read data. Reverted camera resolution and timeout back to default.");
        }
        initializeDashboardTelemetry(50);
        //int cameraMoniterViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMoniterViewId");
        webcam = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "pycam"));

        webcam.setPipeline(scanner);

        webcam.setMillisecondsPermissionTimeout(2500);

        webcam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            @Override
            public void onOpened() {
                webcam.startStreaming(1920, 1080, OpenCvCameraRotation.UPRIGHT);
                ec = webcam.getExposureControl();
                fc = webcam.getFocusControl();
                panTiltZoom = webcam.getPtzControl();
                gain = webcam.getGainControl();
                dashboard.startCameraStream(webcam, webcam.getCurrentPipelineMaxFps());
                startDriverStationWebcamStream(webcam);
            }

            @Override
            public void onError(int errorCode) {
                telemetry.addLine(String.format("OpenCV Pipeline failed to open with error code %d",errorCode));
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
        telemetry.addData("scanned in initialization",scanLevel);
        telemetry.addData("Result",scanner.getResult());
    }
}
