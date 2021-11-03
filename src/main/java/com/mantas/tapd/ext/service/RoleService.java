package com.mantas.tapd.ext.service;

import com.mantas.tapd.ext.dto.Role;
import com.mantas.tapd.ext.dto.Worker;

import java.util.Collection;
import java.util.List;

public interface RoleService {

    List<Role> getRolesByProject(Integer projectId);

    List<Worker> getUsersByProject(Integer projectId);

    List<Worker> getUsersByProject(Integer projectId, Collection<Role> roles);
}
