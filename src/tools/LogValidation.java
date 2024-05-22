package tools;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggingEvent;
import org.apache.log4j.spi.TriggeringEventEvaluator;

public class LogValidation implements TriggeringEventEvaluator {
    private static final Logger LOG = Logger.getLogger(LogValidation.class);
    
    public boolean getConnection() throws IOException {
        URL url;
        url = new URL("https://www.google.com");
        try{
            HttpURLConnection connectionURL = (HttpURLConnection) url.openConnection();
            connectionURL.connect();
            return connectionURL.getResponseCode() == 200;
        } catch(UnknownHostException unknownHostException){
            LOG.warn("No hay conexion a internet", unknownHostException);
            return false;
        }
    }

    @Override
    public boolean isTriggeringEvent(LoggingEvent logEvent) {
        if(!logEvent.getLevel().isGreaterOrEqual(Level.ERROR)) {
            return false;
        }
        try{
            if(getConnection()) {
                return true;
            }
        } catch(IOException ioexception) {
            LOG.warn("No hay conexion a internet", ioexception);
        }
        return false;
    }
    
}
