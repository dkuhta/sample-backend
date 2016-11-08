package com.sample.auth;

import com.softteco.toolset.restlet.AuthorizationException;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import org.apache.commons.httpclient.util.HttpURLConnection;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Post;

/**
 * Created on 3.11.16.
 *
 * @author Denis Kuhta
 * @since JDK1.8
 */
@Api(value = "/auth", description = "Auth resource")
public interface AuthResource {

    @ApiOperation(value = "Get current profile", tags = "auth")
    @ApiResponses(value = {
            @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "OK"),
            @ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized")
    })
    @Get("json")
    ProfileDto getProfile();

    @ApiOperation(value = "Login", tags = "auth")
    @ApiResponses(value = {
            @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "OK"),
            @ApiResponse(code = 422, message = "Unprocessable Entity")
    })
    @Post("json")
    ProfileDto login(AuthDto dto) throws AuthorizationException;

    @ApiOperation(value = "Logout", tags = "auth")
    @ApiResponses(value = {
            @ApiResponse(code = HttpURLConnection.HTTP_NO_CONTENT, message = "OK")
    })
    @Delete("json")
    void logout();
}
