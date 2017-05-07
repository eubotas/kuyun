package com.kuyun.eam.vo;

import com.kuyun.eam.dao.model.EamParts;

/**
 * Created by user on 5/1/2017.
 */
public class EamPartVO extends EamParts {
    private String categoryName;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
