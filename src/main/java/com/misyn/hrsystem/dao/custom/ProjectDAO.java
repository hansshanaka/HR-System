/*
 * ProjectDAO
 */
package com.misyn.hrsystem.dao.custom;

import com.misyn.hrsystem.dao.BaseDAO;
import com.misyn.hrsystem.dto.custom.ProjectDTO;
import com.misyn.hrsystem.exception.JDBCTemplateException;
import java.util.List;

/**
 *
 * @author Shanaka
 * Project DAO interface contains all methods related to Project Function
 */
public interface ProjectDAO extends BaseDAO<ProjectDTO>{
    
    public List<ProjectDTO> getUserProjectList(ProjectDTO projectDTO) throws JDBCTemplateException;
    
    public ProjectDTO insertDevEmp(ProjectDTO projectDTO, String emp)throws JDBCTemplateException;
    
    public ProjectDTO insertUIEmp(ProjectDTO projectDTO, String emp)throws JDBCTemplateException;
    
    public ProjectDTO insertQAEmp(ProjectDTO projectDTO, String emp)throws JDBCTemplateException;
    
    public int getProjectID()throws JDBCTemplateException;
    
    public List<ProjectDTO> getRepProjectList(ProjectDTO projectDTO) throws JDBCTemplateException;
  
    
}
