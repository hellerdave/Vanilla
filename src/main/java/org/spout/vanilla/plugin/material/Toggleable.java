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
package org.spout.vanilla.plugin.material;

import org.spout.api.geo.cuboid.Block;

public interface Toggleable {
	/**
	 * Gets if the Material is toggled on
	 * @param block of this Material
	 * @return True if toggled, False if not
	 */
	public boolean isToggled(Block block);

	/**
	 * Sets if the Material is toggled on
	 * @param block of this Material
	 * @param toggled state to set to
	 */
	public void setToggled(Block block, boolean toggled);

	/**
	 * Toggles the Material between on and off states
	 * @param block to toggle
	 * @return the new On state
	 */
	public boolean toggle(Block block);
}
