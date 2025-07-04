package ir.seeder;

import ir.entity.Permission;
import ir.entity.Role;
import ir.repository.PermissionRepository;
import ir.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
@Slf4j
public class RolePermissionSeeder implements CommandLineRunner {

    private final PermissionRepository permissionRepository;
    private final RoleRepository roleRepository;

    /**
     * Seeds the system with initial permissions and roles at application startup
     * if no roles currently exist in the database.
     * <p>
     * This method is executed automatically by Spring Boot via the CommandLineRunner interface.
     * It first defines a list of permission names, ensures they are present in the database,
     * and then creates roles (e.g., ROLE_CUSTOMER, ROLE_ADMIN_BASIC, ROLE_ADMIN_FULL) with
     * their corresponding permissions.
     * <p>
     * This operation is wrapped in a single transaction to ensure data consistency.
     *
     * @param args optional command-line arguments (not used)
     * @throws Exception if an error occurs during seeding
     */
    @Override
    @Transactional
    public void run(String... args) throws Exception {
        // Check if roles already exist
        if (roleRepository.count() == 0) {
            List<String> permissionNames = List.of(
                    "CAN_VIEW_MEALS",
                    "CAN_PLACE_ORDER",
                    "CAN_CREATE_MENU",
                    "CAN_EDIT_USERS",
                    "CAN_MANAGE_ORDERS"
            );

            // Save all permissions in one transaction
            Set<Permission> allPermissions = new HashSet<>();
            for (String name : permissionNames) {
                Permission permission = permissionRepository.findByName(name)
                        .orElseGet(() -> {
                            Permission newPermission = new Permission();
                            newPermission.setName(name);
                            return newPermission;
                        });
                allPermissions.add(permission);
            }
            permissionRepository.saveAll(allPermissions);

            // Define roles and their permissions
            createOrUpdateRole("ROLE_CUSTOMER", Set.of(
                    getPermission(allPermissions, "CAN_VIEW_MEALS"),
                    getPermission(allPermissions, "CAN_PLACE_ORDER")
            ));

            createOrUpdateRole("ROLE_ADMIN_BASIC", Set.of(
                    getPermission(allPermissions, "CAN_MANAGE_ORDERS")
            ));

            createOrUpdateRole("ROLE_ADMIN_FULL", Set.of(
                    getPermission(allPermissions, "CAN_MANAGE_ORDERS"),
                    getPermission(allPermissions, "CAN_EDIT_USERS"),
                    getPermission(allPermissions, "CAN_CREATE_MENU")
            ));

            log.info("Successfully seeded roles and permissions.");
        } else {
            log.info("Roles already exist, skipping seeding.");
        }
    }

    private void createOrUpdateRole(String roleName, Set<Permission> desiredPermissions) {
        Role role = roleRepository.findByName(roleName)
                .orElseGet(() -> {
                    Role newRole = new Role();
                    newRole.setName(roleName);
                    return newRole;
                });

        if (role.getPermissions() == null) {
            role.setPermissions(new HashSet<>());
        }

        boolean updated = false;
        for (Permission permission : desiredPermissions) {
            if (!role.getPermissions().contains(permission)) {
                role.getPermissions().add(permission);
                updated = true;
            }
        }

        if (role.getId() == null || updated) {
            roleRepository.save(role);
            log.info("{} role: {}", role.getId() == null ? "Created" : "Updated", roleName);
        }
    }

    private Permission getPermission(Set<Permission> permissions, String name) {
        return permissions.stream()
                .filter(p -> p.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Permission not found: " + name));
    }
}