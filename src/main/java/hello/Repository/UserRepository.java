package hello.Repository;

import hello.Entity.User;

import java.util.List;

/**
 * Created by njucong on 2017/5/26.
 */
public interface UserRepository {

    User selectUserById(Long id);

    User selectUserByUsername(String username);

    List<User> selectAllUsers();

    Integer insertUser(User user);

    Integer updateUserOnPasswordById(User user);

    Integer deleteUserById(Long id);

}
