# Multi-Threaded Server in Java

## Overview
This project implements a multi-threaded server in Java designed to handle concurrent requests efficiently. The server is optimized for performance using Java's threading model, with load testing conducted via JMeter to measure the throughput and scalability under different conditions.

## Features
- **Single-Threaded Server**: A basic server handling one request at a time.
- **Multi-Threaded Server**: An optimized server capable of handling multiple requests simultaneously using Java threads, improving performance and scalability.
- **Load Testing with JMeter**: Performance was measured using JMeter, simulating concurrent users to assess throughput and server responsiveness.

## Performance Metrics
- **Single-Threaded Server Throughput**: 4100+ requests per second.
- **Multi-Threaded Server Throughput**: 52000+ requests per second.
- **Concurrent Users**: Appropriate concurrent users were simulated for load testing to ensure real-world traffic handling.

## Requirements
- Java 8 or higher
- JMeter (for load testing)

## Setup
1. Clone this repository:
   ```bash
   git clone https://github.com/yourusername/multi-threaded-server.git
   cd multi-threaded-server

2.  Open the project in intelliJ on preferred IDE.
3.  For Single-Threaded server go inside the package org.server.singlethreaded and run the **SingleThreadedServer** class.
4.  For Multi-Threaded server go inside the package org.server.multithreaded abd run the **MultiThreadedServer** class.
5.  To perform load testing with JMeter, configure the JMeter test plan as per the instructions provided in the jmeter-tests folder.
    

Running the Server
------------------

The server can be started by running the following command after compilation:
The server will start and begin listening for incoming requests. You can simulate requests using JMeter or a similar tool.

Load Testing with JMeter
------------------------

*   The JMeter configuration is designed to simulate a specified number of concurrent users.
    
*   The **single-threaded server** was tested with a throughput of **4100+ requests per second**, while the **multi-threaded server** achieved **52000+ requests per second**.
    

Conclusion
----------

This project demonstrates the significant performance improvement achieved by leveraging multi-threading in Java for server-side applications. The load testing results highlight the scalability benefits of a multi-threaded architecture, making it more suitable for handling high-volume traffic.
