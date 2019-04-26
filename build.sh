#!/bin/bash
#
# Copyright 2019 Aletheia Ware LLC
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
# http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

set -e
set -x

if [ -d out ]; then
    rm -r out
fi
mkdir -p out/code
mkdir -p out/test

SOURCES=(
    source/com/aletheiaware/joy/JoyProto.java
    source/com/aletheiaware/joy/scene/Animation.java
    source/com/aletheiaware/joy/scene/Attribute.java
    source/com/aletheiaware/joy/scene/AttributeNode.java
    source/com/aletheiaware/joy/scene/ColourAttribute.java
    source/com/aletheiaware/joy/scene/LightNode.java
    source/com/aletheiaware/joy/scene/Matrix.java
    source/com/aletheiaware/joy/scene/MatrixTransformationNode.java
    source/com/aletheiaware/joy/scene/MeshLoader.java
    source/com/aletheiaware/joy/scene/RotationAnimation.java
    source/com/aletheiaware/joy/scene/RotationGesture.java
    source/com/aletheiaware/joy/scene/ScaleNode.java
    source/com/aletheiaware/joy/scene/Scene.java
    source/com/aletheiaware/joy/scene/SceneGraphNode.java
    source/com/aletheiaware/joy/scene/TransformationNode.java
    source/com/aletheiaware/joy/scene/TranslateNode.java
    source/com/aletheiaware/joy/scene/Vector.java
    source/com/aletheiaware/joy/scene/VertexMesh.java
    source/com/aletheiaware/joy/scene/VertexNormalMesh.java
    source/com/aletheiaware/joy/utils/JoyUtils.java
)

javac -cp libs/protobuf-lite-3.0.1.jar ${SOURCES[*]} -d out/code
jar cvf out/JoyJava.jar -C out/code .
