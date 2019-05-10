/**
 * (c) 2003-2019 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */

package com.mulesoft.connectors.blend.internal.operations;

import com.mulesoft.connectors.blend.api.ResponseStatus;
import com.mulesoft.connectors.blend.internal.config.BlendlabsConfiguration;
import com.mulesoft.connectors.blend.internal.connection.BlendlabsConnection;
import com.mulesoft.connectors.blend.internal.error.BlendlabsErrorProvider;
import com.mulesoft.connectors.blend.internal.service.LoanService;
import com.mulesoft.connectors.blend.internal.service.LoanServiceImpl;
import org.mule.connectors.commons.template.operation.ConnectorOperations;
import org.mule.runtime.extension.api.annotation.error.Throws;
import org.mule.runtime.extension.api.annotation.metadata.fixed.InputJsonType;
import org.mule.runtime.extension.api.annotation.metadata.fixed.OutputJsonType;
import org.mule.runtime.extension.api.annotation.param.*;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;
import org.mule.runtime.extension.api.annotation.param.display.Example;
import org.mule.runtime.extension.api.annotation.param.display.Summary;
import org.mule.runtime.extension.api.runtime.operation.Result;

import java.io.InputStream;
import java.util.Map;

import static org.mule.runtime.extension.api.annotation.param.MediaType.ANY;

public class LoanOperations extends ConnectorOperations<BlendlabsConfiguration, BlendlabsConnection, LoanService> {
    public LoanOperations() {
        super(LoanServiceImpl::new);
    }


    /**
     * Get a paginated list of active loans sorted by creation date descending. This endpoint also supports a few filtering parameters.
     *
     * @param configuration    Blend configurations
     * @param connection      Blend connection object
     * @param limit           The number of loans to be provided for this call. Minimum is 1, maximum is 100, default is 50.
     * @param cursor          An opaque string used for pagination, pass the cursor back to start at this position
     * @param losIdExists     If losId-exists is true, then the response only contains loans that have losId set. If losId-exists is false, then the response only contains loans that do not have losId set. Otherwise, the response returns loans independent of the losId field.
     * @param crmIdEQ         Filters the list of loans to loans that have the same crmId.
     * @param borrowerEmailEQ Filters the list of loans to loans that have the same borrower email.
     * @param losIdEQ         Filters the list of loans to loans that have the same losId.
     * @param exportable      If exportable is true, then the response contains the list of loans that can be exported (e.g. borrower submitted, lender clicks export, or trid is triggered). If exportable is false, then the response contains the list of loans that cannot be exported. If omitted, the response returns loans independent of exportable field.
     * @return List of paginated loans, with cursors to go the next or previous pages.
     */

    @DisplayName("Get loans")
    @Throws(BlendlabsErrorProvider.class)
    @MediaType(value = MediaType.APPLICATION_JSON, strict = false)
    @OutputJsonType(schema = "metadata/get-loans-schema.json")
    public Result<InputStream, ResponseStatus> getLoans(@Config BlendlabsConfiguration configuration, @Connection BlendlabsConnection connection,
                                                        @Summary("The number of loans to be provided for this call. Minimum is 1, maximum is 100, default is 50.") @Example("<integer>") @Optional String limit,
                                                        @Summary("An opaque string used for pagination, pass the cursor back to start at this position") @Example("<string>") @Optional String cursor,
                                                        @Summary("If losId-exists is true, then the response only contains loans that have losId set. If losId-exists is false, then the response only contains loans that do not have losId set. Otherwise, the response returns loans independent of the losId field.") @Example("<boolean>") @Optional String losIdExists,
                                                        @Summary("Filters the list of loans to loans that have the same crmId.") @Example("<string>") @Optional String crmIdEQ,
                                                        @Summary("Filters the list of loans to loans that have the same borrower email.") @Example("<string>") @Optional String borrowerEmailEQ,
                                                        @Summary("Filters the list of loans to loans that have the same losId.") @Example("<string>") @Optional String losIdEQ,
                                                        @Summary("If exportable is true, then the response contains the list of loans that can be exported (e.g. borrower submitted, lender clicks export, or trid is triggered). If exportable is false, then the response contains the list of loans that cannot be exported. If omitted, the response returns loans independent of exportable field.") @Example("<boolean>") @Optional String exportable

    ) {
        return newExecutionBuilder(configuration, connection)
                .execute(LoanService::getLoansResult).withParam(limit).withParam(cursor).withParam(losIdExists)
                .withParam(crmIdEQ).withParam(borrowerEmailEQ).withParam(losIdEQ).withParam(exportable);
    }


    /**
     * Get Export Statuses
     *
     * @param configuration    Blend configurations
     * @param connection Blend connection object
     * @param loanIds    Loan Ids to query for
     * @return Export statuses
     */

