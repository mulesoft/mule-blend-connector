/**
 * (c) 2003-2019 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */
package incubation;

import static org.hamcrest.MatcherAssert.assertThat;

import org.mule.functional.junit4.MuleArtifactFunctionalTestCase;
import org.mule.runtime.api.event.Event;
import org.mule.runtime.api.metadata.DataType;


import static org.hamcrest.Matchers.is;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import static incubation.TestDataBuilder.*;

public class BlendlabsGetOperationsIT extends MuleArtifactFunctionalTestCase {
	private static final String LOAN_ID = "loanId";
  /**
   * Specifies the mule config xml with the flows that are going to be executed in the tests, this file lives in the test resources.
   */
  @Override
  protected String getConfigFile() {
    return "get-operation-test.xml";
  }
 
  @Test
  public void executeGetLoansOperation() throws Exception {
	  Event blendTest = flowRunner("getLoansFlow").run();
      Object payloadValue= blendTest.getMessage().getPayload().getValue();
	  JSONObject jObject  = new JSONObject(payloadValue);
	  Assert.assertNotNull(jObject);
  }
  
  @Test
  public void executeGetExportStatusesOperation() throws Exception {
	  Map<String, Object> loanData=TestDataBuilder.getLoanData();
	  Event blendTest = flowRunner("getExportStatusFlow").withVariable(LOAN_ID,loanData.get(LOAN_ID) ).run();
	  Object payloadValue=  blendTest.getMessage().getPayload().getValue();
      JSONObject jObject  = new JSONObject(payloadValue);
      Assert.assertNotNull(jObject);
  }

  @Test
  public void executeGetDocumentsOperation() throws Exception {
      Map<String, Object> expectedDataMap = TestDataBuilder.createLoanDocumentsData();
	  List<Map<String, Object>> expectedLoanDocumentDataMap= (List<Map<String, Object>>) expectedDataMap.get("documents");
	  Event blendTest = flowRunner("getDocumentsFlow").run();
	  Object payloadValue=  blendTest
              .getMessage()
              .getPayload()
              .getValue();
       JSONObject jObject  = new JSONObject(payloadValue);
       Assert.assertNotNull(jObject);
  }

  @Test
  public void executeGetDocumentDataOperation() throws Exception {
      Event blendTest = flowRunner("getDocumentDataFlowFlow").withVariable("documentId", DOCUMENT_ID_VALUE).run();
      DataType payloadValue = ( blendTest
              .getMessage()
              .getPayload()
              .getDataType());
     	  Assert.assertNotNull(payloadValue);
    	  assertThat(payloadValue.getMediaType().getCharset().get().name(), is("UTF-8"));

  }

  @Test
  public void executeGetLoanDataOperation() throws Exception {
	  Map<String, Object> loanData=TestDataBuilder.getLoanData();
	  Event blendTest = flowRunner("getLoanDataFlow").run();
	  Object payloadValue=  blendTest.getMessage().getPayload().getValue();
      JSONObject obj = new JSONObject(payloadValue);
      Assert.assertNotNull(obj);
  }
  @Test
  public void executeGetLoanDocumentsOperation() throws Exception {
	  Map<String, Object> expectedDataMap = TestDataBuilder.createLoanDocumentsData();
	  List<Map<String, Object>> expectedLoanDocumentDataMap= (List<Map<String, Object>>) expectedDataMap.get("documents");
	  Event blendTest = flowRunner("getLoanDocumentsFlow").run();
	  Object payloadValue=  blendTest
              .getMessage()
              .getPayload()
              .getValue();
       JSONObject jObject  = new JSONObject(payloadValue);
      Assert.assertNotNull(jObject);
  }


  @Test
  public void executeGetLoanApplicationDetailsOperation() throws Exception {
	  Map<String, Object> loanData=TestDataBuilder.createLoanApplicationData();
	  Event blendTest = flowRunner("getLoanApplicationDetailsFlow").run();
	  Object payloadValue=  blendTest.getMessage().getPayload().getValue();
      JSONObject jObject  = new JSONObject(payloadValue);
      Assert.assertNotNull(jObject);
  }

  @Test
  public void executeGetDisclosuresPackagesOperation() throws Exception {
	  Map<String, Object> loanData=TestDataBuilder.getLoanData();
	  Event blendTest = flowRunner("blendFlow").run();
	  Object payloadValue=  blendTest.getMessage().getPayload().getValue();
      JSONObject jObject  = new JSONObject(payloadValue);
      Assert.assertNotNull(jObject);
  }

  @Test
  public void executeGetLoanBorrowersOperation() throws Exception {
	  Map<String, Object> loanData=TestDataBuilder.getLoanBorrowerData();
	  Event blendTest = flowRunner("GetLoanBorrowerFlow").run();
	  Object payloadValue= blendTest.getMessage().getPayload().getValue();
      JSONObject actualBorrowerData  = new JSONObject(payloadValue);
      Assert.assertNotNull(actualBorrowerData);
  }
 }
