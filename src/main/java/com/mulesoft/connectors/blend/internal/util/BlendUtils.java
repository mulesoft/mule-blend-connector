/**
 * (c) 2003-2019 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */
package com.mulesoft.connectors.blend.internal.util;

import com.google.gson.Gson;
import org.apache.commons.io.IOUtils;
import org.mule.runtime.http.api.domain.message.response.HttpResponse;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class BlendUtils {

    private final static org.slf4j.Logger logger = LoggerFactory.getLogger(BlendUtils.class);

    public static InputStream getContentInputStream(CompletableFuture<HttpResponse> response) {

        InputStream inputStream = null;
        try {
            inputStream = response.get().getEntity().getContent();
            return inputStream;
        } catch (ExecutionException ex) {
            logger.info("Error : ", ex);
        } catch (InterruptedException ex) {
            logger.info("Error : ", ex);
        } catch (Exception ex) {
            logger.info("Error : ", ex);
        }
        return inputStream;
    }

    public static byte[] getByteArrayData(Map map){
        Gson gson = new Gson();
        String jsonBody = gson.toJson(map);
        byte[] byteArrayData = jsonBody.getBytes(StandardCharsets.UTF_8);
        return byteArrayData;
    }

    public static byte[] getByteArrayFromInputStream(InputStream is){
        byte[] byteArray = new byte[0];

        try {
            byteArray = IOUtils.toByteArray(is);
        } catch (IOException e) {
            logger.info("Error while getting byte array from input stream :", e);
        }
        return byteArray;
    }
}
