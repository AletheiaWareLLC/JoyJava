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

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Scene {

    private final Map<String, float[]> floats = new ConcurrentHashMap<>();
    private final Map<String, int[]> ints = new ConcurrentHashMap<>();
    private final Map<String, Matrix> matrices = new ConcurrentHashMap<>();
    private final Map<String, Vector> vectors = new ConcurrentHashMap<>();

    private Animation animation = null;

    public void clearAnimation() {
        animation = null;
    }

    public void setAnimation(Animation a) {
        if (animation == null) {
            animation = a;
        } else {
            System.err.println("Animation dropped: " + a);
            System.err.println("Current animation: " + animation);
        }
    }

    public boolean hasAnimation() {
        return animation != null;
    }

    public void tick() {
        if (animation != null && animation.tick()) {
            animation.onComplete();
            animation = null;
        }
    }

    public float[] getFloatArray(String name) {
        return floats.get(name);
    }

    public void putFloatArray(String name, float[] array) {
        floats.put(name, array);
    }

    public int[] getIntArray(String name) {
        return ints.get(name);
    }

    public void putIntArray(String name, int[] array) {
        ints.put(name, array);
    }

    public Matrix getMatrix(String name) {
        return matrices.get(name);
    }

    public void putMatrix(String name, Matrix matrix) {
        matrices.put(name, matrix);
    }

    public Vector getVector(String name) {
        return vectors.get(name);
    }

    public void putVector(String name, Vector vector) {
        vectors.put(name, vector);
    }
}