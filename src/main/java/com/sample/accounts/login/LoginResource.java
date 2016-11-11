package com.sample.accounts.login;

import com.sample.HttpMessage;
import com.sample.accounts.AccountDto;
import com.softteco.toolset.restlet.AuthorizationException;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import org.apache.commons.httpclient.HttpStatus;
import org.restlet.resource.Delete;
import org.restlet.resource.Post;

/**
 * Created on 3.11.16.
 *
 * @author Denis Kuhta
 * @since JDK1.8
 */
@Api(value = "/auth", description = "Auth resource")
public interface LoginResource {

    @ApiOperation(value = "Login", tags = "auth")
    @ApiResponses(value = {
            @ApiResponse(code = HttpStatus.SC_OK, message = HttpMessage.OK),
            @ApiResponse(code = HttpStatus.SC_UNSUPPORTED_MEDIA_TYPE, message = HttpMessage.UNSUPPORTED_MEDIA_TYPE),
            @ApiResponse(code = HttpStatus.SC_UNPROCESSABLE_ENTITY, message = HttpMessage.UNPROCESSABLE_ENTITY),
            @ApiResponse(code = HttpStatus.SC_INTERNAL_SERVER_ERROR, message = HttpMessage.INTERNAL_SERVER_ERROR)
    })
    @Post("json")
    AccountDto login(LoginDto dto) throws AuthorizationException;

    @ApiOperation(value = "Logout", tags = "auth")
    @ApiResponses(value = {
            @ApiResponse(code = HttpStatus.SC_NO_CONTENT, message = HttpMessage.OK),
            @ApiResponse(code = HttpStatus.SC_UNAUTHORIZED, message = HttpMessage.UNAUTHORIZED),
            @ApiResponse(code = HttpStatus.SC_UNSUPPORTED_MEDIA_TYPE, message = HttpMessage.UNSUPPORTED_MEDIA_TYPE),
            @ApiResponse(code = HttpStatus.SC_UNPROCESSABLE_ENTITY, message = HttpMessage.UNPROCESSABLE_ENTITY),
            @ApiResponse(code = HttpStatus.SC_INTERNAL_SERVER_ERROR, message = HttpMessage.INTERNAL_SERVER_ERROR)
    })
    @Delete("json")
    void logout(LogoutDto dto);
}
