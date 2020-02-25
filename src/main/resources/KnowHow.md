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

## Coordinate system

OpenGl 2D system is defined like this:
- center is in center meaning that point has 0,0 coords
- most lower left point has -1,-1 coords and most top right one 1,1

3rd coordinate "comes out of the screen"

**OpenGL functions in a counter clockwise order.** So, when describing
triangle, starting from one vertex, the following one must be provided
in counter clockwise order.

Obviously connecting triangles have repeating vertices. Index buffer
helps with this. It is another VBO which tells OpenGL how to connect
vertices without repeating them, e.g. for 2 triangles 6 vertices are
needed, but if those 2 triangles are connected into quad, by using index
buffer it is possible to define such quad with only 4 vertices instead
of 6. Index buffer is just an order in which vertices needs to be
connected.

    V0 --------------- V3
    |                  |            0   1   2   3
    |                  |           (V0, V1, V2, V3)
    V1 --------------- V2
    
    Triangles are defined: (0,1,3,3,1,2) --> index buffer

## Shaders written in GLSL (modern programmable pipeline)

Shaders are programs that run on GPU instead of CPU and are written in
GLSL. There are two types of shaders: vertex shader and fragment shader.
Output of the vertex shader is an input of the fragment shader. Keep in
mind that vertex shader output variable must be the same variable (same
name) that is the input of the fragment shader. Vertex shader executes
once per each vertex of the object being rendered. It has 2 main
functions:
- position of the vertex rendered on screen
- color of the vertex

Output of the vertex shader can be whatever is needed in fragment
shader. Any combination of the floats, vectors, matrices...
Fragment shader executes one time for every pixel object covers on the
screen. Output of the fragment shader is **always** RGB color. Input of
the fragment shader functions in a way that fragment shader takes mix
values of the 3 vertices which make up the triangle that the current
pixel being calculated is located in. Values are then linearly
interpolated. So for quad example the vertex shader would run 4 times
and fragment shader cca 10000 times.

## Textures

2d PNG picture with 2^n x 2^n pixels (square) - n is and integer value.
In OpenGL, (0,0) texture coordinate is in top left corner of the
texture, with (1,1) being the most bottom right pixel of the texture.
Instead of referring to it as x,y coordinates, it is usually called u,v
coordinate system or s,t coordinate system.

When adding VBO with texture coords to VAO, texture coords need to be in
the same order as positions in the index buffer.

