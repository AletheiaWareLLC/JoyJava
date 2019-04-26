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

import java.util.ArrayList;
import java.util.List;

public abstract class SceneGraphNode {

    private final List<SceneGraphNode> children = new ArrayList<>();
    private Animation animation;

    public SceneGraphNode() {}

    public void addChild(SceneGraphNode child) {
        if (child == null) {
            throw new NullPointerException();
        }
        children.add(child);
    }

    public List<SceneGraphNode> getChildren() {
        return children;
    }

    public boolean removeChild(SceneGraphNode child) {
        return children.remove(child);
    }

    public void clear() {
        children.clear();
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

    public void draw(Scene scene) {
        if (animation != null && animation.tick()) {
            animation.onComplete();
            animation = null;
        }
        before(scene);
        for (int i = 0; i < children.size(); i++) {
            children.get(i).draw(scene);
        }
        after(scene);
    }

    public abstract void before(Scene scene);

    public abstract void after(Scene scene);
}
