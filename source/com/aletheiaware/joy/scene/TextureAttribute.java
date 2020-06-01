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

public abstract class TextureAttribute implements Attribute {

    public final String textureName;

    public TextureAttribute(String textureName) {
        this.textureName = textureName;
    }

    public int[] getTexture(Scene scene) {
        if (textureName == null) {
            throw new RuntimeException("No texture set");
        }
        return scene.getIntArray(textureName);
    }
}