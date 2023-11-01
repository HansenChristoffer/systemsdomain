package systems.miso.systemsdomain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import systems.miso.systemsdomain.model.SystemsUser;
import systems.miso.systemsdomain.repository.SystemsUserRepository;

@Component
@Profile("test")
public class TestDataInitializer implements CommandLineRunner {
    @Autowired
    private SystemsUserRepository userRepository;

    private static final List<SystemsUser> preDatabaseUsers = new ArrayList<>();

    static {
        preDatabaseUsers.addAll(Arrays.asList(
            SystemsUser.builder()
                .externalId("thisIsAnExternalId")
                .username("user1")
                .firstname("John")
                .lastname("Doe")
                .build(),
            SystemsUser.builder()
                .externalId(UUID.randomUUID().toString().replace("-", ""))
                .username("user2")
                .firstname("John")
                .lastname("Smith")
                .build(),
            SystemsUser.builder()
                .externalId(UUID.randomUUID().toString().replace("-", ""))
                .username("user3")
                .firstname("Jane")
                .lastname("Doe")
                .build(),
            SystemsUser.builder()
                .externalId(UUID.randomUUID().toString().replace("-", ""))
                .username("user4")
                .firstname("Emily")
                .lastname("Brown")
                .build(),
            SystemsUser.builder()
                .externalId(UUID.randomUUID().toString().replace("-", ""))
                .username("user5")
                .firstname("Emily")
                .lastname("Smith")
                .build(),
            SystemsUser.builder()
                .externalId(UUID.randomUUID().toString().replace("-", ""))
                .username("user6")
                .firstname("Mark")
                .lastname("Johnson")
                .build(),
            SystemsUser.builder()
                .externalId(UUID.randomUUID().toString().replace("-", ""))
                .username("user7")
                .firstname("Alice")
                .lastname("Thompson")
                .build(),
            SystemsUser.builder()
                .externalId(UUID.randomUUID().toString().replace("-", ""))
                .username("user8")
                .firstname("Chris")
                .lastname("Cool")
                .build()
        ));
    }

    @Override
    public void run(final String... args) throws Exception {
        userRepository.saveAllAndFlush(preDatabaseUsers);
    }
}
