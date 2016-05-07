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

package com.github.liachmodded.liachlitemod.mixin.client.render;

import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.chunk.RenderChunk;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Collections;
import java.util.Set;

/**
 * Created by liach on 5/3/2016.
 *
 * @author liach
 */
@Mixin(value = RenderGlobal.class)
public abstract class MixinRenderGlobal {

    @Shadow
    private Set<RenderChunk> chunksToUpdate;
    @Shadow
    private @Final Set<BlockPos> setLightUpdates;

    @Inject(method = "<init>", at = @At("RETURN"))
    private void onCreation(CallbackInfo ci) {
        this.chunksToUpdate = Collections.synchronizedSet(this.chunksToUpdate);
        this.setLightUpdates = Collections.synchronizedSet(this.setLightUpdates);
    }

}
