package com.sample.s3;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.text.MessageFormat;

/**
 * Created on 5.10.16.
 *
 * @author Denis Kuhta
 * @since JDK1.8
 */
public abstract class AbstractS3Service implements S3Service {

    private static final Logger LOGGER = LogManager.getLogger(AbstractS3Service.class.getName());

    protected abstract String getAmazonProtocol();

    protected abstract String getAmazonS3Host();

    protected abstract String getAmazonBucket();

    protected abstract String getAmazonKey();

    protected abstract String getAmazonSecretKey();

    private AmazonS3 produceS3Client() {
        AmazonS3 s3 = new AmazonS3Client(new AWSCredentials() {
            @Override
            public String getAWSAccessKeyId() {
                return getAmazonKey();
            }

            @Override
            public String getAWSSecretKey() {
                return getAmazonSecretKey();
            }
        });
        return s3;
    }

    @Override
    public S3Path upload(final File file) throws IOException, S3Exception {
        return upload(file, null);
    }

    @Override
    public S3Path upload(final File file, final S3Path folder) throws IOException, S3Exception {
        if (!file.exists()) {
            throw new FileNotFoundException();
        }

        LOGGER.info(MessageFormat.format("Uploading file={0} to S3", file.getName()));

        AmazonS3 s3 = produceS3Client();

        InputStream inputStream =  null;
        try {
            inputStream = new FileInputStream(file);
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType(getContentType(file));
            metadata.setContentLength((long) inputStream.available());

            S3Path filePath = getFilePath(folder, file.getName());

            PutObjectResult putObjectResult = s3.putObject(new PutObjectRequest(getAmazonBucket(), filePath.getValue(), inputStream, metadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead));

            putObjectResult.getContentMd5();
            LOGGER.info(MessageFormat.format("File={0} has been uploaded to S3 {1}", file.getName(), getFileUrl(filePath)));
            return filePath;
        } catch (AmazonServiceException ase) {
            logAmazonServiceExc(ase);
            throw new S3Exception("Can't upload file!");
        } catch (AmazonClientException ace) {
            logAmazonClientExc(ace);
            throw new S3Exception("Can't upload file!");
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }

    private S3Path getFilePath(final S3Path path, final String fileName) {
        if (path != null && StringUtils.isNoneEmpty(path.getValue())) {
            return S3Path.create(path.getValue(), fileName);
        } else {
            return S3Path.create(fileName);
        }
    }

    private String getContentType(final File file) throws IOException {
        return Files.probeContentType(file.toPath());
    }


    @Override
    public void delete(final S3Path fileName) throws S3Exception {
        AmazonS3 s3 = produceS3Client();

        try {
            s3.deleteObject(getAmazonBucket(), fileName.getValue());
        } catch (AmazonServiceException ase) {
            logAmazonServiceExc(ase);
            throw new S3Exception("Can't delete file!");
        } catch (AmazonClientException ace) {
            logAmazonClientExc(ace);
            throw new S3Exception("Can't delete file!");
        }
    }

    @Override
    public URL getFileUrl(final S3Path filePath) throws S3Exception {
        try {
            return new URL(getAmazonProtocol(), getHost(), "/" + filePath.getValue());
        } catch (MalformedURLException e) {
            LOGGER.error(e);
            throw new S3Exception("Can't get url!");
        }
    }

    private String getHost() {
        return getAmazonBucket() + "." + getAmazonS3Host();
    }

    private void logAmazonServiceExc(final AmazonServiceException ase) {
        StringBuilder err = new StringBuilder("Caught an AmazonServiceException, which means your request made it "
                + "to Amazon S3, but was rejected with an error response for some reason.");
        err.append("Error Message:    ").append(ase.getMessage());
        err.append("HTTP Status Code: ").append(ase.getStatusCode());
        err.append("AWS Error Code:   ").append(ase.getErrorCode());
        err.append("Error Type:       ").append(ase.getErrorType());
        err.append("Request ID:       ").append(ase.getRequestId());
        LOGGER.error(err.toString());
    }

    private void logAmazonClientExc(final AmazonClientException ace) {
        StringBuilder err = new StringBuilder("Caught an AmazonClientException, which means the client encountered "
                + "a serious internal problem while trying to communicate with S3, "
                + "such as not being able to access the network.");
        err.append("Error Message: ").append(ace.getMessage());
        LOGGER.error(err.toString());
    }
}
