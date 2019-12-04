/*
 
 */
package com.misyn.hrsystem.dao.custom.impl;

import com.misyn.hrsystem.dao.custom.FoodDAO;
import com.misyn.hrsystem.dto.custom.FoodDTO;
import com.misyn.hrsystem.exception.JDBCTemplateException;
import static com.misyn.hrsystem.util.QueryConstants.DELETE_FOOD_TMP;
import static com.misyn.hrsystem.util.QueryConstants.INSERT_FOOD_TMP;
import static com.misyn.hrsystem.util.QueryConstants.SELECT_FOOD_ALL_PENDING_LIST;
import static com.misyn.hrsystem.util.QueryConstants.SELECT_FOOD_AUTH_LIST;
import static com.misyn.hrsystem.util.QueryConstants.SELECT_FOOD_PENDING_LIST;
import static com.misyn.hrsystem.util.QueryConstants.SELECT_FOOD_REPORT_LIST;
import static com.misyn.hrsystem.util.QueryConstants.SELECT_MST_FOOD_SAVE_HST;
import static com.misyn.hrsystem.util.QueryConstants.SELECT_TMP_FOOD_SAVE_HST;
import static com.misyn.hrsystem.util.QueryConstants.SELECT_TMP_FOOD_SAVE_MST;
import static com.misyn.hrsystem.util.QueryConstants.UPDATE_TMP_AUTH_FOOD_DETAIL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Shanaka
 * Save food Details deal with food tmp table and food_mst data base table
 */
@Repository
public class FoodDAOImpl implements FoodDAO{
    
    private static final Logger LOGGER = Logger.getLogger(FoodDAOImpl.class);

    protected JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * 
     * @param foodDTO
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public FoodDTO saveMaster(FoodDTO foodDTO) throws JDBCTemplateException {
        int result = 0;
        String query_insrt_hst = "INSERT INTO food_hst (SELECT * FROM food_tmp order by food_id desc limit 1)";

        try {
            result = jdbcTemplate.update(new PreparedStatementCreator() {

                @Override
                public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                    PreparedStatement statement = connection.prepareStatement(INSERT_FOOD_TMP, Statement.RETURN_GENERATED_KEYS);
                    statement.setString(1, foodDTO.getProject_name());
                    statement.setDouble(2, foodDTO.getAmount());
                    statement.setString(3, foodDTO.getImage());
                    statement.setString(4, "P");
                    statement.setString(5, "");
                    statement.setString(6, "1900-01-01 00:00:00");
                    statement.setString(7, foodDTO.getUser_code());
                    return statement;
                }
            });

            if (result > 0) {
                result = jdbcTemplate.update(query_insrt_hst);
            }

        } catch (Exception e) {
            LOGGER.error(e);
        }
        return result > 0 ? foodDTO : null;
    }

    /**
     * 
     * @param t
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public FoodDTO updateMaster(FoodDTO t) throws JDBCTemplateException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * 
     * @param food_id
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public boolean deleteMaster(Object food_id) throws JDBCTemplateException {
        int result = 0;

        try {
            //--Get data from TMP save HST
            result = jdbcTemplate.update(SELECT_TMP_FOOD_SAVE_HST, (Integer)food_id);
            //---Delete Details TMP table
            result = jdbcTemplate.update(DELETE_FOOD_TMP, (Integer)food_id);
   
        } catch (Exception e) {
            LOGGER.error(e);
        }

        return result > 0 ? true : false;
    }

    /**
     * 
     * @param foodDTO
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public List<FoodDTO> getPenFoodList(FoodDTO foodDTO) throws JDBCTemplateException {
        return jdbcTemplate.query(SELECT_FOOD_PENDING_LIST,new BeanPropertyRowMapper(FoodDTO.class),foodDTO.getUser_code());
    }

    /**
     * 
     * @param foodDTO
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public List<FoodDTO> getPenAllFoodList(FoodDTO foodDTO) throws JDBCTemplateException {
         return jdbcTemplate.query(SELECT_FOOD_ALL_PENDING_LIST,new BeanPropertyRowMapper(FoodDTO.class));
    }

    /**
     * 
     * @param foodDTO
     * @param foodID
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public FoodDTO authorizeFood(FoodDTO foodDTO, int foodID) throws JDBCTemplateException {
        
        int result = 0;

        try {
            //---Update Auth Details TMP table
            result = jdbcTemplate.update(UPDATE_TMP_AUTH_FOOD_DETAIL, "A", foodDTO.getAuth_user(), foodID);
            //---Save Detals Maseter Table
            result = jdbcTemplate.update(SELECT_TMP_FOOD_SAVE_MST, foodID);
            //---Get data from MST save HST
            result = jdbcTemplate.update(SELECT_MST_FOOD_SAVE_HST, foodID);
            //--Delete TMP Table
            result = jdbcTemplate.update(DELETE_FOOD_TMP, foodID);
  

        } catch (Exception e) {
            LOGGER.error(e);
        }

        return result > 0 ? foodDTO : null;
    }

    /**
     * 
     * @param foodDTO
     * @param foodID
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public FoodDTO rejectFood(FoodDTO foodDTO, int foodID) throws JDBCTemplateException {
        int result = 0;

        try {
            //---Update Leave TMP table
            result = jdbcTemplate.update(UPDATE_TMP_AUTH_FOOD_DETAIL, "R", foodDTO.getAuth_user(), foodID);
            //--Get data from TMP save HST
            result = jdbcTemplate.update(SELECT_TMP_FOOD_SAVE_HST, foodID);
            //--Delete TMP Table
            result = jdbcTemplate.update(DELETE_FOOD_TMP, foodID);


        } catch (Exception e) {
            LOGGER.error(e);
        }

        return result > 0 ? foodDTO : null;
    }

    /**
     * 
     * @param foodDTO
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public List<FoodDTO> getAuthFoodList(FoodDTO foodDTO) throws JDBCTemplateException {
        return jdbcTemplate.query(SELECT_FOOD_AUTH_LIST,new BeanPropertyRowMapper(FoodDTO.class),foodDTO.getUser_code());
    }

    /**
     * 
     * @param foodDTO
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public List<FoodDTO> getRepFoodList(FoodDTO foodDTO) throws JDBCTemplateException {
        ArrayList arguments = new ArrayList();
        
        String query_food_list = SELECT_FOOD_REPORT_LIST;
        
        query_food_list = (!foodDTO.getUser_code().equals("")
                ? query_food_list.concat(" WHERE fm.user_code = ?") : query_food_list);
        
        if (!foodDTO.getUser_code().equalsIgnoreCase("")) {
            arguments.add(foodDTO.getUser_code());
        }
        
        return jdbcTemplate.query(query_food_list,arguments.toArray(),new BeanPropertyRowMapper(FoodDTO.class));
        
    }
    
}
