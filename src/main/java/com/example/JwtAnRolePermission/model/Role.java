package com.example.JwtAnRolePermission.model;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@Getter
@RequiredArgsConstructor
public enum Role {
    USER(Collections.emptySet()),
    ADMIN(
            Set.of(
                    Permission.ADMIN_READ,
                    Permission.ADMIN_WRITE,
                    Permission.ADMIN_DELETE,
                    Permission.ADMIN_UPDATE,
                    Permission.MANAGER_READ,
                    Permission.MANAGER_WRITE,
                    Permission.MANAGER_DELETE,
                    Permission.MANAGER_UPDATE
            )
    ),
    MANAGER(
            Set.of(
                    Permission.MANAGER_READ,
                    Permission.MANAGER_UPDATE,
                    Permission.ADMIN_WRITE,
                    Permission.MANAGER_DELETE
            )
    );

    private final Set<Permission> permissions;

    List<SimpleGrantedAuthority> getAuthorities() {
        var authorities = getPermissions().stream().map(
                permission -> new SimpleGrantedAuthority(permission.name())
        ).toList();

        authorities.add(new SimpleGrantedAuthority(this.name()));
        return authorities;
    }
}
