package utils;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import javax.sql.DataSource;
public class JdbcUtils {

    public static DataSource getDataSource() {

        DataSource ds =null;
        try {


            String jndi = "java:comp/env/jdbc/nodefinder_db";
            Context cxt = (Context) new InitialContext();
            ds = (DataSource) cxt.lookup(jndi);
        } catch (NamingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return ds;

    }

}
