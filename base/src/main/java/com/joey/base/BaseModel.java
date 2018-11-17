package com.joey.base;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;

/**
 * Created by Joey on 2018/2/24.
 */

public abstract class BaseModel implements Serializable {

    public Object getValue(String key) throws Exception{

        Method[] m = getClass().getMethods();
        for (int i = 0; i < m.length; i++) {
            if (("get" + key).toLowerCase().equals(m[i].getName().toLowerCase()))
                return m[i].invoke(this);
        }
        return null;
    }
}
