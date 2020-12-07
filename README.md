# Collaborative-Graphic-Editor

This was a final project I did in another class, in which 
I built a collaborative graphical editor with scaffolding code
from several Dartmouth faculty members. Like any collaborative client
like Google Docs, you can have multiple editors of the same document
at the same time, drawing shapes/adding and coloring in whatever visuals
they want. Essentially, multiple clients connect to a server, and whatever 
editing any of them does, the others see. So you can add an ellipse, 
and then another person can connect to the main frame and color
or move it, among other things. It handles multiple objects at 
a time, and can draw rectangles, line segments, and "freehand" 
shapes in addition to ellipses.

The client/server set-up in a very similar manner to how a chat server would be
set up. Every editor connects to a thread that talks to the main sketch server
in addition to a main thread for user interaction to handle the drawing. 
The server has a main thread for handling requests to join the server (which
can be either accepted or declined), and also has individual threads to talk to
other clients. The client tells the server about its user's drawing actions. 
The server then tells all the clients about all the drawing actions of each of them.

In this repository, you'll find all the essential files, all of whose purposes are
delineated at the beginning of each document. 
