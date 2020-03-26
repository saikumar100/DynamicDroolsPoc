package com.test.drools.poc.sai;

import com.test.drools.poc.sai.model.Customer;
import com.test.drools.poc.sai.model.Offer;
import org.kie.api.io.ResourceType;
import org.kie.internal.KnowledgeBase;
import org.kie.internal.KnowledgeBaseFactory;
import org.kie.internal.builder.DecisionTableConfiguration;
import org.kie.internal.builder.DecisionTableInputType;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.io.ResourceFactory;
import org.kie.internal.runtime.StatelessKnowledgeSession;

/**
 * Implementation showing how to use drools to create rules dynamically from excel service and executing them.
 * 
 * @author Sai Kumar.
 */
public class ShowMeTheDrools
{
	/**
		Main Method
	 	@throws  Exception
	*/
	public static void main(String args[]) throws Exception
	{
		createDroolsSessionAndExecuteRules();
	}

	/**
	 * Executing rules on sample {@link Customer} & {@link Offer } objects
	 * @throws Exception
	 */
	public static void createDroolsSessionAndExecuteRules() throws Exception {
		KnowledgeBase knowledgeBase = createKnowledgeBaseFromSpreadsheet();
		StatelessKnowledgeSession session = knowledgeBase.newStatelessKnowledgeSession();
		Customer customer = new Customer();
		customer.setLifeStage(Customer.CustomerLifeStage.CAREERFOCUSED);
		customer.setAssets(Customer.CustomerAssets.FROM150KTO300K);
		customer.addNeed(Customer.CustomerNeed.LIFEINSURANCE);
		customer.addNeed(Customer.CustomerNeed.SAVINGACCOUNT);
		customer.addNeed(Customer.CustomerNeed.MORTAGE);
		Offer offer = new Offer();
		session.setGlobal("offer", offer);
		session.execute(customer);
		System.out.println(offer.toString());
	}

	/**
	 * Creates a knowledge Base from excel service as configured in changeSet.xml
	 * @return knowledgeBase
	 * @throws Exception
	 */
	private static KnowledgeBase createKnowledgeBaseFromSpreadsheet() throws Exception {
		DecisionTableConfiguration dtconf = KnowledgeBuilderFactory.newDecisionTableConfiguration();
		dtconf.setInputType(DecisionTableInputType.XLS);
		KnowledgeBuilder knowledgeBuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
		knowledgeBuilder.add( ResourceFactory.newClassPathResource( "changeSet.xml", ShowMeTheDrools.class),

				ResourceType.CHANGE_SET );
		if (knowledgeBuilder.hasErrors()) {
			throw new RuntimeException(knowledgeBuilder.getErrors().toString());
		}
		KnowledgeBase knowledgeBase = KnowledgeBaseFactory.newKnowledgeBase();
		knowledgeBase.addKnowledgePackages(knowledgeBuilder.getKnowledgePackages());
		return knowledgeBase;
	}
}
