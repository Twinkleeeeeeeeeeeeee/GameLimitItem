package util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.briup.util.BackUP;
import com.briup.util.Configuration;
import com.briup.util.Logger;
import com.briup.woss.ConfigurationAWare;
import com.briup.woss.WossModule;
import com.briup.woss.client.Client;
import com.briup.woss.client.Gather;
import com.briup.woss.server.DBStore;
import com.briup.woss.server.Server;

public class ConfigurationImpl implements Configuration{
	Map<String, WossModule> map = new HashMap<String, WossModule>(); 
	public  ConfigurationImpl() {
		this("E:\\workspace\\BD17015_Woss\\src\\conf.xml");
	}
	public ConfigurationImpl(String filname) {
		List<Element> list = new ArrayList<Element>();
		SAXReader saxReader = new SAXReader();
		Properties pro = new Properties();
		String path = filname;
		try {
			Document document =saxReader.read(new File(filname));
			Element element = document.getRootElement();
			list = element.elements();
			for(Element e1:list) {
				String cName = e1.getName();
				String pName = e1.attributeValue("class");
				//System.out.println(cName+":"+pName);
				WossModule woss = (WossModule)Class.forName(pName).newInstance();
				map.put(cName,woss);
				List<Element> list2 = e1.elements();
				for(Element e2:list2) {
					String cName1 = e2.getName();
					String text = e2.getText();
					pro.put(cName1, text);
					//System.out.println(cName1+":"+text);
				}
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(String s:map.keySet()) {
			WossModule w = map.get(s);
			if(w instanceof WossModule) {
				((WossModule)w).init(pro);
			}
			if(w instanceof WossModule) {
				((ConfigurationAWare)w).setConfiguration(this);
			}
		}
		
	}
	@Override
	public BackUP getBackup() throws Exception {
		// TODO Auto-generated method stub
		return (BackUP) map.get("backup");
	}

	@Override
	public Client getClient() throws Exception {
		// TODO Auto-generated method stub
		return (Client) map.get("client");
	}

	@Override
	public DBStore getDBStore() throws Exception {
		// TODO Auto-generated method stub
		return (DBStore) map.get("dbstore");
	}

	@Override
	public Gather getGather() throws Exception {
		// TODO Auto-generated method stub
		return (Gather) map.get("gather");
	}

	@Override
	public Logger getLogger() throws Exception {
		// TODO Auto-generated method stub
		return (Logger) map.get("logger");
	}

	@Override
	public Server getServer() throws Exception {
		// TODO Auto-generated method stub
		return (Server) map.get("server");
	}

		
}
