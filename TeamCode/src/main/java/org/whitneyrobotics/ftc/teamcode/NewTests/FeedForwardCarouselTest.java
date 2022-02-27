package org.whitneyrobotics.ftc.teamcode.NewTests;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.whitneyrobotics.ftc.teamcode.framework.DashboardOpMode;
import org.whitneyrobotics.ftc.teamcode.lib.util.Timer;
import org.whitneyrobotics.ftc.teamcode.lib.util.Toggler;
import org.whitneyrobotics.ftc.teamcode.subsys.FeedForwardCarousel;

@TeleOp(name="FeedForwardCarousel Test",group="New Tests")
public class FeedForwardCarouselTest extends DashboardOpMode {

    private FeedForwardCarousel carousel;
    private Toggler autoEndCarousel = new Toggler(2);

    @Override
    public void init() {
        initializeDashboardTelemetry(50);
        carousel = new FeedForwardCarousel(hardwareMap);
    }

    @Override
    public void loop() {
        if (gamepad2.back) {
            requestOpModeStop();
        }

        carousel.changeAlliance(gamepad2.y);
        if (gamepad2.x) carousel.reloadConstants();

        carousel.operate(gamepad2.left_bumper||gamepad2.left_trigger>0.01,gamepad1.left_bumper);

        telemetry.addData("Carousel in progress", carousel.carouselInProgress());

        telemetry.addData("Error",carousel.errorDebug);
        telemetry.addData("Target",carousel.targetVelocityDebug);
        telemetry.addData("Current",carousel.wheel.getVelocity());
        telemetry.addData("PID Controller Output",carousel.getPIDFOutput());
        telemetry.addData("Motor power",carousel.wheel.getPower());
        //refreshDashboardPacket();
    }
}
