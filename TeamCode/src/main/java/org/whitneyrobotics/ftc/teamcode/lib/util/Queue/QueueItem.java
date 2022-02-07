package org.whitneyrobotics.ftc.teamcode.lib.util.Queue;

import android.os.Build;

import androidx.annotation.RequiresApi;

import org.whitneyrobotics.ftc.teamcode.subsys.WHSRobotImpl;

import java.util.function.*;

@RequiresApi(api = Build.VERSION_CODES.N)
public class QueueItem {

    public enum ProcessMode {
        LINEAR, ITERATIVE //use Linear if OpMode, Iterative if LinearOpMode
    }

    public boolean async = false;
    public WHSRobotImpl robot;
    private boolean processed = false;
    public ProcessMode processMode = ProcessMode.LINEAR;

    public RobotAction action;
    public Supplier exitCondition;

    public QueueItem(RobotAction action, Supplier exitCondition, boolean async) {
        this.action = action;
        this.exitCondition = exitCondition;
        this.async = async;
    }

    public QueueItem setMode(ProcessMode mode){
        this.processMode = mode;
        return this;
    }

    public boolean process(){
        switch(processMode){
            case LINEAR:
                if(!processed){
                    action.invoke();
                }
                processed = (boolean)exitCondition.get();
                break;
            case ITERATIVE:
                do {
                    action.invoke();
                    processed = (boolean)exitCondition.get();
                } while(!processed);
                break;
            default:
                throw new IllegalArgumentException("Specified Process Mode does not exist for QueueItem.ProcessMode enum");
        }
        return processed;
    }

    public boolean isAlive(){return !processed;}

    public boolean isProcessed(){return processed;}
}
