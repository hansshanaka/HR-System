/*
 *ProjectServiceImpl
 */
package com.misyn.hrsystem.service.custom.impl;

import com.misyn.hrsystem.dao.custom.ProjectDAO;
import com.misyn.hrsystem.dto.custom.ProjectDTO;
import com.misyn.hrsystem.exception.JDBCTemplateException;
import com.misyn.hrsystem.service.custom.ProjectService;
import java.util.Arrays;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Shanaka
 * insert and get project details
 */
@Service
public class ProjectServiceImpl implements ProjectService{
    
    private static final Logger LOGGER = Logger.getLogger(FoodServiceImpl.class);
    
    @Autowired
    private ProjectDAO projectDAO;

    /**
     * Save Project
     * @param project
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public ProjectDTO insert(ProjectDTO project) throws JDBCTemplateException {
        ProjectDTO projectDTO = null;
        String dev_emp = "" ;
        String ui_emp = "" ;
        String qa_emp = "" ;
        int pro_id = 0;
        
        try {
            dev_emp = Arrays.toString(project.getDevEmp());
            dev_emp = dev_emp.replace("[", "");
            dev_emp = dev_emp.replace("]", "");            
            project.setDev_emp(dev_emp);
            
            ui_emp = Arrays.toString(project.getUiEmp());
            ui_emp = ui_emp.replace("[", "");
            ui_emp = ui_emp.replace("]", "");            
            project.setUi_emp(ui_emp);
            
            qa_emp = Arrays.toString(project.getQaEmp());
            qa_emp = qa_emp.replace("[", "");
            qa_emp = qa_emp.replace("]", "");            
            project.setQa_emp(qa_emp);
            
            projectDTO = projectDAO.saveMaster(project);
            
            pro_id = projectDAO.getProjectID();
            project.setProject_id(pro_id);
            
            String[] devEmpArray = project.getDevEmp();
            for(int i = 0; i < devEmpArray.length; i++) {
                project.setDep_code(2);
                projectDTO = projectDAO.insertDevEmp(project, devEmpArray[i]);
            }

            String[] uiEmpArray = project.getUiEmp();
            for(int i = 0; i < uiEmpArray.length; i++) {
                project.setDep_code(6);
                projectDTO = projectDAO.insertUIEmp(project, uiEmpArray[i]);
            }
            
            String[] qaEmpArray = project.getQaEmp();
            for(int i = 0; i < qaEmpArray.length; i++) {
                project.setDep_code(7);
                projectDTO = projectDAO.insertQAEmp(project, qaEmpArray[i]);
            }

        } catch (Exception e) {
            LOGGER.error(e);
        }
        return projectDTO;
    }

    @Override
    public ProjectDTO update(ProjectDTO t) throws JDBCTemplateException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ProjectDTO delete(ProjectDTO t) throws JDBCTemplateException {
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
        return projectDAO.getUserProjectList(projectDTO);
    }

    /**
     * 
     * @param projectDTO
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public List<ProjectDTO> getRepProjectList(ProjectDTO projectDTO) throws JDBCTemplateException {
        return projectDAO.getRepProjectList(projectDTO);
    }
    
}
