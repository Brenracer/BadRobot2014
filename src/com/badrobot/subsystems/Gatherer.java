/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.badrobot.subsystems;

import com.badrobot.RobotMap;
import com.badrobot.commands.GatherBall;
import com.badrobot.subsystems.interfaces.IGatherer;
import edu.wpi.first.wpilibj.Relay;

/**
 *
 * @author Isaac
 */
public class Gatherer extends BadSubsystem implements IGatherer
{
    public static Gatherer instance;
    // is the gatherer on and forward
    public static boolean gathererOn, gathererForward;
    
    // main system of gatherer
    Relay gathererSwitch;

    /**
     * Gets the current instance of Gatherer.
     * If one doesn't exist, make one.
     * @return the current instance of Gatherer
     */
    public static Gatherer getInstance()
    {
        if (instance == null)
        {
            instance = new Gatherer();
        }
        return instance;
    }
    
    private Gatherer()
    {
    }
    
    /**
     * Initializes the instance variables.
     * Done here because this can be called twice.
     */
    protected void initialize() 
    {
        if (!RobotMap.isPrototype)
        {
            //final robot code goes here
        }
        else
        {
            gathererOn = false;
            gathererSwitch = new Relay(RobotMap.gathererMotorRelay);
        }
    }
    
    /**
     * Returns the console identity, which is
     * generally the class name.
     * @return the class name
     */
    public String getConsoleIdentity() 
    {
        return "Gatherer";
    }

    protected void initDefaultCommand() 
    {
        this.setDefaultCommand(new GatherBall());
    }
    
    /**
     * Controls the gatherer's gathering system.
     * @param on - should the gatherer be on?
     * @param forward - should the gatherer be moving
     * forward or backward?
     */
    public void gatherBall(boolean on, boolean forward)
    {
        if (forward && !gathererForward)
        {
            gathererSwitch.setDirection(Relay.Direction.kForward);
            gathererForward = true;
        }
        else if (!forward && gathererForward)
        {
            gathererSwitch.setDirection(Relay.Direction.kReverse);
            gathererForward = false;
        }
        
        if (on && !gathererOn)
        {
            gathererSwitch.set(Relay.Value.kOn);
            gathererOn = true;
        }
        else if (!on && gathererOn)
        {
            gathererSwitch.set(Relay.Value.kOff);
            gathererOn = false;
        }
    }
}
