/**
 * (c) 2003-2019 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */
package incubation;

import org.mule.functional.junit4.MuleArtifactFunctionalTestCase;
import org.mule.runtime.api.event.Event;
import org.junit.Assert;
import org.junit.Test;

public class BlendlabsDeleteOperationsIT extends MuleArtifactFunctionalTestCase {

  /**
   * Specifies the mule config xml with the flows that are going to be executed in the tests, this file lives in the test resources.
   */
  @Override
  protected String getConfigFile() {
    return "delete-operation-test.xml";
  }
  
  
  @Test
  public void executeDeleteBorrowerOperation() throws Exception {

      String borrowerId = "5381ce4f-9ff9-4f5d-b9aa-ea626443bdcc";
      Event blendTest = flowRunner("deleteBorrowerFlow").withVariable("borrowerId", borrowerId).run();
	  Object payloadValue=  blendTest.getMessage().getPayload().getValue();
      Assert.assertNotNull(payloadValue);
  }
}
