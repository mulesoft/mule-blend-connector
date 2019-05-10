/**
 * (c) 2003-2019 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */

package com.mulesoft.connectors.blend.internal.operations;

import com.mulesoft.connectors.blend.api.ResponseStatus;
import com.mulesoft.connectors.blend.internal.config.BlendlabsConfiguration;
import com.mulesoft.connectors.blend.internal.connection.BlendlabsConnection;
import com.mulesoft.connectors.blend.internal.error.BlendlabsErrorProvider;
import com.mulesoft.connectors.blend.internal.service.DocumentService;
import com.mulesoft.connectors.blend.internal.service.DocumentServiceImpl;
import org.mule.connectors.commons.template.operation.ConnectorOperations;
import org.mule.runtime.extension.api.annotation.error.Throws;
import org.mule.runtime.extension.api.annotation.metadata.fixed.OutputJsonType;
import org.mule.runtime.extension.api.annotation.param.*;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;
import org.mule.runtime.extension.api.annotation.param.display.Example;
import org.mule.runtime.extension.api.annotation.param.display.Summary;
import org.mule.runtime.extension.api.runtime.operation.Result;

import java.io.InputStream;

import static org.mule.runtime.extension.api.annotation.param.MediaType.ANY;
import static org.mule.runtime.extension.api.annotation.param.MediaType.APPLICATION_JSON;

public class DocumentOperations extends ConnectorOperations<BlendlabsConfiguration, BlendlabsConnection, DocumentService> {

    public DocumentOperations() {
        super(DocumentServiceImpl::new);
    }


    /**
     * Get a paginated list of documents
     *
     * @param configuration    Blend configurations
     * @param connection        Blend connection object
     * @param limit             The number of loans to be provided for this call. Minimum is 1, maximum is 100, default is 50.
     * @param cursor            An opaque string used for pagination, pass the cursor back to start at this position
     * @param exported          If exported is true, then the response only contains documents that have already been exported. If exported is false, then the response only contains documents that have not already been exported. Otherwise, the response returns documents independent of the exported field.
     * @param includeAllExports If true, the losExportedAt time in the response will be that of the latest export (if one exists) by any paradigm. If false/not provided, the losExportedAt time in the response will correspond to the latest export (if one exists) by the paradigm of the current caller.
     * @param uploadedAfter     Returns only documents uploaded after this time (UNIX milliseconds since epoch)
     * @param uploadedBefore    Returns only documents uploaded before this time (UNIX milliseconds since epoch)
     * @param statusIncludes    Comma delimited string of document statuses to filter for
     * @return List of paginated documents, with cursors to go the next or previous pages.
     */

    @DisplayName("Get documents")
    @Throws(BlendlabsErrorProvider.class)
    @MediaType(value = MediaType.APPLICATION_JSON, strict = false)
    @OutputJsonType(schema = "metadata/get-documents.json")
    public Result<InputStream, ResponseStatus> getDocuments(@Config BlendlabsConfiguration configuration, @Connection BlendlabsConnection connection,
                                                            @Summary("The number of loans to be provided for this call. Minimum is 1, maximum is 100, default is 50.") @Optional @Example("<number>") String limit,
                                                            @Summary("An opaque string used for pagination, pass the cursor back to start at this position") @Optional @Example("<string>") String cursor,
                                                            @Summary("If exported is true, then the response only contains documents that have already been exported. If exported is false, then the response only contains documents that have not already been exported. Otherwise, the response returns documents independent of the exported field.") @Optional @Example("<boolean>") String exported,
                                                            @Summary("If true, the losExportedAt time in the response will be that of the latest export (if one exists) by any paradigm. If false/not provided, the losExportedAt time in the response will correspond to the latest export (if one exists) by the paradigm of the current caller.") @Optional @Example("<boolean>") String includeAllExports,
                                                            @Summary("Returns only documents uploaded after this time (UNIX milliseconds since epoch)") @Optional @Example("<number>") String uploadedAfter,
                                                            @Summary("Returns only documents uploaded before this time (UNIX milliseconds since epoch)") @Optional @Example("<number>") String uploadedBefore,
                                                            @Summary("Comma delimited string of document statuses to filter for") @Optional @Example("<string>") String statusIncludes
    ) {

        return newExecutionBuilder(configuration, connection)
                .execute(DocumentService::getDocumentsResult).withParam(limit).withParam(cursor).withParam(exported)
                .withParam(includeAllExports).withParam(uploadedAfter).withParam(uploadedBefore).withParam(statusIncludes);

    }

    /**
     * Download the file associated with a document
     *
     * @param configuration    Blend configurations
     * @param connection Blend connection object
     * @param documentID Document id
     * @return Document binary data
     */
    @DisplayName("Get document data")
    @Throws(BlendlabsErrorProvider.class)
    @MediaType(value = ANY, strict = false)
    public Result<InputStream, ResponseStatus> getDocumentData(@Config BlendlabsConfiguration configuration, @Connection BlendlabsConnection connection, String documentID) {
        return newExecutionBuilder(configuration, connection)
                .execute(DocumentService::getDocumentDataResult).withParam(documentID);

    }

    /**
     * Create a document on a specific loan with a file and related metadata.
     *
     * @param configuration    Blend configurations
     * @param connection    Blend connection object
     * @param documentsBody Document request schema
     * @return Document schema
     */
    @DisplayName("Post document")
    @Throws(BlendlabsErrorProvider.class)
    @MediaType(value = APPLICATION_JSON, strict = false)
    @OutputJsonType(schema = "metadata/post-document-output-schema.json")
    public Result<InputStream, ResponseStatus> postDocument(@Config BlendlabsConfiguration configuration, @Connection BlendlabsConnection connection,
                                                             @Content InputStream documentsBody) {
        return newExecutionBuilder(configuration, connection)
                .execute(DocumentService::getPostDocumentResult).withParam(documentsBody);
    }


}

