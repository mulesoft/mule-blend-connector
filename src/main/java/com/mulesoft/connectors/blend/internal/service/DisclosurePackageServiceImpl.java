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
import org.mule.runtime.api.util.MultiMap;
import org.mule.runtime.extension.api.runtime.operation.Result;
import org.mule.runtime.http.api.HttpConstants;
import org.mule.runtime.http.api.domain.message.response.HttpResponse;

import java.io.InputStream;
import java.util.concurrent.CompletableFuture;

import static com.mulesoft.connectors.blend.internal.attributes.AttributesUtil.setResponseAttributes;
import static com.mulesoft.connectors.blend.internal.error.exception.ResponseValidator.checkErrorResponse;

public class DisclosurePackageServiceImpl extends DefaultConnectorService<BlendlabsConfiguration, BlendlabsConnection> implements DisclosurePackageService{

    public DisclosurePackageServiceImpl(BlendlabsConfiguration config, BlendlabsConnection connection) {
        super(config, connection);
    }

    @Override
    public Result<InputStream, ResponseStatus> getDisclosuresPackagesResult(String loanId, String status) {
        String strUri = getConfig().getAddress() ;
        String actualUrl = new StringBuilder(strUri).append(Urls.DISCLOSURES_PACKAGES).toString();
        MultiMap<String, String> qParams = new MultiMap<>();
        qParams.put(Urls.LOAN_ID, loanId);

        if (status != null && !"".equals(status)) {
            qParams.put(Urls.STATUS, status);
        }
        CompletableFuture<HttpResponse> response = getConnection().sendAsyncRequest(HttpConstants.Method.GET,actualUrl,qParams, null);
        checkErrorResponse(response);
        InputStream str = BlendUtils.getContentInputStream(response);
        return Result.<InputStream,ResponseStatus>builder().output(str).attributes(setResponseAttributes(response)).build();

    }
}
