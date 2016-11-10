package com.sample.s3;

import com.google.inject.ImplementedBy;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Created on 3.10.16.
 *
 * @author Denis Kuhta
 * @since JDK1.8
 */
@ImplementedBy(S3ServiceBean.class)
public interface S3Service {

    /**
     * Upload file to s3.
     *
     * @param file the file to upload
     * @return the file url
     * @throws IOException the io exception if file not found
     * @throws S3Exception the s3 exception if can't upload file
     */
    S3Path upload(File file) throws IOException, S3Exception;

    /**
     * Upload file to s3.
     *
     * @param file   the file
     * @param folder the folder
     * @return the s3 path
     * @throws IOException the io exception if file not found
     * @throws S3Exception the s3 exception if can't upload file
     */
    S3Path upload(File file, S3Path folder) throws IOException, S3Exception;

    /**
     * Gets file url by file name.
     *
     * @param fileName the file name
     * @return the file url
     * @throws S3Exception the s3 exception
     */
    URL getFileUrl(S3Path fileName) throws S3Exception;

    /**
     * Delete file from s3.
     *
     * @param fileName the file name to delete
     * @throws S3Exception the s3 exception if can't delete file
     */
    void delete(S3Path fileName) throws S3Exception;
}
