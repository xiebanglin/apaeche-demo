package com.xiebl.service.impl;

import com.xiebl.common.DownloadStyleUtils;
import com.xiebl.mapper.UserInfoMapper;
import com.xiebl.model.UserInfo;
import com.xiebl.service.DownloadExcelService;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author Administrator
 */
@Service
@Transactional
public class DownloadExcelServiceImpl implements DownloadExcelService {

    @Resource
    private UserInfoMapper userInfoDao;


    @Override
    public void downloadData(OutputStream os, UserInfo userInfo) throws IOException {
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet();
        wb.setSheetName(0, "用户信息");
        genHead(wb, sheet, false);
        genBody(wb, sheet, userInfo);
        wb.write(os);
        os.flush();
    }

    /**
     * 生成模板文件
     *
     * @param os
     * @throws IOException
     */
    @Override
    public void downloadTemplate(OutputStream os) throws IOException {
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet();
        wb.setSheetName(0, "用户信息");
        genHead(wb, sheet, false);
        wb.write(os);
        os.flush();
    }

    /**
     * 生成表头
     *
     * @param wb
     * @param sheet
     * @param template 是否模板
     */
    private void genHead(HSSFWorkbook wb, HSSFSheet sheet, boolean template) {

        HSSFFont font = DownloadStyleUtils.genFont(wb, "微软雅黑", 12, true,
                HSSFColor.HSSFColorPredefined.BLACK.getIndex());
        HSSFCellStyle style = DownloadStyleUtils.genStyle(wb, font, HorizontalAlignment.CENTER,
                VerticalAlignment.CENTER, true, 242, 242, 242);

        // 表头名
        String[] filed = null;
        // 表头大小
        int[] filedLength = null;

        if (template) {
            filed = new String[]{"姓名", "年龄", "地址", "电话"};
            filedLength = new int[]{100, 100, 100, 100};
        } else {
            filed = new String[]{"姓名", "年龄", "地址", "电话"};
            filedLength = new int[]{100, 100, 100, 100};
        }

        HSSFRow head = sheet.createRow(0);
        for (int colNum = 0; colNum < filed.length; colNum++) {
            HSSFCell cell = head.createCell(colNum);
            cell.setCellValue(filed[colNum]);
            sheet.setColumnWidth(colNum, filedLength[colNum] * 37);
            cell.setCellStyle(style);
        }
    }

    /**
     * 生成数据
     *
     * @param wb
     * @param sheet
     * @param userInfo
     */
    private void genBody(HSSFWorkbook wb, HSSFSheet sheet, UserInfo userInfo) {
        // 设置风格
        HSSFFont blackfont = DownloadStyleUtils.genFont(wb, "微软雅黑", 12, false,
                HSSFColor.HSSFColorPredefined.BLACK.getIndex());
        HSSFCellStyle blackLeft = DownloadStyleUtils.genStyle(wb, blackfont, HorizontalAlignment.LEFT,
                VerticalAlignment.CENTER, true, null, null, null);
        HSSFCellStyle blackRight = DownloadStyleUtils.genStyle(wb, blackfont, HorizontalAlignment.RIGHT,
                VerticalAlignment.CENTER, true, null, null, null);

        // 获取数据
        List<UserInfo> list = userInfoDao.getUserInfo(userInfo);
        // 设置日期格式
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 遍历数据并填充到excel中
        for (int rowNum = 0; rowNum < list.size(); rowNum++) {
            UserInfo userInfo1 = list.get(rowNum);
            HSSFRow row = sheet.createRow(rowNum + 1);
            for (int colNum = 0; colNum < 8; colNum++) {
                HSSFCell cell = row.createCell(colNum);
                String value = null;
                switch (colNum) {
                    case 0:
                        value = userInfo1.getUserName();
                        cell.setCellStyle(blackLeft);
                        break;
                    case 1:
                        value = userInfo1.getAge().toString();
                        cell.setCellStyle(blackRight);
                        break;
                    case 2:
                        value = userInfo1.getAddress();
                        cell.setCellStyle(blackLeft);
                        break;
                    case 3:
                        value = userInfo1.getPhoneNum();
                        cell.setCellStyle(blackRight);
                        break;
                }
                cell.setCellValue(value);
            }
        }
    }
}
