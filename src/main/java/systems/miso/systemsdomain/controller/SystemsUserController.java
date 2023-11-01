package systems.miso.systemsdomain.controller;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import systems.miso.systemsdomain.model.dto.SystemsUserDTO;
import systems.miso.systemsdomain.service.SystemsUserService;

@RestController
@RequestMapping(path = "/api/v1")
public class SystemsUserController {
    private final SystemsUserService userService;

    public SystemsUserController(final SystemsUserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/user/find/one/{externalId}", produces = "application/json")
    public ResponseEntity<SystemsUserDTO> findOneUserByExternalId(@PathVariable(required = true) final String externalId) {
        final Optional<SystemsUserDTO> serviceUserDTO = userService.findOneUserByExternalId(externalId);
        return serviceUserDTO.isPresent() 
            ? ResponseEntity.ok(serviceUserDTO.get()) 
            : ResponseEntity.notFound().build();
    }

    @GetMapping(path = "/user/find/{searchString}", produces = "application/json")
    public ResponseEntity<Set<SystemsUserDTO>> findUsersByFirstnameOrLastname(@PathVariable(required = true) final String searchString) {
        final Set<SystemsUserDTO> serviceUsers = userService.findUsersByFirstnameOrLastname(searchString);
        return !serviceUsers.isEmpty() 
            ? ResponseEntity.ok(serviceUsers) 
            : ResponseEntity.notFound().build();
    }

    @PostMapping(path = "/user/add/one", consumes = "application/json", produces = "application/json")
    public ResponseEntity<SystemsUserDTO> insertOneUser(@RequestBody final SystemsUserDTO systemsUserDTO) {
        final SystemsUserDTO serviceUserDTO = userService.insertOneUser(Objects.requireNonNull(systemsUserDTO, "SystemsUserDTO not allowed to be null!"));

        return serviceUserDTO != null 
            ? ResponseEntity.ok(serviceUserDTO) 
            : ResponseEntity.badRequest().build();
    }
}
