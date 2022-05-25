package com.mantas.tapd.service;

import com.mantas.tapd.dto.Role;
import com.mantas.tapd.dto.Worker;

import java.util.Collection;
import java.util.List;

public interface RoleService {

    List<Role> getRolesByProject(Integer projectId);

    List<Worker> getUsersByProject(Integer projectId);

    List<Worker> getUsersByProject(Integer projectId, Collection<Role> roles);
}
