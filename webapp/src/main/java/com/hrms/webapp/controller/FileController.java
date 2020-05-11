package com.hrms.webapp.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.hrms.api.domain.dto.FileDocument;
import com.hrms.webapp.mongoDBService.FileService;
import com.hrms.api.until.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

/**
 * @author 孔超
 * @date 2020/5/11 15:17
 */
@Slf4j
@RestController
@RequestMapping("files")
public class FileController {
    @Resource
    FileService fileService;

    /**
     * 上传文件列表
     *
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @RequestMapping("/list")
    public List<FileDocument> list(int pageIndex, int pageSize) {
        return fileService.listFilesByPage(pageIndex, pageSize);
    }

    /**
     * 在线显示文件
     *
     * @param id 文件id
     * @return
     */
    @GetMapping("/view")
    public ResponseEntity<Object> serveFileOnline(String id) {
        Optional<FileDocument> file = fileService.getById(id);
        if (file.isPresent()) {
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "fileName=" + file.get().getName())
                    .header(HttpHeaders.CONTENT_TYPE, file.get().getContentType())
                    .header(HttpHeaders.CONTENT_LENGTH, file.get().getSize() + "").header("Connection", "close")
                    .body(file.get().getContent());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("File was not found");
        }
    }

    /**
     * 下载文件
     *
     * @param id
     * @return
     * @throws UnsupportedEncodingException
     */
    @GetMapping("/down")
    public ResponseEntity<Object> downloadFileById(String id) throws UnsupportedEncodingException {
        Optional<FileDocument> file = fileService.getById(id);
        if (file.isPresent()) {
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; fileName=" + URLEncoder.encode(file.get().getName(), "utf-8"))
                    .header(HttpHeaders.CONTENT_TYPE, "application/octet-stream")
                    .header(HttpHeaders.CONTENT_LENGTH, file.get().getSize() + "").header("Connection", "close")
                    .body(file.get().getContent());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("File was not found");
        }
    }

    /**
     * 表单上传文件
     * 当数据库中存在该md5值时，可以实现秒传功能
     *
     * @param file 文件
     * @return
     */
    @PostMapping("/upload")
    public Result formUpload(@RequestParam("file") MultipartFile file) {
        Result result = null;
        try {
            if (file != null && !file.isEmpty()) {
                String fileMd5 = getMD5(file.getInputStream());
                FileDocument fileDocument = fileService.saveFile(fileMd5, file);

                System.out.println(fileDocument);
                result = new Result(1, "上传成功", fileDocument.getId());
            } else {
                result = new Result(0, "请上传文件");
            }
        } catch (IOException e) {
            log.error("IOE异常", e);
            result = new Result(-1, "系统异常");
        } catch (Exception e) {
            log.error("上传文件系统异常，", e);
            result = new Result(-1, "系统异常");

        }
        return result;
    }

    /**
     * 删除附件
     *
     * @param id
     * @return
     */
    @GetMapping("/delete")
    public Result deleteFileByGetMethod(String id) {
        Result result = null;
        if (id != null) {
            fileService.removeFile(id, true);
            result = new Result(1, "删除成功");
        } else {
            result = new Result(0, "请传输文件");
        }
        return result;
    }

    /**
     * 流转换成MD5字符串
     *
     * @param inputStream
     * @return
     */
    private String getMD5(InputStream inputStream) {
        BigInteger md5 = null;
        try {
            byte[] buffer = inputStreamToByte(inputStream);
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(buffer, 0, buffer.length);
            byte[] b = md.digest();
            md5 = new BigInteger(1, b);
        } catch (NoSuchAlgorithmException | IOException e) {
            e.printStackTrace();
        }
        assert md5 != null;
        return md5.toString(16);
    }

    /**
     * 流转换成byte数组
     *
     * @param inStream
     * @return
     * @throws IOException
     */
    private byte[] inputStreamToByte(InputStream inStream) throws IOException {
        ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
        //buff用于存放循环读取的临时数据
        byte[] buff = new byte[100];
        int rc = 0;
        while ((rc = inStream.read(buff, 0, 100)) > 0) {
            swapStream.write(buff, 0, rc);
        }
        return swapStream.toByteArray();
    }

}
