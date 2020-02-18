# OpenGL know how stuff

All listed information is connected to new programmable pipeline instead
of old fixed pipeline.

## VAO and VBO

Vertex Array Object is a structure for storing and rendering models in
OpenGL (no more old display lists). Simplified, it is an object with
stored data about 3D model divided into slots (attribute lists - usually
16 such lists). Different sort of data are stored into those lists, e.g.
vertex positions, vertex colours, normal vectors, texture coords...

The data sets stored in each attribute list are stored as Vertex Buffer
Objects. So, VBO is a list of data, any data.

Each VAO has it's own ID which can be used to access certain VAO.

## Storing of 3D object

Each 3D object consists of triangles in 3D space. Each triangle is
defined by 3 vertices and each vertex is defined by 3 coords (x,y,z).

These vertices are stored in VBO as mentioned before, and that VBO than
is stored into one of the attribute lists in VAO.

