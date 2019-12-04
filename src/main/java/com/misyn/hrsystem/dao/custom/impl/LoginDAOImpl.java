/*
 * LoginDAOImpl
 */
package com.misyn.hrsystem.dao.custom.impl;

import com.misyn.hrsystem.dao.custom.LoginDAO;
import com.misyn.hrsystem.dto.custom.UserDTO;
import com.misyn.hrsystem.exception.JDBCTemplateException;
import com.misyn.hrsystem.util.Cryptography;
import static com.misyn.hrsystem.util.QueryConstants.CHECK_LOGIN;
import java.util.ArrayList;
import javax.sql.DataSource;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Shanaka
 * Check login Details 
 * deal with user table in DB
 */
@Repository
public class LoginDAOImpl implements LoginDAO{
    
    private static final Logger LOGGER = Logger.getLogger(LoginDAOImpl.class);
    
    Cryptography cryp = new Cryptography();

    protected JdbcTemplate jdbcTemplate;
    
    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * 
     * @param loginDTO
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public UserDTO checkLogin(UserDTO loginDTO) throws JDBCTemplateException {  
        
        
        ArrayList arguments = new ArrayList();
        arguments.add(loginDTO.getUser_name());
        arguments.add(cryp.encrypt(loginDTO.getPassword()));
        
        return (UserDTO) jdbcTemplate.queryForObject(CHECK_LOGIN, arguments.toArray(),new BeanPropertyRowMapper(UserDTO.class));
        
        
    }
    
}
