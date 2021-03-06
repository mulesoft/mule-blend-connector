/**
 * (c) 2003-2019 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */

package com.mulesoft.connectors.blend.internal.service;

import com.mulesoft.connectors.blend.api.ResponseStatus;
import com.mulesoft.connectors.blend.internal.config.BlendlabsConfiguration;
import com.mulesoft.connectors.blend.internal.connection.BlendlabsConnection;
import com.mulesoft.connectors.blend.internal.util.BlendUtils;
import com.mulesoft.connectors.blend.internal.util.Urls;
import org.mule.connectors.commons.template.service.DefaultConnectorService;
import org.mule.runtime.extension.api.runtime.operation.Result;
import org.mule.runtime.http.api.HttpConstants;
import org.mule.runtime.http.api.domain.message.response.HttpResponse;

import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import static com.mulesoft.connectors.blend.internal.attributes.AttributesUtil.setResponseAttributes;
import static com.mulesoft.connectors.blend.internal.error.exception.ResponseValidator.checkErrorResponse;

public class RealtorServiceImpl extends DefaultConnectorService<BlendlabsConfiguration, BlendlabsConnection> implements RealtorService {

    public RealtorServiceImpl(BlendlabsConfiguration config, BlendlabsConnection connection) {
        super(config, connection);
    }

    @Override
    public Result<InputStream, ResponseStatus> getResultPostRealtorService(Map<String, Object> postRealtorBody) {

        String strUri = getConfig().getAddress() ;
        String actualUrl = new StringBuilder(strUri).append(Urls.REALTORS).toString();
        byte[] byteArray = BlendUtils.getByteArrayData(postRealtorBody);

        CompletableFuture<HttpResponse> response = getConnection().sendAsyncRequestWithoutQueryParam(HttpConstants.Method.POST, actualUrl, byteArray);
        checkErrorResponse(response);
        InputStream str = BlendUtils.getContentInputStream(response);
        return Result.<InputStream,ResponseStatus>builder().output(str).attributes(setResponseAttributes(response)).build();
    }
}
