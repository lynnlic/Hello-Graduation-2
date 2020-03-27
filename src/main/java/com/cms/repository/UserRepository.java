package com.cms.repository;

import com.cms.domain.UserInfo;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * {@link UserInfo} {@link Repository}
 */
@Repository
public class UserRepository {
    /**
     * 采用内存型的存储方式->Map
     */
    private final ConcurrentMap<Integer, UserInfo> repository = new ConcurrentHashMap<>();

    private final static AtomicInteger idGenerator  = new AtomicInteger();

    /**
     *保存用户对象
     * @param userInfo {@link UserInfo} 对象
     * @return 如果保存成功，返回<code>true</code>,否则返回<code>false</code>
     */
    public boolean save(UserInfo userInfo){
        Integer id = idGenerator.incrementAndGet();//从1开始增加
        userInfo.setID(id);
        return repository.put(id, userInfo) == null;
    }

    /**
     * 返回所有用户
     */
    public Collection<UserInfo> findAll(){
        return repository.values();
    }

    /**
     * 删除ID指定用户
     */
    //public String
}

