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
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import static com.mulesoft.connectors.blend.internal.attributes.AttributesUtil.setResponseAttributes;
import static com.mulesoft.connectors.blend.internal.error.exception.ResponseValidator.checkErrorResponse;

public class LoanServiceImpl extends DefaultConnectorService<BlendlabsConfiguration, BlendlabsConnection> implements LoanService {
    public LoanServiceImpl(BlendlabsConfiguration config, BlendlabsConnection connection) {
        super(config, connection);
    }

    @Override
    public Result<InputStream, ResponseStatus> getLoansResult(String limit, String cursor, String losIdExists, String crmIdEQ, String borrowerEmailEQ, String losIdEQ, String exportable) {
        String strUri = getConfig().getAddress() ;
        String actualUrl = new StringBuilder(strUri).append(Urls.LOANS).append(Urls.SPLIT_EXPRESSION).toString();
        MultiMap<String, String> qParams = new MultiMap<>();

        if (limit != null && !"".equals(limit)) {
            qParams.put(Urls.LIMIT, limit);
        }if (cursor != null && !"".equals(cursor)) {
            qParams.put(Urls.CURSOR, cursor);
        }if (losIdExists != null && !"".equals(losIdExists)) {
            qParams.put(Urls.LOSID_EXISTS, losIdExists);
        }if (crmIdEQ != null && !"".equals(crmIdEQ)) {
            qParams.put(Urls.CRMID_EQ, crmIdEQ);
        }if (losIdEQ != null && !"".equals(losIdEQ)) {
            qParams.put(Urls.LOSID_EQ, losIdEQ);
        }if (exportable != null && !"".equals(exportable)) {
            qParams.put(Urls.EXPORTABLE, exportable);
        }if (borrowerEmailEQ != null && !"".equals(borrowerEmailEQ)) {
            qParams.put(Urls.BORROWER_EMAIL_EQ, borrowerEmailEQ);
        }
        CompletableFuture<HttpResponse> response = getConnection().sendAsyncRequest(HttpConstants.Method.GET,actualUrl,qParams, null);
        checkErrorResponse(response);
        InputStream str = BlendUtils.getContentInputStream(response);
        return Result.<InputStream,ResponseStatus>builder().output(str).attributes(setResponseAttributes(response)).build();
    }

    @Override
    public Result<InputStream, ResponseStatus> getExportStatusResult(String loanIds) {
        String strUri = getConfig().getAddress() ;
        String actualUrl = new StringBuilder(strUri).append(Urls.LOANS).append(Urls.SPLIT_EXPRESSION)
                .append(Urls.EXPORT_STATUSES).append(Urls.SPLIT_EXPRESSION).toString();

        MultiMap<String, String> qParams = new MultiMap<>();
        qParams.put(Urls.IDS, loanIds);

        CompletableFuture<HttpResponse> response = getConnection().sendAsyncRequest(HttpConstants.Method.GET,actualUrl,qParams, null);
        checkErrorResponse(response);
        InputStream str = BlendUtils.getContentInputStream(response);
        return Result.<InputStream,ResponseStatus>builder().output(str).attributes(setResponseAttributes(response)).build();
    }

    @Override
    public Result<InputStream, ResponseStatus> getLoanDataResult(String loanId, String format, String version) {
        String strUri = getConfig().getAddress() ;
        String actualUrl = new StringBuilder(strUri).append(Urls.LOANS).append(Urls.SPLIT_EXPRESSION)
                .append(loanId).append(Urls.SPLIT_EXPRESSION).toString();

        MultiMap<String, String> qParams = new MultiMap<>();
        if (format != null && !"".equals(format)) {
            qParams.put(Urls.FORMAT, format);
        }if (version != null && !"".equals(version)) {
            qParams.put(Urls.VERSION, version);
        }

        CompletableFuture<HttpResponse> response = getConnection().sendAsyncRequest(HttpConstants.Method.GET,actualUrl,qParams, null);
        checkErrorResponse(response);
        InputStream str = BlendUtils.getContentInputStream(response);
        return Result.<InputStream,ResponseStatus>builder().output(str).attributes(setResponseAttributes(response)).build();
    }

