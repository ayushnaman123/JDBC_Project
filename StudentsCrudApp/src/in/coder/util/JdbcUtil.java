package in.coder.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class JdbcUtil {
	private JdbcUtil() {
	}
    
	static {
		// Step1: loading and register the Driver
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}
	
	public static Connection getJdbcConnection() throws IOException, SQLException {
		
        String configFile = "src\\in\\coder\\properties\\Application.properties";
		HikariConfig config = new HikariConfig(configFile);
		HikariDataSource dataSource = new HikariDataSource(config);
		 dataSource.getConnection();
		return dataSource.getConnection();
	}

	
}
