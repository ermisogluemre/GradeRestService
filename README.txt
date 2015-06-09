This Eclipse project wraps the JAR from the CALAEGSEngine project in a WAR.

To build run the war task in ANT.  

NOTE:  
Before building this project, build the CALAEGSEngine project first.  The build attempts to copy
the calaegs.jar from the CALAEGSEngine project.  The location of the project can be configured
in build.xml.

The resulting WAR file is in the dist directory.

