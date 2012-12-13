package com.jyams.security.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jyams.security.dao.RoleDao;
import com.jyams.security.model.Role;

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
