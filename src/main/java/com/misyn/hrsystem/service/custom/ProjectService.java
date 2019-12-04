/*
 * ProjectService
 */
package com.misyn.hrsystem.service.custom;

import com.misyn.hrsystem.dto.custom.ProjectDTO;
import com.misyn.hrsystem.exception.JDBCTemplateException;
import com.misyn.hrsystem.service.BaseService;
import java.util.List;

/**
 *
 * @author Shanaka
 * Project service interface contains all methods related to Project Function
 */
public interface ProjectService extends BaseService<ProjectDTO>{
    
    public List<ProjectDTO> getUserProjectList(ProjectDTO projectDTO) throws JDBCTemplateException;
    
    public List<ProjectDTO> getRepProjectList(ProjectDTO projectDTO) throws JDBCTemplateException;
    
}
