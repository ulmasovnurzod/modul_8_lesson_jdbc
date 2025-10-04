package uz.pdp.mapper;

import org.springframework.jdbc.core.RowMapper;
import uz.pdp.entities.Users;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<Users> {


    @Override
    public Users mapRow(ResultSet rs, int rowNum) throws SQLException {
        Users users = new Users();
        users.setId(rs.getInt("id"));
        users.setFullName(rs.getString("fullName"));
        users.setEmail(rs.getString("email"));

        return users;
    }
}
