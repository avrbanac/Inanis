# Changelog

All Notable changes to `Inanis` will be documented in this file.

Updates should follow the [Keep a CHANGELOG](http://keepachangelog.com/) principles.

## Unreleased
- Nothing here

## v0.0.8 - 2020-02-28 @avrbanac

### Added
- creation of the projection matrix
- projection matrix needs to load in renderer CTOR
- location methods in ShaderProgram
- new uniform variable in shader program: projectionMatrix
- entities package with Entity class
- Maths util class
- toolbox package
- new uniform variable in shader program: transformationMatrix
- methods that load up values to uniform locations
- getUniformLocation and getAllUniformLocations methods in ShaderProgram

### Fixed
- Renderer now takes shader program in CTOR for projection purposes
- Renderer.render() now takes Entity argument and loads transformationMatrix
- glPosition in vertex shader now takes transformationMatrix uniform

## v0.0.7 - 2020-02-25 @avrbanac

### Added
- render must bind texture
- render method must also activate texture
- render method now enables and disables vertAttribArray 1 also
- binding new VBO to VAO in ShaderProgram
- another VBO with texture coords to model VAO
- added TexturedModel class to represent model with texture (pojo)
- textures package with ModelTexture (pojo)
- Inanis static exit method (1 entry point, 1 exit point)
- Inanis error code enumeration
- new configuration points to global config class (InanisConfig)
- loader now has loadTexture method
- texture to the rendered quad

### Fixed
- bindAttribute() in AbstractShaderProgram needs to be called before link program
- switching to camelCase variable names for shaders
- fragment shader now uses texture() method
- fragment shader now uses uniform sampler2D
- fragment shader takes passed texture coordinates
- vertex shader no longer outputs color but 2d vec (pass_texture_coords)
- Renderer now renders TexturedModel instead of RawModel

## v0.0.6 - 2020-02-24 @avrbanac

### Added
- abstract shader program and static implementation
- basic vertex and fragment shaders for OpenGL ver 400 core (resources)

## v0.0.5 - 2020-02-19 @avrbanac

### Added
- loader now has storeDataInIntBuffer method
- implementing index buffer idea

### Fixed
- TestQuadModel now needs to provide Vertex data and Index buffer
- Render#render now uses different method
- Loader#loadToVAO return size of indices.length instead of positions.length/3
- Loader#loadToVAO now takes int[] indices argument also

## v0.0.4 - 2020-02-18 @avrbanac

### Added
- TestQuadModel (test render method)
- VAO and VBO creation and model storage
- renderers package with Renderer class
- loaders package with Loader class
- models package with RawModel class
- [KnowHow](src/main/resources/KnowHow.md) documentation file

### Fixed
- moved GL11.glClear to Renderer class (from DisplayManager.render())

## v0.0.3 - 2020-02-15 @avrbanac

### Added
- main engine (for now working with single thread)
- display manager
- input manager singleton to wrap all inputs together
- inputs singletons (for keyboard and mouse inputs)
- error callback for GLFW
- configuration class (keep all configuration in one place)

### Tested
- main window creation and keyboard input
- main engine loop

## v0.0.2 - 2020-02-11 @avrbanac

### Added
- resources: log4j2.xml
- main class (entry point)
- log4j2 support
- lwjgl3 support

## v0.0.1 - 2020-02-11 @avrbanac

### Added
- project basic structure
- gradle basics
- documentation skeleton

### Deprecated
- Nothing

### Fixed
- Nothing

### Removed
- Nothing

### Tested
- Nothing

### Security
- Nothing