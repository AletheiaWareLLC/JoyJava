/*
 * Copyright 2020 Aletheia Ware LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.aletheiaware.joy.scene;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.List;

import com.aletheiaware.joy.JoyProto.Mesh;

public class VertexNormalTextureMesh {

    public final int vertexCount;
    public final int vertexBufferSize;
    public final int normalBufferSize;
    public final int textureBufferSize;
    public final FloatBuffer vertexBuffer;
    public final FloatBuffer normalBuffer;
    public final FloatBuffer textureBuffer;

    public VertexNormalTextureMesh(int vertices, FloatBuffer vb, FloatBuffer nb, FloatBuffer tb) {
        this.vertexCount = vertices;
        System.out.println("VertexCount: " + vertexCount);
        this.vertexBufferSize = vertices * 3 * 4;// *3:xyz, *4:sizeof(float)
        System.out.println("VertexBufferSize: " + vertexBufferSize);
        this.normalBufferSize = vertices * 3 * 4;// *3:xyz, *4:sizeof(float)
        System.out.println("NormalBufferSize: " + normalBufferSize);
        this.textureBufferSize = vertices * 2 * 4;// *3:uv, *4:sizeof(float)
        System.out.println("TextureBufferSize: " + textureBufferSize);
        this.vertexBuffer = vb;
        this.normalBuffer = nb;
        this.textureBuffer = tb;
    }

    public VertexNormalTextureMesh(Mesh mesh) throws IOException {
        this.vertexCount = mesh.getVertices();
        System.out.println("Vertices: " + vertexCount);
        this.vertexBufferSize = vertexCount * 3 * 4;// *3:xyz, *4:sizeof(float)
        this.normalBufferSize = vertexCount * 3 * 4;// *3:xyz, *4:sizeof(float)
        this.textureBufferSize = vertexCount * 2 * 4;// *2:uv, *4:sizeof(float)
        System.out.println("VertexBufferSize: " + vertexBufferSize);
        System.out.println("NormalBufferSize: " + normalBufferSize);
        System.out.println("TextureBufferSize: " + textureBufferSize);
        final ByteBuffer vbb = ByteBuffer.allocateDirect(vertexBufferSize);
        final ByteBuffer nbb = ByteBuffer.allocateDirect(normalBufferSize);
        final ByteBuffer tbb = ByteBuffer.allocateDirect(textureBufferSize);
        vbb.order(ByteOrder.nativeOrder());
        nbb.order(ByteOrder.nativeOrder());
        tbb.order(ByteOrder.nativeOrder());
        this.vertexBuffer = vbb.asFloatBuffer();
        this.normalBuffer = nbb.asFloatBuffer();
        this.textureBuffer = tbb.asFloatBuffer();
        List<Double> vs = mesh.getVertexList();
        System.out.println("Vertices: " + vs.size());
        for (Double v : vs) {
            vertexBuffer.put(v.floatValue());
        }
        List<Double> ns = mesh.getNormalList();
        System.out.println("Normals: " + ns.size());
        for (Double n : ns) {
            normalBuffer.put(n.floatValue());
        }
        List<Double> ts = mesh.getTexCoordList();
        System.out.println("Textures: " + ts.size());
        for (Double t : ts) {
            textureBuffer.put(t.floatValue());
        }
    }

    public VertexNormalTextureMesh(byte[] data) throws IOException {
        this(new ByteArrayInputStream(data));
    }

    public VertexNormalTextureMesh(InputStream in) throws IOException {
        this(new DataInputStream(in));
    }

    public VertexNormalTextureMesh(DataInputStream in) throws IOException {
        this.vertexCount = in.readInt();
        this.vertexBufferSize = vertexCount * 3 * 4;// *3:xyz, *4:sizeof(float)
        this.normalBufferSize = vertexCount * 3 * 4;// *3:xyz, *4:sizeof(float)
        this.textureBufferSize = vertexCount * 2 * 4;// *2:uv, *4:sizeof(float)
        final ByteBuffer vbb = ByteBuffer.allocateDirect(vertexBufferSize);
        final ByteBuffer nbb = ByteBuffer.allocateDirect(normalBufferSize);
        final ByteBuffer tbb = ByteBuffer.allocateDirect(textureBufferSize);
        vbb.order(ByteOrder.nativeOrder());
        nbb.order(ByteOrder.nativeOrder());
        tbb.order(ByteOrder.nativeOrder());
        this.vertexBuffer = vbb.asFloatBuffer();
        this.normalBuffer = nbb.asFloatBuffer();
        this.textureBuffer = tbb.asFloatBuffer();
        for (int i = 0; i < vertexCount; i++) {
            // Vertex
            vertexBuffer.put(in.readFloat());
            vertexBuffer.put(in.readFloat());
            vertexBuffer.put(in.readFloat());
            // Normal
            normalBuffer.put(in.readFloat());
            normalBuffer.put(in.readFloat());
            normalBuffer.put(in.readFloat());
            // Texture
            textureBuffer.put(in.readFloat());
            textureBuffer.put(in.readFloat());
        }
    }

}
