package com.xiebl.service;

import java.io.File;

/**
 * @ProjectName: apaechepoi-demo
 * @Package: com.xiebl.service
 * @ClassName: ImportExcel
 * @Author: xiebanglin
 * @Description: 导入excel
 * @Date: 2021/1/20 11:37
 * @Version: 1.0
 */

public interface ImportExcelService {
    /**
     * 导入excel表格
     *
     * @param temp
     * @param fileName
     * @return
     * @throws
     */
    public StringBuffer uploadExcel(File temp, String fileName) throws Exception;
}
