/**
 * (c) 2003-2019 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */

package com.mulesoft.connectors.blend.internal.operations;

import com.mulesoft.connectors.blend.api.ResponseStatus;
import com.mulesoft.connectors.blend.internal.config.BlendlabsConfiguration;
import com.mulesoft.connectors.blend.internal.connection.BlendlabsConnection;
import com.mulesoft.connectors.blend.internal.error.BlendlabsErrorProvider;
import com.mulesoft.connectors.blend.internal.service.DisclosurePackageService;
import com.mulesoft.connectors.blend.internal.service.DisclosurePackageServiceImpl;
import org.mule.connectors.commons.template.operation.ConnectorOperations;
import org.mule.runtime.extension.api.annotation.error.Throws;
import org.mule.runtime.extension.api.annotation.metadata.fixed.OutputJsonType;
import org.mule.runtime.extension.api.annotation.param.Config;
import org.mule.runtime.extension.api.annotation.param.Connection;
import org.mule.runtime.extension.api.annotation.param.MediaType;
import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;
import org.mule.runtime.extension.api.annotation.param.display.Example;
import org.mule.runtime.extension.api.annotation.param.display.Summary;
import org.mule.runtime.extension.api.runtime.operation.Result;

import java.io.InputStream;

public class DisclosurePackageOperations extends ConnectorOperations<BlendlabsConfiguration, BlendlabsConnection, DisclosurePackageService> {

    public DisclosurePackageOperations() {
        super(DisclosurePackageServiceImpl::new);
    }



    /**
     * Retrieves status and metadata for all disclosures packages on a specific loan.
     *
     * @param configuration    Blend configurations
     * @param connection Blend connection object
     * @param loanId     Loan id to filter disclosures packages by
     * @param status     Disclosures package status to filter by
     * @return Disclosures package list.
     */

    @DisplayName("Get Disclosures Packages")
    @Throws(BlendlabsErrorProvider.class)
    @MediaType(value = MediaType.APPLICATION_JSON, strict = false)
    @OutputJsonType(schema = "metadata/get-disclosures-packages-schema.json")
    public Result<InputStream, ResponseStatus> getDisclosuresPackages(@Config BlendlabsConfiguration configuration, @Connection BlendlabsConnection connection,
                                                                      @Summary("Loan id to filter disclosures packages by") String loanId,
                                                                      @Summary("Disclosures package status to filter by") @Optional @Example("<string>") String status) {


        return newExecutionBuilder(configuration, connection)
                .execute(DisclosurePackageService::getDisclosuresPackagesResult)
                .withParam(loanId).withParam(status);


    }

}

