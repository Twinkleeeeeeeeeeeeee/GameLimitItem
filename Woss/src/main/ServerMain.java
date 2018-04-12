package main;

import java.util.Collection;

import com.briup.util.BIDR;

import log.LoggerImp;
import util.ConfigurationImpl;
import woss.server.DBImpl;
import woss.server.ServerImpl;

public class ServerMain {
	public static void main(String[] args) throws Exception {
		ConfigurationImpl con = new ConfigurationImpl();
		ServerImpl sl = (ServerImpl) con.getServer();
		DBImpl db = (DBImpl) con.getDBStore(); 
		LoggerImp llmp = (LoggerImp) con.getLogger();
		try {
			Collection<BIDR> recive = sl.revicer();
			llmp.info("���ݽ��ճɹ�");
			llmp.info("���ڴ������ݿ�");
			db.saveToDB(recive);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
