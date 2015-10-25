/**
 * anothergltry - Another GL try
 * Copyright (c) 2015, Matej Kormuth <http://www.github.com/dobrakmato>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package eu.matejkormuth.jge.core;

import eu.matejkormuth.jge.configuration.SimpleConfiguration;
import eu.matejkormuth.jge.filesystem.Content;
import eu.matejkormuth.jge.filesystem.FileSystem;
import eu.matejkormuth.jge.filesystem.filesystems.LocalFileSystem;
import eu.matejkormuth.jge.rendering.GLRenderer;
import eu.matejkormuth.jge.rendering.RenderPipeline;
import eu.matejkormuth.jge.scene.SceneManager;
import groovy.lang.GroovyShell;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.groovy.control.CompilerConfiguration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
public class Engine {

    public static StdInConsole console;
    public static GLRenderer renderer;
    public static GroovyShell shell;
    public static FileSystem fileSystem;
    public static Content content;
    public static SceneManager sceneManager;
    public static RenderPipeline renderPipeline;

    public Engine() {
        internal();
    }

    private void internal() {
        log.info("Engine started at {}", new SimpleDateFormat().format(new Date()));

        // Start console.
        console = new StdInConsole();

        // Load Engine configuration.
        SimpleConfiguration configuration;
        try {
            configuration = SimpleConfiguration.loadFrom(new FileReader(new File("./engine.properties")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        // Options for Engine from configuration.
        int width = configuration.getInteger("width", 1280);
        int height = configuration.getInteger("height", 720);
        boolean fullscreen = configuration.getBoolean("fullscreen", false);

        // Init GL.
        renderer = new GLRenderer();
        renderer.create(width, height, fullscreen);

        // Init scene manager.
        sceneManager = new SceneManager();

        // Init render pipeline.
        renderPipeline = new RenderPipeline(sceneManager);

        // Init AL.

        // Init Scripting
        CompilerConfiguration config = new CompilerConfiguration();
        config.setScriptBaseClass("eu.matejkormuth.jge.scripting.BaseScript");
        shell = new GroovyShell(config);

        // Init virtual file systems.
        fileSystem = new LocalFileSystem(new File("./content/").getAbsolutePath());

        // Init Resources and Content loader.
        content = new Content(fileSystem);

        // Init all other sub-systems.

        // Start main script.
        shell.evaluate(fileSystem.readText("main.groovy"));

        // Start main game loop.
    }

    public static void shutdown() {
        log.info("Shutting down!");
    }
}
