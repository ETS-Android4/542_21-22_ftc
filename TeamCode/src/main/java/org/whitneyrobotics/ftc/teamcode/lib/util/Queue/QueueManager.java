package org.whitneyrobotics.ftc.teamcode.lib.util.Queue;

import android.os.Build;

import androidx.annotation.RequiresApi;

import org.checkerframework.checker.units.qual.A;

import java.util.LinkedList;
@RequiresApi(api = Build.VERSION_CODES.N)
public class QueueManager {
    public static LinkedList<QueueItem> queue = new LinkedList<>();
    public static void add(QueueItem task){
        if(queue.size() > 0){
            if(queue.get(queue.size()-1) != task){
                queue.add(task);
            }
        }
    }

    public static void processQueue(){
        if(queue.size() > 0){
            LinkedList<QueueItem> processSubset = new LinkedList<>();
            processSubset.add(queue.get(0));
            if(processSubset.get(0).async){
                for(int i = 1; i<queue.size(); i++){
                    if(queue.get(i).async){
                        processSubset.add(queue.get(i));
                    } else {
                        break;
                    }
                }
            }
            for(QueueItem task : processSubset){
                task.process();
            }
        }
         cleanQueue();
    }

    public static void cleanQueue(){
        for(QueueItem task : queue){
            if(!task.isAlive()){
                queue.remove(task);
            }
        }
    }

    public static int queueSize(){
        return queue.size();
    }

    public static boolean queueEmpty(){
        return queue.isEmpty();
    }
}