    @Override
    public Result<InputStream, ResponseStatus> getLoanDocumentsResult(String loanId, String includeAllDocuments, String includeAllExports) {
        String strUri = getConfig().getAddress() ;
        String actualUrl = new StringBuilder(strUri).append(Urls.LOANS).append(Urls.SPLIT_EXPRESSION)
                .append(loanId).append(Urls.SPLIT_EXPRESSION).append(Urls.DOCUMENTS).append(Urls.SPLIT_EXPRESSION).toString();

        MultiMap<String, String> qParams = new MultiMap<>();
        if (includeAllDocuments != null && !"".equals(includeAllDocuments)) {
            qParams.put(Urls.INCLUDE_ALL_DOCUMENTS, includeAllDocuments);
        }if (includeAllExports != null && !"".equals(includeAllExports)) {
            qParams.put(Urls.INCLUDE_ALL_EXPORTS, includeAllExports);
        }
        CompletableFuture<HttpResponse> response = getConnection().sendAsyncRequest(HttpConstants.Method.GET,actualUrl,qParams, null);
        checkErrorResponse(response);
        InputStream str = BlendUtils.getContentInputStream(response);
        return Result.<InputStream,ResponseStatus>builder().output(str).attributes(setResponseAttributes(response)).build();
    }

    @Override
    public Result<InputStream, ResponseStatus> getLoanApplicationDetailsResult(String loanId) {
        String strUri = getConfig().getAddress() ;
        String actualUrl = new StringBuilder(strUri).append(Urls.LOANS).append(Urls.SPLIT_EXPRESSION)
                .append(loanId).append(Urls.SPLIT_EXPRESSION).append(Urls.APPLICATION_DETAILS).append(Urls.SPLIT_EXPRESSION).toString();

        CompletableFuture<HttpResponse> response = getConnection().sendAsyncRequestWithoutQueryParam(HttpConstants.Method.GET,actualUrl,null);
        checkErrorResponse(response);
        InputStream str = BlendUtils.getContentInputStream(response);
        return Result.<InputStream,ResponseStatus>builder().output(str).attributes(setResponseAttributes(response)).build();
    }

    @Override
    public Result<InputStream, ResponseStatus> getPostExportStatusResult(String loanId, Map<String, Object> body) {
        String strUri = getConfig().getAddress() ;
        String actualUrl = new StringBuilder(strUri).append(Urls.LOANS).append(Urls.SPLIT_EXPRESSION)
        .append(loanId).append(Urls.SPLIT_EXPRESSION).append(Urls.EXPORT_STATUS).append(Urls.SPLIT_EXPRESSION).toString();
        byte[] byteArray = BlendUtils.getByteArrayData(body);

        CompletableFuture<HttpResponse> response = getConnection().sendAsyncRequestWithoutQueryParam(HttpConstants.Method.POST, actualUrl, byteArray);
        checkErrorResponse(response);
        InputStream str = BlendUtils.getContentInputStream(response);
        return Result.<InputStream,ResponseStatus>builder().output(str).attributes(setResponseAttributes(response)).build();
    }

    @Override
    public Result<InputStream, ResponseStatus> getPostLoanResult(Map<String, Object> loanBody) {
        String strUri = getConfig().getAddress() ;
        String actualUrl = new StringBuilder(strUri).append(Urls.LOANS).toString();
        byte[] byteArray = BlendUtils.getByteArrayData(loanBody);

        CompletableFuture<HttpResponse> response = getConnection().sendAsyncRequestWithoutQueryParam(HttpConstants.Method.POST, actualUrl, byteArray);
        checkErrorResponse(response);
        InputStream str = BlendUtils.getContentInputStream(response);
        return Result.<InputStream,ResponseStatus>builder().output(str).attributes(setResponseAttributes(response)).build();
    }

