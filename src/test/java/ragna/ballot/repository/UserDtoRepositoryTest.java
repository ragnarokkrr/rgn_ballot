package ragna.ballot.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import ragna.ballot.repository.model.User;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserDtoRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void shouldFindAUserByName() {

        User user = User.builder()
                .name("John Connor")
                .username("jconnor")
                .build();

        User first = userRepository.saveAndFlush(user);

        Optional<User> jconnor = userRepository.findByUsername("jconnor");

        assertThat(jconnor.isPresent()).isTrue();
        assertThat(jconnor.get())
                .hasFieldOrPropertyWithValue("name", "John Connor")
                .hasFieldOrPropertyWithValue("username", "jconnor");
    }

}
