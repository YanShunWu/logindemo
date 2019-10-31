package com.yswu.project.model;



import javax.persistence.*;

/**
 * @Author yswu3
 * @Date 2019/10/31.
 */
@Entity
public class UserInfo {
    public UserInfo() {
    }
    public UserInfo(String userName, String passWord, String mobile, String idCardNum) {
        this.userName = userName;
        this.password = passWord;
        this.mobile = mobile;
        this.idCardNum = idCardNum;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true, columnDefinition = "varchar(20)")
    private String userName;

    @Column(nullable = false, columnDefinition = "varchar(255)")
    private String password;

    @Column(nullable = false , columnDefinition = "varchar(50)")
    private String mobile;

    @Column(nullable = false, columnDefinition = "varchar(80)")
    private String idCardNum;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getIdCardNum() {
        return idCardNum;
    }

    public void setIdCardNum(String idCardNum) {
        this.idCardNum = idCardNum;
    }
}
