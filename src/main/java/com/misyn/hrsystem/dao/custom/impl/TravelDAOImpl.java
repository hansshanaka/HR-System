/*
 * TravelDAOimpl
 */
package com.misyn.hrsystem.dao.custom.impl;

import com.misyn.hrsystem.dao.custom.TravelDAO;
import com.misyn.hrsystem.dto.custom.TaxiDTO;
import com.misyn.hrsystem.exception.JDBCTemplateException;
import static com.misyn.hrsystem.util.QueryConstants.DELETE_TAXI_TMP;
import static com.misyn.hrsystem.util.QueryConstants.INSERT_TAXI_TMP;
import static com.misyn.hrsystem.util.QueryConstants.SELECT_MST_TAXI_SAVE_HST;
import static com.misyn.hrsystem.util.QueryConstants.SELECT_TAXI_ALL_PENDING_LIST;
import static com.misyn.hrsystem.util.QueryConstants.SELECT_TAXI_AUTH_LIST;
import static com.misyn.hrsystem.util.QueryConstants.SELECT_TAXI_PENDING_LIST;
import static com.misyn.hrsystem.util.QueryConstants.SELECT_TAXI_REPORT_LIST;
import static com.misyn.hrsystem.util.QueryConstants.SELECT_TMP_TAXI_SAVE_HST;
import static com.misyn.hrsystem.util.QueryConstants.SELECT_TMP_TAXI_SAVE_MST;
import static com.misyn.hrsystem.util.QueryConstants.UPDATE_TMP_AUTH_TAXI_DETAIL;
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
 * Save Tavel Details 
 * deal with taxi table in DB
 */
@Repository
public class TravelDAOImpl implements TravelDAO{
    
    private static final Logger LOGGER = Logger.getLogger(TravelDAOImpl.class);

    protected JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * 
     * @param taxiDTO
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public TaxiDTO saveMaster(TaxiDTO taxiDTO) throws JDBCTemplateException {
        int result = 0;
        String query_insrt_hst = "INSERT INTO taxi_hst (SELECT * FROM taxi_tmp order by taxi_id desc limit 1)";

        try {
            result = jdbcTemplate.update(new PreparedStatementCreator() {

                @Override
                public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                    PreparedStatement statement = connection.prepareStatement(INSERT_TAXI_TMP, Statement.RETURN_GENERATED_KEYS);
                    statement.setString(1, taxiDTO.getProject_name());
                    statement.setDouble(2, taxiDTO.getAmount());
                    statement.setString(3, taxiDTO.getStart());
                    statement.setString(4, taxiDTO.getEnd());
                    statement.setDouble(5, taxiDTO.getDistance());
                    statement.setString(6, taxiDTO.getImage());
                    statement.setString(7, "P");
                    statement.setString(8, "1900-01-01 00:00:00");
                    statement.setString(9, "");                    
                    statement.setString(10, taxiDTO.getUser_code());
                    return statement;
                }
            });

            if (result > 0) {
                result = jdbcTemplate.update(query_insrt_hst);
            }

        } catch (Exception e) {
            LOGGER.error(e);
        }
        return result > 0 ? taxiDTO : null;
    }

    /**
     * 
     * @param t
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public TaxiDTO updateMaster(TaxiDTO t) throws JDBCTemplateException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * 
     * @param taxi_id
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public boolean deleteMaster(Object taxi_id) throws JDBCTemplateException {
        int result = 0;

        try {
            //--Get data from TMP save HST
            result = jdbcTemplate.update(SELECT_TMP_TAXI_SAVE_HST, (Integer)taxi_id);
            //---Delete Details TMP table
            result = jdbcTemplate.update(DELETE_TAXI_TMP, (Integer)taxi_id);
   
        } catch (Exception e) {
            LOGGER.error(e);
        }

        return result > 0 ? true : false;
    }

    /**
     * 
     * @param taxiDTO
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public List<TaxiDTO> getPenTravelList(TaxiDTO taxiDTO) throws JDBCTemplateException {
        return jdbcTemplate.query(SELECT_TAXI_PENDING_LIST,new BeanPropertyRowMapper(TaxiDTO.class),taxiDTO.getUser_code());
    }

    /**
     * 
     * @param taxiDTO
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public List<TaxiDTO> getPenAllTravelList(TaxiDTO taxiDTO) throws JDBCTemplateException {
        return jdbcTemplate.query(SELECT_TAXI_ALL_PENDING_LIST,new BeanPropertyRowMapper(TaxiDTO.class));
    }

    /**
     * 
     * @param taxiDTO
     * @param taxiID
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public TaxiDTO authorizeTaxi(TaxiDTO taxiDTO, int taxiID) throws JDBCTemplateException {
        int result = 0;

        try {
            //---Update Auth Details TMP table
            result = jdbcTemplate.update(UPDATE_TMP_AUTH_TAXI_DETAIL, "A", taxiDTO.getAuth_user(), taxiID);
            //---Save Detals Maseter Table
            result = jdbcTemplate.update(SELECT_TMP_TAXI_SAVE_MST, taxiID);
            //---Get data from MST save HST
            result = jdbcTemplate.update(SELECT_MST_TAXI_SAVE_HST, taxiID);
            //--Delete TMP Table
            result = jdbcTemplate.update(DELETE_TAXI_TMP, taxiID);
  
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return result > 0 ? taxiDTO : null;
    }

    /**
     * 
     * @param taxiDTO
     * @param taxiID
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public TaxiDTO rejectTaxi(TaxiDTO taxiDTO, int taxiID) throws JDBCTemplateException {
        int result = 0;
        try {
            //---Update Leave TMP table
            result = jdbcTemplate.update(UPDATE_TMP_AUTH_TAXI_DETAIL, "R", taxiDTO.getAuth_user(), taxiID);
            //--Get data from TMP save HST
            result = jdbcTemplate.update(SELECT_TMP_TAXI_SAVE_HST, taxiID);
            //--Delete TMP Table
            result = jdbcTemplate.update(DELETE_TAXI_TMP, taxiID);

        } catch (Exception e) {
            LOGGER.error(e);
        }

        return result > 0 ? taxiDTO : null;
    }

    /**
     * 
     * @param taxiDTO
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public List<TaxiDTO> getAuthTravelList(TaxiDTO taxiDTO) throws JDBCTemplateException {
        return jdbcTemplate.query(SELECT_TAXI_AUTH_LIST,new BeanPropertyRowMapper(TaxiDTO.class),taxiDTO.getUser_code());
    }

    /**
     * 
     * @param taxiDTO
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public List<TaxiDTO> getRepTravelList(TaxiDTO taxiDTO) throws JDBCTemplateException {
                ArrayList arguments = new ArrayList();
        
        String query_taxi_list = SELECT_TAXI_REPORT_LIST;
        
        query_taxi_list = (!taxiDTO.getUser_code().equals("")
                ? query_taxi_list.concat(" WHERE tm.user_code = ?") : query_taxi_list);
        
        if (!taxiDTO.getUser_code().equalsIgnoreCase("")) {
            arguments.add(taxiDTO.getUser_code());
        }
        
        return jdbcTemplate.query(query_taxi_list, arguments.toArray(),new BeanPropertyRowMapper(TaxiDTO.class));
        
    }

   
    
}
