package com.sample.s3;

/**
 * Created on 5.10.16.
 *
 * @author Denis Kuhta
 * @since JDK1.8
 */
public class S3Exception extends Exception {

    public S3Exception(final String message) {
        super(message);
    }
}
