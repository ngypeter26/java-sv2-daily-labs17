package day01;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class ActorsRepository {
    private DataSource dataSource;

    public ActorsRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void saveActor(String name){
        try(Connection connection = dataSource.getConnection()){
            // előőkészített állítás, a kérdőjel helyére mehet más is
            PreparedStatement statement = connection.prepareStatement("insert into actors(actor_name) values(?)");
            statement.setString(1,name);// 1-től indexel, nem nulla
            statement.executeUpdate();
        }catch(SQLException sqle){
            throw new IllegalStateException("Cannot connect",sqle);
        }
    }
}
