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

import java.util.Arrays;

public class Vector {

    public static final Vector XV = new Vector(1, 0, 0);
    public static final Vector YV = new Vector(0, 1, 0);
    public static final Vector ZV = new Vector(0, 0, 1);

    private static final int X = 0;
    private static final int Y = 1;
    private static final int Z = 2;

    private float[] arr = new float[3];

    public Vector() {
    }

    public Vector(float x, float y, float z) {
        this();
        set(x, y, z);
    }

    public float[] get() {
        return arr;
    }

    public float getX() {
        return arr[X];
    }

    public float getY() {
        return arr[Y];
    }

    public float getZ() {
        return arr[Z];
    }

    public Vector setX(float x) {
        arr[X] = x;
        return this;
    }

    public Vector setY(float y) {
        arr[Y] = y;
        return this;
    }

    public Vector setZ(float z) {
        arr[Z] = z;
        return this;
    }

    public Vector set(Vector v) {
        setX(v.getX());
        setY(v.getY());
        setZ(v.getZ());
        return this;
    }

    public Vector set(float x, float y, float z) {
        setX(x);
        setY(y);
        setZ(z);
        return this;
    }

    public Vector set(float[] a) {
        setX(a[X]);
        setY(a[Y]);
        setZ(a[Z]);
        return this;
    }

    /**
     * Rounds all values in the Vector to the given precision.
     */
    public Vector round(float precision) {
        float scale = (float) Math.pow(10, precision);
        setX(Math.round(getX() * scale) / scale);
        setY(Math.round(getY() * scale) / scale);
        setZ(Math.round(getZ() * scale) / scale);
        return this;
    }

    public Vector cap(float min, float max) {
        setX(Math.min(max, Math.max(min, getX())));
        setY(Math.min(max, Math.max(min, getY())));
        setZ(Math.min(max, Math.max(min, getZ())));
        return this;
    }

    public float magnitude() {
        float x = getX();
        float y = getY();
        float z = getZ();
        return (float) Math.sqrt((x * x) + (y * y) + (z * z));
    }

    public Vector normalize() {
        float mag = magnitude();
        setX(getX() / mag);
        setY(getY() / mag);
        setZ(getZ() / mag);
        return this;
    }

    public float dot(Vector v) {
        float sum = 0.0f;
        sum += getX() * v.getX();
        sum += getY() * v.getY();
        sum += getZ() * v.getZ();
        return sum;
    }

    public Vector scale(float scale) {
        setX(getX() * scale);
        setY(getY() * scale);
        setZ(getZ() * scale);
        return this;
    }

    public float angle(Vector v) {
        return (float) Math.acos(dot(v) / (magnitude() * v.magnitude()));
    }

    public Vector cross(Vector v) {
        float x = (getY() * v.getZ()) - (getZ() * v.getY());
        float y = (getZ() * v.getX()) - (getX() * v.getZ());
        float z = (getX() * v.getY()) - (getY() * v.getX());
        return new Vector(x, y, z);
    }

    public Vector clone() {
        return new Vector(getX(), getY(), getZ());
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(arr);
    }

    @Override
    public boolean equals(Object o) {
        if (o != null && o instanceof Vector) {
            Vector v = (Vector) o;
            return v.getX() == getX()
                    && v.getY() == getY()
                    && v.getZ() == getZ();
        }
        return false;
    }

    @Override
    public String toString() {
        return Arrays.toString(arr);
    }
}
