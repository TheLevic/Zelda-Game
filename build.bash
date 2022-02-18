#!/bin/bash
set -u -e
echo "compiling program!"
javac Game.java View.java Controller.java Model.java Brick.java Json.java
java Game
