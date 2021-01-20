package com.xiebl.service;

import com.xiebl.model.UserInfo;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @ProjectName: apaechepoi-demo
 * @Package: com.xiebl.service
 * @ClassName: DownloadExcelService
 * @Author: xiebanglin
 * @Description: 下载excel表格
 * @Date: 2021/1/20 11:42
 * @Version: 1.0
 */
public interface DownloadExcelService {
    /**
     * 导出excel表
     *
     * @param os
     * @param userInfo
     * @throws IOException
     */
    public void downloadData(OutputStream os, UserInfo userInfo) throws IOException;
}