    @Override
    public Result<InputStream, ResponseStatus> getPostLosMilestonesResult(Map<String, Object> losMilestonesBody) {
        String strUri = getConfig().getAddress() ;
        String actualUrl = new StringBuilder(strUri).append(Urls.LOANS).append(Urls.SPLIT_EXPRESSION).append(Urls.LOS_MILESTONES).toString();
        byte[] byteArray = BlendUtils.getByteArrayData(losMilestonesBody);

        CompletableFuture<HttpResponse> response = getConnection().sendAsyncRequestWithoutQueryParam(HttpConstants.Method.POST, actualUrl, byteArray);
        checkErrorResponse(response);
        InputStream str = BlendUtils.getContentInputStream(response);
        return Result.<InputStream,ResponseStatus>builder().output(str).attributes(setResponseAttributes(response)).build();
    }

    @Override
    public Result<InputStream, ResponseStatus> getPatchLoanResult(String loanId, Map<String, Object> patchBody) {
        String strUri = getConfig().getAddress() ;
        String actualUrl = new StringBuilder(strUri).append(Urls.LOANS).append(Urls.SPLIT_EXPRESSION).append(loanId).toString();
        byte[] byteArray = BlendUtils.getByteArrayData(patchBody);

        CompletableFuture<HttpResponse> response = getConnection().sendAsyncRequestWithoutQueryParam(HttpConstants.Method.PATCH, actualUrl, byteArray);
        checkErrorResponse(response);
        InputStream str = BlendUtils.getContentInputStream(response);
        return Result.<InputStream,ResponseStatus>builder().output(str).attributes(setResponseAttributes(response)).build();
    }

    @Override
    public Result<InputStream, ResponseStatus> getPutLoanApplicationDetailsResult(String loanId, Map<String, Object> putLoanApplicationDetailsBody) {
        String strUri = getConfig().getAddress() ;
        String actualUrl = new StringBuilder(strUri).append(Urls.LOANS).append(Urls.SPLIT_EXPRESSION)
                .append(loanId).append(Urls.SPLIT_EXPRESSION).append(Urls.APPLICATION_DETAILS).toString();
        byte[] byteArray = BlendUtils.getByteArrayData(putLoanApplicationDetailsBody);

        CompletableFuture<HttpResponse> response = getConnection().sendAsyncRequestWithoutQueryParam(HttpConstants.Method.PUT, actualUrl, byteArray);
        checkErrorResponse(response);
        InputStream str = BlendUtils.getContentInputStream(response);
        return Result.<InputStream,ResponseStatus>builder().output(str).attributes(setResponseAttributes(response)).build();
    }

    @Override
    public Result<InputStream, ResponseStatus> getLoanBorrowersResult(String loanId, String losIdEq) {
        CompletableFuture<HttpResponse> response = null;
        String strUri = getConfig().getAddress() ;
        String actualUrl = new StringBuilder(strUri).append(Urls.LOANS).append(Urls.SPLIT_EXPRESSION)
                .append(loanId).append(Urls.SPLIT_EXPRESSION).append(Urls.BORROWERS).append(Urls.SPLIT_EXPRESSION).toString();

        MultiMap<String, String> qParams = new MultiMap<>();
        if (losIdEq != null && !"".equals(losIdEq)) {
            qParams.put(Urls.LOSID_EQ, losIdEq);
            response = getConnection().sendAsyncRequest(HttpConstants.Method.GET,actualUrl,qParams, null);
        }else{
            response = getConnection().sendAsyncRequestWithoutQueryParam(HttpConstants.Method.GET,actualUrl, null);
        }

        checkErrorResponse(response);
        InputStream str = BlendUtils.getContentInputStream(response);
        return Result.<InputStream,ResponseStatus>builder().output(str).attributes(setResponseAttributes(response)).build();
    }
}
