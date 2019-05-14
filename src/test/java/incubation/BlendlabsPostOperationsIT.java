/**
 * (c) 2003-2019 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */
package incubation;
import org.junit.Ignore;
import org.mule.functional.junit4.MuleArtifactFunctionalTestCase;
import org.mule.runtime.api.event.Event;

import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import static incubation.TestDataBuilder.LOAN_ID_VALUE;


public class BlendlabsPostOperationsIT extends MuleArtifactFunctionalTestCase {

    /**
     * Specifies the mule config xml with the flows that are going to be executed in the tests, this file lives in the test resources.
     */
    @Override
    protected String getConfigFile() {
        return "post-operation-test.xml";
    }


    @Test
    public void executePostLoansOperation() throws Exception {

        Map<String, Object> loanData = TestDataBuilder.createLoanDataNew();
        Event blendTest = flowRunner("postLoanFlow").withPayload(loanData).run();
        Object payloadValue = blendTest.getMessage().getPayload().getValue();

        JSONObject jObject = new JSONObject(payloadValue);
        Assert.assertNotNull(jObject);
    }

    @Test
    public void executePostExportStatusesOperation() throws Exception {
        Map<String, Object> expectedData = TestDataBuilder.createForPostExportStatusesData();
        Event blendTest = flowRunner("postExportStatusFlow").withPayload(expectedData).run();
        Object payloadValue =  blendTest.getMessage().getPayload().getValue();
        JSONObject actualData = new JSONObject(payloadValue);
        Assert.assertNotNull(actualData);

    }

    @Test
    public void executePostBorrowerOperation() throws Exception {
        String loadId = "3d42f5ec-5a11-41fc-888d-14bbc9a098cc";

        Map<String, Object> loanBorrowerData = TestDataBuilder.createBorrowerData();
        loanBorrowerData.put("loanId", loadId);
        Event blendTest = flowRunner("postBorrowerFlow").withPayload(loanBorrowerData).run();
        Object payloadValue = blendTest.getMessage().getPayload().getValue();
        JSONObject jObject = new JSONObject(payloadValue);
        Assert.assertNotNull(jObject);

    }

    @Test
    public void executePostLoMilestonesOperation() throws Exception {
        String loadId = "3d42f5ec-5a11-41fc-888d-14bbc9a098cc";
        Map<String, Object> postLosMilstonesData = TestDataBuilder.createPostLosMilstonesData(loadId);
        Event blendTest = flowRunner("postLosMilestonesFlow").withPayload(postLosMilstonesData).run();
        Object payloadValue = blendTest.getMessage().getPayload().getValue();
        JSONObject jObject = new JSONObject(payloadValue);
        Assert.assertNotNull(jObject);
    }

    @Test
    public void executePostRealtorOperation() throws Exception {
        Map<String, Object> realtorData = TestDataBuilder.createPostRealtorData();
        Event blendTest = flowRunner("postRealtorFlowFlow").withPayload(realtorData).run();
        Object payloadValue = blendTest.getMessage().getPayload().getValue();
        JSONObject jObject = new JSONObject(payloadValue);
        Assert.assertNotNull(jObject);
    }

    @Test
    public void executePostDocumentOperation() throws Exception {

        Map<String, Object> loanData=TestDataBuilder.createLoanData();
        Event blendTest = flowRunner("PostDocumentFlow").withPayload(loanData).run();
        Object payloadValue=  blendTest.getMessage().getPayload().getValue();
        JSONObject jObject  = new JSONObject(payloadValue);
        Assert.assertNotNull(jObject);
    }
}


