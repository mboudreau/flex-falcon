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

package org.apache.flex.compiler.tree.mxml;

import org.apache.flex.compiler.definitions.IVariableDefinition;

/**
 * This AST node represents an MXML tag that creates an instance of an
 * ActionScript class.
 */
public interface IMXMLInstanceNode extends IMXMLClassReferenceNode
{
    /**
     * The compile-time identifier specified for this instance.
     * 
     * @return The id as a String, or <code>null</code> if no compile-time
     * identifier was specified.
     */
    String getID();

    /**
     * The compile-time identifier used by the compiler for this instance. If no
     * <code>id</code> is specified, then the compiler generates an identifier
     * if one is needed.
     * 
     * @return The id as a String, or <code>null</code> if the instance does not
     * have an identifier.
     */
    String getEffectiveID();

    /**
     * Returns the variable definition that is generated by the id.
     * 
     * @return An {@link IVariableDefinition} object representing the variable
     * definition.
     */
    IVariableDefinition resolveID();

    /**
     * The states (or state groups) in which this instance is included.
     * 
     * @return An array of Strings which are the names of states or state
     * groups.
     */
    String[] getIncludeIn();

    /**
     * The states (or state groups) from which this instance is excluded.
     * 
     * @return An array of Strings which are the names of states or state
     * groups.
     */
    String[] getExcludeFrom();

    /**
     * The item creation policy for this instance.
     * 
     * @return Either <code>"immediate"</code> or <code>"deferred"</code>.
     */
    String getItemCreationPolicy();

    /**
     * The item destruction policy for this instance.
     * 
     * @return Either <code>"auto"</code> or <code>"never"</code>.
     */
    String getItemDestructionPolicy();
}
