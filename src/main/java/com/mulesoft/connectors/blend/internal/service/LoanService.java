/**
 * (c) 2003-2019 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */
package com.mulesoft.connectors.blend.internal.service;

import com.mulesoft.connectors.blend.api.ResponseStatus;
import org.mule.connectors.commons.template.service.ConnectorService;
import org.mule.runtime.extension.api.runtime.operation.Result;

import java.io.InputStream;
import java.util.Map;

public interface LoanService extends ConnectorService {
    Result<InputStream, ResponseStatus> getLoansResult(String limit, String cursor, String losIdExists, String crmIdEQ, String borrowerEmailEQ, String losIdEQ, String exportable);

    Result<InputStream, ResponseStatus> getExportStatusResult(String loanIds);

    Result<InputStream, ResponseStatus> getLoanDataResult(String loanId, String format, String version);

    Result<InputStream, ResponseStatus> getLoanDocumentsResult(String loanId, String includeAllDocuments, String includeAllExports);

    Result<InputStream, ResponseStatus> getLoanApplicationDetailsResult(String loanId);

    Result<InputStream, ResponseStatus> getPostExportStatusResult(String loanId, Map<String, Object> body);

    Result<InputStream, ResponseStatus> getPostLoanResult(Map<String, Object> loanBody);

    Result<InputStream, ResponseStatus> getPostLosMilestonesResult(Map<String, Object> losMilestonesBody);

    Result<InputStream, ResponseStatus> getPatchLoanResult(String loanId, Map<String, Object> patchBody);

    Result<InputStream, ResponseStatus> getPutLoanApplicationDetailsResult(String loanId, Map<String, Object> putLoanApplicationDetailsBody);

    Result<InputStream, ResponseStatus> getLoanBorrowersResult(String loanId, String losIdEq);
}
