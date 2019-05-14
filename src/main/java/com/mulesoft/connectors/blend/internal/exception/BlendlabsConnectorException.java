/**
 * (c) 2003-2019 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */
package com.mulesoft.connectors.blend.internal.exception;

import org.mule.runtime.extension.api.error.ErrorTypeDefinition;
import org.mule.runtime.extension.api.exception.ModuleException;

public class BlendlabsConnectorException extends ModuleException {

    public <T extends Enum<T>> BlendlabsConnectorException(String message, ErrorTypeDefinition<T> errorTypeDefinition) {
        super(message, errorTypeDefinition);
    }

}
