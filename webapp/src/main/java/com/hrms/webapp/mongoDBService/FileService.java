package com.hrms.webapp.mongoDBService;

import com.hrms.api.domain.dto.FileDocument;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

/**
 * @author 孔超
 * @date 2020/5/11 15:20
 */
public interface FileService {
    /**
     * 保存信息的文件
     *
     * @param md5
     * @param file
     * @return
     */
    public FileDocument saveFile(String md5, MultipartFile file);

    /**
     * 移除文件
     *
     * @param id
     * @param isDeleteFile
     */
    public void removeFile(String id, boolean isDeleteFile);

    /**
     * 根据id查询附件
     *
     * @param id
     * @return
     */
    public Optional<FileDocument> getById(String id);

    /**
     * 根据md5获取文件对象
     *
     * @param md5
     * @return
     */
    public FileDocument getByMd5(String md5);

    /**
     * 文件分页
     *
     * @param pageIndex
     * @param pageSize
     * @return
     */
    public List<FileDocument> listFilesByPage(int pageIndex, int pageSize);
}