    @DisplayName("Get export status")
    @Throws(BlendlabsErrorProvider.class)
    @MediaType(value = MediaType.APPLICATION_JSON, strict = false)
    @OutputJsonType(schema = "metadata/get-export-schema.json")
    public Result<InputStream, ResponseStatus> getExportStatus(@Config BlendlabsConfiguration configuration, @Connection BlendlabsConnection connection,
                                                               @Summary("Loan Ids to query for") @Example("<string>,<string>") String loanIds) {

        return newExecutionBuilder(configuration, connection)
                .execute(LoanService::getExportStatusResult).withParam(loanIds);
    }


    /**
     * Get loan details in JSON, MISMO 3.3.1, or Fannie 3.2 format. The MISMO 3.3.1 and Fannie 3.2 responses are base64 encoded and contain more details about the loan than the JSON response does.
     *
     * @param configuration    Blend configurations
     * @param connection Blend connection object
     * @param loanId     Loan id
     * @param format     Response format
     * @param version    There is no version requirement for json. Fannie version must be 3.2. Mismo version must be 3.3.1.
     * @return loan details in given format
     */

    @DisplayName("Get loan data")
    @Throws(BlendlabsErrorProvider.class)
    @MediaType(value = MediaType.APPLICATION_JSON, strict = false)
    @OutputJsonType(schema = "metadata/get-loan-data-response-schema.json")
    public Result<InputStream, ResponseStatus> getLoanData(@Config BlendlabsConfiguration configuration, @Connection BlendlabsConnection connection,
                                                           String loanId,
                                                           @Summary("Response format") @Optional @Example("<string>") String format,
                                                           @Summary("There is no version requirement for json. Fannie version must be 3.2. Mismo version must be 3.3.1.") @Optional @Example("<string>") String version
    ) {

        return newExecutionBuilder(configuration, connection)
                .execute(LoanService::getLoanDataResult)
                .withParam(loanId).withParam(format).withParam(version);
    }


    /**
     * Get a list of all the uploaded and signature complete documents on a specific loan, ordered by document creation date.
     *
     * @param configuration       Blend configurations
     * @param connection          Blend connection object
     * @param loanId              Loan id
     * @param includeAllExports   If true, the los exported at time in the response will be that of the latest export (if one exists) by any paradigm. If false/not provided, the losExportedAt time in the response will correspond to the latest export (if one exists) by the paradigm of the current caller.
     * @param includeAllDocuments If true, returns a list of all the documents on the loan, including signature pending documents.
     * @return List of documents
     */
    @DisplayName("Get loan documents")
    @Throws(BlendlabsErrorProvider.class)
    @MediaType(value = MediaType.APPLICATION_JSON, strict = false)
    @OutputJsonType(schema = "metadata/get-loan-documents.json")
    public Result<InputStream, ResponseStatus> getLoanDocuments(@Config BlendlabsConfiguration configuration, @Connection BlendlabsConnection connection, String loanId,
                                                                @Summary("If true, returns a list of all the documents on the loan, including signature pending documents.") @Optional @Example("<boolean>") String includeAllDocuments,
                                                                @Summary("If true, the losExportedAt time in the response will be that of the latest export (if one exists) by any paradigm. If false/not provided, the losExportedAt time in the response will correspond to the latest export (if one exists) by the paradigm of the current caller.") @Optional @Example("<boolean>") String includeAllExports
    ) {

        return newExecutionBuilder(configuration, connection)
                .execute(LoanService::getLoanDocumentsResult)
                .withParam(loanId).withParam(includeAllDocuments).withParam(includeAllExports);
    }


    /**
     * Get the list of borrower pairs on the loan (relevant for mortgage loans)
     *
     * @param configuration    Blend configurations
     * @param connection Blend connection object
     * @param loanId     Loan id
     * @return Loan application Details
     */

    @DisplayName("Get loan application details")
    @Throws(BlendlabsErrorProvider.class)
    @MediaType(value = MediaType.APPLICATION_JSON)
    @OutputJsonType(schema = "metadata/get-loan-application-details-schema.json")
    public Result<InputStream, ResponseStatus> getLoanApplicationDetails(@Config BlendlabsConfiguration configuration, @Connection BlendlabsConnection connection, String loanId) {

        return newExecutionBuilder(configuration, connection)
                .execute(LoanService::getLoanApplicationDetailsResult)
                .withParam(loanId);
    }


    /**
     * Update export status on a loan
     *
     * @param configuration    Blend configurations
     * @param connection Blend connection object
     * @param loanId     Loan id
     * @param body       Export status and reason for that status
     * @return Export status schema.
     */
    @DisplayName("Post export status")
    @Throws(BlendlabsErrorProvider.class)
    @MediaType(value = MediaType.APPLICATION_JSON, strict = false)
    @OutputJsonType(schema = "metadata/export-status-schema.json")
    public Result<InputStream, ResponseStatus> postExportStatus(@Config BlendlabsConfiguration configuration, @Connection BlendlabsConnection connection,
                                                                String loanId,
                                                                @Summary("Export status and reason for that status") @InputJsonType(schema = "metadata/export-status-schema.json") @Content Map<String, Object> body
    ) {

        return newExecutionBuilder(configuration, connection)
                .execute(LoanService::getPostExportStatusResult)
                .withParam(loanId).withParam(body);

    }


