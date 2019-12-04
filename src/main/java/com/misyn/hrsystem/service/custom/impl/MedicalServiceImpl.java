/*
 * 
 */
package com.misyn.hrsystem.service.custom.impl;

import com.misyn.hrsystem.dao.custom.MedicalDAO;
import com.misyn.hrsystem.dto.custom.MedicalDTO;
import com.misyn.hrsystem.exception.JDBCTemplateException;
import com.misyn.hrsystem.service.custom.MedicalService;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Shanaka
 * Save and get  medical claims
 * authorized and reject medical claims
 */
@Service
public class MedicalServiceImpl implements MedicalService{
    
    private static final Logger LOGGER = Logger.getLogger(FoodServiceImpl.class);
    
    @Autowired
    private MedicalDAO medicalDAO;

    /**
     * Save medical claim
     * @param medical
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public MedicalDTO insert(MedicalDTO medical) throws JDBCTemplateException {
        return medicalDAO.saveMaster(medical);
    }

    /**
     * update medical claims
     * @param t
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public MedicalDTO update(MedicalDTO t) throws JDBCTemplateException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * delete medical claim
     * @param medicalDTO
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public MedicalDTO delete(MedicalDTO medicalDTO) throws JDBCTemplateException {
        boolean result = false;
        try {
            result = medicalDAO.deleteMaster(medicalDTO.getMedical_id());
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return result ? medicalDTO : null;
    }

    /**
     * get pending details
     * @param medicalDTO
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public List<MedicalDTO> getPenMedicalList(MedicalDTO medicalDTO) throws JDBCTemplateException {
         return medicalDAO.getPenMedicalList(medicalDTO);
    }

    /**
     * get pending all
     * @param medicalDTO
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public List<MedicalDTO> getPenAllMedicalList(MedicalDTO medicalDTO) throws JDBCTemplateException {
        return medicalDAO.getPenAllMedicalList(medicalDTO);
    }

    /**
     * authorize medical claim
     * @param medicalDTO
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public MedicalDTO authorizeMedical(MedicalDTO medicalDTO) throws JDBCTemplateException {
        MedicalDTO medical= null;

        try {            
            Integer[] medicalIDArray = medicalDTO.getMedicalID();
            for(int i = 0; i < medicalIDArray.length; i++) { 
                medical = medicalDAO.authorizeMedical(medicalDTO,medicalIDArray[i]);
            }
            
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return medicalDTO;
    }

    /**
     * reject Medical
     * @param medicalDTO
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public MedicalDTO rejectMedical(MedicalDTO medicalDTO) throws JDBCTemplateException {
        MedicalDTO medical= null;
        
        try {            
            Integer[] medicalIDArray = medicalDTO.getMedicalID();
            for(int i = 0; i < medicalIDArray.length; i++) {                               
                medical = medicalDAO.rejectMedical(medicalDTO,medicalIDArray[i]);
            }
            
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return medical;
    }

    /**
     * authorize medical
     * @param medicalDTO
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public List<MedicalDTO> getAuthMedicalList(MedicalDTO medicalDTO) throws JDBCTemplateException {
        return medicalDAO.getAuthMedicalList(medicalDTO);
    }

    /**
     * generate medical claims
     * @param medicalDTO
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public List<MedicalDTO> getRepMedicalList(MedicalDTO medicalDTO) throws JDBCTemplateException {
        return medicalDAO.getRepMedicalList(medicalDTO);
    }
    
}
