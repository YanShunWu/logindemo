package com.yswu.project.repository;

import com.yswu.project.model.UserInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @Author yswu3
 * @Date 2019/10/31.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserInfoRepositoryTest {
    @Resource
    private UserInfoRepository userInfoRepository;

    @Test
    public void findByUserNameTest() {
        UserInfo userInfo = userInfoRepository.findByUserName("findByUserNameTest");
        if (userInfo == null) {
            userInfoRepository.save(new UserInfo("findByUserNameTest", "11111", "123", "340"));
        }
        userInfo = userInfoRepository.findByUserName("findByUserNameTest");
        Assert.assertNotNull(userInfo);
    }

}
