package woss.server;



import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collection;
import java.util.Properties;

import base.BIDR;
import base.WossModel;
import common.ConfigurationAware;
import util.ConfigurationImpl;

public class Sever implements ConfigurationAware,WossModel{
	ServerSocket server = null;
	ConfigurationImpl con;
	@Override
	public void init(Properties pro) {
		// TODO Auto-generated method stub
		
	}
	public Collection<BIDR> revicer() throws Exception {
		// TODO Auto-generated method stub
		server = new ServerSocket(9999);
		Socket socket = server.accept();
		InputStream is = socket.getInputStream();
		ObjectInputStream ois = new ObjectInputStream(is);
		Object readObject = ois.readObject();
		ois.close();
		return (Collection<BIDR>)readObject;
	}
	@Override
	public void setConfiguration(ConfigurationAware config) {
		// TODO Auto-generated method stub
		
	}

}
