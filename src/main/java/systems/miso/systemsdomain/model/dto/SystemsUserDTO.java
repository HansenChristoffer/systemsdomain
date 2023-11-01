package systems.miso.systemsdomain.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class SystemsUserDTO {
    private String externalId;
    private String username;
    private String firstname;
    private String lastname;
}
