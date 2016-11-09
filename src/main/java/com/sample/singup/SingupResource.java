package com.sample.singup;

import com.google.inject.ImplementedBy;
import com.sample.HttpMessage;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import org.apache.commons.httpclient.HttpStatus;
import org.restlet.resource.Post;

/**
 * Created on 7.11.16.
 *
 * @author Denis Kuhta
 * @since JDK1.8
 */
@ImplementedBy(SingupResourceBean.class)
public interface SingupResource {

    @ApiOperation(value = "Sing up", tags = "singup")
    @ApiResponses(value = {
            @ApiResponse(code = HttpStatus.SC_NO_CONTENT, message = HttpMessage.OK),
            @ApiResponse(code = HttpStatus.SC_CONFLICT, message = HttpMessage.ENTITY_ALREADY_EXISTS),
            @ApiResponse(code = HttpStatus.SC_UNSUPPORTED_MEDIA_TYPE, message = HttpMessage.UNSUPPORTED_MEDIA_TYPE),
            @ApiResponse(code = HttpStatus.SC_UNPROCESSABLE_ENTITY, message = HttpMessage.UNPROCESSABLE_ENTITY),
            @ApiResponse(code = HttpStatus.SC_INTERNAL_SERVER_ERROR, message = HttpMessage.INTERNAL_SERVER_ERROR)
    })
    @Post("json")
    void singup(SingupDto dto);
}
