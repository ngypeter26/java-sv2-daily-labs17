package day01;

import org.mariadb.jdbc.MariaDbDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class MainV0 {
    public static void main(String[] args) {
        MariaDbDataSource dataSource = new MariaDbDataSource();
        try {
            dataSource.setUrl("jdbc:mariadb://localhost:3306/movies-actors?useUnicode=true");
            dataSource.setUserName("root");
            dataSource.setPassword("Knoxwille26");
        } catch (SQLException sqle) {
            throw new IllegalStateException("Cannot reach database",sqle);
        }

        try(Connection connection = dataSource.getConnection();Statement stat = connection.createStatement()){
            stat.executeUpdate("insert into actors(actor_Name) values('John Doe')");
        }catch(SQLException sql){
            throw new IllegalStateException("Cannot connect",sql);
        }

    }
}
