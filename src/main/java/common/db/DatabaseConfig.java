package common.db;

import org.h2.jdbcx.JdbcDataSource;

import javax.sql.DataSource;

public class DatabaseConfig {

    public static DataSource getDatasource() {
        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setURL("jdbc:h2:tcp://localhost/~/test"); // 데이터베이스 URL
        dataSource.setUser("sa"); // 사용자 이름
        dataSource.setPassword(""); // 비밀번호

        return dataSource;
    }
}
