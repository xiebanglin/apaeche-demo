package com.xiebl.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @ProjectName: apaechepoi-demo
 * @Package: com.xiebl.model
 * @ClassName: UserInfo
 * @Author: xiebanglin
 * @Description: 用户信息
 * @Date: 2021/1/20 11:43
 * @Version: 1.0
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {

    private String id;
    private String userName;
    private Integer age;
    private String address;
    private String phoneNum;

}
