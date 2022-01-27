package org.whitneyrobotics.ftc.teamcode.autoop;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.whitneyrobotics.ftc.teamcode.lib.geometry.Position;
import org.whitneyrobotics.ftc.teamcode.lib.util.Queue.QueueItem;
import org.whitneyrobotics.ftc.teamcode.lib.util.Queue.QueueManager;
import org.whitneyrobotics.ftc.teamcode.subsys.WHSRobotImpl;

@RequiresApi(api = Build.VERSION_CODES.N)
public class QueueManagerTest extends OpMode {
    WHSRobotImpl robot;
    private int autoState = 0;
    Position carousel = new Position(-1000,1000);
    QueueItem driveToCarousel;

    @Override
    public void init() {
        robot = new WHSRobotImpl(hardwareMap);
        Position carousel = new Position(-1000,1000);
        //QueueItem driveToCarousel = new QueueItem(() -> robot.driveToTarget(carousel,false),() -> robot.driveToTargetInProgress(),false);

    }

    @Override
    public void loop() {
        QueueManager.processQueue();
        QueueManager.cleanQueue();
        switch(autoState){
            case 0:
                driveToCarousel = new QueueItem(() -> robot.driveToTarget(carousel,false),() -> robot.driveToTargetInProgress(),true);
                driveToCarousel.setMode(QueueItem.ProcessMode.LINEAR);
                autoState++;
                break;
            case 1:
                QueueManager.add(driveToCarousel);
                if(QueueManager.queueEmpty()){
                    autoState++;
                }
                break;
            case 2:
                break;
        }
    }
}
