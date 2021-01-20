package com.xiebl.mapper;

import com.xiebl.model.UserInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @ProjectName: apaechepoi-demo
 * @Package: com.xiebl.dao
 * @ClassName: UserInfoDao
 * @Author: xiebanglin
 * @Description: 用户信息dao
 * @Date: 2021/1/20 11:45
 * @Version: 1.0
 */
@Mapper
public interface UserInfoMapper {
    /**
     * 查询用户信息列表
     *
     * @param userInfo
     * @return
     */
    public List<UserInfo> getUserInfo(UserInfo userInfo);

    /**
     * 新增用户信息
     *
     * @param userInfo
     */
    public void insertUserInfo(UserInfo userInfo);

}
