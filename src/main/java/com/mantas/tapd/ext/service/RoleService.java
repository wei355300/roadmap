package com.mantas.tapd.ext.service;

import com.mantas.tapd.ext.dto.Role;

import java.util.List;

public interface RoleService {

    List<Role> getRolesByProject(Integer projectId);
}
