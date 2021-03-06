/*
 *
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package org.apache.flex.compiler.internal.tree.mxml;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.apache.flex.compiler.tree.ASTNodeID;
import org.apache.flex.compiler.tree.mxml.IMXMLFileNode;
import org.apache.flex.compiler.tree.mxml.IMXMLStateNode;
import org.junit.Test;

/**
 * JUnit tests for {@link MXMLStateNode}.
 * 
 * @author Gordon Smith
 */
public class MXMLStateNodeTests extends MXMLInstanceNodeTests
{
	@Override
	protected  String getPrefix()
	{
		return "";
	}
	
	@Override
	protected String getPostfix()
	{
		return "";
	}

	private IMXMLStateNode getMXMLStateNode(String code)
	{
		IMXMLFileNode fileNode = getMXMLFileNode(code);
		IMXMLStateNode node = (IMXMLStateNode)findFirstDescendantOfType(fileNode, IMXMLStateNode.class);
		assertThat("getNodeID", node.getNodeID(), is(ASTNodeID.MXMLStateID));
		assertThat("getName", node.getName(), is("mx.states.State"));
		return node;
	}
	
	@Test
	public void MXMLStateNode_oneState()
	{
		String code =
				"<s:Application xmlns:fx='http://ns.adobe.com/mxml/2009' xmlns:s='library://ns.adobe.com/flex/spark'>\n" +
			    "    <s:states>\n" +
				"        <s:State name='s1' stateGroups='g1, g2' enterState='trace()' exitState='trace()'/>\n" +
			    "    </s:states>\n" +
				"</s:Application>";
		IMXMLStateNode node = getMXMLStateNode(code);
		assertThat("getStateName", node.getStateName(), is("s1"));
		String[] groups = node.getStateGroups();
		assertThat("getStateGroups", groups.length, is(2));
		assertThat("group 0", groups[0], is("g1"));
		assertThat("group 1", groups[1], is("g2"));
	}
}
