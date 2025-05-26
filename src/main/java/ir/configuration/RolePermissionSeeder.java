package ir.configuration;

import ir.entity.Permission;
import ir.entity.Role;
import ir.repository.PermissionRepository;
import ir.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
@Slf4j
public class RolePermissionSeeder implements CommandLineRunner {

    private final PermissionRepository permissionRepository;
    private final RoleRepository roleRepository;

    @Override
    public void run(String... args) {
        List<String> permissionNames = List.of(
                "CAN_VIEW_MEALS",
                "CAN_PLACE_ORDER",
                "CAN_CREATE_MENU",
                "CAN_EDIT_USERS",
                "CAN_MANAGE_ORDERS"
        );

        Set<Permission> allPermissions = new HashSet<>();
        for (String name : permissionNames) {
            Permission permission = permissionRepository.findByName(name)
                    .orElseGet(() -> permissionRepository.save(new Permission(name)));
            allPermissions.add(permission);
        }
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
    }
        private void createOrUpdateRole (
                String roleName, Set < Permission > desiredPermissions){
            Role role = roleRepository.findByName(roleName).orElseGet(() -> {
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
                log.info(
                        "{} role: {}", role.getId() == null ? "Created" : "Updated", roleName);
            }
        }

        private Permission getPermission (Set < Permission > permissions, String name){
            return permissions.stream()
                    .filter(p -> p.getName().equals(name))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Permission not found: " + name));
        }

        // 3. Create default admin user (optional)
//        String defaultEmail = "admin@example.com";
//        if (userRepository.findByEmail(defaultEmail).isEmpty()) {
//            User admin = new User();
//            admin.setEmail(defaultEmail);
//            admin.setPassword(passwordEncoder.encode("admin123")); // use a strong password
//            admin.setRoles(Set.of(
//                    roleRepository.findByName("ROLE_ADMIN_FULL")
//                            .orElseThrow(() -> new IllegalStateException("ROLE_ADMIN_FULL not found"))
//            ));
//            userRepository.save(admin);
//            System.out.println("✅ Default admin user created");
//        }
//    }

}

