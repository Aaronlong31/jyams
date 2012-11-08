package com.jyams.secure.manager;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.jyams.secure.model.Role;

@Transactional(rollbackFor = Exception.class)
public interface RoleManager {

	@Transactional(readOnly = true)
	List<Role> listRoles();
}
