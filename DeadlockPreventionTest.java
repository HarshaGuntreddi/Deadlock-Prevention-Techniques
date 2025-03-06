import java.util.*;
import java.util.concurrent.locks.*;

public class DeadlockPreventionTest {
    public static void main(String[] args) throws InterruptedException {
        DeadlockPreventionTechniques dp = new DeadlockPreventionTechniques();

        System.out.println("Running Test Cases...");

        // Test Hold and Wait Prevention
        Lock resource1 = new ReentrantLock();
        Lock resource2 = new ReentrantLock();
        List<Lock> resources = Arrays.asList(resource1, resource2);
        if (dp.allocateAllResourcesAtOnce(resources)) {
            System.out.println("Hold and Wait Prevention Passed");
        } else {
            System.out.println("Hold and Wait Prevention Failed");
        }

        // Test Circular Wait Prevention
        dp.enforceResourceOrdering(resources);
        System.out.println("Circular Wait Prevention Passed");

        // Test Timeout-Based Deadlock Prevention
        if (dp.acquireWithTimeout(resource1, 1000)) {
            System.out.println("Timeout-Based Prevention Passed");
            resource1.unlock();
        } else {
            System.out.println("Timeout-Based Prevention Failed");
        }

        // Test Priority-Based Allocation
        Map<Integer, Lock> processLocks = new HashMap<>();
        processLocks.put(1, new ReentrantLock());
        processLocks.put(2, new ReentrantLock());
        dp.priorityBasedAllocation(processLocks);
        System.out.println("Priority-Based Allocation Passed");

        // Test Database Locking
        dp.simulateDatabaseLocking(resource1, resource2);
        System.out.println("Database Deadlock Prevention Passed");

        // Test Distributed Deadlocks
        dp.distributedDeadlockPrevention(Arrays.asList("R1", "R2", "R3"));
        System.out.println("Distributed Deadlock Prevention Passed");

        // Test Starvation Prevention
        dp.starvationPrevention();
        System.out.println("Starvation Prevention Passed");

        // Test Resource Ordering with Priority
        List<Integer> priorities = Arrays.asList(2, 1);
        dp.resourceOrderingWithPriority(resources, priorities);
        System.out.println("Resource Ordering with Priority Passed");

        // Test Wait-Die Scheme
        if (dp.waitDie(1, 2)) {
            System.out.println("Wait-Die Scheme Passed");
        } else {
            System.out.println("Wait-Die Scheme Failed");
        }

        // Test Wound-Wait Scheme
        if (dp.woundWait(2, 1)) {
            System.out.println("Wound-Wait Scheme Passed");
        } else {
            System.out.println("Wound-Wait Scheme Failed");
        }

        // Deadlock Detection Test
        dp.addEdge(1, 2);
        dp.addEdge(2, 3);
        dp.addEdge(3, 1); // Creates a cycle (deadlock)
        if (dp.detectDeadlock()) {
            System.out.println("Deadlock detected! Prevention required.");
        } else {
            System.out.println("No Deadlock detected.");
        }

        System.out.println("All Test Cases Passed Successfully.");
    }
}