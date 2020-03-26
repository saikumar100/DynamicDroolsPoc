/**
 * Copyright 2018 Gabriel Stelmach
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to 
 * permit persons to whom the Software is furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the 
 * Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE 
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR 
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR 
 * OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 * 
 * Licensed under the MIT license: https://opensource.org/licenses/MIT
 */
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