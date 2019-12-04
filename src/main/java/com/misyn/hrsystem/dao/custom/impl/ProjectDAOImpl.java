/*
 *ProjectDAOImpl
 */
package com.misyn.hrsystem.dao.custom.impl;

import com.misyn.hrsystem.dao.custom.ProjectDAO;
import com.misyn.hrsystem.dto.custom.ProjectDTO;
import com.misyn.hrsystem.exception.JDBCTemplateException;
import static com.misyn.hrsystem.util.AppConstant.AND;
import static com.misyn.hrsystem.util.QueryConstants.INSERT_PROJECT;
import static com.misyn.hrsystem.util.QueryConstants.INSERT_PROJECT_EMP_DETAILS;
import static com.misyn.hrsystem.util.QueryConstants.SELECT_PROJECT_REPORT_LIST;
import static com.misyn.hrsystem.util.QueryConstants.SELECT_USER_PROJECT_LIST;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Shanaka
 * Save Project Details 
 * deal with project table in DB
 */
@Repository
public class ProjectDAOImpl implements ProjectDAO {

    private static final Logger LOGGER = Logger.getLogger(ProjectDAOImpl.class);

    protected JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * 
     * @param project
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public ProjectDTO saveMaster(ProjectDTO project) throws JDBCTemplateException {
        int result = 0;
        String query_insrt_hst = "INSERT INTO project (SELECT * FROM project order by project_id desc limit 1)";
        try {
            result = jdbcTemplate.update(new PreparedStatementCreator() {

                @Override
                public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                    PreparedStatement statement = connection.prepareStatement(INSERT_PROJECT);
                    statement.setString(1, project.getProject_name());
                    statement.setString(2, project.getDescription());
                    statement.setString(3, project.getClient_name());
                    statement.setString(4, project.getStart_date());
                    statement.setString(5, project.getQa_date());
                    statement.setString(6, project.getUat_date());
                    statement.setString(7, project.getDelivary_date());
                    statement.setString(8, project.getDev_emp());
                    statement.setString(9, project.getQa_emp());
                    statement.setString(10, project.getUi_emp());
                    statement.setString(11, project.getUser_code());

                    return statement;
                }
            });

            if (result > 0) {
                result = jdbcTemplate.update(query_insrt_hst);
            }

        } catch (Exception e) {
            LOGGER.error(e);
        }

        return result > 0 ? project : null;
    }

    /**
     * 
     * @param t
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public ProjectDTO updateMaster(ProjectDTO t) throws JDBCTemplateException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * 
     * @param id
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public boolean deleteMaster(Object id) throws JDBCTemplateException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * 
     * @param projectDTO
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public List<ProjectDTO> getUserProjectList(ProjectDTO projectDTO) throws JDBCTemplateException {

        ArrayList arguments = new ArrayList();
        arguments.add("%" + projectDTO.getUser_code() + "%");
        arguments.add("%" + projectDTO.getUser_code() + "%");
        arguments.add("%" + projectDTO.getUser_code() + "%");

        return jdbcTemplate.query(SELECT_USER_PROJECT_LIST, arguments.toArray(), new BeanPropertyRowMapper(ProjectDTO.class));
    }

    /**
     * 
     * @param projectDTO
     * @param emp
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public ProjectDTO insertDevEmp(ProjectDTO projectDTO, String emp) throws JDBCTemplateException {
        int result = 0;
        try {
            result = jdbcTemplate.update(new PreparedStatementCreator() {

                @Override
                public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                    PreparedStatement statement = connection.prepareStatement(INSERT_PROJECT_EMP_DETAILS);
                    statement.setString(1, emp);
                    statement.setInt(2, projectDTO.getDep_code());
                    statement.setInt(3, projectDTO.getProject_id());

                    return statement;
                }
            });

        } catch (Exception e) {
            LOGGER.error(e);
        }

        return result > 0 ? projectDTO : null;
    }

    /**
     * 
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public int getProjectID() throws JDBCTemplateException {
        String query = "SELECT MAX(project_id) FROM project";
        return jdbcTemplate.query(query, new ResultSetExtractor<Integer>() {

            @Override
            public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
                int id = 0;
                if (rs.next()) {
                    id = rs.getInt("MAX(project_id)");
                }
                return id;
            }
        });
    }

    /**
     * 
     * @param projectDTO
     * @param emp
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public ProjectDTO insertUIEmp(ProjectDTO projectDTO, String emp) throws JDBCTemplateException {
        int result = 0;
        try {
            result = jdbcTemplate.update(new PreparedStatementCreator() {

                @Override
                public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                    PreparedStatement statement = connection.prepareStatement(INSERT_PROJECT_EMP_DETAILS);
                    statement.setString(1, emp);
                    statement.setInt(2, projectDTO.getDep_code());
                    statement.setInt(3, projectDTO.getProject_id());

                    return statement;
                }
            });

        } catch (Exception e) {
            LOGGER.error(e);
        }

        return result > 0 ? projectDTO : null;
    }

    /**
     * 
     * @param projectDTO
     * @param emp
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public ProjectDTO insertQAEmp(ProjectDTO projectDTO, String emp) throws JDBCTemplateException {
        int result = 0;
        try {
            result = jdbcTemplate.update(new PreparedStatementCreator() {

                @Override
                public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                    PreparedStatement statement = connection.prepareStatement(INSERT_PROJECT_EMP_DETAILS);
                    statement.setString(1, emp);
                    statement.setInt(2, projectDTO.getDep_code());
                    statement.setInt(3, projectDTO.getProject_id());

                    return statement;
                }
            });

        } catch (Exception e) {
            LOGGER.error(e);
        }

        return result > 0 ? projectDTO : null;
    }

    /**
     * 
     * @param projectDTO
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public List<ProjectDTO> getRepProjectList(ProjectDTO projectDTO) throws JDBCTemplateException {
        ArrayList arguments = new ArrayList();
        
        String query_project_list = SELECT_PROJECT_REPORT_LIST;
        
        query_project_list = (!projectDTO.getUser_code().equals("")
                ? query_project_list.concat(AND + "p.dev_emp LIKE ? OR p.qa_emp LIKE ? OR p.ui_emp LIKE ?") : query_project_list);

        if (!projectDTO.getUser_code().equalsIgnoreCase("")) {
            arguments.add("%" + projectDTO.getUser_code() + "%");
            arguments.add("%" + projectDTO.getUser_code() + "%");
            arguments.add("%" + projectDTO.getUser_code() + "%");
        }

        return jdbcTemplate.query(query_project_list, arguments.toArray(), new BeanPropertyRowMapper(ProjectDTO.class));
    }

}
