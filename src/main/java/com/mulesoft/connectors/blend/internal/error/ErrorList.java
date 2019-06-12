/**
 * (c) 2003-2019 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */

package com.mulesoft.connectors.blend.internal.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "trackingId",
        "error",
        "display"
})

public class ErrorList {


    @JsonPropertyDescription("Tracking id of the error")
    private String trackingId;

    @JsonPropertyDescription("Error message")
    private String error;

    @JsonPropertyDescription("Error to display")
    private String display;

    public String getTrackingId() {
        return trackingId;
    }

    public void setTrackingId(String trackingId) {
        this.trackingId = trackingId;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    @Override
    public String toString(){
        String returnMsg = new String();
        returnMsg = (trackingId != null) ? returnMsg + "trackingId: " + trackingId + "\n" : returnMsg;
        returnMsg = (error != null) ? returnMsg + "error: " + error + "\n" : returnMsg;
        returnMsg = (display != null) ? returnMsg + "display: " + display + "\n" : returnMsg;
        return returnMsg;
    }
}
