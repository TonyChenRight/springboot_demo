package com.tony.springboot.demo.context;

import com.tony.springboot.demo.model.bo.UserBO;

public class UserConext {
    private static final ThreadLocal<UserBO> CONTEXT = ThreadLocal.withInitial(UserBO::new);

    public static void setUser(UserBO user) {
        CONTEXT.set(user);
    }
    public static UserBO getUser() {
        return CONTEXT.get();
    }
    public static void clear() {
        CONTEXT.remove();
    }
}
