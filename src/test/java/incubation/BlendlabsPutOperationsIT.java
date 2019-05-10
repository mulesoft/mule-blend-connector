/**
 * (c) 2003-2019 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */
package incubation;

import org.mule.functional.junit4.MuleArtifactFunctionalTestCase;
import org.mule.runtime.api.event.Event;

import java.util.Map;

import org.junit.Assert;
import org.junit.Test;


public class BlendlabsPutOperationsIT extends MuleArtifactFunctionalTestCase {

    /**
     * Specifies the mule config xml with the flows that are going to be executed in the tests, this file lives in the test resources.
     */
    @Override
    protected String getConfigFile() {
        return "put-operation-test.xml";
    }

    @Test
    public void executePutLoanApplicationOperation() throws Exception {
        Map<String, Object> loanData = TestDataBuilder.putApplicationLoanData();
        Map<String, Object> loanApplicationData = TestDataBuilder.putLoanApplicationData();
        Event blendTest = flowRunner("incubation-blendlabs-connectorFlow26").withVariable("loanId", loanData.get("loanId")).withPayload(loanApplicationData).run();
        Object payloadValue=  blendTest.getMessage().getPayload().getValue();
        Assert.assertNotNull(payloadValue);

    }

}
