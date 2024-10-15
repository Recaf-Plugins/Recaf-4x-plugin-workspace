# Recaf plugin workspace

This is a sample maven workspace for creating plugins for Recaf `4.X`.

## Plugin documentation

The developer documentation can be found here: [Developer Documentation](https://recaf.coley.software/dev/index.html).

The source and javadoc artifacts are also available and can be fetched in your IDE workspace.

## Building

Once you've downloaded or cloned the repository, you can compile with `gradlew build`.
This will generate the file `build/libs/example-plugin-{VERSION}.jar`. 

To add your plugin to Recaf:

1. Navigate to the `plugins` folder.
    - Windows: `%APPDATA%/Recaf/plugins`
    - Linux: `$HOME/Recaf/plugins`
2. Copy your plugin jar into this folder
3. Run Recaf to verify your plugin loads.

## Changing plugin information

The plugin information like its id, version, name, and description are all assigned in the `build.gradle` file in the `ext` block.

## Running Recaf from this project

You can easily launch Recaf by running `gradlew runRecaf`. The custom `runRecaf` task will ensure your plugin is loaded when Recaf opens.