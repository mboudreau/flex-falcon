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
import org.apache.flex.compiler.tree.mxml.IMXMLObjectNode;
import org.apache.flex.compiler.tree.mxml.IMXMLFileNode;
import org.apache.flex.compiler.tree.mxml.IMXMLPropertySpecifierNode;
import org.apache.flex.compiler.tree.mxml.IMXMLStringNode;
import org.junit.Ignore;
import org.junit.Test;

/**
 * JUnit tests for {@link MXMLObjectNode}.
 * 
 * @author Gordon Smith
 */
public class MXMLObjectNodeTests extends MXMLInstanceNodeTests
{
	private static String EOL = "\n\t\t";
	
	private IMXMLObjectNode getMXMLObjectNode(String code)
	{
		IMXMLFileNode fileNode = getMXMLFileNode(code);
		IMXMLObjectNode node = (IMXMLObjectNode)findFirstDescendantOfType(fileNode, IMXMLObjectNode.class);
		assertThat("getNodeID", node.getNodeID(), is(ASTNodeID.MXMLObjectID));
		assertThat("getName", node.getName(), is("Object"));
		return node;
	}
	
	@Test
	public void MXMLObjectNode_empty1()
	{
		String code = "<fx:Object/>";
		IMXMLObjectNode node = getMXMLObjectNode(code);
		assertThat("getChildCount", node.getChildCount(), is(0));
	}
	
	@Test
	public void MXMLObjectNode_empty2()
	{
		String code = "<fx:Object></fx:Object>";
		IMXMLObjectNode node = getMXMLObjectNode(code);
		assertThat("getChildCount", node.getChildCount(), is(0));
	}
	
	@Test
	public void MXMLObjectNode_empty3()
	{
		String code = "<fx:Object> \t\r\n</fx:Object>";
		IMXMLObjectNode node = getMXMLObjectNode(code);
		assertThat("getChildCount", node.getChildCount(), is(0));
	}
		
	@Test
	public void MXMLArrayNode_two_string_properties1()
	{
		String code =
			"<fx:Object>" + EOL +
		    "    <fx:a>" + EOL +
		    "        <fx:String>xxx</fx:String>" + EOL +
		    "    </fx:a>" + EOL +
		    "    <fx:b>" + EOL +
		    "        <fx:String>yyy</fx:String>" + EOL +
		    "    </fx:b>" + EOL +
			"</fx:Object>";
		IMXMLObjectNode node = getMXMLObjectNode(code);
		assertThat("getChildCount", node.getChildCount(), is(2));
		IMXMLPropertySpecifierNode child0 = (IMXMLPropertySpecifierNode)node.getChild(0);
		assertThat("[0]name", child0.getName(), is("a"));
		assertThat("[0]value", ((IMXMLStringNode)child0.getInstanceNode()).getValue(), is("xxx"));
		IMXMLPropertySpecifierNode child1 = (IMXMLPropertySpecifierNode)node.getChild(1);
		assertThat("[1]name", child1.getName(), is("b"));
		assertThat("[1]value", ((IMXMLStringNode)child1.getInstanceNode()).getValue(), is("yyy"));
	}
	
	@Test
	public void MXMLArrayNode_two_string_properties2()
	{
		String code =
			"<fx:Object>" + EOL +
		    "    <fx:a>xxx</fx:a>" + EOL +
		    "    <fx:b>yyy</fx:b>" + EOL +
			"</fx:Object>";
		IMXMLObjectNode node = getMXMLObjectNode(code);
		assertThat("getChildCount", node.getChildCount(), is(2));
		IMXMLPropertySpecifierNode child0 = (IMXMLPropertySpecifierNode)node.getChild(0);
		assertThat("[0]name", child0.getName(), is("a"));
		assertThat("[0]value", ((IMXMLStringNode)child0.getInstanceNode()).getValue(), is("xxx"));
		IMXMLPropertySpecifierNode child1 = (IMXMLPropertySpecifierNode)node.getChild(1);
		assertThat("[1]name", child1.getName(), is("b"));
		assertThat("[1]value", ((IMXMLStringNode)child1.getInstanceNode()).getValue(), is("yyy"));
	}
	
	@Test
	public void MXMLArrayNode_two_string_properties3()
	{
		String code = "<fx:Object a='xxx' b='yyy'/>";
		IMXMLObjectNode node = getMXMLObjectNode(code);
		assertThat("getChildCount", node.getChildCount(), is(2));
		IMXMLPropertySpecifierNode child0 = (IMXMLPropertySpecifierNode)node.getChild(0);
		assertThat("[0]name", child0.getName(), is("a"));
		assertThat("[0]value", ((IMXMLStringNode)child0.getInstanceNode()).getValue(), is("xxx"));
		IMXMLPropertySpecifierNode child1 = (IMXMLPropertySpecifierNode)node.getChild(1);
		assertThat("[1]name", child1.getName(), is("b"));
		assertThat("[1]value", ((IMXMLStringNode)child1.getInstanceNode()).getValue(), is("yyy"));
	}
	
	@Ignore
	@Test
	public void MXMLObjectNode_with_databinding()
	{
		String code = "<fx:Object>{a.b}</fx:Object>";
		IMXMLObjectNode node = getMXMLObjectNode(code);
		assertThat("databinding node", node.getChild(0).getNodeID(), is(ASTNodeID.MXMLDataBindingID));
		assertThat("databinding node child count", node.getChild(0).getChildCount(), is(1));
		assertThat("identifier node", node.getChild(0).getChild(0).getNodeID(), is(ASTNodeID.MemberAccessExpressionID));
	}
}