    /**
     * Create a new loan in Blend with minimal borrower and loan data. This endpoint is usually used to create loans in Blend from CRMs or other central borrower databases.
     *
     * @param configuration    Blend configurations
     * @param connection Blend connection object
     * @param loanBody   Loan creation body
     * @return New loan details
     */


    @DisplayName("Post loan")
    @Throws(BlendlabsErrorProvider.class)
    @MediaType(value = MediaType.APPLICATION_JSON, strict = false)
    @OutputJsonType(schema = "metadata/loan-response-schema.json")
    public Result<InputStream, ResponseStatus> postLoan(@Config BlendlabsConfiguration configuration, @Connection BlendlabsConnection connection,
                                                        @Summary("Loan Creation Body") @InputJsonType(schema = "metadata/loan-request-schema.json") @Content Map<String, Object> loanBody
    ) {

        return newExecutionBuilder(configuration, connection)
                .execute(LoanService::getPostLoanResult)
                .withParam(loanBody);
    }


    /**
     * Bulk update a list of loans to be on specific los milestones
     *
     * @param configuration    Blend configurations
     * @param connection        Blend connection object
     * @param losMilestonesBody List of loan ids and the milestones to update to
     * @return All milestones updated successfully with status and loan id.
     */

    @DisplayName("Post los milestones")
    @Throws(BlendlabsErrorProvider.class)
    @MediaType(value = MediaType.APPLICATION_JSON, strict = false)
    @OutputJsonType(schema = "metadata/post-losmilestone-response-schema.json")
    public Result<InputStream, ResponseStatus> postLosMilestones(@Config BlendlabsConfiguration configuration, @Connection BlendlabsConnection connection,
                                                                 @Summary("List of loan ids and the milestones to update to") @InputJsonType(schema = "metadata/los-milestone-schema.json") @Content Map<String, Object> losMilestonesBody
    ) {

        return newExecutionBuilder(configuration, connection)
                .execute(LoanService::getPostLosMilestonesResult)
                .withParam(losMilestonesBody);

    }


    /**
     * Update properties on a specific loan.
     *
     * @param configuration    Blend configurations
     * @param connection    Blend connection object
     * @param loanId        Loan id
     * @param patchLoanBody Loan update body
     * @return does not return any value.
     *
     */


    @DisplayName("Patch loan")
    @Throws(BlendlabsErrorProvider.class)
    @MediaType(value = MediaType.APPLICATION_JSON, strict = false)
    public Result<InputStream, ResponseStatus> patchLoan(@Config BlendlabsConfiguration configuration, @Connection BlendlabsConnection connection,
                                                         String loanId,
                                                         @Summary("Loan Update Body") @InputJsonType(schema = "metadata/loan-request-schema.json") @Content Map<String, Object> patchLoanBody
    ) {

        return newExecutionBuilder(configuration, connection)
                .execute(LoanService::getPatchLoanResult)
                .withParam(loanId).withParam(patchLoanBody);
    }


    /**
     * Update the location of borrowers on the loan (by 1003/borrower pairing and location on the borrower pair)
     *
     * @param configuration    Blend configurations
     * @param connection                    Blend connection object
     * @param loanId                        Loan id
     * @param putLoanApplicationDetailsBody New borrower pair
     * @return Successful update
     */


    @DisplayName("Put loan application details")
    @Throws(BlendlabsErrorProvider.class)
    @MediaType(value = ANY, strict = false)
    public Result<InputStream, ResponseStatus> putLoanApplicationDetails(@Config BlendlabsConfiguration configuration, @Connection BlendlabsConnection connection,
                                                                         String loanId,
                                                                         @InputJsonType(schema = "metadata/put-loan-application-input-schema.json") @Content Map<String, Object> putLoanApplicationDetailsBody
    ) {


        return newExecutionBuilder(configuration, connection)
                .execute(LoanService::getPutLoanApplicationDetailsResult)
                .withParam(loanId).withParam(putLoanApplicationDetailsBody);

    }


    /**
     * Get a list of borrowers on the loan
     *
     * @param configuration    Blend configurations
     * @param connection Blend connection object
     * @param loanId     Loan id
     * @param losIdEq    Returns only the borrower matching the given LOS identifier, if they exist on this loan
     * @return List of borrowers
     */


    @DisplayName("Get loan borrowers")
    @Throws(BlendlabsErrorProvider.class)
    @MediaType(value = MediaType.APPLICATION_JSON, strict = false)
    @OutputJsonType(schema = "metadata/get-loan-borrowers-schema.json")
    public Result<InputStream, ResponseStatus> getLoanBorrowers(@Config BlendlabsConfiguration configuration, @Connection BlendlabsConnection connection,
                                                                String loanId,
                                                                @Summary("Returns only the borrower matching the given LOS identifier, if they exist on this loan") @Optional @Example("<string>") String losIdEq
    ) {

        return newExecutionBuilder(configuration, connection)
                .execute(LoanService::getLoanBorrowersResult)
                .withParam(loanId).withParam(losIdEq);
    }

}

