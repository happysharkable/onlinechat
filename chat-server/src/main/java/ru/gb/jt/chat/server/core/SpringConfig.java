package ru.gb.jt.chat.server.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
public class SpringConfig {

    @Bean
    public ChatServer chatServer(SqlClient sqlClient) {
        return new ChatServer(sqlClient);
    }

    @Bean
    public SqlClient sqlClient(DataSource dataSource) throws SQLException {
        return new SqlClient(dataSource);
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("org.sqlite.JDBC");
        ds.setUrl("jdbc:sqlite:chat-server/chat.db");
        return ds;
    }
}
