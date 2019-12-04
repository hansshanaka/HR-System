/*
 * TravelServiceImpl
 */
package com.misyn.hrsystem.service.custom.impl;

import com.misyn.hrsystem.dao.custom.TravelDAO;
import com.misyn.hrsystem.dto.custom.TaxiDTO;
import com.misyn.hrsystem.exception.JDBCTemplateException;
import com.misyn.hrsystem.service.custom.TravelService;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Shanaka
 * save travel authorize travel, get Travel Details
 */
@Service
public class TravelServiceImpl implements TravelService{
    
    private static final Logger LOGGER = Logger.getLogger(TravelServiceImpl.class);
    
    @Autowired
    private TravelDAO travelDAO;

    /**
     * 
     * @param taxiDTO
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public TaxiDTO insert(TaxiDTO taxiDTO) throws JDBCTemplateException {
        return travelDAO.saveMaster(taxiDTO);
    }

    /**
     * 
     * @param t
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public TaxiDTO update(TaxiDTO t) throws JDBCTemplateException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * 
     * @param taxiDTO
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public TaxiDTO delete(TaxiDTO taxiDTO) throws JDBCTemplateException {
        boolean result = false;
        try {
            result = travelDAO.deleteMaster(taxiDTO.getTaxi_id());
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return result ? taxiDTO : null;
    }

    /**
     * 
     * @param taxiDTO
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public List<TaxiDTO> getPenTravelList(TaxiDTO taxiDTO) throws JDBCTemplateException {
        return travelDAO.getPenTravelList(taxiDTO);
    }

    /**
     * 
     * @param taxiDTO
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public List<TaxiDTO> getPenAllTravelList(TaxiDTO taxiDTO) throws JDBCTemplateException {
        return travelDAO.getPenAllTravelList(taxiDTO);
    }

    /**
     * authorize
     * @param taxiDTO
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public TaxiDTO authorizeTravel(TaxiDTO taxiDTO) throws JDBCTemplateException {
        TaxiDTO taxi= null;

        try {            
            Integer[] taxiIDArray = taxiDTO.getTaxiID();
            for(int i = 0; i < taxiIDArray.length; i++) {
                taxi = travelDAO.authorizeTaxi(taxiDTO,taxiIDArray[i]);
            }
            
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return taxi;
    }

    /**
     * 
     * @param taxiDTO
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public TaxiDTO rejectTaxi(TaxiDTO taxiDTO) throws JDBCTemplateException {
        TaxiDTO taxi= null;        
        try {            
            Integer[] taxiIDArray = taxiDTO.getTaxiID();
            for(int i = 0; i < taxiIDArray.length; i++) {                               
                taxi = travelDAO.rejectTaxi(taxiDTO,taxiIDArray[i]);
            }
            
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return taxi;
    }

    /**
     * 
     * @param taxiDTO
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public List<TaxiDTO> getAuthTravelList(TaxiDTO taxiDTO) throws JDBCTemplateException {
        return travelDAO.getAuthTravelList(taxiDTO);
    }

    /**
     * 
     * @param taxiDTO
     * @return
     * @throws JDBCTemplateException 
     */
    @Override
    public List<TaxiDTO> getRepTravelList(TaxiDTO taxiDTO) throws JDBCTemplateException {
        return travelDAO.getRepTravelList(taxiDTO);
    }
    
}
