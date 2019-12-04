/*
 *
 */
package com.misyn.hrsystem.dto.custom;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Shanaka
 * Gride DTO for List convert to Json
 */
public class GridDTO {

    private int draw;
    private int recordsTotal;
    private int recordsFiltered;
    private List<String[]> data = new ArrayList<>();

    public GridDTO(int draw, int recordsTotal, int recordsFiltered) {
        this.draw = draw;
        this.recordsTotal = recordsTotal;
        this.recordsFiltered = recordsFiltered;
    }

    public GridDTO() {
    }

    /**
     * @return the draw
     */
    public int getDraw() {
        return draw;
    }

    /**
     * @param draw the draw to set
     */
    public void setDraw(int draw) {
        this.draw = draw;
    }

    /**
     * @return the recordsTotal
     */
    public int getRecordsTotal() {
        return recordsTotal;
    }

    /**
     * @param recordsTotal the recordsTotal to set
     */
    public void setRecordsTotal(int recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    /**
     * @return the recordsFiltered
     */
    public int getRecordsFiltered() {
        return recordsFiltered;
    }

    /**
     * @param recordsFiltered the recordsFiltered to set
     */
    public void setRecordsFiltered(int recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

    /**
     * @return the data
     */
    public List<String[]> getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(List<String[]> data) {
        this.data = data;
    }

}

