package com.yswu.project.repository;

import com.yswu.project.model.UserInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;

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
    public void testSave() {
        Date date = new Date();

        userInfoRepository.save(new UserInfo("wuyanshun2","11111","123","340"));

//		Assert.assertEquals(3, userRepository.findAll().size());
//		Assert.assertEquals("bb", userRepository.findByUserNameOrEmail("bb", "bb@126.com").getNickName());
//		userRepository.delete(userRepository.findByUserName("aa"));
    }
}
