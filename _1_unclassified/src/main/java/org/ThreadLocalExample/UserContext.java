package org.ThreadLocalExample;

import java.io.Closeable;
import java.io.IOException;

/**
 * 实现 AutoCloseable，try-source的时候自动释放资源 （移除保存在 threadLocal 的数据）
 *
 * @author hongchuzhen
 * @date 12/27/2023
 */
public class UserContext implements AutoCloseable {
    /**
     * 如果不明白这里为什么可以用 static 点 ThreadLocal 的 get方法看看
     */
    static final ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public String getContextUser() {
        return threadLocal.get();
    }

    /**
     *
     * @param userId
     */
    public void setContextUser(String userId) {
        threadLocal.set(userId);
    }
    @Override
    public void close() throws IOException {
        System.out.println("i was closed !!!");
        threadLocal.remove();
    }
}
