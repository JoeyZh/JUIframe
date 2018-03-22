package com.joey.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.joey.db.dao.DaoMaster;
import com.joey.db.dao.DaoSession;

/**
 * Created by Joey on 18/3/20.
 * 用于工具类的的编写
 */

public class DataHelperUtil {

    private DaoMaster.DevOpenHelper mHelper;
    private SQLiteDatabase db;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;

    private static DataHelperUtil manager;

    public synchronized static DataHelperUtil getInstance() {
        if (manager != null) {
            return manager;
        }
        synchronized (DataHelperUtil.class) {
            if (manager != null)
                return manager;
            manager = new DataHelperUtil();
            return manager;
        }
    }

    public void init(Context context, String name) {
        mHelper = new DaoMaster.DevOpenHelper(context, name, null);
        db = mHelper.getWritableDatabase();
        // 注意：该数据库连接属于 DaoMaster，所以多个 Session 指的是相同的数据库连接。
        mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();
    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }

    public SQLiteDatabase getDb() {
        return db;
    }

}
