package com.bi.template.model;

import com.orm.SugarRecord;

/**
 * Created by Mohamad on 30-Jul-16.
 */
public class MockObject extends SugarRecord {

    // using sugar orm sqlite
    // http://satyan.github.io/sugar/index.html
    int userId;
    String title;
    String body;

    @Override
    public String toString() {
        return title;
    }
}
