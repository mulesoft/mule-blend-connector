/**
 * (c) 2003-2019 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */

package com.mulesoft.connectors.blend.internal.operations;

import com.mulesoft.connectors.blend.api.ResponseStatus;
import com.mulesoft.connectors.blend.internal.config.BlendlabsConfiguration;
import com.mulesoft.connectors.blend.internal.connection.BlendlabsConnection;
import com.mulesoft.connectors.blend.internal.error.BlendlabsErrorProvider;
import com.mulesoft.connectors.blend.internal.service.RealtorService;
import com.mulesoft.connectors.blend.internal.service.RealtorServiceImpl;
import org.mule.connectors.commons.template.operation.ConnectorOperations;
import org.mule.runtime.extension.api.annotation.error.Throws;
import org.mule.runtime.extension.api.annotation.metadata.fixed.InputJsonType;
import org.mule.runtime.extension.api.annotation.metadata.fixed.OutputJsonType;
import org.mule.runtime.extension.api.annotation.param.Config;
import org.mule.runtime.extension.api.annotation.param.Connection;
import org.mule.runtime.extension.api.annotation.param.Content;
import org.mule.runtime.extension.api.annotation.param.MediaType;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;
import org.mule.runtime.extension.api.runtime.operation.Result;

import java.io.InputStream;
import java.util.Map;

public class RealtorOperations extends ConnectorOperations<BlendlabsConfiguration, BlendlabsConnection, RealtorService> {
    public RealtorOperations() {
        super(RealtorServiceImpl::new);
    }


    /**
     * Add realtor to loan
     *
     * @param configuration    Blend configurations
     * @param connection      Blend connection object
     * @param postRealtorBody Realtor request schema
     * @return Realtor created with id
     */

    @DisplayName("Post realtor")
    @Throws(BlendlabsErrorProvider.class)
    @MediaType(value = MediaType.APPLICATION_JSON, strict = false)
    @OutputJsonType(schema = "metadata/realtor-response-schema.json")
    public Result<InputStream, ResponseStatus> postRealtor(@Config BlendlabsConfiguration configuration, @Connection BlendlabsConnection connection,
                                                           @InputJsonType(schema = "metadata/realtor-request-schema.json") @Content Map<String, Object> postRealtorBody) {
        return newExecutionBuilder(configuration, connection)
                .execute(RealtorService::getResultPostRealtorService).withParam(postRealtorBody);
    }

}

