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

public class Matrix {

    private float[] arr = new float[16];

    public Matrix() {
    }

    public Matrix(float[] array) {
        this();
        set(array);
    }

    public Matrix set(Matrix m) {
        set(m.get());
        return this;
    }

    public Matrix set(float[] array) {
        System.arraycopy(array, 0, arr, 0, 16);
        return this;
    }

    public float[] get() {
        return arr;
    }

    public void multiply(float[] vin, float[] vout) {
        vout[0] = vin[0] * arr[4 * 0 + 0] + vin[1] * arr[4 * 1 + 0] + vin[2] * arr[4 * 2 + 0] + vin[3] * arr[4 * 3 + 0];
        vout[1] = vin[0] * arr[4 * 0 + 1] + vin[1] * arr[4 * 1 + 1] + vin[2] * arr[4 * 2 + 1] + vin[3] * arr[4 * 3 + 1];
        vout[2] = vin[0] * arr[4 * 0 + 2] + vin[1] * arr[4 * 1 + 2] + vin[2] * arr[4 * 2 + 2] + vin[3] * arr[4 * 3 + 2];
        vout[3] = vin[0] * arr[4 * 0 + 3] + vin[1] * arr[4 * 1 + 3] + vin[2] * arr[4 * 2 + 3] + vin[3] * arr[4 * 3 + 3];
    }

    public Matrix makeIdentity() {
        arr[4 * 0 + 0] = 1f;
        arr[4 * 0 + 1] = 0f;
        arr[4 * 0 + 2] = 0f;
        arr[4 * 0 + 3] = 0f;

        arr[4 * 1 + 0] = 0f;
        arr[4 * 1 + 1] = 1f;
        arr[4 * 1 + 2] = 0f;
        arr[4 * 1 + 3] = 0f;

        arr[4 * 2 + 0] = 0f;
        arr[4 * 2 + 1] = 0f;
        arr[4 * 2 + 2] = 1f;
        arr[4 * 2 + 3] = 0f;

        arr[4 * 3 + 0] = 0f;
        arr[4 * 3 + 1] = 0f;
        arr[4 * 3 + 2] = 0f;
        arr[4 * 3 + 3] = 1f;
        return this;
    }

    public boolean makeInverse(Matrix m) {
        final float[] a = m.get();
        final float[] b = get();

        float[] temp = new float[16];

        temp[0] = a[5] * a[10] * a[15] - a[5] * a[11] * a[14] - a[9] * a[6] * a[15] + a[9] * a[7] * a[14] + a[13] * a[6] * a[11] - a[13] * a[7] * a[10];
        temp[4] = -a[4] * a[10] * a[15] + a[4] * a[11] * a[14] + a[8] * a[6] * a[15] - a[8] * a[7] * a[14] - a[12] * a[6] * a[11] + a[12] * a[7] * a[10];
        temp[8] = a[4] * a[9] * a[15] - a[4] * a[11] * a[13] - a[8] * a[5] * a[15] + a[8] * a[7] * a[13] + a[12] * a[5] * a[11] - a[12] * a[7] * a[9];
        temp[12] = -a[4] * a[9] * a[14] + a[4] * a[10] * a[13] + a[8] * a[5] * a[14] - a[8] * a[6] * a[13] - a[12] * a[5] * a[10] + a[12] * a[6] * a[9];
        temp[1] = -a[1] * a[10] * a[15] + a[1] * a[11] * a[14] + a[9] * a[2] * a[15] - a[9] * a[3] * a[14] - a[13] * a[2] * a[11] + a[13] * a[3] * a[10];
        temp[5] = a[0] * a[10] * a[15] - a[0] * a[11] * a[14] - a[8] * a[2] * a[15] + a[8] * a[3] * a[14] + a[12] * a[2] * a[11] - a[12] * a[3] * a[10];
        temp[9] = -a[0] * a[9] * a[15] + a[0] * a[11] * a[13] + a[8] * a[1] * a[15] - a[8] * a[3] * a[13] - a[12] * a[1] * a[11] + a[12] * a[3] * a[9];
        temp[13] = a[0] * a[9] * a[14] - a[0] * a[10] * a[13] - a[8] * a[1] * a[14] + a[8] * a[2] * a[13] + a[12] * a[1] * a[10] - a[12] * a[2] * a[9];
        temp[2] = a[1] * a[6] * a[15] - a[1] * a[7] * a[14] - a[5] * a[2] * a[15] + a[5] * a[3] * a[14] + a[13] * a[2] * a[7] - a[13] * a[3] * a[6];
        temp[6] = -a[0] * a[6] * a[15] + a[0] * a[7] * a[14] + a[4] * a[2] * a[15] - a[4] * a[3] * a[14] - a[12] * a[2] * a[7] + a[12] * a[3] * a[6];
        temp[10] = a[0] * a[5] * a[15] - a[0] * a[7] * a[13] - a[4] * a[1] * a[15] + a[4] * a[3] * a[13] + a[12] * a[1] * a[7] - a[12] * a[3] * a[5];
        temp[14] = -a[0] * a[5] * a[14] + a[0] * a[6] * a[13] + a[4] * a[1] * a[14] - a[4] * a[2] * a[13] - a[12] * a[1] * a[6] + a[12] * a[2] * a[5];
        temp[3] = -a[1] * a[6] * a[11] + a[1] * a[7] * a[10] + a[5] * a[2] * a[11] - a[5] * a[3] * a[10] - a[9] * a[2] * a[7] + a[9] * a[3] * a[6];
        temp[7] = a[0] * a[6] * a[11] - a[0] * a[7] * a[10] - a[4] * a[2] * a[11] + a[4] * a[3] * a[10] + a[8] * a[2] * a[7] - a[8] * a[3] * a[6];
        temp[11] = -a[0] * a[5] * a[11] + a[0] * a[7] * a[9] + a[4] * a[1] * a[11] - a[4] * a[3] * a[9] - a[8] * a[1] * a[7] + a[8] * a[3] * a[5];
        temp[15] = a[0] * a[5] * a[10] - a[0] * a[6] * a[9] - a[4] * a[1] * a[10] + a[4] * a[2] * a[9] + a[8] * a[1] * a[6] - a[8] * a[2] * a[5];

        float determinant = a[0] * temp[0] + a[1] * temp[4] + a[2] * temp[8] + a[3] * temp[12];

        if (determinant == 0) {
            return false;
        }

        determinant = 1.0f / determinant;

        for (int i = 0; i < 16; i++) {
            b[i] = temp[i] * determinant;
        }

        return true;
    }

