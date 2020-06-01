/*
 * Copyright 2019 Aletheia Ware LLC
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

public class VertexMesh {

    public final int vertexCount;
    public final int vertexBufferSize;
    public final FloatBuffer vertexBuffer;

    public VertexMesh(int vertices, FloatBuffer vb) {
        this.vertexCount = vertices;
        System.out.println("VertexCount: " + vertexCount);
        this.vertexBufferSize = vertices * 3 * 4;// *3:xyz, *4:sizeof(float)
        System.out.println("VertexBufferSize: " + vertexBufferSize);
        this.vertexBuffer = vb;
    }

    public VertexMesh(Mesh mesh) throws IOException {
        this.vertexCount = mesh.getVertices();
        System.out.println("VertexCount: " + vertexCount);
        this.vertexBufferSize = vertexCount * 3 * 4;// *3:xyz, *4:sizeof(float)
        System.out.println("VertexBufferSize: " + vertexBufferSize);
        final ByteBuffer vbb = ByteBuffer.allocateDirect(vertexBufferSize);
        vbb.order(ByteOrder.nativeOrder());
        this.vertexBuffer = vbb.asFloatBuffer();
        List<Double> vs = mesh.getVertexList();
        System.out.println("Vertices: " + vs.size());
        for (Double v : vs) {
            vertexBuffer.put(v.floatValue());
        }
    }

    public VertexMesh(byte[] data) throws IOException {
        this(new ByteArrayInputStream(data));
    }

    public VertexMesh(InputStream in) throws IOException {
        this(new DataInputStream(in));
    }

    public VertexMesh(DataInputStream in) throws IOException {
        this.vertexCount = in.readInt();
        this.vertexBufferSize = vertexCount * 3 * 4;// *3:xyz, *4:sizeof(float)
        final ByteBuffer vbb = ByteBuffer.allocateDirect(vertexBufferSize);
        vbb.order(ByteOrder.nativeOrder());
        this.vertexBuffer = vbb.asFloatBuffer();
        for (int i = 0; i < vertexCount; i++) {
            // Vertex
            vertexBuffer.put(in.readFloat());
            vertexBuffer.put(in.readFloat());
            vertexBuffer.put(in.readFloat());
        }
    }
}
