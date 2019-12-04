/*
 *MeedicalDAOImpl
 */
package com.misyn.hrsystem.dao.custom.impl;

import com.misyn.hrsystem.dao.custom.MedicalDAO;
import com.misyn.hrsystem.dto.custom.MedicalDTO;
import com.misyn.hrsystem.exception.JDBCTemplateException;
import static com.misyn.hrsystem.util.QueryConstants.DELETE_MEDICAL_TMP;
import static com.misyn.hrsystem.util.QueryConstants.INSERT_MEDICAL_TMP;
import static com.misyn.hrsystem.util.QueryConstants.SELECT_MEDICAL_ALL_PENDING_LIST;
import static com.misyn.hrsystem.util.QueryConstants.SELECT_MEDICAL_AUTH_LIST;
import static com.misyn.hrsystem.util.QueryConstants.SELECT_MEDICAL_PENDING_LIST;
import static com.misyn.hrsystem.util.QueryConstants.SELECT_MEDICAL_REPORT_LIST;
import static com.misyn.hrsystem.util.QueryConstants.SELECT_MST_MEDICAL_SAVE_HST;
import static com.misyn.hrsystem.util.QueryConstants.SELECT_TMP_MEDICAL_SAVE_HST;
import static com.misyn.hrsystem.util.QueryConstants.SELECT_TMP_MEDICAL_SAVE_MST;
import static com.misyn.hrsystem.util.QueryConstants.UPDATE_TMP_AUTH_MEDICAL_DETAIL;
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
 * Save Medical details. update, authorize , reject 
 * deal with medical_tmp,medical_mst,medical_hst tables in DB
 */
@Repository
public class MeedicalDAOImpl implements MedicalDAO{
    
    private static final Logger LOGGER = Logger.getLogger(FoodDAOImpl.class);

    protected JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    /**
     * 
     * @param medicalDTO
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public MedicalDTO saveMaster(MedicalDTO medicalDTO) throws JDBCTemplateException {
        int result = 0;
        String query_insrt_hst = "INSERT INTO food_hst (SELECT * FROM medical_tmp order by medical_id desc limit 1)";

        try {
            result = jdbcTemplate.update(new PreparedStatementCreator() {

                @Override
                public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                    PreparedStatement statement = connection.prepareStatement(INSERT_MEDICAL_TMP, Statement.RETURN_GENERATED_KEYS);
                    statement.setString(1, medicalDTO.getDescription());
                    statement.setDouble(2, medicalDTO.getAmount());
                    statement.setString(3, medicalDTO.getImage());
                    statement.setString(4, "P");
                    statement.setString(5, "1900-01-01 00:00:00");
                    statement.setString(6, "");                    
                    statement.setString(7, medicalDTO.getUser_code());
                    return statement;
                }
            });

            if (result > 0) {
                result = jdbcTemplate.update(query_insrt_hst);
            }

        } catch (Exception e) {
            LOGGER.error(e);
        }
        return result > 0 ? medicalDTO : null;
    }

    /**
     * 
     * @param t
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public MedicalDTO updateMaster(MedicalDTO t) throws JDBCTemplateException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * 
     * @param medical_id
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public boolean deleteMaster(Object medical_id) throws JDBCTemplateException {
        int result = 0;

        try {
            //--Get data from TMP save HST
            result = jdbcTemplate.update(SELECT_TMP_MEDICAL_SAVE_HST, (Integer)medical_id);
            //---Delete Details TMP table
            result = jdbcTemplate.update(DELETE_MEDICAL_TMP, (Integer)medical_id);
   
        } catch (Exception e) {
            LOGGER.error(e);
        }

        return result > 0 ? true : false;
    }

    /**
     * 
     * @param medicalDTO
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public List<MedicalDTO> getPenMedicalList(MedicalDTO medicalDTO) throws JDBCTemplateException {
       return jdbcTemplate.query(SELECT_MEDICAL_PENDING_LIST,new BeanPropertyRowMapper(MedicalDTO.class),medicalDTO.getUser_code());
    }

    /**
     * 
     * @param medicalDTO
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public List<MedicalDTO> getPenAllMedicalList(MedicalDTO medicalDTO) throws JDBCTemplateException {
        return jdbcTemplate.query(SELECT_MEDICAL_ALL_PENDING_LIST,new BeanPropertyRowMapper(MedicalDTO.class));
    }

    /**
     * 
     * @param medicalDTO
     * @param medicalID
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public MedicalDTO authorizeMedical(MedicalDTO medicalDTO, int medicalID) throws JDBCTemplateException {
        int result = 0;

        try {
            //---Update Auth Details TMP table
            result = jdbcTemplate.update(UPDATE_TMP_AUTH_MEDICAL_DETAIL, "A", medicalDTO.getAuth_user(), medicalID);
            //---Save Detals Maseter Table
            result = jdbcTemplate.update(SELECT_TMP_MEDICAL_SAVE_MST, medicalID);
            //---Get data from MST save HST
            result = jdbcTemplate.update(SELECT_MST_MEDICAL_SAVE_HST, medicalID);
            //--Delete TMP Table
            result = jdbcTemplate.update(DELETE_MEDICAL_TMP, medicalID);
  

        } catch (Exception e) {
            LOGGER.error(e);
        }

        return result > 0 ? medicalDTO : null;
    }

    /**
     * 
     * @param medicalDTO
     * @param medicalID
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public MedicalDTO rejectMedical(MedicalDTO medicalDTO, int medicalID) throws JDBCTemplateException {
        int result = 0;

        try {
            //---Update Leave TMP table
            result = jdbcTemplate.update(UPDATE_TMP_AUTH_MEDICAL_DETAIL, "R", medicalDTO.getAuth_user(), medicalID);
            //--Get data from TMP save HST
            result = jdbcTemplate.update(SELECT_TMP_MEDICAL_SAVE_HST, medicalID);
            //--Delete TMP Table
            result = jdbcTemplate.update(DELETE_MEDICAL_TMP, medicalID);

        } catch (Exception e) {
            LOGGER.error(e);
        }
        return result > 0 ? medicalDTO : null;
    }

    /**
     * 
     * @param medicalDTO
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public List<MedicalDTO> getAuthMedicalList(MedicalDTO medicalDTO) throws JDBCTemplateException {
        return jdbcTemplate.query(SELECT_MEDICAL_AUTH_LIST,new BeanPropertyRowMapper(MedicalDTO.class),medicalDTO.getUser_code());
    }

    /**
     * 
     * @param medicalDTO
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public List<MedicalDTO> getRepMedicalList(MedicalDTO medicalDTO) throws JDBCTemplateException {
        ArrayList arguments = new ArrayList();
        
        String query_medical_list = SELECT_MEDICAL_REPORT_LIST;
        
        query_medical_list = (!medicalDTO.getUser_code().equals("")
                ? query_medical_list.concat(" WHERE mm.user_code = ?") : query_medical_list);
        
        if (!medicalDTO.getUser_code().equalsIgnoreCase("")) {
            arguments.add(medicalDTO.getUser_code());
        }
        
        return jdbcTemplate.query(query_medical_list, arguments.toArray(),new BeanPropertyRowMapper(MedicalDTO.class));
        
    }
    
}
