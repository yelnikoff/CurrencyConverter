package eu.yelnikoff.curverter.entities.user;

import java.util.Optional;
import java.nio.CharBuffer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public Optional<User> findByLogin(String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);

        if (user.isEmpty() || !passwordEncoder.matches(CharBuffer.wrap(password), user.get().getPasswordHash()))
            return Optional.empty();

        return user;
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public boolean existsByEmail(String email) {
        return findByEmail(email).isPresent();
    }

    public User save(SignUpUserDto signUpUserDto) {
        User user = new User();

        user.setFirstName(signUpUserDto.getFirstName());
        user.setLastName(signUpUserDto.getLastName());
        user.setEmail(signUpUserDto.getEmail());
        user.setCompanyName(signUpUserDto.getCompanyName());
        user.setPasswordHash(passwordEncoder.encode(CharBuffer.wrap(signUpUserDto.getPassword())));

        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository
                .findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new UserIdentity(user);
    }

}
