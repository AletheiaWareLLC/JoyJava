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

public abstract class TransformationNode extends SceneGraphNode {

    private final Matrix savedModel = new Matrix();

    public TransformationNode() {
        super();
    }

    public abstract Matrix getMatrix(Scene scene);

    @Override
    public void before(Scene scene) {
        Matrix model = scene.getMatrix("model");
        // Save Model Matrix
        System.arraycopy(model.get(), 0, savedModel.get(), 0, 16);
        Matrix transformation = getMatrix(scene);
        // Apply transformation
        model.makeMultiplication(savedModel, transformation);
    }

    @Override
    public void after(Scene scene) {
        Matrix model = scene.getMatrix("model");
        // Restore Model Matrix
        System.arraycopy(savedModel.get(), 0, model.get(), 0, 16);
    }
}