package com.sample.s3;

import org.apache.commons.lang3.StringUtils;

/**
 * Created on 6.10.16.
 *
 * @author Denis Kuhta
 * @since JDK1.8
 */
public final class S3Path {

    private String value;

    private S3Path() {
        this.value = "";
    }

    public static S3Path create(final String... args) {
        S3Path s3Path = new S3Path();
        String value = "";
        for (String folder : args) {
            if (StringUtils.isNoneEmpty(folder)) {
                value += folder + "/";
            }
        }
        if (StringUtils.isNotEmpty(value)) {
            s3Path.value = value.substring(0, value.length() - 1);
        }
        return s3Path;
    }

    public String getValue() {
        return value;
    }
}
