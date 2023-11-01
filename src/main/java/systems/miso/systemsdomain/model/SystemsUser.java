package systems.miso.systemsdomain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class SystemsUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "internal_id")
    private Integer internalId;
    
    @Column(name = "external_id", unique = true, nullable = false, updatable = false, insertable = true)
    private String externalId;
    
    @Column(unique = true, nullable = false, updatable = false, insertable = true)
    private String username;
    
    private String firstname;

    private String lastname;
}
