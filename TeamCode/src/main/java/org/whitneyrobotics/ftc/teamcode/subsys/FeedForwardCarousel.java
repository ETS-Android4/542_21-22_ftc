package org.whitneyrobotics.ftc.teamcode.subsys;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.whitneyrobotics.ftc.teamcode.framework.PIDSubsystem;
import org.whitneyrobotics.ftc.teamcode.lib.control.PIDFController;
import org.whitneyrobotics.ftc.teamcode.lib.util.Functions;
import org.whitneyrobotics.ftc.teamcode.lib.util.RobotConstants;
import org.whitneyrobotics.ftc.teamcode.lib.util.SimpleTimer;
import org.whitneyrobotics.ftc.teamcode.lib.util.Toggler;

public class FeedForwardCarousel implements PIDSubsystem {
    public final DcMotorEx wheel;
    public double currentVelocityDebug;
    public double targetVelocityDebug;
    public double errorDebug;
    public Alliance alliance;
    public boolean carouselInProgress;

    //carousel speedup time
    public double SPEEDUP_TIME = 0.8;
    public double END_TIME = 1.2;
    //custom class definitions
    private PIDFController carouselController;
    private final Toggler allianceTog = new Toggler(2);
    private final Toggler powerTog = new Toggler(2);
    private final SimpleTimer carouselTimer = new SimpleTimer();
    private int carouselState;

    /**
     * */
    public double[] targetRPM = {0,217.5,260}; //find experimentally

    @Override
    public void reloadConstants() {
        carouselController = new PIDFController(RobotConstants.CAROUSEL_CONSTANTS);
    }

    @Override
    public void resetEncoders(){
        wheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        wheel.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public enum CarouselStage {
        OFF, SLOW, FAST
    }

    public enum Alliance {
        RED, BLUE
    }

    public FeedForwardCarousel(HardwareMap hardwareMap){
        wheel = hardwareMap.get(DcMotorEx.class,"carouselMotor");
        wheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        wheel.setDirection(DcMotorSimple.Direction.REVERSE);
        carouselController = new PIDFController(RobotConstants.CAROUSEL_CONSTANTS);
    }

    public void changeAlliance(boolean changeAlliance){
        allianceTog.changeState(changeAlliance);
    }


    /**
     * Sets the initial alliance and overrides the toggler.
     * @param alliance The Alliance to use
     * @see Alliance
     * */
    public void setAlliance(Alliance alliance){
        this.alliance = alliance;
        allianceTog.setState(alliance.ordinal());
    }

    /**
     * Returns whether or not the carousel is in progress
     * @return Whether or not the carousel is in progress
     * */
    public boolean carouselInProgress(){
        return carouselInProgress;
    }
    /**
     * Operates the carousel with the speed specified by the specific CarouselStage enum.
     * @param carouselStage The specific stage of the carousel operation
     * */
    private void operateCarousel(CarouselStage carouselStage){
        //absolute valued current and target to ignore wheel direction
        double currentVelocity = Math.abs(wheel.getVelocity(AngleUnit.DEGREES)/6); //rpm
        currentVelocityDebug = currentVelocity;
        double targetVelocity = Math.abs(targetRPM[carouselStage.ordinal()]);
        targetVelocityDebug = targetVelocity;
        double error = targetVelocity - currentVelocity;
        errorDebug = error;
        carouselController.calculate(error,targetVelocity,69.420); //placeholder for PIDF lambda
        double power = Functions.map(carouselController.getOutput(),0,RobotConstants.carouselMaxRPM,0,1);
        operateWheel(power);
    }

    public void operateWheel(double power){
        wheel.setPower(power * (allianceTog.currentState())==1 ? -1 : 1);
    }

    /**
     * Code for both autonomous carousel operation and driver-controlled
     * This will automate the carousel process, and due to it's stateful nature, it will <u>not</u> cause conflict with timer-controlled and toggler operation.
     * @param toggleOnOff Command to listen for for start/stop. If passed with autoStop = true, this will turn the carousel intoa  timer-based operation. Pass in true for autonomous operation.
     * @param autoStop Option for if you want carousel to stop automatically. Pass in false for toggler, and true for Autonomous and timer-controlled.
     * */
    public void operate(boolean toggleOnOff, boolean autoStop){
        powerTog.changeState(toggleOnOff);
        switch(carouselState){
            case 0:
                carouselTimer.set(END_TIME);
                if(powerTog.currentState() == 1){
                    carouselState++;
                    carouselInProgress = true;
                }
                break;
            case 1:
                if((carouselTimer.isExpired() && autoStop) || powerTog.currentState() == 0){
                    carouselState++;
                    break;
                }
                if(carouselTimer.getTimeElapsed()<=SPEEDUP_TIME){
                    operateCarousel(CarouselStage.SLOW);
                } else {
                    operateCarousel(CarouselStage.FAST);
                }
                break;
            case 2:
                powerTog.setState(0); //remove any conflicts with manual stop and auto stop
                wheel.setPower(0.0);
                carouselState = 0;
                carouselInProgress = false;
                break;
            default:
                carouselState = 0;
                break;
        }
    }
}
