package com.yswu.project.repository;

import com.yswu.project.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author yswu3
 * @Date 2019/10/31.
 */
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
    UserInfo findByUserName(String userName);
}
