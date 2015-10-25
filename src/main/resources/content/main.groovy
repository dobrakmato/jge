package content
import eu.matejkormuth.jge.core.Engine
import eu.matejkormuth.jge.scene.Scene

println("This is Groovy test script!");

/*
 * Include all game files.
 */

/*
 * Define some main methods.
 */

def loadDefaultScene() {
    Scene scene = Engine.content.load(Scene.class, "scenes/outdoor.scene");
    Engine.sceneManager.setScene(scene);
}

/**
 * Load the default scene and start the game.
 */
def main() {
    loadDefaultScene();
}

// Start the application.
main();