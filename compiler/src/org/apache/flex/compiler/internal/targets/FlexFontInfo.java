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

package org.apache.flex.compiler.internal.targets;

import org.apache.flex.abc.ABCConstants;

/**
 * Utility class to store font information obtained from the font transcoder
 */
public final class FlexFontInfo
{
    public FlexFontInfo(boolean bold, boolean italic)
    {
        if (!bold && !italic)
            regularOp = ABCConstants.OP_pushtrue;
        else
            regularOp = ABCConstants.OP_pushfalse;
            
        if (bold)
            boldOp = ABCConstants.OP_pushtrue;
        else
            boldOp = ABCConstants.OP_pushfalse;
    
        if (italic)
            italicOp = ABCConstants.OP_pushtrue;
        else
            italicOp = ABCConstants.OP_pushfalse;

        if (bold && italic)
            boldItalicOp = ABCConstants.OP_pushtrue;
        else
            boldItalicOp = ABCConstants.OP_pushfalse;
    }

    int regularOp;
    int boldOp;
    int italicOp;
    int boldItalicOp;
}