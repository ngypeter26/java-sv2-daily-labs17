package day01;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    public List<String> findActorsWithprefix(String prefix){
        List<String> result = new ArrayList<>();
        try(Connection connection = dataSource.getConnection()){
            PreparedStatement stat = connection.prepareStatement("SELECT actor_name FROM actors WHERE actor_name LIKE ?");
            stat.setString(1,prefix+"%");
            try(ResultSet rs = stat.executeQuery()){
                while(rs.next()){
                    String actorName = rs.getString("actor_name");
                    result.add(actorName);
                }
            }
        }catch(SQLException sqle){
            throw new IllegalStateException("Cannot query",sqle);
        }
        return result;
    }
}
