package ru.gb.jt.chat.server.core;

import javax.sql.DataSource;
import java.sql.*;

public class SqlClient {

    private Connection connection;
    private Statement statement;

    public SqlClient(DataSource dataSource) throws SQLException {
        this(dataSource.getConnection());
    }

    public SqlClient(Connection connection) {
        this.connection = connection;
    }

    synchronized void connect() {
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    synchronized void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    synchronized String getNickname(String login, String password) {
        String query = String.format("select nickname from users where login='%s' and password='%s'", login, password);
        try (ResultSet set = statement.executeQuery(query)) {
            if (set.next())
                return set.getString(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

}
