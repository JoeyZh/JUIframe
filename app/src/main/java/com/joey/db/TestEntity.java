package com.joey.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Joey on 2018/3/21.
 */

@Entity
public class TestEntity {

    @Id
    private String id;
    private String name;

    @Generated(hash = 1244894756)
    public TestEntity(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Generated(hash = 1020448049)
    public TestEntity() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
