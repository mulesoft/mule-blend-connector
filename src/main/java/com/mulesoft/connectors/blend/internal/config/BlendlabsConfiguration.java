/**
 * (c) 2003-2019 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */
package com.mulesoft.connectors.blend.internal.config;

import com.mulesoft.connectors.blend.internal.connection.provider.BlendlabsConnectionProvider;
import com.mulesoft.connectors.blend.internal.operations.*;
import org.mule.connectors.commons.template.config.ConnectorConfig;
import org.mule.runtime.extension.api.annotation.Operations;
import org.mule.runtime.extension.api.annotation.connectivity.ConnectionProviders;
import org.mule.runtime.extension.api.annotation.param.Parameter;
/**
 * This class represents an extension configuration, values set in this class are commonly used across multiple
 * operations since they represent something core from the extension.
 */
@Operations({RealtorOperations.class, LoanOperations.class, BorrowerOperations.class, DisclosurePackageOperations.class, DocumentOperations.class})
@ConnectionProviders(BlendlabsConnectionProvider.class)
public class BlendlabsConfiguration implements ConnectorConfig {

    @Parameter
    protected static String address;

    public String getAddress() {
        return address;
    }

    public static String getAddressValue(){
        return address;
    }

}
