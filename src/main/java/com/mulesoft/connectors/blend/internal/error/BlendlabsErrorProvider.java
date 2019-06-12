/**
 * (c) 2003-2019 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */
package com.mulesoft.connectors.blend.internal.error;

import org.mule.runtime.extension.api.annotation.error.ErrorTypeProvider;
import org.mule.runtime.extension.api.error.ErrorTypeDefinition;

import static com.mulesoft.connectors.blend.internal.error.ErrorTypes.*;

import java.util.HashSet;
import java.util.Set;

public class BlendlabsErrorProvider implements ErrorTypeProvider {

    @SuppressWarnings("rawtypes")
	@Override
    public Set<ErrorTypeDefinition> getErrorTypes() {
        HashSet<ErrorTypeDefinition> errors = new HashSet<>();
        errors.add(BAD_REQUEST);
        errors.add(UNAUTHORIZED);
        errors.add(FORBIDDEN);
        errors.add(NOT_FOUND);
        errors.add(METHOD_NOT_ALLOWED);
        errors.add(NOT_ACCEPTABLE);
        errors.add(REQUEST_TIMEOUT);
        errors.add(CONFLICT);
        errors.add(INTERNAL_SERVER_ERROR);
        errors.add(NOT_IMPLEMENTED);
        errors.add(BAD_GATEWAY);
        errors.add(SERVICE_UNAVAILABLE);
        errors.add(EMPTY_HEAD_COUNT);
        errors.add(DEAD_TOKEN);
        errors.add(GENERIC_EXCEPTION);
        errors.add(UN_PROCESSABLE_ENTITY);
        return errors;
    }
}
