package com.sample.s3;

import com.google.inject.Inject;
import com.google.inject.name.Named;

/**
 * Created on 3.10.16.
 *
 * @author Denis Kuhta
 * @since JDK1.8
 */
public class S3ServiceBean extends AbstractS3Service {

    @Inject
    @Named("amazon.protocol")
    private String protocol;

    @Inject
    @Named("amazon.s3.host")
    private String s3Host;

    @Inject
    @Named("amazon.bucket")
    private String bucket;

    @Inject
    @Named("amazon.key")
    private String key;

    @Inject
    @Named("amazon.secret.key")
    private String secret;

    @Override
    protected String getAmazonProtocol() {
        return protocol;
    }

    protected String getAmazonS3Host() {
        return s3Host;
    }

    @Override
    protected String getAmazonBucket() {
        return bucket;
    }

    @Override
    protected String getAmazonKey() {
        return key;
    }

    @Override
    protected String getAmazonSecretKey() {
        return secret;
    }
}
