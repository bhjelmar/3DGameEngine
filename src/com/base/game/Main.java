package com.base.game;

import com.base.engine.core.CoreEngine;

public class Main {

    public static void main(String[] args) {
        CoreEngine coreEngine = new CoreEngine(800, 600, 60, new TestGame());
        coreEngine.createWindow("3D Game Engine");
        coreEngine.start();
    }

}
