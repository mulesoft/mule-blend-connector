/**
 * (c) 2003-2019 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */
package incubation;


import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class TestDataBuilder {

	public static final String LOAN_ID_VALUE = "964ff961-8ff8-4c43-8b56-f67672e561d8";
	public static final String BORROWER_ID_VALUE = "d97e089a-3901-464d-827b-5b5631b199bd";
	public static final String DOCUMENT_ID_VALUE = "028976fd-c81e-4cd6-928b-6b6f079387d8";
	private TestDataBuilder() {
	    throw new IllegalStateException("Test DataBuilder Class");
	  }
	
	public static Map<String, Object> createLoanData() {
		 	Map<String, Object> entry = new HashMap<>();
		 	
		    Map<String, Object> borrower = new HashMap<>();  
		    Map<String, Object> currentAddress = new HashMap<>();
		    Map<String, Object> name = new HashMap<>();
		    Map<String, Object> propertyAddress = new HashMap<>();
		    Map<String, Object> applicationSource=new HashMap<>();
		    
	        entry.put("loanType", "MORTGAGE");
	        entry.put("applicationType", "FULL_APPLICATION");
	        entry.put("loanPurposeType", "PURCHASE");
	        entry.put("propertyType", "SINGLE_FAMILY");
	        entry.put("propertySearchType", "NOT_STARTED");
	        entry.put("propertyUsageType", "PRIMARY_RESIDENCE");
	        entry.put("loanAmount", 100000);
	        entry.put("propertyValue", 200000);
	        
	        propertyAddress.put("streetAddressLine1", "100 Main St");
	        propertyAddress.put("streetAddressLine2", "Apt 10");
	        propertyAddress.put("city", "Chicago");
	        propertyAddress.put("state", "IL");
	        propertyAddress.put("zipCode", "60007");
	        propertyAddress.put("zipCodePlusFour", "1000");
	        
	        name.put("firstName", "Venkatesh");
	        name.put("middleName", "M");
	        name.put("lastName", "Velisoju");
	        name.put("suffixName", "Mr.");
	        
	        currentAddress.put("streetAddressLine1", "100 Main St");
	        currentAddress.put("streetAddressLine2", "Apt 10");
	        currentAddress.put("city", "Chicago");
	        currentAddress.put("state", "IL");
	        currentAddress.put("zipCode", "60007");
	        currentAddress.put("zipCodePlusFour", "1000");
	        
	        borrower.put("name", name);
	        borrower.put("email", "velisoju.mouny@gmail.com");
	        borrower.put("SSN", "000113933");
	        borrower.put("dateOfBirth", 19081992);
	        borrower.put("homePhone", "7287820821");
	        borrower.put("currentAddress", currentAddress);
	        
	        entry.put("propertyAddress", propertyAddress);
	        entry.put("borrower", borrower);
	        entry.put("leadId", "leadId");
	        entry.put("crmId", "crmId1");
	        entry.put("referenceNumber", "100");
	        entry.put("referrerEmail", "vijay.rao@apisero.com");
	        entry.put("sendEmailInvite", true);
	        entry.put("applicationTemplateId", "");
	       
	        applicationSource.put("type", "LOS");
	        applicationSource.put("name", " Borrower");
	        
	        entry.put("applicationSource", applicationSource);
	        
	
	       
	        return entry;
	}
	public static Map<String, Object> createForPostExportStatusesData()
	{
		Map<String, Object> entry = new HashMap<>();

		entry.put("status","SUCCESS");
		entry.put("reason","Succeessfully...");
		return entry;
	}

	public static Map<String, Object> createLoanDataNew() {
		Map<String, Object> entry = new HashMap<>();

		Map<String, Object> borrower = new HashMap<>();
		Map<String, Object> currentAddress = new HashMap<>();
		Map<String, Object> name = new HashMap<>();
		Map<String, Object> propertyAddress = new HashMap<>();
		Map<String, Object> applicationSource=new HashMap<>();

		entry.put("loanType", "MORTGAGE");
		entry.put("applicationType", "FULL_APPLICATION");
		entry.put("loanPurposeType", "PURCHASE");
		entry.put("propertyType", "SINGLE_FAMILY");
		entry.put("propertySearchType", "NOT_STARTED");
		entry.put("propertyUsageType", "PRIMARY_RESIDENCE");
		entry.put("loanAmount", 1000001);
		entry.put("propertyValue", 2000001);

		propertyAddress.put("streetAddressLine1", "100 Main St");
		propertyAddress.put("streetAddressLine2", "Apt 101");
		propertyAddress.put("city", "Chicago");
		propertyAddress.put("state", "IL");
		propertyAddress.put("zipCode", "60007");
		propertyAddress.put("zipCodePlusFour", "1000");

		name.put("firstName", "Venkatesh1");
		name.put("middleName", "M");
		name.put("lastName", "Velisoju1");
		name.put("suffixName", "Mr.");

		currentAddress.put("streetAddressLine1", "100 Main St1");
		currentAddress.put("streetAddressLine2", "Apt 101");
		currentAddress.put("city", "Chicago");
		currentAddress.put("state", "IL");
		currentAddress.put("zipCode", "60007");
		currentAddress.put("zipCodePlusFour", "1000");

		borrower.put("name", name);
		borrower.put("email", "velisoju.mouny@gmail.com");
		borrower.put("SSN", "000113933");
		borrower.put("dateOfBirth", 190819921);
		borrower.put("homePhone", "7287820821");
		borrower.put("currentAddress", currentAddress);

		entry.put("propertyAddress", propertyAddress);
		entry.put("borrower", borrower);
		entry.put("leadId", "leadId1");
		entry.put("crmId", "crmId1");
		entry.put("referenceNumber", "100");
		entry.put("referrerEmail", "");
		entry.put("sendEmailInvite", true);
		entry.put("applicationTemplateId", "");

		applicationSource.put("type", "LOS");
		applicationSource.put("name", " Borrower1");

		entry.put("applicationSource", applicationSource);



		return entry;
	}

	public static Map<String, Object> createPatchLoanData() {
	 	Map<String, Object> entry = new HashMap<>();
	 	
	    Map<String, Object> borrower = new HashMap<>();  
	    Map<String, Object> currentAddress = new HashMap<>();
	    Map<String, Object> name = new HashMap<>();
	    Map<String, Object> propertyAddress = new HashMap<>();
	  
	    
        entry.put("loanType", "MORTGAGE");
        entry.put("applicationType", "FULL_APPLICATION");
        entry.put("loanPurposeType", "PURCHASE");
        entry.put("propertyType", "SINGLE_FAMILY");
        entry.put("propertySearchType", "NOT_STARTED");
        entry.put("propertyUsageType", "PRIMARY_RESIDENCE");
        entry.put("loanAmount", 100000);
        entry.put("propertyValue", 200000);
        
        propertyAddress.put("streetAddressLine1", "100 Main St");
        propertyAddress.put("streetAddressLine2", "Apt 10");
        propertyAddress.put("city", "Chicago");
        propertyAddress.put("state", "IL");
        propertyAddress.put("zipCode", "60007");
        propertyAddress.put("zipCodePlusFour", "1000");
        
        name.put("firstName", "Venkateshwarlu");
        name.put("middleName", "M");
        name.put("lastName", "Velisoju");
        name.put("suffixName", "Mr.");
        
        currentAddress.put("streetAddressLine1", "100 Main St");
        currentAddress.put("streetAddressLine2", "Apt 10");
        currentAddress.put("city", "Chicago");
        currentAddress.put("state", "IL");
        currentAddress.put("zipCode", "60007");
        currentAddress.put("zipCodePlusFour", "1000");
        
        borrower.put("name", name);
        borrower.put("email", "velisoju.mouny@gmail.com");
        borrower.put("SSN", "000113933");
        borrower.put("dateOfBirth", 19081992);
        borrower.put("homePhone", "7287820821");
        borrower.put("currentAddress", currentAddress);
        
        entry.put("propertyAddress", propertyAddress);
        entry.put("borrower", borrower);
        
        return entry;
}
	
	public static Map<String, Object> createLoanDocumentsData() {
	 	Map<String, Object> entry = new HashMap<>();
	 	
	 	List<Map<String, Object>> documentsList = new ArrayList<>();
	    Map<String, Object> document = new HashMap<>();  
	   
        List<String> borrowerList=new ArrayList<>();
        
        
        document.put("id", "028976fd-c81e-4cd6-928b-6b6f079387d8");
        document.put("type", "GENERATED_ASSET_STATEMENT");
        document.put("loanId", "002db918-4f50-49a5-b98b-d2e94a9158dd");
        document.put("name", "Borrower - Generated Asset Statement - Bank of America - 1111 - January 2019");
        document.put("downloadUrl", "https://blend-borrower-apisero.beta.blendlabs.com/v1/documents/028976fd-c81e-4cd6-928b-6b6f079387d8");
        document.put("created", "1546908953093");
        
        borrowerList.add("d87fc814-b73b-4736-a0e2-2a468d1c8645");
        document.put("borrowerIds", borrowerList);
        document.put("losExportedAt", "1553765992984");
        
        documentsList.add(document);
        entry.put("documents", documentsList);
       
        return entry;
}
	
	 static Map<String, Object> createPostExportStatusesData() {
	        Map<String, Object> postExportStatusesData = new HashMap<>();
	        postExportStatusesData.put("status", "ABLE_TO_EXPORT");
	        postExportStatusesData.put("reason", "import failed on los");
	        return postExportStatusesData;
	    }

	static Map<String, Object> getLoanData() {
		Map<String, Object> loanData = new HashMap<>();
		loanData.put("loanId", "964ff961-8ff8-4c43-8b56-f67672e561d8");
		loanData.put("format", "mismo");
		loanData.put("version", "3.3.1");
		loanData.put("borrowerId", BORROWER_ID_VALUE);
		return loanData;
	}

	 static Map<String, Object> createBorrowerData() {
	        Map<String, Object> postBorrowerData = new HashMap<>();
	        Map<String, Object> name = new HashMap<>();
	        Map<String, Object> currentAddress = new HashMap<>();
	        name.put("firstName", "Venkatesh");
	        name.put("middleName", "M");
	        name.put("lastName", "Velisoju");
	        name.put("suffixName", "Mr.");

	        postBorrowerData.put("email", "Venky@gmail.com");
	        postBorrowerData.put("type", "COBORROWER");
	        postBorrowerData.put("SSN", "000113935");
	        postBorrowerData.put("dateOfBirth", 0);
	        postBorrowerData.put("homePhone", "1112223333");
	        postBorrowerData.put("name", name);

	        currentAddress.put("streetAddressLine1", "100 Main St");
	        currentAddress.put("streetAddressLine2", "Apt 10");
	        currentAddress.put("city", "Chicago");
	        currentAddress.put("state", "IL");
	        currentAddress.put("zipCode", "60007");
	        currentAddress.put("zipCodePlusFour", "1000");

	        postBorrowerData.put("currentAddress", currentAddress);
	        postBorrowerData.put("fileNumber", 2);
	        postBorrowerData.put("filePosition", 1);
	        return postBorrowerData;
	    }
	 static Map<String, Object> createPatchBorrowerData() {
	        Map<String, Object> postBorrowerData = new HashMap<>();
	        Map<String, Object> name = new HashMap<>();
	        Map<String, Object> currentAddress = new HashMap<>();
	        name.put("firstName", "Venkatesh");
	        name.put("middleName", "M");
	        name.put("lastName", "Velisoju");
	        name.put("suffixName", "Mr.");
	        postBorrowerData.put("name", name);
	        postBorrowerData.put("email", "Venkat@gmail.com");
	        postBorrowerData.put("type", "COBORROWER");
	        postBorrowerData.put("SSN", "000113935");
	        postBorrowerData.put("dateOfBirth", 0);
	        postBorrowerData.put("homePhone", "1112223333");

	        currentAddress.put("streetAddressLine1", "100 Main St");
	        currentAddress.put("streetAddressLine2", "Apt 10");
	        currentAddress.put("city", "Chicago");
	        currentAddress.put("state", "IL");
	        currentAddress.put("zipCode", "60007");
	        currentAddress.put("zipCodePlusFour", "1000");

	        postBorrowerData.put("currentAddress", currentAddress);

	        return postBorrowerData;
	    }

	 static Map<String, Object> createLoanApplicationData() {
	        Map<String, Object> loanApplicationData = new HashMap<>();
	        List<Map<String, Object>> borrowerList=new ArrayList<>();
	        Map<String, Object> borrowerPairs = new HashMap<>();

	        borrowerPairs.put("primaryBorrowerId", "1a65502b-03dc-46d3-9261-eeee26549a11");
	        borrowerPairs.put("secondaryBorrowerId", "d3b7ea10-fcad-46c4-afe0-4be99f8623e4");
	        borrowerList.add(borrowerPairs);
	        loanApplicationData.put("borrowerPairs", borrowerList);
	        return loanApplicationData;
	    }
	 
	 static Map<String, Object> createPostLosMilstonesData(String loanId) {
	        Map<String, Object> postLosMilstonesData = new HashMap<>();
	        Map<String, Object> entry = new HashMap<>();
	        List<Map<String, Object>> postLosMilstonesList= new ArrayList<>();
	        postLosMilstonesData.put("losMilestone", "UNDERWRITING");
	        postLosMilstonesData.put("loanId", loanId);
	        postLosMilstonesList.add(postLosMilstonesData);
	        entry.put("losMilestones", postLosMilstonesList);
	        return entry;
	    }
	 
	 static Map<String, Object> createPostRealtorData() {
		 Random random = new Random(System.nanoTime());
		 int number = random.nextInt(1000000000);
		String email = new StringBuilder("manalinirmale").append(number).append("@gmail.com").toString();
		 Map<String, Object> postRealtorData = new HashMap<>();
	        Map<String, Object> name = new HashMap<>();
	        Map<String, Object> contact = new HashMap<>();
	        
	        name.put("firstName", "Venkatesh");
	        name.put("middleName", "M");
	        name.put("lastName", "Velisoju");
	        name.put("suffixName", "Mr.");
	        
	        contact.put("email", email);
	        contact.put("phoneNumber", "7287820821");
	        
	        postRealtorData.put("loanId", LOAN_ID_VALUE);
	        postRealtorData.put("name",name);
	        postRealtorData.put("contact", contact);	
	        
	        return postRealtorData;
	    }
	 
	 static Map<String, Object> createPostDisclosuresPackagesData() {
		    Map<String, Object> postDisclosuresPackagesData = new HashMap<>();
	        Map<String, Object> documents = new HashMap<>();
	        List<Map<String, Object>> documentList= new ArrayList<>();
	        Map<String, Object> borrower = new HashMap<>();
	        List<Map<String, Object>> borrowerList= new ArrayList<>();
	        Map<String, Object> recipients = new HashMap<>();
	        
	        borrower.put("id", "d87fc814-b73b-4736-a0e2-2a468d1c8645");
	        borrowerList.add(borrower);
	        
	        documents.put("id", "028976fd-c81e-4cd6-928b-6b6f079387d8");
	        documents.put("borrowers", borrowerList);
	        documentList.add(documents);
	        
	        recipients.put("borrowers", borrowerList);
	        
	        postDisclosuresPackagesData.put("loanId", LOAN_ID_VALUE);
	        postDisclosuresPackagesData.put("losId","test123");
	        postDisclosuresPackagesData.put("type","initial-disclosures");
	        postDisclosuresPackagesData.put("description","This is your initial loan estimate");
	        
	        postDisclosuresPackagesData.put("documents", documentList);
	        postDisclosuresPackagesData.put("recipients", recipients);
	        
	        return postDisclosuresPackagesData;
	    }
	 
	 static Map<String, Object> createPatchDisclosuresPackagesData() {
		    Map<String, Object> postDisclosuresPackagesData = new HashMap<>();
	        Map<String, Object> recipients = new HashMap<>();
	        
	      
	        recipients.put("id", "d87fc814-b73b-4736-a0e2-2a468d1c8645");
	        recipients.put("timestamp", "2019-01-30T19:31:38.902Z");
	        postDisclosuresPackagesData.put("recipient",recipients);
	        postDisclosuresPackagesData.put("losId","test123");
	        postDisclosuresPackagesData.put("status","DECLINED");
	        
	        return postDisclosuresPackagesData;
	    }

	static Map<String, Object> putApplicationLoanData() {
		Map<String, Object> putApplicationLoanData = new HashMap<>();
		putApplicationLoanData.put("loanId", "aacd3f54-054f-47c1-b1ef-74089a10a3f2");
		putApplicationLoanData.put("format", "mismo");
		putApplicationLoanData.put("version", "3.3.1");
		putApplicationLoanData.put("borrowerId", "ac058a3a-974f-4638-bef2-b2ca7286a9e9");
		return putApplicationLoanData;
	}

	static Map<String, Object> putLoanApplicationData() {
		Map<String, Object> loanApplicationData = new HashMap<>();
		List<Map<String, Object>> borrowerList=new ArrayList<>();
		Map<String, Object> borrowerPairs = new HashMap<>();

		borrowerPairs.put("primaryBorrowerId", "ac058a3a-974f-4638-bef2-b2ca7286a9e9");
		borrowerList.add(borrowerPairs);
		loanApplicationData.put("borrowerPairs", borrowerList);
		return loanApplicationData;
	}

	static Map<String, Object> getLoanBorrowerData() {
		Map<String, Object> loanData = new HashMap<>();
		loanData.put("loanId", "964ff961-8ff8-4c43-8b56-f67672e561d8");
		loanData.put("format", "mismo");
		loanData.put("version", "3.3.1");
		loanData.put("borrowerId", "a5b08942-6ebd-4514-9600-733497ee1f9b");
		return loanData;
	}

	static Map<String, Object> createLoanBorrowerData() {
		Map<String, Object> postBorrowerData = new HashMap<>();
		Map<String, Object> name = new HashMap<>();
		Map<String, Object> currentAddress = new HashMap<>();
		name.put("firstName", "Venkatesh");
		name.put("middleName", "M");
		name.put("lastName", "Velisoju");
		name.put("suffixName", "Mr.");

		postBorrowerData.put("email", "velisoju.mouny@gmail.com");
		postBorrowerData.put("type", "BORROWER");
		postBorrowerData.put("SSN", "000113935");
		postBorrowerData.put("dateOfBirth", 0);
		postBorrowerData.put("homePhone", "1112223333");
		postBorrowerData.put("name", name);

		currentAddress.put("streetAddressLine1", "100 Main St");
		currentAddress.put("streetAddressLine2", "Apt 10");
		currentAddress.put("city", "Chicago");
		currentAddress.put("state", "IL");
		currentAddress.put("zipCode", "60007");
		currentAddress.put("zipCodePlusFour", "1000");

		postBorrowerData.put("currentAddress", currentAddress);
		postBorrowerData.put("fileNumber", 2);
		postBorrowerData.put("filePosition", 1);
		return postBorrowerData;
	}
}
