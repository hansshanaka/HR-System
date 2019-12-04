/*
 * Medical Service
 */
package com.misyn.hrsystem.service.custom;

import com.misyn.hrsystem.dto.custom.MedicalDTO;
import com.misyn.hrsystem.exception.JDBCTemplateException;
import com.misyn.hrsystem.service.BaseService;
import java.util.List;

/**
 *
 * @author Shanaka
 * Medical service interface contains all methods related to Medical reimbursement Function
 */
public interface MedicalService extends BaseService<MedicalDTO>{
    
    public List<MedicalDTO> getPenMedicalList(MedicalDTO medicalDTO) throws JDBCTemplateException;
    
    public List<MedicalDTO> getPenAllMedicalList(MedicalDTO medicalDTO) throws JDBCTemplateException;
    
    public MedicalDTO authorizeMedical(MedicalDTO medicalDTO)throws JDBCTemplateException;
    
    public MedicalDTO rejectMedical(MedicalDTO medicalDTO)throws JDBCTemplateException;
    
    public List<MedicalDTO> getAuthMedicalList(MedicalDTO medicalDTO) throws JDBCTemplateException;
    
    public List<MedicalDTO> getRepMedicalList(MedicalDTO medicalDTO) throws JDBCTemplateException;
    
}
