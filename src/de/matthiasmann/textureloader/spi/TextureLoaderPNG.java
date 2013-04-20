/*
 * Copyright (c) 2008-2013, Matthias Mann
 *
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *     * Redistributions of source code must retain the above copyright notice,
 *       this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     * Neither the name of Matthias Mann nor the names of its contributors may
 *       be used to endorse or promote products derived from this software
 *       without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package de.matthiasmann.textureloader.spi;

import de.matthiasmann.textureloader.Texture;
import de.matthiasmann.textureloader.TextureLoader;
import de.matthiasmann.twl.utils.PNGDecoder;
import java.io.IOException;
import java.net.URL;
import java.nio.ByteBuffer;

/**
 *
 * @author Matthias Mann
 */
public class TextureLoaderPNG extends TextureLoader {
    
    private PNGDecoder decoder;

    public TextureLoaderPNG(URL url) {
        super(url);
    }

    @Override
    public boolean open() throws IOException {
        inputStream = url.openStream();
        decoder = new PNGDecoder(inputStream);
        width = decoder.getWidth();
        height = decoder.getHeight();
        format = Texture.Format.BGRA;
        return true;
    }

    @Override
    public void decode(ByteBuffer bb) throws IOException {
        decoder.decode(bb, width*4, PNGDecoder.Format.BGRA);
    }
}