package systems.miso.systemsdomain.util;

import java.util.Objects;

import systems.miso.systemsdomain.model.SystemsUser;
import systems.miso.systemsdomain.model.dto.SystemsUserDTO;

public class SystemsUserConvertUtils {
    private SystemsUserConvertUtils() {
    }

    public static SystemsUser toUser(final SystemsUserDTO dto) {
        Objects.requireNonNull(dto, "UserDTO is not allowed to be null!");

        return SystemsUser.builder()
            .externalId(dto.getExternalId())
            .username(dto.getUsername())
            .firstname(dto.getFirstname())
            .lastname(dto.getLastname())
            .build();
    }

    public static SystemsUserDTO toUserDTO(final SystemsUser user) {
        Objects.requireNonNull(user, "User is not allowed to be null!");

        return SystemsUserDTO.builder()
            .externalId(user.getExternalId())
            .username(user.getUsername())
            .firstname(user.getFirstname())
            .lastname(user.getLastname())
            .build();
    }
}
