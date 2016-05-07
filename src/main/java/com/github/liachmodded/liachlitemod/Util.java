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

import static com.mumfrey.liteloader.gl.GL.GL_BLEND;
import static com.mumfrey.liteloader.gl.GL.GL_DEPTH_TEST;
import static com.mumfrey.liteloader.gl.GL.GL_LINES;
import static com.mumfrey.liteloader.gl.GL.GL_LINE_STRIP;
import static com.mumfrey.liteloader.gl.GL.GL_QUADS;
import static net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher.staticPlayerX;
import static net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher.staticPlayerY;
import static net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher.staticPlayerZ;

import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.AxisAlignedBB;
import org.lwjgl.opengl.GL11;

import java.awt.Color;

/**
 * Created by liach on 5/4/2016.
 *
 * @author liach
 */
public final class Util {

    private static final VertexFormat DEFAULT = DefaultVertexFormats.POSITION_TEX;

    private Util() {
    }

    public static void drawESPForEntity(Entity entity, Color color) {
        AxisAlignedBB aabb = new AxisAlignedBB(
                entity.getEntityBoundingBox().minX - 0.05 - entity.posX
                        + (entity.posX - staticPlayerX),
                entity.getEntityBoundingBox().minY - entity.posY
                        + (entity.posY - staticPlayerY),
                entity.getEntityBoundingBox().minZ - 0.05 - entity.posZ
                        + (entity.posZ - staticPlayerZ),
                entity.getEntityBoundingBox().maxX + 0.05 - entity.posX
                        + (entity.posX - staticPlayerX),
                entity.getEntityBoundingBox().maxY + 0.1 - entity.posY
                        + (entity.posY - staticPlayerY),
                entity.getEntityBoundingBox().maxZ + 0.05 - entity.posZ
                        + (entity.posZ - staticPlayerZ));
        drawESP(aabb, color);
    }

    public static void drawESP(final AxisAlignedBB bb, final Color color) {
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(GL_BLEND);
        GL11.glLineWidth(1.5F);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL_DEPTH_TEST);
        GL11.glDepthMask(false);
        RenderGlobal.drawOutlinedBoundingBox(bb, color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
        //drawBoundingBox(bb);
        //drawOutlinedBoundingBox(bb);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL_DEPTH_TEST);
        GL11.glDepthMask(true);
        GL11.glDisable(GL_BLEND);
    }

