# Deadlock Prevention Techniques

## 📌 Description
This Java-based project implements efficient deadlock prevention techniques for multi-threaded applications. It provides algorithms to detect, prevent, and resolve deadlocks using resource ordering, timeout mechanisms, and priority-based allocation, ensuring smooth concurrent execution.This project implements various **deadlock prevention techniques** in Java. It demonstrates strategies to avoid deadlocks in concurrent programming by implementing multiple algorithms and testing them under different scenarios.

## 🔥 Features
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

## 🤝 Contributing
Contributions are welcome! Follow these steps:
1. Fork the project.
2. Create a new branch (`git checkout -b feature-branch`).
3. Commit your changes (`git commit -m 'Add new feature'`).
4. Push to the branch (`git push origin feature-branch`).
5. Open a Pull Request.


## 📧 Contact
For any inquiries, contact [beharshaguntreddi@gmail.com]
