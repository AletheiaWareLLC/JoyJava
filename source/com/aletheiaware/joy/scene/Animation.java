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

/**
 * Base class for time-based scenegraph animations.
 */
public abstract class Animation {

    public Animation() {
    }

    /**
     * Called for each animation update.
     *
     * @return true iff animation is complete.
     */
    public abstract boolean tick();

    /**
     * Called once the animation is complete.
     *
     * <p>Subclasses should override to perform an action at the end of the animation.
     */
    public void onComplete() {
        // Do nothing
    }
}