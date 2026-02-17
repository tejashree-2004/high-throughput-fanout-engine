🚀 High Throughput Fan-Out Engine

A high-throughput fan-out engine designed to stream large input data and dispatch records to multiple downstream sinks safely and efficiently. The system demonstrates backpressure control, concurrency, rate limiting, retry handling, and operational metrics.

📌 Overview

This project simulates a real-world data pipeline where a large stream of records must be processed and delivered to multiple downstream systems without memory overflow or uncontrolled throughput.

The engine is built with:

Streaming ingestion (memory safe) Bounded queue backpressure Parallel dispatch Rate-limited sinks Retry + Dead Letter Queue (DLQ) Pluggable transformation strategy Observability metrics

The architecture focuses on correctness, resilience, and predictable performance.

🧠 Architecture Input File ↓ Streaming Reader ↓ Bounded Blocking Queue (Backpressure) ↓ Fan-Out Engine ↓ Transformers ↓ Parallel Sink Dispatch ↓ Metrics + DLQ

🧩 Components Streaming Reader

Reads the input file line-by-line without loading the entire dataset into memory.

Backpressure Queue

A fixed-size blocking queue prevents uncontrolled producer speed and protects system memory.

Transformers

Implements a strategy pattern allowing extensible record transformation (JSON example included).

Fan-Out Engine

Parallel worker pool dispatches records to multiple sinks concurrently.

Mock Sinks

Simulated downstream systems with configurable rate limits.

Retry + DLQ

Failed sends retry up to 3 times. Persistent failures are redirected to a Dead Letter Queue.

Metrics

Tracks success/failure counters and prints operational stats periodically.

⚙️ Project Structure engine ├ App.java ├ config/ ├ reader/ ├ queue/ ├ transform/ ├ sink/ ├ engine/ ├ metrics/ └ dlq/

Each package has a single responsibility, making the system modular and extensible.

▶ Running the Application Requirements

Java 17+

Maven 3+

Build mvn clean package

Run mvn exec:java

or run App.java directly from IDE.

📈 Features Implemented

Streaming ingestion (no full file load) Backpressure queue Concurrent dispatch Rate-limited sinks Retry mechanism Dead Letter Queue Metrics reporting Extensible transformation layer

🔒 Resilience Considerations

Prevents memory overload via bounded queue Protects sinks using rate limiting Ensures failed records are never silently dropped Retry + DLQ guarantees safe handling Thread pool isolates workload spikes

🧪 Assumptions

Input data is well-formed Sinks are simulated Single-node execution No external distributed coordination required

📝 Development Notes

This system was developed using standard technical research and documented engineering patterns. Architectural and implementation decisions were designed and validated to ensure correctness, performance, and resilience.

✅ Summary

This engine demonstrates a production-style streaming pipeline with:

Controlled throughput Parallel fan-out Operational safety Clean modular design It serves as a foundation for scalable real-time data delivery systems.
