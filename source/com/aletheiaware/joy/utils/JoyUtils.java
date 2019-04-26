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

package com.aletheiaware.joy.utils;

import com.aletheiaware.joy.JoyProto.Mesh;
import com.aletheiaware.joy.JoyProto.Shader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public final class JoyUtils {

    public static final String TAG = "Joy";

    public static final float[] X = new float[] {1, 0, 0, 1};
    public static final float[] Y = new float[] {0, 1, 0, 1};
    public static final float[] Z = new float[] {0, 0, 1, 1};

    private JoyUtils() {}

    /**
     * Rounds all values in the given array to -1, 0, or 1.
     */
    public static void round(float[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > 0.5) {
                arr[i] = 1.0f;
            } else if (arr[i] < -0.5) {
                arr[i] = -1.0f;
            } else {
                arr[i] = 0.0f;
            }
        }
    }

    /**
     * Maps a value in a given range to another given range.
     */
    public static float map(float value, float inMin, float inMax, float outMin, float outMax) {
        return (value - inMin) / (inMax - inMin) * (outMax - outMin) + outMin;
    }

    /**
     * Reads and returns a Mesh from the given File.
     */
    public static Mesh readMesh(File file) throws Exception {
        FileInputStream in = new FileInputStream(file);
        Mesh m = Mesh.parseDelimitedFrom(in);
        in.close();
        return m;
    }

    /**
     * Writes the given Mesh to the given File.
     */
    public static void writeMesh(File file, Mesh mesh) throws Exception {
        FileOutputStream out = new FileOutputStream(file);
        mesh.writeDelimitedTo(out);
        out.flush();
        out.close();
    }

    /**
     * Reads and returns a Shader from the given File.
     */
    public static Shader readShader(File file) throws Exception {
        FileInputStream in = new FileInputStream(file);
        Shader s = Shader.parseDelimitedFrom(in);
        in.close();
        return s;
    }

    /**
     * Writes the given Shader to the given File.
     */
    public static void writeShader(File file, Shader shader) throws Exception {
        FileOutputStream out = new FileOutputStream(file);
        shader.writeDelimitedTo(out);
        out.flush();
        out.close();
    }

}
