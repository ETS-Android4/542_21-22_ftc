package org.whitneyrobotics.ftc.teamcode.lib.util.Queue;

//Needed a take none make none Function interface, (Consumers don't work), so I made my own
@FunctionalInterface
public interface RobotAction {
    void invoke ();
}
