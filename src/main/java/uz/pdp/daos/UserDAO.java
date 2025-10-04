package uz.pdp.daos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import uz.pdp.entities.Users;
import uz.pdp.mapper.UserMapper;
import uz.pdp.utilFile.JsonUtil;
import java.util.List;

@Component
@EnableAspectJAutoProxy
public class UserDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Users getUserById(Integer id) {
        String sql = "select * from users where id=?";
        return jdbcTemplate.queryForObject(sql, new UserMapper(), id);
    }

    public int addUsers(Users user) {

        String sql = "Insert into users(fullName, email) values(?,?)";
        int rows = jdbcTemplate.update(sql, user.getFullName(), user.getEmail());

        if (rows > 0) {
            List<Users> usersList = getAllUsers();
            JsonUtil.writeListToJsonFile(usersList, "user_json");
        }
        return rows;

    }

    public List<Users> getAllUsers() {
        String sql = "SELECT * FROM users";
        return jdbcTemplate.query(sql, new UserMapper());
    }

    public int updateUser(Users user) {
        String sql = "UPDATE users SET fullName=?, email=? WHERE id=?";
        int update = jdbcTemplate.update(sql, user.getFullName(), user.getEmail(), user.getId());

        if (update > 0) {
            // update muvaffaqiyatli bo'lsa, yangilangan listni JSON faylga yozamiz
            List<Users> allUsers = getAllUsers();  // DB dan barcha userlarni olish
            JsonUtil.writeListToJsonFile(allUsers, "user_json");
        }

        return update;
    }


    public int delete(Integer id) {
        String sql = "delete from users where id=?";
        int delete = jdbcTemplate.update(sql, id);

        if(delete > 0) {
            List<Users> allUsers = getAllUsers();
            JsonUtil.writeListToJsonFile(allUsers, "user_json");
        }
        return delete;
    }
}
