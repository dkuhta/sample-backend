package com.sample.singup;

import com.google.inject.ImplementedBy;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import org.apache.commons.httpclient.util.HttpURLConnection;
import org.restlet.resource.Post;

/**
 * Created on 7.11.16.
 *
 * @author Denis Kuhta
 * @since JDK1.8
 */
@ImplementedBy(SingupResourceImpl.class)
public interface SingupResource {

    @ApiOperation(value = "Sing up", tags = "singup")
    @ApiResponses(value = {
            @ApiResponse(code = HttpURLConnection.HTTP_NO_CONTENT, message = "OK"),
            @ApiResponse(code = 409, message = "Entity already exists"),
            @ApiResponse(code = 422, message = "Unprocessable Entity"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @Post("json")
    void singup(SingupDto dto);
}
