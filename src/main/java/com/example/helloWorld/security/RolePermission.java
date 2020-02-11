package com.example.helloWorld.security;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="role_permission")
public class RolePermission {
    @Id
    private String id;
    private String roleId;
    private String permissionId;

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getRoleId() {
        return roleId;
    }

    public String getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
    }
}
