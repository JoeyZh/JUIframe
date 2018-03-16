package com.joey.ui.general;

import android.app.Activity;

import java.util.Stack;

/**
 * Created by Joey on 2018/3/13.
 */

public class JActivityManager {
    public Stack<Activity> activityStack;
    private static JActivityManager instance;

    private JActivityManager() {
    }

    public static JActivityManager getActivityManager() {
        if (instance == null) {
            instance = new JActivityManager();
        }

        return instance;
    }

    public void pop(Activity activity) {
        activityStack.remove(activity);
    }

    private void popActivity(Activity activity) {
        if (activity != null) {
            activity.finish();
        }
    }

    public Activity currentActivity() {
        Activity activity = null;
        if (null != activityStack && 0 != activityStack.size()) {
            activity = (Activity) activityStack.lastElement();
        }

        return activity;
    }

    public void pushActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack();
        }

        activityStack.add(activity);
    }

    public void popAllActivityExceptOne(Class<?> cls) {
        if (null != activityStack && activityStack.size() != 0) {
            int len = activityStack.size();

            for (int i = len - 1; i >= 0; --i) {
                Activity activity = (Activity) activityStack.get(i);
                if (activity != null && (null == cls || !activity.getClass().equals(cls))) {
                    this.popActivity(activity);
                }
            }

        }
    }

    public void popThisActivity(Class<?> cls) {
        while (true) {
            Activity activity = this.currentActivity();
            if (activity != null) {
                if (null == cls || !activity.getClass().equals(cls)) {
                    continue;
                }

                this.popActivity(activity);
            }

            return;
        }
    }

    public void popUntilActivity(Class<?> cls) {
        while (!activityStack.isEmpty()) {
            if (this.currentActivity().getClass().equals(cls)) {
                return;
            }
            this.popActivity(this.currentActivity());
        }

    }

    public boolean isMain() {
        if (activityStack == null) {
            return false;
        }
        return activityStack.size() == 1;
    }
}
