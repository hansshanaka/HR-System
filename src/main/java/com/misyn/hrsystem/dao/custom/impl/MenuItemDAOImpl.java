
package com.misyn.hrsystem.dao.custom.impl;

import com.misyn.hrsystem.dao.custom.MenuItemDAO;
import com.misyn.hrsystem.dto.custom.MenuItemDTO;
import com.misyn.hrsystem.exception.JDBCTemplateException;
import static com.misyn.hrsystem.util.QueryConstants.SELECT_EMPLOYEE_ALL_SUB_MENU_OPTION;
import static com.misyn.hrsystem.util.QueryConstants.SELECT_EMPLOYEE_MENU_OPTION;
import static com.misyn.hrsystem.util.QueryConstants.SELECT_EMPLOYEE_SUB_MENU_OPTION;
import static com.misyn.hrsystem.util.QueryConstants.SELECT_USER_SUB_MENU_OPTION;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Shanaka
 * Save Menu item Details 
 * deal with menu option, role option tables in DB
 */
@Repository
public class MenuItemDAOImpl implements MenuItemDAO {

    private static final Logger LOGGER = Logger.getLogger(MenuItemDAOImpl.class);

    protected JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * 
     * @param role_id
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public List<MenuItemDTO> getMenuItemDTOList(String role_id) throws JDBCTemplateException {
        List<MenuItemDTO> list = null;
        try {
            list = jdbcTemplate.query(SELECT_EMPLOYEE_MENU_OPTION, new RowMapper<MenuItemDTO>() {

                @Override
                public MenuItemDTO mapRow(ResultSet rs, int rowNo) throws SQLException {
                    MenuItemDTO menuItemDTO = getMenuItemDTO(rs);
                    return menuItemDTO;
                }
            },role_id);
        } catch (DataAccessException dataAccessException) {
            LOGGER.error(dataAccessException);
            throw new JDBCTemplateException(dataAccessException);
        }
        return list;
    }
    
    /**
     * 
     * @param menuId
     * @param role_id
     * @return
     * @throws JDBCTemplateException 
     */
     @Override
    public List<MenuItemDTO> getSubMenuItemDTOList(int menuId,String role_id) throws JDBCTemplateException {
        List<MenuItemDTO> list = null;
        try {
            list = jdbcTemplate.query(SELECT_EMPLOYEE_SUB_MENU_OPTION, new RowMapper<MenuItemDTO>() {

                @Override
                public MenuItemDTO mapRow(ResultSet rs, int rowNo) throws SQLException {
                    MenuItemDTO menuItemDTO = getMenuItemDTO(rs);
                    return menuItemDTO;
                }
            },menuId,role_id);
        } catch (DataAccessException dataAccessException) {
            LOGGER.error(dataAccessException);
            throw new JDBCTemplateException(dataAccessException);
        }
        return list;
    }

    /**
     * 
     * @param menuId
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public List<MenuItemDTO> getAllSubMenuItemDTOList(int menuId) throws JDBCTemplateException {
        List<MenuItemDTO> list = null;
        try {
            list = jdbcTemplate.query(SELECT_EMPLOYEE_ALL_SUB_MENU_OPTION, new RowMapper<MenuItemDTO>() {

                @Override
                public MenuItemDTO mapRow(ResultSet rs, int rowNo) throws SQLException {
                    MenuItemDTO menuItemDTO = getMenuItemDTO(rs);
                    return menuItemDTO;
                }
            },menuId);
        } catch (DataAccessException dataAccessException) {
            LOGGER.error(dataAccessException);
            throw new JDBCTemplateException(dataAccessException);
        }
        return list;

    }

    /**
     * 
     * @param resultSet
     * @return
     * @throws SQLException 
     */
    private MenuItemDTO getMenuItemDTO(ResultSet resultSet) throws SQLException {

        MenuItemDTO menuItemDTO = null;
        try {
            menuItemDTO = new MenuItemDTO();
            menuItemDTO.setMenuId(resultSet.getInt("menu_option_id"));
            menuItemDTO.setMenuName(resultSet.getString("menu_name"));
            menuItemDTO.setMenuParentId(resultSet.getInt("menu_parent_id"));
            menuItemDTO.setMenuUrl(resultSet.getString("menu_url"));
        } catch (SQLException sqlException) {
            LOGGER.error(sqlException);
        }
        return menuItemDTO;
    }

    /**
     * 
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public List<MenuItemDTO> getAllMenuItemList() throws JDBCTemplateException {
        List<MenuItemDTO> list = null;
        try {
            list = jdbcTemplate.query("SELECT * FROM menu_option WHERE  ISNULL(menu_parent_id)", new RowMapper<MenuItemDTO>() {

                @Override
                public MenuItemDTO mapRow(ResultSet rs, int rowNo) throws SQLException {
                    MenuItemDTO menuItemDTO = getMenuItemDTO(rs);
                    return menuItemDTO;
                }
            });
        } catch (DataAccessException dataAccessException) {
            LOGGER.error(dataAccessException);
            throw new JDBCTemplateException(dataAccessException);
        }
        
        return list;
    }

    /**
     * 
     * @param role_id
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public List<MenuItemDTO> getUserSubMenuItemList(String role_id) throws JDBCTemplateException {
        return jdbcTemplate.query(SELECT_USER_SUB_MENU_OPTION, new BeanPropertyRowMapper(MenuItemDTO.class), role_id);
    }    

   
}
