package hello.Service.implement;

import hello.Entity.Dto.CustomUserDetails;
import hello.Entity.User;
import hello.Repository.UserRepository;
import hello.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by njucong on 2017/5/25.
 */
@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    // 实现UserRepository接口的实体类来初始化UserService服务
    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.selectUserByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("Could not find the user '" + username + "'");
        }

        // Not involve authorities, so pass null to authorities
        return new CustomUserDetails(user, true, true, true, true, null);
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return Optional.ofNullable(userRepository.selectUserById(id));
    }

    @Override
    public boolean saveUser(User user) {
        return userRepository.insertUser(user) > 0;
    }

    @Override
    public boolean modifyUserOnPasswordById(User user) {
        return userRepository.updateUserOnPasswordById(user) > 0;
    }

    @Override
    public boolean deleteUserById(Long id) {
        return userRepository.deleteUserById(id) > 0;
    }
}
