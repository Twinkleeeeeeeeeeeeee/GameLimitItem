package util;

import java.awt.geom.Path2D;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.ConnectException;
import java.util.Properties;

import com.briup.util.BackUP;
import com.briup.util.Configuration;
import com.briup.woss.ConfigurationAWare;



public class BackUpImp implements BackUP,ConfigurationAWare{
	private String path;//= "E:\\workspace\\BD17015_Woss\\file";
	ConfigurationImpl conn = null;
	@Override
	public void init(Properties pro) {
		// TODO Auto-generated method stub
		path =pro.getProperty("back-temp");
	}

	@Override
	public Object load(String fileName, boolean isRemove) throws Exception {
		// TODO Auto-generated method stub
		File file = new File(path,fileName);
		Object obj = null;
		if(file.exists() && file.canRead()) {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
			obj = ois.readObject();
			ois.close();
		}
		if(isRemove) {
			file.delete();
		}
		return obj;
	}

	@Override
	public void store(String fileName, Object obj, boolean bool) throws Exception {
		// TODO Auto-generated method stub
		File file = new File(path,fileName);
		
		if(!file.exists()) {
			System.out.println("文件不存在!正在创建备份文件...");
			file.createNewFile();
			System.out.println("创建备份文件完成！");
		}
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file,bool));
		oos.writeObject(obj);
		oos.flush();
	}

	@Override
	public void setConfiguration(Configuration config) {
		// TODO Auto-generated method stub
		conn = (ConfigurationImpl) config;
	}


}
