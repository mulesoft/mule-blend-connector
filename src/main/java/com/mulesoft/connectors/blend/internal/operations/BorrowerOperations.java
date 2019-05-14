/**
 * (c) 2003-2019 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */

package com.mulesoft.connectors.blend.internal.operations;

import com.mulesoft.connectors.blend.api.ResponseStatus;
import com.mulesoft.connectors.blend.internal.config.BlendlabsConfiguration;
import com.mulesoft.connectors.blend.internal.connection.BlendlabsConnection;
import com.mulesoft.connectors.blend.internal.error.BlendlabsErrorProvider;
import com.mulesoft.connectors.blend.internal.service.BorrowerService;
import com.mulesoft.connectors.blend.internal.service.BorrowerServiceImpl;
import org.mule.connectors.commons.template.operation.ConnectorOperations;
import org.mule.runtime.extension.api.annotation.error.Throws;
import org.mule.runtime.extension.api.annotation.metadata.fixed.InputJsonType;
import org.mule.runtime.extension.api.annotation.metadata.fixed.OutputJsonType;
import org.mule.runtime.extension.api.annotation.param.Config;
import org.mule.runtime.extension.api.annotation.param.Connection;
import org.mule.runtime.extension.api.annotation.param.Content;
import org.mule.runtime.extension.api.annotation.param.MediaType;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;
import org.mule.runtime.extension.api.annotation.param.display.Summary;
import org.mule.runtime.extension.api.runtime.operation.Result;

import java.io.InputStream;
import java.util.Map;

import static org.mule.runtime.extension.api.annotation.param.MediaType.ANY;

public class  BorrowerOperations extends ConnectorOperations<BlendlabsConfiguration, BlendlabsConnection, BorrowerService> {

    public BorrowerOperations() {
        super(BorrowerServiceImpl::new);
    }


    /**
     * Add a borrower to a specific loan. You can only add non-primary borrowers through this endpoint.
     *
     * @param configuration    Blend configurations
     * @param connection       Blend connection object
     * @param postBorrowerBody Borrower parameters
     * @return Borrower schema name expanded schema.
     */


    @DisplayName("Create a Borrower")
    @Throws(BlendlabsErrorProvider.class)
    @MediaType(value = MediaType.APPLICATION_JSON, strict = false)
    @OutputJsonType(schema = "metadata/post-borrower-schema.json")
    public Result<InputStream, ResponseStatus> postBorrower(@Config BlendlabsConfiguration configuration, @Connection BlendlabsConnection connection,
                                                            @Summary("Borrower parameters") @InputJsonType(schema = "metadata/post-borrower-input-schema.json") @Content Map<String, Object> postBorrowerBody
    ) {
        return newExecutionBuilder(configuration, connection)
                .execute(BorrowerService::getPostBorrowerResult).withParam(postBorrowerBody);
    }


    /**
     * Update a borrower's details.
     *
     * @param configuration    Blend configurations
     * @param connection        Blend connection object
     * @param borrowerId        Borrower ID
     * @param patchBorrowerBody Borrower update parameters
     * @return Success message.
     */

    @DisplayName("Update a Borrower")
    @Throws(BlendlabsErrorProvider.class)
    @MediaType(value = MediaType.APPLICATION_JSON, strict = false)
    @OutputJsonType(schema = "metadata/post-borrower-schema.json")
    public Result<InputStream, ResponseStatus> patchBorrower(@Config BlendlabsConfiguration configuration, @Connection BlendlabsConnection connection,
                                                             String borrowerId,
                                                             @Summary("Borrower update parameters") @InputJsonType(schema = "metadata/patch-borrower-input-schema.json") @Content Map<String, Object> patchBorrowerBody
    ) {

        return newExecutionBuilder(configuration, connection)
                .execute(BorrowerService::getPatchBorrowerResult).withParam(borrowerId).withParam(patchBorrowerBody);

    }


    /**
     /**
     * Delete borrower from a specific loan.
     *
     * @param configuration    Blend configurations
     * @param connection Blend connection object
     * @param borrowerId Borrower ID
     * @return Successful deletion message.
     */


    @DisplayName("Delete a Borrower")
    @Throws(BlendlabsErrorProvider.class)
    @MediaType(value = ANY, strict = false)
    public Result<InputStream, ResponseStatus> deleteBorrower(@Config BlendlabsConfiguration configuration,
                                                              @Connection BlendlabsConnection connection, String borrowerId) {

        return newExecutionBuilder(configuration, connection)
                .execute(BorrowerService::deleteBorrowerService).withParam(borrowerId);

    }

}

