package log;

import java.util.Properties;

import com.briup.util.Configuration;
import com.briup.util.Logger;
import com.briup.woss.ConfigurationAWare;

import util.ConfigurationImpl;

public class LoggerImp implements Logger,ConfigurationAWare{
	
	org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(LoggerImp.class);
	private ConfigurationImpl con;
	@Override
	public void init(Properties arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void debug(String s) {
		// TODO Auto-generated method stub
		logger.debug(s);
	}

	@Override
	public void error(String s) {
		// TODO Auto-generated method stub
		logger.error(s);
	}

	@Override
	public void fatal(String s) {
		// TODO Auto-generated method stub
		logger.fatal(s);
	}

	@Override
	public void info(String s) {
		// TODO Auto-generated method stub
		logger.info(s);
	}

	@Override
	public void warn(String s) {
		// TODO Auto-generated method stub
		logger.warn(s);
	}

	@Override
	public void setConfiguration(Configuration config) {
		// TODO Auto-generated method stub
		con = (ConfigurationImpl) config;
	}

}
