package main;

import java.util.Collection;

import com.briup.util.BIDR;
import com.briup.util.Logger;

import log.LoggerImp;
import util.ConfigurationImpl;
import woss.client.ClientImpl;
import woss.client.GatherImpl;

public class ClientMain {
	public static void main(String[] args) throws Exception {
		ConfigurationImpl con = new ConfigurationImpl();
		GatherImpl list = (GatherImpl) con.getGather();
		ClientImpl client = (ClientImpl) con.getClient();
		LoggerImp lImp = (LoggerImp) con.getLogger(); 
		try {
			Collection<BIDR> data = list.gather();
			client.send(data);
			lImp.info("导入数据库成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
