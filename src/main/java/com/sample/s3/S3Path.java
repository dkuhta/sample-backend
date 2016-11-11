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
        StringBuilder valueSb = new StringBuilder();
        for (String folder : args) {
            if (StringUtils.isNoneEmpty(folder)) {
                valueSb.append(folder).append("/");
            }
        }
        if (StringUtils.isNotEmpty(valueSb.toString())) {
            s3Path.value = valueSb.toString().substring(0, valueSb.length() - 1);
        }
        return s3Path;
    }

    public String getValue() {
        return value;
    }
}