    /**
     * Draw the quad around the bounding box.
     *
     * @param axisalignedbb The bounding box
     */
    public static void drawBoundingBox(final AxisAlignedBB axisalignedbb) {
        final Tessellator tessellator = Tessellator.getInstance();
        final VertexBuffer buffer = tessellator.getBuffer();

        buffer.begin(GL_QUADS, DEFAULT); // starts x
        buffer.pos(axisalignedbb.minX - staticPlayerX, axisalignedbb.minY
                - staticPlayerY, axisalignedbb.minZ - staticPlayerZ);
        buffer.pos(axisalignedbb.minX - staticPlayerX, axisalignedbb.maxY
                - staticPlayerY, axisalignedbb.minZ - staticPlayerZ);
        buffer.pos(axisalignedbb.maxX - staticPlayerX, axisalignedbb.minY
                - staticPlayerY, axisalignedbb.minZ - staticPlayerZ);
        buffer.pos(axisalignedbb.maxX - staticPlayerX, axisalignedbb.maxY
                - staticPlayerY, axisalignedbb.minZ - staticPlayerZ);
        buffer.pos(axisalignedbb.maxX - staticPlayerX, axisalignedbb.minY
                - staticPlayerY, axisalignedbb.maxZ - staticPlayerZ);
        buffer.pos(axisalignedbb.maxX - staticPlayerX, axisalignedbb.maxY
                - staticPlayerY, axisalignedbb.maxZ - staticPlayerZ);
        buffer.pos(axisalignedbb.minX - staticPlayerX, axisalignedbb.minY
                - staticPlayerY, axisalignedbb.maxZ - staticPlayerZ);
        buffer.pos(axisalignedbb.minX - staticPlayerX, axisalignedbb.maxY
                - staticPlayerY, axisalignedbb.maxZ - staticPlayerZ);
        buffer.finishDrawing();
        buffer.begin(GL_QUADS, DEFAULT);
        buffer.pos(axisalignedbb.maxX - staticPlayerX, axisalignedbb.maxY
                - staticPlayerY, axisalignedbb.minZ - staticPlayerZ);
        buffer.pos(axisalignedbb.maxX - staticPlayerX, axisalignedbb.minY
                - staticPlayerY, axisalignedbb.minZ - staticPlayerZ);
        buffer.pos(axisalignedbb.minX - staticPlayerX, axisalignedbb.maxY
                - staticPlayerY, axisalignedbb.minZ - staticPlayerZ);
        buffer.pos(axisalignedbb.minX - staticPlayerX, axisalignedbb.minY
                - staticPlayerY, axisalignedbb.minZ - staticPlayerZ);
        buffer.pos(axisalignedbb.minX - staticPlayerX, axisalignedbb.maxY
                - staticPlayerY, axisalignedbb.maxZ - staticPlayerZ);
        buffer.pos(axisalignedbb.minX - staticPlayerX, axisalignedbb.minY
                - staticPlayerY, axisalignedbb.maxZ - staticPlayerZ);
        buffer.pos(axisalignedbb.maxX - staticPlayerX, axisalignedbb.maxY
                - staticPlayerY, axisalignedbb.maxZ - staticPlayerZ);
        buffer.pos(axisalignedbb.maxX - staticPlayerX, axisalignedbb.minY
                - staticPlayerY, axisalignedbb.maxZ - staticPlayerZ);
        buffer.finishDrawing(); // ends x
        buffer.begin(GL_QUADS, DEFAULT);// starts y
        buffer.pos(axisalignedbb.minX - staticPlayerX, axisalignedbb.maxY
                - staticPlayerY, axisalignedbb.minZ - staticPlayerZ);
        buffer.pos(axisalignedbb.maxX - staticPlayerX, axisalignedbb.maxY
                - staticPlayerY, axisalignedbb.minZ - staticPlayerZ);
        buffer.pos(axisalignedbb.maxX - staticPlayerX, axisalignedbb.maxY
                - staticPlayerY, axisalignedbb.maxZ - staticPlayerZ);
        buffer.pos(axisalignedbb.minX - staticPlayerX, axisalignedbb.maxY
                - staticPlayerY, axisalignedbb.maxZ - staticPlayerZ);
        buffer.pos(axisalignedbb.minX - staticPlayerX, axisalignedbb.maxY
                - staticPlayerY, axisalignedbb.minZ - staticPlayerZ);
        buffer.pos(axisalignedbb.minX - staticPlayerX, axisalignedbb.maxY
                - staticPlayerY, axisalignedbb.maxZ - staticPlayerZ);
        buffer.pos(axisalignedbb.maxX - staticPlayerX, axisalignedbb.maxY
                - staticPlayerY, axisalignedbb.maxZ - staticPlayerZ);
        buffer.pos(axisalignedbb.maxX - staticPlayerX, axisalignedbb.maxY
                - staticPlayerY, axisalignedbb.minZ - staticPlayerZ);
        buffer.finishDrawing();
        buffer.begin(GL_QUADS, DEFAULT);
        buffer.pos(axisalignedbb.minX - staticPlayerX, axisalignedbb.minY
                - staticPlayerY, axisalignedbb.minZ - staticPlayerZ);
        buffer.pos(axisalignedbb.maxX - staticPlayerX, axisalignedbb.minY
                - staticPlayerY, axisalignedbb.minZ - staticPlayerZ);
        buffer.pos(axisalignedbb.maxX - staticPlayerX, axisalignedbb.minY
                - staticPlayerY, axisalignedbb.maxZ - staticPlayerZ);
        buffer.pos(axisalignedbb.minX - staticPlayerX, axisalignedbb.minY
                - staticPlayerY, axisalignedbb.maxZ - staticPlayerZ);
        buffer.pos(axisalignedbb.minX - staticPlayerX, axisalignedbb.minY
                - staticPlayerY, axisalignedbb.minZ - staticPlayerZ);
        buffer.pos(axisalignedbb.minX - staticPlayerX, axisalignedbb.minY
                - staticPlayerY, axisalignedbb.maxZ - staticPlayerZ);
        buffer.pos(axisalignedbb.maxX - staticPlayerX, axisalignedbb.minY
                - staticPlayerY, axisalignedbb.maxZ - staticPlayerZ);
        buffer.pos(axisalignedbb.maxX - staticPlayerX, axisalignedbb.minY
                - staticPlayerY, axisalignedbb.minZ - staticPlayerZ);
        buffer.finishDrawing(); // ends y
        buffer.begin(GL_QUADS, DEFAULT);// starts z
        buffer.pos(axisalignedbb.minX - staticPlayerX, axisalignedbb.minY
                - staticPlayerY, axisalignedbb.minZ - staticPlayerZ);
        buffer.pos(axisalignedbb.minX - staticPlayerX, axisalignedbb.maxY
                - staticPlayerY, axisalignedbb.minZ - staticPlayerZ);
        buffer.pos(axisalignedbb.minX - staticPlayerX, axisalignedbb.minY
                - staticPlayerY, axisalignedbb.maxZ - staticPlayerZ);
        buffer.pos(axisalignedbb.minX - staticPlayerX, axisalignedbb.maxY
                - staticPlayerY, axisalignedbb.maxZ - staticPlayerZ);
        buffer.pos(axisalignedbb.maxX - staticPlayerX, axisalignedbb.minY
                - staticPlayerY, axisalignedbb.maxZ - staticPlayerZ);
        buffer.pos(axisalignedbb.maxX - staticPlayerX, axisalignedbb.maxY
                - staticPlayerY, axisalignedbb.maxZ - staticPlayerZ);
        buffer.pos(axisalignedbb.maxX - staticPlayerX, axisalignedbb.minY
                - staticPlayerY, axisalignedbb.minZ - staticPlayerZ);
        buffer.pos(axisalignedbb.maxX - staticPlayerX, axisalignedbb.maxY
                - staticPlayerY, axisalignedbb.minZ - staticPlayerZ);
        buffer.finishDrawing();
        buffer.begin(GL_QUADS, DEFAULT);
        buffer.pos(axisalignedbb.minX - staticPlayerX, axisalignedbb.maxY
                - staticPlayerY, axisalignedbb.maxZ - staticPlayerZ);
        buffer.pos(axisalignedbb.minX - staticPlayerX, axisalignedbb.minY
                - staticPlayerY, axisalignedbb.maxZ - staticPlayerZ);
        buffer.pos(axisalignedbb.minX - staticPlayerX, axisalignedbb.maxY
                - staticPlayerY, axisalignedbb.minZ - staticPlayerZ);
        buffer.pos(axisalignedbb.minX - staticPlayerX, axisalignedbb.minY
                - staticPlayerY, axisalignedbb.minZ - staticPlayerZ);
        buffer.pos(axisalignedbb.maxX - staticPlayerX, axisalignedbb.maxY
                - staticPlayerY, axisalignedbb.minZ - staticPlayerZ);
        buffer.pos(axisalignedbb.maxX - staticPlayerX, axisalignedbb.minY
                - staticPlayerY, axisalignedbb.minZ - staticPlayerZ);
        buffer.pos(axisalignedbb.maxX - staticPlayerX, axisalignedbb.maxY
                - staticPlayerY, axisalignedbb.maxZ - staticPlayerZ);
        buffer.pos(axisalignedbb.maxX - staticPlayerX, axisalignedbb.minY
                - staticPlayerY, axisalignedbb.maxZ - staticPlayerZ);
        buffer.finishDrawing(); // ends z
    }