    public Matrix makeMultiplication(Matrix m1, Matrix m2) {
        final float[] a = m1.get();
        final float[] b = m2.get();

        final float a00 = a[4 * 0 + 0];
        final float a01 = a[4 * 0 + 1];
        final float a02 = a[4 * 0 + 2];
        final float a03 = a[4 * 0 + 3];
        final float a10 = a[4 * 1 + 0];
        final float a11 = a[4 * 1 + 1];
        final float a12 = a[4 * 1 + 2];
        final float a13 = a[4 * 1 + 3];
        final float a20 = a[4 * 2 + 0];
        final float a21 = a[4 * 2 + 1];
        final float a22 = a[4 * 2 + 2];
        final float a23 = a[4 * 2 + 3];
        final float a30 = a[4 * 3 + 0];
        final float a31 = a[4 * 3 + 1];
        final float a32 = a[4 * 3 + 2];
        final float a33 = a[4 * 3 + 3];

        final float b00 = b[4 * 0 + 0];
        final float b01 = b[4 * 0 + 1];
        final float b02 = b[4 * 0 + 2];
        final float b03 = b[4 * 0 + 3];
        final float b10 = b[4 * 1 + 0];
        final float b11 = b[4 * 1 + 1];
        final float b12 = b[4 * 1 + 2];
        final float b13 = b[4 * 1 + 3];
        final float b20 = b[4 * 2 + 0];
        final float b21 = b[4 * 2 + 1];
        final float b22 = b[4 * 2 + 2];
        final float b23 = b[4 * 2 + 3];
        final float b30 = b[4 * 3 + 0];
        final float b31 = b[4 * 3 + 1];
        final float b32 = b[4 * 3 + 2];
        final float b33 = b[4 * 3 + 3];

        arr[4 * 0 + 0] = a00 * b00 + a10 * b01 + a20 * b02 + a30 * b03;
        arr[4 * 0 + 1] = a01 * b00 + a11 * b01 + a21 * b02 + a31 * b03;
        arr[4 * 0 + 2] = a02 * b00 + a12 * b01 + a22 * b02 + a32 * b03;
        arr[4 * 0 + 3] = a03 * b00 + a13 * b01 + a23 * b02 + a33 * b03;

        arr[4 * 1 + 0] = a00 * b10 + a10 * b11 + a20 * b12 + a30 * b13;
        arr[4 * 1 + 1] = a01 * b10 + a11 * b11 + a21 * b12 + a31 * b13;
        arr[4 * 1 + 2] = a02 * b10 + a12 * b11 + a22 * b12 + a32 * b13;
        arr[4 * 1 + 3] = a03 * b10 + a13 * b11 + a23 * b12 + a33 * b13;

        arr[4 * 2 + 0] = a00 * b20 + a10 * b21 + a20 * b22 + a30 * b23;
        arr[4 * 2 + 1] = a01 * b20 + a11 * b21 + a21 * b22 + a31 * b23;
        arr[4 * 2 + 2] = a02 * b20 + a12 * b21 + a22 * b22 + a32 * b23;
        arr[4 * 2 + 3] = a03 * b20 + a13 * b21 + a23 * b22 + a33 * b23;

        arr[4 * 3 + 0] = a00 * b30 + a10 * b31 + a20 * b32 + a30 * b33;
        arr[4 * 3 + 1] = a01 * b30 + a11 * b31 + a21 * b32 + a31 * b33;
        arr[4 * 3 + 2] = a02 * b30 + a12 * b31 + a22 * b32 + a32 * b33;
        arr[4 * 3 + 3] = a03 * b30 + a13 * b31 + a23 * b32 + a33 * b33;
        return this;
    }

