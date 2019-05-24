/**
 * (c) 2003-2019 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */
package com.mulesoft.connectors.blend.internal.error;

import org.mule.runtime.extension.api.error.ErrorTypeDefinition;

import java.util.HashMap;
import java.util.Map;

public enum ErrorTypes implements ErrorTypeDefinition<ErrorTypes> {

    BAD_REQUEST(400),
    UNAUTHORIZED(401),
    FORBIDDEN(403),
    NOT_FOUND(404),
    METHOD_NOT_ALLOWED(405),
    NOT_ACCEPTABLE(406),
    REQUEST_TIMEOUT(408),
    CONFLICT(409),
    INTERNAL_SERVER_ERROR(500),
    NOT_IMPLEMENTED(501),
    BAD_GATEWAY(502),
    SERVICE_UNAVAILABLE(503),
    EMPTY_HEAD_COUNT(0, "X-Total-Count header is Blank"),
    DEAD_TOKEN(1, "Dead access token"),
    GENERIC_EXCEPTION(2),
    UN_PROCESSABLE_ENTITY(422);


    private static Map<Integer, ErrorTypes> map = new HashMap<>();

    static {
        for (ErrorTypes error : ErrorTypes.values()) {
            map.put(error.status, error);
        }
    }

    private Integer status;
    private String message;

    ErrorTypes(Integer status) {
        this.status = status;
    }

    ErrorTypes(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    public static ErrorTypes valueOf(int status) {
        return map.get(status);
    }

    public static ErrorTypes getError(Integer status) {
        return ErrorTypes.valueOf(status);
    }

}
