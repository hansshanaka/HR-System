/*

 */
package com.misyn.hrsystem.service.custom;

import com.misyn.hrsystem.dto.custom.TaxiDTO;
import com.misyn.hrsystem.exception.JDBCTemplateException;
import com.misyn.hrsystem.service.BaseService;
import java.util.List;

/**
 *
 * @author Shanaka
 * Travel service interface contains all methods related to Travel reimbursement Function
 */
public interface TravelService extends BaseService<TaxiDTO>{
    
    public List<TaxiDTO> getPenTravelList(TaxiDTO taxiDTO) throws JDBCTemplateException;
    
    public List<TaxiDTO> getPenAllTravelList(TaxiDTO taxiDTO) throws JDBCTemplateException;
    
    public TaxiDTO authorizeTravel(TaxiDTO taxiDTO)throws JDBCTemplateException;
    
    public TaxiDTO rejectTaxi(TaxiDTO taxiDTO)throws JDBCTemplateException;
    
    public List<TaxiDTO> getAuthTravelList(TaxiDTO taxiDTO) throws JDBCTemplateException;
    
    public List<TaxiDTO> getRepTravelList(TaxiDTO taxiDTO) throws JDBCTemplateException;
}
