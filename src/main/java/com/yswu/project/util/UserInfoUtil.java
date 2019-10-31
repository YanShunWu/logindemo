package com.yswu.project.util;

import org.apache.logging.log4j.util.Strings;
import org.springframework.util.DigestUtils;

/**
 * 用户信息加密解密的工具
 *
 * @Author yswu3
 * @Date 2019/10/31.
 */
public class UserInfoUtil {

    private static final String AES_KEY = "1234567890123456";

    /**
     * 获取转换后的密码
     *
     * @param originalPwd 原始密码
     * @return
     */
    public static String getTransedPwd(String originalPwd) {
        if (Strings.isBlank(originalPwd)) {
            return originalPwd;
        }
        return BCrypt.hashpw(md5(originalPwd), genSalt());
    }

    /**
     * 判断密码是否正确
     *
     * @param inpw    用户输入的密码
     * @param storepw 数据库存储的密码
     * @return true-密码正确
     */
    public static boolean checkpw(String inpw, String storepw) {
        if (Strings.isBlank(inpw)) {
            return inpw.equals(storepw);
        }
        if (BCrypt.checkpw(md5(inpw), storepw)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 获取脱敏后的信息
     *
     * @param originalInfo 原始信息
     * @return 脱敏后的信息
     */
    public static String getDesensitizationResult(String originalInfo) throws Exception {
        if (Strings.isBlank(originalInfo)) {
            return originalInfo;
        }
        return AESUtil.encrypt(originalInfo, AES_KEY);
    }

    /**
     * 获取还原后的脱敏信息
     *
     * @param desensitizationInfo 脱敏后的信息
     * @return 还原后的脱敏信息
     */
    public static String getOriginalInfo(String desensitizationInfo) throws Exception {
        if (Strings.isBlank(desensitizationInfo)) {
            return desensitizationInfo;
        }
        return AESUtil.decrypt(desensitizationInfo, AES_KEY);
    }


    /**
     * 生成盐
     *
     * @return
     */
    private static String genSalt() {
        return BCrypt.gensalt(12);
    }

    /**
     * 生成md5值
     * @param src
     * @return
     */
    private static String md5(String src) {
        return DigestUtils.md5DigestAsHex(src.getBytes());
    }


}
