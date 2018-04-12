package woss.server;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collection;
import java.util.Properties;

import com.briup.util.BIDR;
import com.briup.util.Configuration;
import com.briup.woss.ConfigurationAWare;
import com.briup.woss.server.Server;

import util.ConfigurationImpl;

public class ServerImpl implements Server,ConfigurationAWare{
	ServerSocket server = null;
	ConfigurationImpl con;
	@Override
	public void init(Properties arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
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
	public void shutdown() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setConfiguration(Configuration config) {
		// TODO Auto-generated method stub
		con = (ConfigurationImpl) config;
	}

}
