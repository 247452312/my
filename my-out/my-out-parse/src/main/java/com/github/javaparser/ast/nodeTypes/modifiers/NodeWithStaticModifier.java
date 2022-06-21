/*
 * Copyright (C) 2007-2010 Júlio Vilmar Gesser.
 * Copyright (C) 2011, 2013-2021 The JavaParser Team.
 *
 * This file is part of JavaParser.
 *
 * JavaParser can be used either under the terms of
 * a) the GNU Lesser General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 * b) the terms of the Apache License
 *
 * You should have received a copy of both licenses in LICENCE.LGPL and
 * LICENCE.APACHE. Please refer to those files for details.
 *
 * JavaParser is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 */

package com.github.javaparser.ast.nodeTypes.modifiers;

import static com.github.javaparser.ast.Modifier.Keyword.STATIC;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.nodeTypes.NodeWithModifiers;

/**
 * A node that can be static.
 */
public interface NodeWithStaticModifier<N extends Node> extends NodeWithModifiers<N> {

    /**
     * @return true, if the modifier {@code static} is explicitly added to this node. If the node is implicitly static
     * without an explicit modifier (e.g. nested records), this method should be overridden.
     */
    default boolean isStatic() {
        return hasModifier(STATIC);
    }

    @SuppressWarnings("unchecked")
    default N setStatic(boolean set) {
        return setModifier(STATIC, set);
    }

}
