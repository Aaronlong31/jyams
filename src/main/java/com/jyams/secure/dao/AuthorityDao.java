package com.jyams.secure.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jyams.secure.model.Authority;
import com.jyams.secure.model.Module;
import com.jyams.util.dao.IBatisEntityDao;

@Repository
@SuppressWarnings("unchecked")
public class AuthorityDao extends IBatisEntityDao<Authority> {

    public List<Module> getModules() {
        return getSqlMapClientTemplate().queryForList(
                "com.jyams.secure.dao.AuthorityDao.getModule");
    }

    public List<Module> getModuleWithAuthority() {
        return getSqlMapClientTemplate().queryForList(
                "com.jyams.secure.dao.AuthorityDao.getModuleWithAuthority");
    }
}
