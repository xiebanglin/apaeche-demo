package com.xiebl.controller;

import com.xiebl.common.NormalBusiException;
import com.xiebl.common.Result;
import com.xiebl.model.UserInfo;
import com.xiebl.service.DownloadExcelService;
import com.xiebl.service.ImportExcelService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @ProjectName: apaechepoi-demo
 * @Package: com.xiebl.controller
 * @ClassName: ExcelManageController
 * @Author: xiebanglin
 * @Description: excel表格导入导出
 * @Date: 2021/1/20 11:36
 * @Version: 1.0
 */
@RestController
@Slf4j
public class ExcelManageController {

    @Resource
    private DownloadExcelService downloadExcelService;

    @Resource
    private ImportExcelService importExcelService;

    /**
     * 文件上传
     *
     * @param file
     * @return
     */
    @RequestMapping("/importExcel")
    @ResponseBody
    public Result upload(@RequestParam(name = "file") MultipartFile file) throws Exception {
        Result ret = new Result();
        String fileName = file.getOriginalFilename();
        if (!StringUtils.endsWith(fileName, "xlsx") && !StringUtils.endsWith(fileName, "xls")) {
            ret.setCode(-2);
            ret.setMsg("上传失败，文件类型错误");
            return ret;
        }

        File temp = null;
        try {
            temp = File.createTempFile(fileName, UUID.randomUUID().toString());
            file.transferTo(temp);
            temp.deleteOnExit();
        } catch (IOException e) {
            ret.setCode(-2);
            ret.setMsg("上传失败,请联系管理员");
            log.info(e.getMessage());
            return ret;
        }

        StringBuffer msg = null;
        try {
            msg = importExcelService.uploadExcel(temp, fileName);
        } catch (NormalBusiException e) {
            ret.setCode(-2);
            ret.setMsg(e.getMessage());
            return ret;
        }

        ret.setCode(0);
        ret.setMsg(msg.toString());
        return ret;
    }

    /**
     * 导出
     *
     * @param response
     * @param cond
     * @throws IOException
     */
    @RequestMapping("/downloadExcel")
    public void download(HttpServletResponse response, UserInfo cond, String start, String end)
            throws IOException {
        String fileName = "用户信息-" + new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + ".xls";
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        response.setHeader("Content-disposition",
                "attachment;filename*=UTF-8''" + URLEncoder.encode(fileName, "UTF-8"));
        OutputStream os = response.getOutputStream();
        downloadExcelService.downloadData(os, cond);
    }

    /**
     * 导出模板
     *
     * @param response
     * @throws IOException
     */
    @RequestMapping("/downloadTemplate")
    public void download(HttpServletResponse response) throws IOException {
        String fileName = "用户信息模板.xls";
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        response.setHeader("Content-disposition",
                "attachment;filename*=UTF-8''" + URLEncoder.encode(fileName, "UTF-8"));
        OutputStream os = response.getOutputStream();
        downloadExcelService.downloadTemplate(os);
    }
}