    public Matrix makeScale(Vector v) {
        arr[4 * 0 + 0] = v.getX();
        arr[4 * 0 + 1] = 0f;
        arr[4 * 0 + 2] = 0f;
        arr[4 * 0 + 3] = 0f;

        arr[4 * 1 + 0] = 0f;
        arr[4 * 1 + 1] = v.getY();
        arr[4 * 1 + 2] = 0f;
        arr[4 * 1 + 3] = 0f;

        arr[4 * 2 + 0] = 0f;
        arr[4 * 2 + 1] = 0f;
        arr[4 * 2 + 2] = v.getZ();
        arr[4 * 2 + 3] = 0f;

        arr[4 * 3 + 0] = 0f;
        arr[4 * 3 + 1] = 0f;
        arr[4 * 3 + 2] = 0f;
        arr[4 * 3 + 3] = 1f;
        return this;
    }

    public Matrix makeTranslate(Vector v) {
        arr[4 * 0 + 0] = 1f;
        arr[4 * 0 + 1] = 0f;
        arr[4 * 0 + 2] = 0f;
        arr[4 * 0 + 3] = 0f;

        arr[4 * 1 + 0] = 0f;
        arr[4 * 1 + 1] = 1f;
        arr[4 * 1 + 2] = 0f;
        arr[4 * 1 + 3] = 0f;

        arr[4 * 2 + 0] = 0f;
        arr[4 * 2 + 1] = 0f;
        arr[4 * 2 + 2] = 1f;
        arr[4 * 2 + 3] = 0f;

        arr[4 * 3 + 0] = v.getX();
        arr[4 * 3 + 1] = v.getY();
        arr[4 * 3 + 2] = v.getZ();
        arr[4 * 3 + 3] = 1f;
        return this;
    }

    public Matrix makeRotationAxis(final float angle, final Vector v) {
        final float c = (float) Math.cos(angle);
        final float s = (float) Math.sin(angle);

        final float omc = 1 - c;
        v.normalize();
        final float x = v.getX();
        final float y = v.getY();
        final float z = v.getZ();

        arr[4 * 0 + 0] = x * x * omc + c;
        arr[4 * 0 + 1] = y * x * omc + z * s;
        arr[4 * 0 + 2] = z * x * omc + -y * s;
        arr[4 * 0 + 3] = 0f;

        arr[4 * 1 + 0] = x * y * omc + -z * s;
        arr[4 * 1 + 1] = y * y * omc + c;
        arr[4 * 1 + 2] = z * y * omc + x * s;
        arr[4 * 1 + 3] = 0f;

        arr[4 * 2 + 0] = x * z * omc + y * s;
        arr[4 * 2 + 1] = y * z * omc + -x * s;
        arr[4 * 2 + 2] = z * z * omc + c;
        arr[4 * 2 + 3] = 0f;

        arr[4 * 3 + 0] = 0f;
        arr[4 * 3 + 1] = 0f;
        arr[4 * 3 + 2] = 0f;
        arr[4 * 3 + 3] = 1f;

        return this;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(arr);
    }

    @Override
    public boolean equals(Object o) {
        if (o != null && o instanceof Matrix) {
            Matrix m = (Matrix) o;
            return Arrays.equals(m.get(), get());
        }
        return false;
    }

    @Override
    public String toString() {
        return Arrays.toString(arr);
    }
}