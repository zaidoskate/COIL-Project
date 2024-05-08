/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tools;

import org.apache.log4j.Level;
import org.apache.log4j.Priority;
import org.apache.log4j.spi.LoggingEvent;
import org.apache.log4j.spi.TriggeringEventEvaluator;

/**
 *
 * @author chuch
 */
public class LogValidation implements TriggeringEventEvaluator {

    @Override
    public boolean isTriggeringEvent(LoggingEvent logEvent) {
        
        return true;
    }
    
}
