# Changelog

All Notable changes to `Inanis` will be documented in this file.

Updates should follow the [Keep a CHANGELOG](http://keepachangelog.com/) principles.

## Unreleased
- Nothing here

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