    /**
     * Draws lines for the edges of the bounding box.
     *
     * @param axisalignedbb The bounding box
     */
    public static void drawOutlinedBoundingBox(final AxisAlignedBB axisalignedbb) {
        final VertexBuffer var2 = Tessellator.getInstance().getBuffer();
        var2.begin(GL_LINE_STRIP, DEFAULT);
        var2.pos(axisalignedbb.minX - staticPlayerX, axisalignedbb.minY
                - staticPlayerY, axisalignedbb.minZ - staticPlayerZ);
        var2.pos(axisalignedbb.maxX - staticPlayerX, axisalignedbb.minY
                - staticPlayerY, axisalignedbb.minZ - staticPlayerZ);
        var2.pos(axisalignedbb.maxX - staticPlayerX, axisalignedbb.minY
                - staticPlayerY, axisalignedbb.maxZ - staticPlayerZ);
        var2.pos(axisalignedbb.minX - staticPlayerX, axisalignedbb.minY
                - staticPlayerY, axisalignedbb.maxZ - staticPlayerZ);
        var2.pos(axisalignedbb.minX - staticPlayerX, axisalignedbb.minY
                - staticPlayerY, axisalignedbb.minZ - staticPlayerZ);
        var2.finishDrawing();
        var2.begin(GL_LINE_STRIP, DEFAULT);
        var2.pos(axisalignedbb.minX - staticPlayerX, axisalignedbb.maxY
                - staticPlayerY, axisalignedbb.minZ - staticPlayerZ);
        var2.pos(axisalignedbb.maxX - staticPlayerX, axisalignedbb.maxY
                - staticPlayerY, axisalignedbb.minZ - staticPlayerZ);
        var2.pos(axisalignedbb.maxX - staticPlayerX, axisalignedbb.maxY
                - staticPlayerY, axisalignedbb.maxZ - staticPlayerZ);
        var2.pos(axisalignedbb.minX - staticPlayerX, axisalignedbb.maxY
                - staticPlayerY, axisalignedbb.maxZ - staticPlayerZ);
        var2.pos(axisalignedbb.minX - staticPlayerX, axisalignedbb.maxY
                - staticPlayerY, axisalignedbb.minZ - staticPlayerZ);
        var2.finishDrawing();
        var2.begin(GL_LINES, DEFAULT);
        var2.pos(axisalignedbb.minX - staticPlayerX, axisalignedbb.minY
                - staticPlayerY, axisalignedbb.minZ - staticPlayerZ);
        var2.pos(axisalignedbb.minX - staticPlayerX, axisalignedbb.maxY
                - staticPlayerY, axisalignedbb.minZ - staticPlayerZ);
        var2.pos(axisalignedbb.maxX - staticPlayerX, axisalignedbb.minY
                - staticPlayerY, axisalignedbb.minZ - staticPlayerZ);
        var2.pos(axisalignedbb.maxX - staticPlayerX, axisalignedbb.maxY
                - staticPlayerY, axisalignedbb.minZ - staticPlayerZ);
        var2.pos(axisalignedbb.maxX - staticPlayerX, axisalignedbb.minY
                - staticPlayerY, axisalignedbb.maxZ - staticPlayerZ);
        var2.pos(axisalignedbb.maxX - staticPlayerX, axisalignedbb.maxY
                - staticPlayerY, axisalignedbb.maxZ - staticPlayerZ);
        var2.pos(axisalignedbb.minX - staticPlayerX, axisalignedbb.minY
                - staticPlayerY, axisalignedbb.maxZ - staticPlayerZ);
        var2.pos(axisalignedbb.minX - staticPlayerX, axisalignedbb.maxY
                - staticPlayerY, axisalignedbb.maxZ - staticPlayerZ);
        var2.finishDrawing();
    }

}
