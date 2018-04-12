package log;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.briup.util.Configuration;

import util.ConfigurationImpl;

public class LoggerTest {
		private Logger logger;
		public LoggerTest() {
			logger = Logger.getLogger("LoggerTest.class");
			PropertyConfigurator.configure("src/log4j.properties");
			logger.info("dasdas");

		}
		public void init(Properties arg0) {
			// TODO Auto-generated method stub
			
		}

		
		public void debug(String s) {
			// TODO Auto-generated method stub
			logger.debug(s);
		}

		
		public void error(String s) {
			// TODO Auto-generated method stub
			logger.error(s);
		}

		
		public void fatal(String s) {
			// TODO Auto-generated method stub
			logger.fatal(s);
		}

		
		public void info(String s) {
			// TODO Auto-generated method stub
			logger.info(s);
		}

		
		public void warn(String s) {
			// TODO Auto-generated method stub
			logger.warn(s);
		}


	
		
}
