package com.example.JwtAnRolePermission.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Permission {

    ADMIN_READ("admin:read"),
    ADMIN_WRITE("admin:write"),
    ADMIN_DELETE("admin:delete"),
    ADMIN_UPDATE("admin:update"),

    MANAGER_READ("manager:read"),
    MANAGER_WRITE("manager:write"),
    MANAGER_DELETE("manager:delete"),
    MANAGER_UPDATE("manager:update");

    private final String permission;
}
