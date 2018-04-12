package woss.server;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Collection;
import java.util.Properties;

import com.briup.util.BIDR;
import com.briup.util.Configuration;
import com.briup.woss.ConfigurationAWare;
import com.briup.woss.server.DBStore;

import log.LoggerImp;
import util.BackUpImp;
import util.ConfigurationImpl;

public class DBImpl implements DBStore,ConfigurationAWare{
	
	private String driver;// = "oracle.jdbc.driver.OracleDriver";
	//b.irl(数据库服务器的地址与端口
	private String url;// = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
	//c.d. 用户名密码
	private String user;// = "ljq";
	private String password;// = "0915";
	private ConfigurationImpl con ;
	//写在try外边方便释放资源
	@Override
	public void init(Properties pro) {
		// TODO Auto-generated method stub
			driver = pro.getProperty("driver");
			url = pro.getProperty("url");
			user = pro.getProperty("userName");
			password = pro.getProperty("passWord");
	}
	Connection conn = null;
	PreparedStatement ps = null;
	
	//数据入库
	@Override
	public void saveToDB(Collection<BIDR> data) throws Exception{
		// TODO Auto-generated method stub
		LoggerImp llmp =  (LoggerImp) con.getLogger();
		try {
			Calendar day = Calendar.getInstance();
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
			if(conn == null) {
				llmp.info("数据库连接失败");
				return;
			}
			llmp.info("数据库连接成功");
			String sql = "insert into t_detail_"+day.get(day.DAY_OF_MONTH)+" values(?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			int i = 1;
			for(BIDR bidr:data) {
				String aaa_login_name = bidr.getAAA_login_name();
				String login_ip = bidr.getLogin_ip();
				Timestamp login_date = bidr.getLogin_date();
				Timestamp logout_date = bidr.getLogout_date();
				String nas_ip = bidr.getNAS_ip();
				Integer time_duration = bidr.getTime_deration();
				ps.setString(1, aaa_login_name);
				ps.setString(2, login_ip);
				ps.setDate(3,new Date(login_date.getTime()));
				ps.setDate(4,new Date(logout_date.getTime()));
				ps.setString(5, nas_ip);
				ps.setInt(6,time_duration);
				ps.addBatch();
				
				llmp.debug("第"+i+"条数据执行完成");
				i++;
			}
			ps.executeBatch();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			BackUpImp back = new BackUpImp();
			try {
				back.store("back_up.txt", data, back.STORE_APPEND);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				llmp.error(e1.getMessage());
			}
		}finally {
			try {
				if (ps != null) {
					ps.close();
					}
				if(conn !=null) {
					conn.close();
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					llmp.error(e.getMessage());
				}
			llmp.info("数据全部导入成功，数据总量为:"+data.size());
		}

	}
	@Override
	public void setConfiguration(Configuration config) {
		// TODO Auto-generated method stub
		con = (ConfigurationImpl) config;
	}

}
