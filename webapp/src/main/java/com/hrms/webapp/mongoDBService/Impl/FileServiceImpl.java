package com.hrms.webapp.mongoDBService.Impl;

import com.hrms.api.domain.dto.FileDocument;
import com.hrms.webapp.mongoDBService.FileService;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.mongodb.client.result.DeleteResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Field;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author 孔超
 * @date 2020/5/11 15:18
 */
@Slf4j
@Service("fileService")
public class FileServiceImpl implements FileService {
    private static String collectionName = "fileDate";

    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private GridFsTemplate gridFsTemplate;
    @Autowired
    private GridFSBucket gridFSBucket;

    /**
     * 表单上传附件
     *
     * @param md5
     * @param file
     * @return
     */
    @Override
    public FileDocument saveFile(String md5, MultipartFile file) {
        //已存在该文件，则实现秒传
        FileDocument fileDocument = getByMd5(md5);
        if (fileDocument != null) {
            return fileDocument;
        }

        fileDocument = new FileDocument();
        fileDocument.setName(file.getOriginalFilename());
        fileDocument.setSize(file.getSize());
        fileDocument.setContentType(file.getContentType());
        fileDocument.setUploadDate(new Date());
        fileDocument.setMd5(md5);
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        fileDocument.setSuffix(suffix);

        try {
            //文件存入gridfs
            String gridfsId = uploadFileToGridFS(file.getInputStream(), file.getContentType());
            fileDocument.setGridfsId(gridfsId);
            //上传的信息保存在mongodb
            fileDocument = mongoTemplate.save(fileDocument, collectionName);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return fileDocument;
    }

    /**
     * 上传文件到Mongodb的GridFs中
     *
     * @param in
     * @param contentType
     * @return
     */
    private String uploadFileToGridFS(InputStream in, String contentType) {
        String gridfsId = UUID.randomUUID().toString();
        //文件，存储在GridFS中
        gridFsTemplate.store(in, gridfsId, contentType);
        return gridfsId;
    }

    /**
     * 删除附件
     *
     * @param id           文件id
     * @param isDeleteFile 是否删除文件
     */
    @Override
    public void removeFile(String id, boolean isDeleteFile) {
        FileDocument fileDocument = mongoTemplate.findById(id, FileDocument.class, collectionName);
        if (fileDocument != null) {
            Query query = new Query().addCriteria(Criteria.where("_id").is(id));
            DeleteResult result = mongoTemplate.remove(query, collectionName);
            System.out.println("result:" + result.getDeletedCount());

            if (isDeleteFile) {
                Query deleteQuery = new Query().addCriteria(Criteria.where("filename").is(fileDocument.getGridfsId()));
                gridFsTemplate.delete(deleteQuery);
            }
        }
    }

    /**
     * 查询附件
     *
     * @param id 文件id
     * @return
     * @throws IOException
     */
    @Override
    public Optional<FileDocument> getById(String id) {
        FileDocument fileDocument = mongoTemplate.findById(id, FileDocument.class, collectionName);
        if (fileDocument != null) {
            Query gridQuery = new Query().addCriteria(Criteria.where("filename").is(fileDocument.getGridfsId()));
            try {
                GridFSFile fsFile = gridFsTemplate.findOne(gridQuery);
                GridFSDownloadStream in = gridFSBucket.openDownloadStream(fsFile.getObjectId());
                if (in.getGridFSFile().getLength() > 0) {
                    GridFsResource resource = new GridFsResource(fsFile, in);
                    fileDocument.setContent(inputStreamToByte(resource.getInputStream()));
                    return Optional.of(fileDocument);
                } else {
                    fileDocument = null;
                    return Optional.empty();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return Optional.empty();
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


    /**
     * 根据md5获取文件对象
     *
     * @param md5
     * @return
     */
    @Override
    public FileDocument getByMd5(String md5) {
        Query query = new Query().addCriteria(Criteria.where("md5").is(md5));
        return mongoTemplate.findOne(query, FileDocument.class, collectionName);
    }

    /**
     * 文件分页
     *
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @Override
    public List<FileDocument> listFilesByPage(int pageIndex, int pageSize) {
        Sort sort = Sort.by(Sort.Order.desc("uploadDate"));
        Query query = new Query().with(sort);
        long skip = (pageIndex - 1) * pageSize;
        query.skip(skip);
        query.limit(pageSize);
        Field field = query.fields();
        field.exclude("content");
        List<FileDocument> files = mongoTemplate.find(query, FileDocument.class, collectionName);
        return files;
    }
}
