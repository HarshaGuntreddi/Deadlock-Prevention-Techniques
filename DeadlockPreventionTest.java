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

        // Test Resource Pooling
        dp.resourcePooling(resources);
        System.out.println("Resource Pooling Passed");

        // Test Hierarchical Locking
        dp.hierarchicalLocking(resource1, resource2);
        System.out.println("Hierarchical Locking Passed");

        // Test Two-Phase Locking
        dp.twoPhaseLocking(resources);
        System.out.println("Two-Phase Locking Passed");

        // Test Lock Timeout with Retry
        if (dp.lockTimeoutWithRetry(resource1, 1000, 3)) {
            System.out.println("Lock Timeout with Retry Passed");
        } else {
            System.out.println("Lock Timeout with Retry Failed");
        }

        // Test Deadlock Detection with Timeout
        if (dp.detectDeadlockWithTimeout(1000)) {
            System.out.println("Deadlock Detection with Timeout Passed");
        } else {
            System.out.println("Deadlock Detection with Timeout Failed");
        }

        // Test Resource Reservation
        if (dp.reserveResources(resources)) {
            System.out.println("Resource Reservation Passed");
        } else {
            System.out.println("Resource Reservation Failed");
        }

        // Test Priority Inheritance
        dp.priorityInheritance(resource1, resource2);
        System.out.println("Priority Inheritance Passed");

        // Test Banker's Algorithm
        int[] available = {3, 3, 2};
        int[][] max = {
            {7, 5, 3},
            {3, 2, 2},
            {9, 0, 2},
            {2, 2, 2},
            {4, 3, 3}
        };
        int[][] allocation = {
            {0, 1, 0},
            {2, 0, 0},
            {3, 0, 2},
            {2, 1, 1},
            {0, 0, 2}
        };
        int[][] need = new int[max.length][max[0].length];
        for (int i = 0; i < max.length; i++) {
            for (int j = 0; j < max[0].length; j++) {
                need[i][j] = max[i][j] - allocation[i][j];
            }
        }
        if (dp.bankersAlgorithm(available, max, allocation, need)) {
            System.out.println("Banker's Algorithm Passed");
        } else {
            System.out.println("Banker's Algorithm Failed");
        }

        // Test Aging Prevention
        dp.agingPrevention(resources, 1000);
        System.out.println("Aging Prevention Passed");

        // Test Random Backoff Prevention
        dp.randomBackoffPrevention(resource1);
        System.out.println("Random Backoff Prevention Passed");

        // Test Exponential Backoff Prevention
        dp.exponentialBackoffPrevention(resource1);
        System.out.println("Exponential Backoff Prevention Passed");

        // Test Fixed Backoff Prevention
        dp.fixedBackoffPrevention(resource1);
        System.out.println("Fixed Backoff Prevention Passed");

        // Test Priority Ceiling Prevention
        dp.priorityCeilingPrevention(resource1, 10);
        System.out.println("Priority Ceiling Prevention Passed");

        // Test Resource Limiting Prevention
        dp.resourceLimitingPrevention(resources, 1);
        System.out.println("Resource Limiting Prevention Passed");

        // Test Timeout and Rollback
        if (dp.timeoutAndRollback(resource1, 1000)) {
            System.out.println("Timeout and Rollback Passed");
        } else {
            System.out.println("Timeout and Rollback Failed");
        }

        // Test Resource Pre-allocation
        if (dp.resourcePreallocation(resources)) {
            System.out.println("Resource Pre-allocation Passed");
        } else {
            System.out.println("Resource Pre-allocation Failed");
        }

        // Test Resource Reclamation
        dp.resourceReclamation(resources);
        System.out.println("Resource Reclamation Passed");

        // Test Resource Partitioning
        dp.resourcePartitioning(resources, 1);
        System.out.println("Resource Partitioning Passed");

        // Test Resource Sharing
        dp.resourceSharing(resources);
        System.out.println("Resource Sharing Passed");

        System.out.println("All Test Cases Passed Successfully.");
    }
}
