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

public class VertexNormalMesh {

    public final int bufferSize;
    public final int numVertices;
    public final FloatBuffer vertexBuffer;
    public final FloatBuffer normalBuffer;

    public VertexNormalMesh(int size, int vertices, FloatBuffer vb, FloatBuffer nb) {
        this.bufferSize = size;
        this.numVertices = vertices;
        this.vertexBuffer = vb;
        this.normalBuffer = nb;
    }

    public VertexNormalMesh(Mesh mesh) throws IOException {
        this.numVertices = mesh.getVertices();
        System.out.println("Vertices: " + numVertices);
        this.bufferSize = numVertices * 3 * 4;// *3:xyz, *4:sizeof(float)
        System.out.println("Buffer: " + bufferSize);
        final ByteBuffer vbb = ByteBuffer.allocateDirect(bufferSize);
        final ByteBuffer nbb = ByteBuffer.allocateDirect(bufferSize);
        vbb.order(ByteOrder.nativeOrder());
        nbb.order(ByteOrder.nativeOrder());
        this.vertexBuffer = vbb.asFloatBuffer();
        this.normalBuffer = nbb.asFloatBuffer();
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
    }

    public VertexNormalMesh(byte[] data) throws IOException {
        this(new ByteArrayInputStream(data));
    }

    public VertexNormalMesh(InputStream in) throws IOException {
        this(new DataInputStream(in));
    }

    public VertexNormalMesh(DataInputStream in) throws IOException {
        this.numVertices = in.readInt();
        this.bufferSize = numVertices * 3 * 4;// *3:xyz, *4:sizeof(float)
        final ByteBuffer vbb = ByteBuffer.allocateDirect(bufferSize);
        final ByteBuffer nbb = ByteBuffer.allocateDirect(bufferSize);
        vbb.order(ByteOrder.nativeOrder());
        nbb.order(ByteOrder.nativeOrder());
        this.vertexBuffer = vbb.asFloatBuffer();
        this.normalBuffer = nbb.asFloatBuffer();
        for (int i = 0; i < numVertices; i++) {
            // Vertex
            vertexBuffer.put(in.readFloat());
            vertexBuffer.put(in.readFloat());
            vertexBuffer.put(in.readFloat());
            // Normal
            normalBuffer.put(in.readFloat());
            normalBuffer.put(in.readFloat());
            normalBuffer.put(in.readFloat());
        }
    }

}
