package systems.miso.systemsdomain.service;

import static systems.miso.systemsdomain.util.StringUtils.isEmpty;
import static systems.miso.systemsdomain.util.SystemsUserConvertUtils.toUser;
import static systems.miso.systemsdomain.util.SystemsUserConvertUtils.toUserDTO;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import systems.miso.systemsdomain.model.SystemsUser;
import systems.miso.systemsdomain.model.dto.SystemsUserDTO;
import systems.miso.systemsdomain.repository.SystemsUserRepository;
import systems.miso.systemsdomain.util.SystemsUserConvertUtils;

@Service
public class SystemsUserService {
    private final SystemsUserRepository userRepository;

    public SystemsUserService(final SystemsUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<SystemsUserDTO> findOneUserByExternalId(final String externalId) {
        final Optional<SystemsUser> repoUser = userRepository.findOneUserByExternalId(externalId);

        if (repoUser.isPresent()) {
            return Optional.ofNullable(toUserDTO(repoUser.get()));
        }

        return Optional.empty();
    }

    public Set<SystemsUserDTO> findUsersByFirstnameOrLastname(final String searchString) {
        final Set<SystemsUser> repoUsers = userRepository.findUsersByFirstnameOrLastname(searchString, searchString);

        return repoUsers.stream()
            .map(SystemsUserConvertUtils::toUserDTO)
            .collect(Collectors.toSet());
    }

    public SystemsUserDTO insertOneUser(final SystemsUserDTO userDTO) {
        if (isEmpty(userDTO.getExternalId())) {
            userDTO.setExternalId(UUID.randomUUID().toString().replace("-", ""));
        }

        if (userDTO.getUsername() == null) {
            return null;
        }

        return toUserDTO(userRepository.saveAndFlush(toUser(userDTO)));
    }
}
