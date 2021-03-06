package com.sample.accounts;

import com.sample.HttpMessage;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import org.apache.commons.httpclient.HttpStatus;
import org.restlet.resource.Get;
import org.restlet.resource.Put;

/**
 * Created on 9.11.16.
 *
 * @author Denis Kuhta
 * @since JDK1.8
 */
public interface AccountResource {

    @ApiOperation(value = "Get current profile", tags = "account")
    @ApiResponses(value = {
            @ApiResponse(code = HttpStatus.SC_OK, message = HttpMessage.OK),
            @ApiResponse(code = HttpStatus.SC_UNAUTHORIZED, message = HttpMessage.UNAUTHORIZED),
            @ApiResponse(code = HttpStatus.SC_INTERNAL_SERVER_ERROR, message = HttpMessage.INTERNAL_SERVER_ERROR)
    })
    @Get("json")
    AccountDto getProfile();

    @ApiOperation(value = "Update profile", tags = "account")
    @ApiResponses(value = {
            @ApiResponse(code = HttpStatus.SC_OK, message = HttpMessage.OK),
            @ApiResponse(code = HttpStatus.SC_UNAUTHORIZED, message = HttpMessage.UNAUTHORIZED),
            @ApiResponse(code = HttpStatus.SC_UNSUPPORTED_MEDIA_TYPE, message = HttpMessage.UNSUPPORTED_MEDIA_TYPE),
            @ApiResponse(code = HttpStatus.SC_UNPROCESSABLE_ENTITY, message = HttpMessage.UNPROCESSABLE_ENTITY),
            @ApiResponse(code = HttpStatus.SC_INTERNAL_SERVER_ERROR, message = HttpMessage.INTERNAL_SERVER_ERROR)
    })
    @Put("json")
    AccountDto update(AccountUpdateDto dto);
}
