# Iterative-Socket-Server
An iterative (single-threaded) server for use in a client-server configuration to examine, analyze, and study the effects an iterative server has on the efficiency (average turn-around time) of processing client requests.
There are two (2) programs: 1) an iterative (single-threaded) server that accepts requests from clients, and 2) a multi-threaded client capable of spawning numerous client sessions that connect to the server
The server and client programs must connect to the same network address and port
The iterative (single-threaded) server should handle one client request at a time (serially)
The Java ServerSocket object caches client requests automatically
The iterative (single-threaded) server must support the following client requests:
Date and Time - the date and time on the server
Uptime - how long the server has been running since last boot-up
Memory Use - the current memory usage on the server
Netstat - lists network connections on the server
Current Users - list of users currently connected to the server
Running Processes - list of programs currently running on the server
Create a multi-threaded client that transmits requests to the server on a specified network port
The client program must be able to spawn multiple client sessions
When the server program is launched, the server should
Listen for client requests on the specified network address and port
When a client request is received, the server should
Determine which operation is being requested
Perform the requested operation and collect the resulting output
Reply to the client request with the output from the operation performed
Perform any necessary clean up activities
Go back to listening for client requests
When the client program is launched, the client should
Request the network address and port to which to connect
Request the operation to request (refer to the list above)
Request how many client requests to generate (1, 5, 10, 15, 20 and 25)
Collect the following data
Turn-around Time (elapsed time) for each client request
The time required for the client request to travel to the server, be processed by the server, and return to the client
Total Turn-around Time (sum of the turn-around times for all the client requests)
Average Turn-around Time (Total Turn-around Time divided by the number client requests)
Deliverables

Program 

The source code, as well as any other software required, of the client and server programs. 

Report 

Described the purpose of the project
Described the goals of the project
Introduced readers to what they will find in the remaining sections of the paper
Client-Server Setup and Configuration
Described the design of the Client and Server programs
Discussed the design decisions that were made
Explained the basic operation of the Client and Server programs
Testing and Data Collection
Explained how the Iterative Server was tested
Listed and explained the data collected
Provided the data collected for each of the six (6) operations
Data Analysis
Answered the following questions:
What affect, if any, does increasing the number of clients have on the Turn-around Time for individual clients?
What affect, if any, does increasing the number of clients have on the Average Turn-around Time?
What is the primary cause of the affect on individual client Turn-around Time and Average Turn-around Time?
Conclusion
Conclusions from data analysis
Lessons Learned
Listed lessons learned during the Iterative Server assignment
Writing the Client and Server programs
Collecting data
Problems overcame
