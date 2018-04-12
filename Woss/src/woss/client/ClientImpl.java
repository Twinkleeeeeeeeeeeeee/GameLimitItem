package woss.client;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Collection;
import java.util.Properties;

import com.briup.util.BIDR;
import com.briup.util.Configuration;
import com.briup.woss.ConfigurationAWare;
import com.briup.woss.client.Client;

import log.LoggerImp;
import util.ConfigurationImpl;

public class ClientImpl implements Client,ConfigurationAWare{
	Socket socket = null;
	ConfigurationImpl con;
	private String  ip;
	private Integer port;
	@Override
	public void init(Properties pro) {
		// TODO Auto-generated method stub
		ip = pro.getProperty("server_ip");
		port = Integer.parseInt(pro.getProperty("server_port"));
	}

	@Override
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
	public void setConfiguration(Configuration config) {
		// TODO Auto-generated method stub
		con = (ConfigurationImpl) config;
	}
	
}
