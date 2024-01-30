package common.db;

import common.logger.CustomLogger;
import java.util.Objects;
import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitializer {

    public static void initializeDatabase(DataSource dataSource, String scriptFilePath) {
        try (Connection conn = dataSource.getConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                Objects.requireNonNull(
                    DatabaseInitializer.class.getClassLoader().getResourceAsStream(scriptFilePath)), StandardCharsets.UTF_8))) {
            String line;
            StringBuilder sqlQuery = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                sqlQuery.append(line);

                // SQL 명령문은 ";"로 종료되므로, ";"를 만나면 쿼리 실행
                if (line.endsWith(";")) {
                    try (Statement stmt = conn.createStatement()) {
                        stmt.execute(sqlQuery.toString());
                        sqlQuery = new StringBuilder(); // 다음 SQL 명령문을 위해 StringBuilder 초기화
                    } catch (SQLException e) {
                        CustomLogger.printError(e);
                    }
                }
            }
        } catch (Exception e) {
            CustomLogger.printError(e);
        }
    }
}

