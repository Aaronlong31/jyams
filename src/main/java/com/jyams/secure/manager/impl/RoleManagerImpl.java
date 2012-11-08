package com.jyams.secure.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jyams.secure.dao.RoleDao;
import com.jyams.secure.manager.RoleManager;
import com.jyams.secure.model.Role;

@Service
@Transactional(rollbackFor = Exception.class)
public class RoleManagerImpl implements RoleManager {

	@Autowired
	private RoleDao roleDao;

	@Override
	public List<Role> listRoles() {
		return roleDao.getAll();
	}

}
