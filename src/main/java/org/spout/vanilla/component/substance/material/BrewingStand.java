/*
 * This file is part of Vanilla.
 *
 * Copyright (c) 2011-2012, VanillaDev <http://www.spout.org/>
 * Vanilla is licensed under the SpoutDev License Version 1.
 *
 * Vanilla is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * In addition, 180 days after any changes are published, you can use the
 * software, incorporating those changes, under the terms of the MIT license,
 * as described in the SpoutDev License Version 1.
 *
 * Vanilla is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License,
 * the MIT license and the SpoutDev License Version 1 along with this program.
 * If not, see <http://www.gnu.org/licenses/> for the GNU Lesser General Public
 * License and see <http://www.spout.org/SpoutDevLicenseV1.txt> for the full license,
 * including the MIT license.
 */
package org.spout.vanilla.component.substance.material;

import org.spout.api.entity.Player;
import org.spout.api.material.BlockMaterial;

import org.spout.vanilla.component.inventory.window.block.BrewingStandWindow;
import org.spout.vanilla.inventory.Container;
import org.spout.vanilla.inventory.block.BrewingStandInventory;
import org.spout.vanilla.material.block.controlled.BrewingStandBlock;

public class BrewingStand extends WindowBlockComponent implements Container {
	private final BrewingStandInventory inventory = new BrewingStandInventory();

	@Override
	public BrewingStandBlock getMaterial() {
		return (BrewingStandBlock) super.getMaterial();
	}

	@Override
	public void setMaterial(BlockMaterial material) {
		if (!(material instanceof BrewingStandBlock)) {
			throw new IllegalArgumentException("Material passed in must be an instance of a BrewingStandBlock.");
		}
		super.setMaterial(material);
	}

	@Override
	public BrewingStandInventory getInventory() {
		return inventory;
	}

	@Override
	public void openWindow(Player player) {
		player.add(BrewingStandWindow.class).init(inventory).open();
	}
}