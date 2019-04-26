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

public abstract class RotationGesture {

    private final float TWO_PI = 2.0f * (float) Math.PI;
    private final float scale;
    private final float threshold;
    private boolean move = false;
    private int previousX;
    private int previousY;

    public RotationGesture(float scale) {
        this.scale = scale;
        threshold = scale / 100.0f;
        System.out.println("RotationGesture");
        System.out.println("Scale: " + scale);
        System.out.println("Threshold: " + threshold);
    }

    public void start(int x, int y) {
        previousX = x;
        previousY = y;
        move = false;
    }

    public void move(int x, int y) {
        float deltaX = x - previousX;
        float deltaY = y - previousY;
        if (Math.abs(deltaX) < threshold && Math.abs(deltaY) < threshold) {
            // Not a move
            return;
        }
        move = true;
        float radX = (deltaY / scale) * TWO_PI;
        float radY = (deltaX / scale) * TWO_PI;
        rotate(radX, radY);
        previousX = x;
        previousY = y;
    }

    public boolean hasRotated() {
        return move;
    }

    public abstract void rotate(float x, float y);
}
