/*
 * TravelDAO
 */
package com.misyn.hrsystem.dao.custom;

import com.misyn.hrsystem.dao.BaseDAO;
import com.misyn.hrsystem.dto.custom.TaxiDTO;
import com.misyn.hrsystem.exception.JDBCTemplateException;
import java.util.List;

/**
 *
 * @author Shanaka
 * Travel DAO interface contains all methods related to Travel Function
 */
public interface TravelDAO extends BaseDAO<TaxiDTO>{
    
    public List<TaxiDTO> getPenTravelList(TaxiDTO taxiDTO) throws JDBCTemplateException;
    
    public List<TaxiDTO> getPenAllTravelList(TaxiDTO taxiDTO) throws JDBCTemplateException;
    
    public TaxiDTO authorizeTaxi(TaxiDTO taxiDTO,int taxiID)throws JDBCTemplateException;
    
    public TaxiDTO rejectTaxi(TaxiDTO taxiDTO,int taxiID)throws JDBCTemplateException;
    
    public List<TaxiDTO> getAuthTravelList(TaxiDTO taxiDTO) throws JDBCTemplateException;
    
    public List<TaxiDTO> getRepTravelList(TaxiDTO taxiDTO) throws JDBCTemplateException;
    
}
