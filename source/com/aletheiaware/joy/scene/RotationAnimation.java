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

import com.aletheiaware.joy.utils.JoyUtils;

public class RotationAnimation extends Animation {

    private final Vector axis = new Vector();
    private final Matrix rotation;
    private final Matrix temp;
    private final Matrix inverse;
    private final long duration;
    private final float radians;
    private long start = -1;
    private float totalAngle = 0;

    public RotationAnimation(Matrix rotation, Matrix inverse, Matrix temp, long duration, float radians, float x, float y, float z) {
        super();
        this.rotation = rotation;
        this.inverse = inverse;
        this.temp = temp;
        this.duration = duration;
        this.radians = radians;

        final float[] ain = new float[4];
        ain[0] = x;
        ain[1] = y;
        ain[2] = z;
        ain[3] = 1;
        final float[] aout = new float[4];
        inverse.multiply(ain, aout);
        JoyUtils.round(aout);
        axis.set(aout[0], aout[1], aout[2]);
    }

    @Override
    public boolean tick() {
        if (start < 0) {
            start = System.currentTimeMillis();
        }
        float progress = System.currentTimeMillis() - start;
        float angle = JoyUtils.map(progress, 0, duration, 0, radians);
        angle -= totalAngle;// subtract the amount it was rotated in previous ticks
        totalAngle += angle;// add to the total the amount rotated this tick
        // System.out.println("Tick " + progress + "/" + duration + " : " + angle);
        temp.makeRotationAxis(angle, axis);
        rotation.makeMultiplication(rotation, temp);
        if (progress >= duration) {
            JoyUtils.round(rotation.get());// Round rotation to proper values
            return true;
        }
        return false;
    }
}