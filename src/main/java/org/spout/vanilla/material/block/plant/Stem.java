/*
 * This file is part of Vanilla.
 *
 * Copyright (c) 2011-2012, SpoutDev <http://www.spout.org/>
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
package org.spout.vanilla.material.block.plant;

import org.spout.api.entity.Entity;
import org.spout.api.event.player.PlayerInteractEvent;
import org.spout.api.geo.cuboid.Block;
import org.spout.api.inventory.ItemStack;
import org.spout.api.material.BlockMaterial;
import org.spout.api.material.block.BlockFace;

import org.spout.vanilla.material.VanillaMaterials;
import org.spout.vanilla.material.block.attachable.GroundAttachable;
import org.spout.vanilla.material.item.misc.Dye;
import org.spout.vanilla.util.VanillaPlayerUtil;

public class Stem extends GroundAttachable implements Plant {
	public Stem(String name, int id) {
		super(name, id);
	}

	@Override
	public void initialize() {
		super.initialize();
		this.setResistance(0.0F).setHardness(0.0F).setOpacity((byte) 1);
	}

	@Override
	public boolean hasGrowthStages() {
		return true;
	}

	@Override
	public int getNumGrowthStages() {
		return 8;
	}

	@Override
	public int getMinimumLightToGrow() {
		return 9;  //TODO Verify this.
	}

	@Override
	public boolean canAttachTo(BlockMaterial material, BlockFace face) {
		return face == BlockFace.TOP && material.equals(VanillaMaterials.FARMLAND);
	}

	@Override
	public void onInteractBy(Entity entity, Block block, PlayerInteractEvent.Action type, BlockFace clickedFace) {
		super.onInteractBy(entity, block, type, clickedFace);
		ItemStack current = entity.getInventory().getCurrentItem();
		if (current != null && current.getSubMaterial().equals(Dye.BONE_MEAL)) {
			if (this.getGrowthStage(block) != 0x7) {
				if (!VanillaPlayerUtil.isCreative(entity)) {
					entity.getInventory().addCurrentItemAmount(-1);
				}
				this.setGrowthStage(block, 0x7);
			}
		}
	}

	public int getGrowthStage(Block block) {
		return block.getData();
	}

	public void setGrowthStage(Block block, int stage) {
		block.setData(stage & 0x7);
	}

	public boolean isFullyGrown(Block block) {
		return block.getData() == 0x7;
	}
}