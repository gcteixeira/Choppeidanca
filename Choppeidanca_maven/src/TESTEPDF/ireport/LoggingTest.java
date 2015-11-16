package TESTEPDF.ireport;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggingTest {  
    static Logger logger = Logger.getLogger(LoggingTest.class);  
  
    
    public static void main(String[] args) {
		
    	BasicConfigurator.configure();  
    	
    	logger.setLevel(Level.INFO);
    	logger.debug("Isso nao vai aparecer..");
    	logger.info("Inicializando..");
    	
    	 try {  
             throw new Exception("Loga esse, Log4J!");  
         } catch (Exception e) {  
             logger.error("Oops, deu erro: ?" + e.getMessage());  
         }  
   
         logger.info("Finalizando...");  
    	
//    	logger.setLevel(Level.INFO);  
//        logger.debug(?Isso nao vai aparecer...?);  
//        logger.info(?Inicializando...?);  
    	
	}
    
}  
