package dao;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;

public class Dao {
    static DataSource ds;

    public Connection getConnection() throws Exception {
        if (ds==null) {
            InitialContext ic=new InitialContext();
            ds=(DataSource)ic.lookup("java:/comp/env/jdbc/kaihatsu");
        }
        return ds.getConnection();
    }
}
