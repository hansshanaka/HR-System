/*
 * MEdical DAO
 */
package com.misyn.hrsystem.dao.custom;

import com.misyn.hrsystem.dao.BaseDAO;
import com.misyn.hrsystem.dto.custom.MedicalDTO;
import com.misyn.hrsystem.exception.JDBCTemplateException;
import java.util.List;

/**
 *
 * @author Shanaka
 * Medical DAO interface contains all methods related to Medical Function
 */
public interface MedicalDAO extends BaseDAO<MedicalDTO>{
    
    public List<MedicalDTO> getPenMedicalList(MedicalDTO medicalDTO) throws JDBCTemplateException;
    
    public List<MedicalDTO> getPenAllMedicalList(MedicalDTO medicalDTO) throws JDBCTemplateException;
    
    public MedicalDTO authorizeMedical(MedicalDTO medicalDTO,int medicalID)throws JDBCTemplateException;
    
    public MedicalDTO rejectMedical(MedicalDTO medicalDTO,int medicalID)throws JDBCTemplateException;
    
    public List<MedicalDTO> getAuthMedicalList(MedicalDTO medicalDTO) throws JDBCTemplateException;
    
    public List<MedicalDTO> getRepMedicalList(MedicalDTO medicalDTO) throws JDBCTemplateException;
    
}
