package com.bi.template.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by Mohamad on 30-Jul-16.
 */
@Table(name = "Mock", id = "_id")
public class MockObject extends Model {
    @Column(name ="id")
    public long id;
    @Column(name = "userId")
    public int userId;
    @Column(name = "title")
    public String title;
    @Column(name = "body")
    public String body;

    @Override
    public String toString() {
        return title;
    }
}
