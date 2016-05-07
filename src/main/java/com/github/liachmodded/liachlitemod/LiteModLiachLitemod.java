/*
 * The MIT License (MIT)
 *
 * Copyright (c) liachmodded <https://github.com/liachmodded>
 * Copyright (c) contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.github.liachmodded.liachlitemod;

import com.google.common.collect.Lists;
import com.mumfrey.liteloader.PostRenderListener;
import com.mumfrey.liteloader.Tickable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityFallingBlock;

import java.awt.Color;
import java.io.File;
import java.util.List;

/**
 * Created by liach on 4/23/2016.
 *
 * @author liach
 */
public class LiteModLiachLitemod implements Tickable, PostRenderListener {

    private static final String NAME = "liachlitemod";
    private static final String VERSION = "0.1-SNAPSHOT";

    private final List<Entity> blocks = Lists.newCopyOnWriteArrayList();

    @Override public String getVersion() {
        return VERSION;
    }

    @Override public void init(File configPath) {

    }

    @Override public void upgradeSettings(String version, File configPath, File oldConfigPath) {

    }

    @Override public String getName() {
        return NAME;
    }

    @Override public void onPostRenderEntities(float partialTicks) {

    }

    @Override public void onPostRender(float partialTicks) {
        blocks.stream().forEach(entity -> Util.drawESPForEntity(entity, Color.RED));
    }

    @Override public void onTick(Minecraft minecraft, float partialTicks, boolean inGame, boolean clock) {
        if (inGame && clock) {
            WorldClient world = minecraft.theWorld;
            if (world == null) {
                return;
            }
            blocks.clear();
            world.loadedEntityList.stream().filter(e -> e instanceof EntityFallingBlock).forEach(blocks::add);
        }
    }

}
