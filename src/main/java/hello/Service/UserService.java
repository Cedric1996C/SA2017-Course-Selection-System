package hello.Service;

import hello.Entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import java.util.Optional;

/**
 * Created by njucong on 2017/5/25.
 */
public interface UserService extends UserDetailsService{

    Optional<User> getUserById(Long Id);

    boolean saveUser(User user);

    boolean modifyUserOnPasswordById(User user);

    boolean deleteUserById(Long id);

}
