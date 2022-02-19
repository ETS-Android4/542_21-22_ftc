package org.whitneyrobotics.ftc.teamcode.framework;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Unified class extension for creating FtcDashboard instances without having to repeat code
 */
public abstract class DashboardOpMode extends OpMode {
    /**
     * FtcDashboard instance
     */
    protected FtcDashboard dashboard;
    /**
     * Telemetry instance to transmit packets to
     */
    protected Telemetry dashboardTelemetry;
    /**
     * Packet to add graphics to display on Dashboard
     */
    protected TelemetryPacket packet = new TelemetryPacket();

    /**
     * Reassign telemetry to the output of this function and creates a paired telemetry stream
     * @param msTransmissionInterval How often to transmit new packets
     * @return Paired telemetry stream that displays on FTC Driver Station
     * @see Telemetry
     */
    protected Telemetry initializeDashboard(int msTransmissionInterval){
        dashboard = FtcDashboard.getInstance();
        dashboardTelemetry = dashboard.getTelemetry();
        Telemetry telemetryStream = new MultipleTelemetry(telemetry, dashboardTelemetry);
        telemetryStream.setMsTransmissionInterval(msTransmissionInterval);
        telemetry = telemetryStream;
        return telemetryStream;
    }

    protected void refreshDashboardPacket(){
        dashboard.sendTelemetryPacket(packet);
    }

}
