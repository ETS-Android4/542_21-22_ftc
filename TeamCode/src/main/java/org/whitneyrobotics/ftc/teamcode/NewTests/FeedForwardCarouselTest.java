package org.whitneyrobotics.ftc.teamcode.NewTests;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.whitneyrobotics.ftc.teamcode.framework.DashboardOpMode;
import org.whitneyrobotics.ftc.teamcode.lib.util.Toggler;
import org.whitneyrobotics.ftc.teamcode.subsys.FeedForwardCarousel;

@TeleOp(name="FeedForwardCarousel Test",group="New Tests")
public class FeedForwardCarouselTest extends DashboardOpMode {

    private FeedForwardCarousel carousel;
    private Toggler autoEndCarousel = new Toggler(2);

    @Override
    public void init() {
        initializeDashboard(25);
        carousel = new FeedForwardCarousel(hardwareMap);
    }

    @Override
    public void loop() {
        carousel.changeAlliance(gamepad2.y);
        autoEndCarousel.changeState(gamepad2.left_trigger>0.01);
        if(gamepad2.x){ carousel.reloadConstants(); }
        if(gamepad2.left_bumper){ autoEndCarousel.setState(0); }

        carousel.operate(gamepad2.left_bumper || gamepad2.left_trigger>0.01, autoEndCarousel.currentState() == 1);

        telemetry.addData("Carousel in progress", carousel.carouselInProgress());

        telemetry.addData("Error",carousel.errorDebug);
        telemetry.addData("Target",carousel.targetVelocityDebug);
        refreshDashboardPacket();
    }

}
