package com.sample.accounts.password;

import com.google.inject.ImplementedBy;
import com.sample.HttpMessage;
import com.softteco.toolset.jpa.DataNotFoundException;
import com.softteco.toolset.restlet.AuthorizationException;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import org.apache.commons.httpclient.HttpStatus;
import org.restlet.resource.Post;
import org.restlet.resource.Put;

/**
 * Created on 9.11.16.
 *
 * @author Denis Kuhta
 * @since JDK1.8
 */
@ImplementedBy(PasswordResourceBean.class)
public interface PasswordResource {

    @ApiOperation(value = "Reset password", tags = "password")
    @ApiResponses(value = {
            @ApiResponse(code = HttpStatus.SC_NO_CONTENT, message = HttpMessage.OK),
            @ApiResponse(code = HttpStatus.SC_NOT_FOUND, message = HttpMessage.NOT_FOUND),
            @ApiResponse(code = HttpStatus.SC_UNSUPPORTED_MEDIA_TYPE, message = HttpMessage.UNSUPPORTED_MEDIA_TYPE),
            @ApiResponse(code = HttpStatus.SC_UNPROCESSABLE_ENTITY, message = HttpMessage.UNPROCESSABLE_ENTITY),
            @ApiResponse(code = HttpStatus.SC_INTERNAL_SERVER_ERROR, message = HttpMessage.INTERNAL_SERVER_ERROR)
    })
    @Post("json")
    void reset(PasswordResetDto dto) throws DataNotFoundException;

    @ApiOperation(value = "Update password", tags = "password")
    @ApiResponses(value = {
            @ApiResponse(code = HttpStatus.SC_NO_CONTENT, message = HttpMessage.OK),
            @ApiResponse(code = HttpStatus.SC_UNAUTHORIZED, message = HttpMessage.UNAUTHORIZED),
            @ApiResponse(code = HttpStatus.SC_UNSUPPORTED_MEDIA_TYPE, message = HttpMessage.UNSUPPORTED_MEDIA_TYPE),
            @ApiResponse(code = HttpStatus.SC_UNPROCESSABLE_ENTITY, message = HttpMessage.UNPROCESSABLE_ENTITY),
            @ApiResponse(code = HttpStatus.SC_INTERNAL_SERVER_ERROR, message = HttpMessage.INTERNAL_SERVER_ERROR)
    })
    @Put("json")
    void update(PasswordUpdateDto dto) throws AuthorizationException;
}
