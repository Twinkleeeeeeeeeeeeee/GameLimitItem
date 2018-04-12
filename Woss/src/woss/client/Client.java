package woss.client;

import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Collection;
import java.util.Properties;

import com.briup.util.BIDR;
import com.briup.woss.WossModule;

import common.ConfigurationAware;
import log.LoggerImp;
import util.ConfigurationImpl;

public class Client implements WossModule,ConfigurationAware{
	Socket socket = null;
	ConfigurationImpl con;
	private String  ip;
	private Integer port;
	@Override
	public void init(Properties arg0) {
		// TODO Auto-generated method stub
		
	}
	public void send(Collection<BIDR> obj) throws Exception {
		// TODO Auto-generated method stub
		LoggerImp lImp = new LoggerImp();
		socket = new Socket(ip, port);
		OutputStream os = socket.getOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(os);
		lImp.info("客户端准备发送数据:");
		oos.writeObject(obj);
		oos.flush();
		oos.close();
	}
	@Override
	public void setConfiguration(ConfigurationAware config) {
		// TODO Auto-generated method stub
		
	}
	
}
