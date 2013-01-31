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
package org.spout.vanilla.plugin.material.item.potion;

import org.spout.api.entity.Entity;
import org.spout.api.event.player.PlayerInteractEvent.Action;
import org.spout.api.geo.cuboid.Block;
import org.spout.api.inventory.ItemStack;

import org.spout.api.util.BlockIterator;
import org.spout.vanilla.api.inventory.Slot;
import org.spout.vanilla.plugin.component.inventory.PlayerInventory;
import org.spout.vanilla.plugin.component.misc.HeadComponent;
import org.spout.vanilla.plugin.material.VanillaMaterials;
import org.spout.vanilla.plugin.material.block.liquid.Water;
import org.spout.vanilla.plugin.material.item.VanillaItemMaterial;
import org.spout.vanilla.plugin.util.PlayerUtil;

public class GlassBottle extends VanillaItemMaterial {
	public GlassBottle(String name, int id) {
		super(name, id, null);
	}
	
	// TODO: handle cauldron
	@Override
	public void onInteract(Entity entity, Action action) {
		if (action == Action.RIGHT_CLICK) {
			HeadComponent head = entity.get(HeadComponent.class);
			if (head == null) {
				return;
			}
			Block block;
			BlockIterator iterator = head.getBlockView();
			while (true) {
				if (!iterator.hasNext()) {
					return;
				}
				block = iterator.next();
				if (block.getMaterial() instanceof Water && VanillaMaterials.WATER.isSource(block)) {
					break;
				}
			}

			Slot slot = PlayerUtil.getHeldSlot(entity);
			if (slot != null) {
				ItemStack fullbottle = new ItemStack(Potion.WATER_BOTTLE, 1);
				entity.get(PlayerInventory.class).getQuickbar().add(fullbottle);
				if (!fullbottle.isEmpty()) {
					entity.get(PlayerInventory.class).getMain().add(fullbottle);
				}
				if (fullbottle.isEmpty() && !PlayerUtil.isCostSuppressed(entity)) {
					slot.addAmount(-1);
				}
			}
		}
	}
}
