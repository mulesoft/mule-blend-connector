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
import static com.mulesoft.connectors.blend.internal.util.BlendUtils.getByteArrayFromInputStream;


public class DocumentServiceImpl extends DefaultConnectorService<BlendlabsConfiguration, BlendlabsConnection> implements DocumentService {

    public DocumentServiceImpl(BlendlabsConfiguration config, BlendlabsConnection connection) {
        super(config, connection);
    }

    @Override
    public Result<InputStream, ResponseStatus> getDocumentsResult(String limit, String cursor, String exported, String includeAllExports, String uploadedAfter, String uploadedBefore, String statusIncludes) {
        String strUri = getConfig().getAddress() ;
        String actualUrl = new StringBuilder(strUri).append(Urls.SPLIT_EXPRESSION).append(Urls.DOCUMENTS).toString();
        MultiMap<String, String> qParams = new MultiMap<>();

        if (limit != null && !"".equals(limit)) {
            qParams.put(Urls.LIMIT, limit);
        }if (cursor != null && !"".equals(cursor)) {
            qParams.put(Urls.CURSOR, cursor);
        }if (exported != null && !"".equals(exported)) {
            qParams.put(Urls.EXPORTED, exported);
        }if (includeAllExports != null && !"".equals(includeAllExports)) {
            qParams.put(Urls.INCLUDE_ALL_EXPORTS, includeAllExports);
        }if (uploadedAfter != null && !"".equals(uploadedAfter)) {
            qParams.put(Urls.UPLOADED_AFTER, uploadedAfter);
        }if (uploadedBefore != null && !"".equals(uploadedBefore)) {
            qParams.put(Urls.UPLOADED_BEFORE, uploadedBefore);
        }if (statusIncludes != null && !"".equals(statusIncludes)) {
            qParams.put(Urls.STATUS_INCLUDES, statusIncludes);
        }
        CompletableFuture<HttpResponse> response = getConnection().sendAsyncRequest(HttpConstants.Method.GET,actualUrl,qParams, null);
        checkErrorResponse(response);
        InputStream str = BlendUtils.getContentInputStream(response);
        return Result.<InputStream,ResponseStatus>builder().output(str).attributes(setResponseAttributes(response)).build();
    }

    @Override
    public Result<InputStream, ResponseStatus> getDocumentDataResult(String documentID) {
        String strUri = getConfig().getAddress() ;
        String actualUrl = new StringBuilder(strUri).append(Urls.SPLIT_EXPRESSION).append(Urls.DOCUMENTS).append(Urls.SPLIT_EXPRESSION).append(documentID).toString();
        CompletableFuture<HttpResponse> response = getConnection().sendAsyncRequestWithoutQueryParam(HttpConstants.Method.GET,actualUrl, null);
        checkErrorResponse(response);
        InputStream str = BlendUtils.getContentInputStream(response);
        return Result.<InputStream,ResponseStatus>builder().output(str).attributes(setResponseAttributes(response)).build();
    }

    @Override
    public Result<InputStream, ResponseStatus> getPostDocumentResult(InputStream documentsBody) {
        String strUri = getConfig().getAddress() ;
        String actualUrl = new StringBuilder(strUri).append(Urls.SPLIT_EXPRESSION).append(Urls.DOCUMENTS).toString();
        byte[] byteArray = getByteArrayFromInputStream(documentsBody);
        CompletableFuture<HttpResponse> response = getConnection().sendAsyncRequestDocumentBody(HttpConstants.Method.POST, actualUrl, byteArray);
        checkErrorResponse(response);
        InputStream str = BlendUtils.getContentInputStream(response);
        return Result.<InputStream,ResponseStatus>builder().output(str).attributes(setResponseAttributes(response)).build();
    }

}

