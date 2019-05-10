/**
 * (c) 2003-2019 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */
package incubation;

import org.mule.functional.junit4.MuleArtifactFunctionalTestCase;
import org.mule.runtime.api.event.Event;

import java.util.Map;

import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import static incubation.TestDataBuilder.*;

public class BlendlabsPatchOperationsIT extends MuleArtifactFunctionalTestCase {

  /**
   * Specifies the mule config xml with the flows that are going to be executed in the tests, this file lives in the test resources.
   */
  @Override
  protected String getConfigFile() {
    return "patch-operation-test.xml";
  }
  @Test
  public void executePatchLoanOperation() throws Exception {
	  Map<String, Object> loanData=TestDataBuilder.createPatchLoanData();
	  Event blendTest = flowRunner("incubation-blendlabs-connectorFlow27").withVariable("loanId", LOAN_ID_VALUE).withPayload(loanData).run();
      Object payloadValue=  blendTest.getMessage().getPayload().getValue();
      JSONObject jObject = new JSONObject(payloadValue);
      Assert.assertNotNull(jObject);
  }

 @Test
  public void executePatchBorrowerOperation() throws Exception {
	  Map<String, Object> loanBorrowerData=TestDataBuilder.createPatchBorrowerData();
	  Event blendTest = flowRunner("incubation-blendlabs-connectorFlow28").withVariable("borrowerId", BORROWER_ID_VALUE).withPayload(loanBorrowerData).run();
	  Object payloadValue=  blendTest.getMessage().getPayload().getValue();
	  JSONObject jObject = new JSONObject(payloadValue);
      Assert.assertNotNull(jObject);
      //Assert.assertNotNull(jObject.getString("id"));
  }
  
}
