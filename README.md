# Deadlock Prevention Techniques

##  Description
This Java-based project implements efficient deadlock prevention techniques for multi-threaded applications. It provides algorithms to detect, prevent, and resolve deadlocks using resource ordering, timeout mechanisms, and priority-based allocation, ensuring smooth concurrent execution.This project implements various **deadlock prevention techniques** in Java. It demonstrates strategies to avoid deadlocks in concurrent programming by implementing multiple algorithms and testing them under different scenarios.

##  Features
- **Hold and Wait Prevention**: Allocates all required resources at once.
- **Circular Wait Prevention**: Enforces resource ordering to prevent circular dependencies.
- **Timeout-Based Deadlock Prevention**: Uses timeouts while acquiring locks to avoid indefinite waiting.
- **Priority-Based Allocation**: Allocates resources based on process priority.
- **Database Locking Simulation**: Simulates database deadlock prevention using row and table locking.
- **Distributed Deadlock Prevention**: Implements unique global resource ordering for distributed systems.
- **Starvation Prevention**: Uses fair locks to ensure resource fairness.
- **Resource Ordering with Priority**: Assigns resource locks based on predefined priorities.
- **Wait-Die & Wound-Wait Schemes**: Implements classical deadlock avoidance strategies.
- **Deadlock Detection Algorithm**: Uses cycle detection in a resource allocation graph.
- **Resource Pooling**: Manages resources in a pool to prevent deadlocks.
- **Hierarchical Locking**: Acquires locks in a hierarchical manner.
- **Two-Phase Locking**: Acquires and releases locks in two phases.
- **Lock Timeout with Retry**: Retries acquiring locks after a timeout.
- **Deadlock Detection with Timeout**: Detects deadlocks within a specified timeout.
- **Resource Reservation**: Reserves resources before allocation.
- **Priority Inheritance**: Low-priority processes inherit the priority of high-priority processes.
- **Banker's Algorithm**: Ensures safe resource allocation to avoid deadlocks.
- **Aging Prevention**: Prevents starvation by aging processes.
- **Random Backoff Prevention**: Uses random backoff to avoid contention.
- **Exponential Backoff Prevention**: Uses exponential backoff to avoid contention.
- **Fixed Backoff Prevention**: Uses fixed backoff to avoid contention.
- **Priority Ceiling Prevention**: Uses priority ceilings to prevent priority inversion.
- **Resource Limiting Prevention**: Limits the number of resources to prevent over-allocation.
- **Timeout and Rollback**: Releases locks if a timeout occurs.
- **Resource Pre-allocation**: Pre-allocates resources to prevent deadlocks.
- **Resource Reclamation**: Reclaims resources after use.
- **Resource Partitioning**: Partitions resources to prevent contention.
- **Resource Sharing**: Shares resources among processes.


## 🛠️ Technologies Used
- **Java**: Core programming language.
- **Concurrency Utilities**: Java's `Lock` and `ReentrantLock` for thread synchronization.
- **Graph Algorithms**: Used for deadlock detection.

## 🚀 How to Run the Project
1. **Clone the repository**:
   ```bash
   git clone https://github.com/HarshaGuntreddi/Deadlock-Prevention-Techniques.git
   ```
2. **Navigate to the project directory**:
   ```bash
   cd Deadlock-Prevention-Techniques
   ```
3. **Compile the Java files**:
   ```bash
   javac DeadlockPreventionTechniques.java DeadlockPreventionTest.java
   ```
4. **Run the test class**:
   ```bash
   java DeadlockPreventionTest
   ```

##  Contributing
Contributions are welcome! Follow these steps:
1. Fork the project.
2. Create a new branch (`git checkout -b feature-branch`).
3. Commit your changes (`git commit -m 'Add new feature'`).
4. Push to the branch (`git push origin feature-branch`).
5. Open a Pull Request.


## 📧 Contact
For any inquiries, contact [beharshaguntreddi@gmail.com]
