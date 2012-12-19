/*
 * This file is part of Vanilla.
 *
 * Copyright (c) 2011-2012, Spout LLC <http://www.spout.org/>
 * Vanilla is licensed under the Spout License Version 1.
 *
 * Vanilla is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option)
 * any later version.
 *
 * In addition, 180 days after any changes are published, you can use the
 * software, incorporating those changes, under the terms of the MIT license,
 * as described in the Spout License Version 1.
 *
 * Vanilla is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License for
 * more details.
 *
 * You should have received a copy of the GNU Lesser General Public License,
 * the MIT license and the Spout License Version 1 along with this program.
 * If not, see <http://www.gnu.org/licenses/> for the GNU Lesser General Public
 * License and see <http://spout.in/licensev1> for the full license, including
 * the MIT license.
 */
package org.spout.vanilla.event.cause;

import org.spout.api.event.Cause;
import org.spout.api.event.cause.BaseCause;
import org.spout.api.exception.MaxCauseChainReached;
import org.spout.api.geo.cuboid.Block;

public class BlockCombustCause extends BaseCause<Block> implements CombustCause<Block> {
	private final Block block;

	/**
	 * Contains the source of combustion.
	 * @param block The block causing the combustion.
	 */
	public BlockCombustCause(Block block) {
		this.block = block;
	}

	/**
	 * Creates a cause with a parent. If the {@link #chainPosition} is larger than {@link org.spout.api.Engine#getCauseChainMaximum()}
	 * a {@link org.spout.api.exception.MaxCauseChainReached} RuntimeException will be thrown and the {@link #parentCause},
	 * {@link #mainCause} and {@link #chainPosition} reseted.
	 * @param block who caused this cause
	 * @param parent cause of this cause
	 */
	public BlockCombustCause(Block block, Cause parent) {
		super(parent);
		this.block = block;
	}

	/**
	 * Checks if the Class of the parent cause is the same class as the new cause
	 * @return true if class of parent cause and new cause are the same
	 */
	@Override
	protected boolean causeOfSameClass() {
		return getParentCause() != null && getParentCause().getClass() == this.getClass();
	}

	/**
	 * Throws the {@link org.spout.api.exception.MaxCauseChainReached} Exception with the point of the cause
	 */
	@Override
	protected void throwException() {
		throw new MaxCauseChainReached(block.getPosition());
	}

	@Override
	public Block getSource() {
		return block;
	}
}