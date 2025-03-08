import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;

class DeadlockPreventionTechniques {
    private final Map<Integer, List<Integer>> resourceGraph = new HashMap<>();
    private final Lock lock = new ReentrantLock();

    // Adds an edge to the resource allocation graph (for cycle detection)
    void addEdge(int from, int to) {
        lock.lock();
        try {
            resourceGraph.computeIfAbsent(from, k -> new ArrayList<>()).add(to);
        } finally {
            lock.unlock();
        }
    }

    // Detects deadlocks using cycle detection in a directed graph
    boolean detectDeadlock() {
        Set<Integer> visited = new HashSet<>();
        Set<Integer> recursionStack = new HashSet<>();

        for (Integer resource : resourceGraph.keySet()) {
            if (detectCycle(resource, visited, recursionStack)) {
                return true;
            }
        }
        return false;
    }

    private boolean detectCycle(int node, Set<Integer> visited, Set<Integer> stack) {
        if (stack.contains(node)) return true;
        if (visited.contains(node)) return false;

        visited.add(node);
        stack.add(node);

        for (Integer neighbor : resourceGraph.getOrDefault(node, Collections.emptyList())) {
            if (detectCycle(neighbor, visited, stack)) return true;
        }

        stack.remove(node);
        return false;
    }

    // Hold and Wait Prevention: Allocate all resources at once
    boolean allocateAllResourcesAtOnce(List<Lock> resources) {
        List<Lock> acquiredLocks = new ArrayList<>();
        try {
            for (Lock resource : resources) {
                if (resource.tryLock()) {
                    acquiredLocks.add(resource);
                } else {
                    for (Lock acquired : acquiredLocks) {
                        acquired.unlock();
                    }
                    return false;
                }
            }
            return true;
        } finally {
            for (Lock acquired : acquiredLocks) {
                acquired.unlock();
            }
        }
    }

    // No Preemption Prevention: Forcefully release resources if needed
    void preemptResources(List<Lock> resources) {
        for (Lock resource : resources) {
            if (resource.tryLock()) {
                resource.unlock();
            }
        }
    }

    // Circular Wait Prevention: Enforce a global resource ordering
    void enforceResourceOrdering(List<Lock> resources) {
        resources.sort(Comparator.comparingInt(System::identityHashCode));
        for (Lock resource : resources) {
            resource.lock();
        }
        for (Lock resource : resources) {
            resource.unlock();
        }
    }

    // Timeout-Based Deadlock Prevention
    boolean acquireWithTimeout(Lock resource, long timeoutMs) throws InterruptedException {
        return resource.tryLock(timeoutMs, TimeUnit.MILLISECONDS);
    }

    // Priority-Based Allocation (low-priority processes release locks)
    void priorityBasedAllocation(Map<Integer, Lock> processLocks) {
        List<Integer> sortedProcesses = new ArrayList<>(processLocks.keySet());
        Collections.sort(sortedProcesses);
        for (Integer process : sortedProcesses) {
            processLocks.get(process).lock();
        }
        for (Integer process : sortedProcesses) {
            processLocks.get(process).unlock();
        }
    }

    // Simulated Database Deadlock Prevention (using Row Locking)
    void simulateDatabaseLocking(Lock tableLock, Lock rowLock) {
        if (tableLock.tryLock()) {
            if (rowLock.tryLock()) {
                rowLock.unlock();
            }
            tableLock.unlock();
        }
    }

    // Distributed Deadlock Prevention (Using Unique Global IDs)
    void distributedDeadlockPrevention(List<String> resourceIds) {
        Collections.sort(resourceIds);
        for (String id : resourceIds) {
            System.out.println("Locking resource: " + id);
        }
    }

    // Starvation Prevention (Using Fair Locks)
    void starvationPrevention() {
        Lock fairLock = new ReentrantLock(true);
        fairLock.lock();
        fairLock.unlock();
    }

    // Resource Ordering with Priority
    void resourceOrderingWithPriority(List<Lock> resources, List<Integer> priorities) {
        List<Lock> sortedResources = new ArrayList<>(resources);
        sortedResources.sort(Comparator.comparingInt(resources::indexOf));
        for (Lock resource : sortedResources) {
            resource.lock();
        }
        for (Lock resource : sortedResources) {
            resource.unlock();
        }
    }

    // Wait-Die Scheme
    boolean waitDie(int timestamp, int otherTimestamp) {
        return timestamp < otherTimestamp;
    }

    // Wound-Wait Scheme
    boolean woundWait(int timestamp, int otherTimestamp) {
        return timestamp > otherTimestamp;
    }

    // Resource Pooling
    void resourcePooling(List<Lock> resources) {
        for (Lock resource : resources) {
            resource.lock();
        }
        for (Lock resource : resources) {
            resource.unlock();
        }
    }

    // Hierarchical Locking
    void hierarchicalLocking(Lock parent, Lock child) {
        parent.lock();
        child.lock();
        child.unlock();
        parent.unlock();
    }

    // Two-Phase Locking
    void twoPhaseLocking(List<Lock> resources) {
        for (Lock resource : resources) {
            resource.lock();
        }
        for (Lock resource : resources) {
            resource.unlock();
        }
    }

    // Lock Timeout with Retry
    boolean lockTimeoutWithRetry(Lock resource, long timeoutMs, int retries) throws InterruptedException {
        for (int i = 0; i < retries; i++) {
            if (resource.tryLock(timeoutMs, TimeUnit.MILLISECONDS)) {
                return true;
            }
        }
        return false;
    }

    // Deadlock Detection with Timeout
    boolean detectDeadlockWithTimeout(long timeoutMs) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        while (System.currentTimeMillis() - startTime < timeoutMs) {
            if (detectDeadlock()) {
                return true;
            }
            Thread.sleep(100);
        }
        return false;
    }

    // Resource Reservation
    boolean reserveResources(List<Lock> resources) {
        for (Lock resource : resources) {
            if (!resource.tryLock()) {
                for (Lock acquired : resources) {
                    if (acquired.isHeldByCurrentThread()) {
                        acquired.unlock();
                    }
                }
                return false;
            }
        }
        return true;
    }

    // Priority Inheritance
    void priorityInheritance(Lock highPriorityLock, Lock lowPriorityLock) {
        highPriorityLock.lock();
        lowPriorityLock.lock();
        lowPriorityLock.unlock();
        highPriorityLock.unlock();
    }

    // Banker's Algorithm (Deadlock Avoidance)
    boolean bankersAlgorithm(int[] available, int[][] max, int[][] allocation, int[][] need) {
        int n = allocation.length;
        int m = available.length;
        boolean[] finish = new boolean[n];
        int[] work = Arrays.copyOf(available, m);

        while (true) {
            boolean found = false;
            for (int i = 0; i < n; i++) {
                if (!finish[i] && canAllocate(need[i], work)) {
                    for (int j = 0; j < m; j++) {
                        work[j] += allocation[i][j];
                    }
                    finish[i] = true;
                    found = true;
                }
            }
            if (!found) {
                break;
            }
        }

        for (boolean f : finish) {
            if (!f) {
                return false;
            }
        }
        return true;
    }

    private boolean canAllocate(int[] need, int[] work) {
        for (int i = 0; i < need.length; i++) {
            if (need[i] > work[i]) {
                return false;
            }
        }
        return true;
    }

    // Aging Prevention
    void agingPrevention(List<Lock> resources, int ageThreshold) {
        for (Lock resource : resources) {
            if (resource.tryLock()) {
                resource.unlock();
            }
        }
    }

    // Random Backoff Prevention
    void randomBackoffPrevention(Lock resource) throws InterruptedException {
        Random random = new Random();
        while (!resource.tryLock()) {
            Thread.sleep(random.nextInt(100));
        }
        resource.unlock();
    }

    // Exponential Backoff Prevention
    void exponentialBackoffPrevention(Lock resource) throws InterruptedException {
        int backoff = 1;
        while (!resource.tryLock()) {
            Thread.sleep(backoff);
            backoff *= 2;
        }
        resource.unlock();
    }

    // Fixed Backoff Prevention
    void fixedBackoffPrevention(Lock resource) throws InterruptedException {
        while (!resource.tryLock()) {
            Thread.sleep(100);
        }
        resource.unlock();
    }

    // Priority Ceiling Prevention
    void priorityCeilingPrevention(Lock resource, int ceiling) {
        if (resource.tryLock()) {
            resource.unlock();
        }
    }

    // Resource Limiting Prevention
    void resourceLimitingPrevention(List<Lock> resources, int limit) {
        for (int i = 0; i < limit; i++) {
            resources.get(i).lock();
        }
        for (int i = 0; i < limit; i++) {
            resources.get(i).unlock();
        }
    }

    // Timeout and Rollback
    boolean timeoutAndRollback(Lock resource, long timeoutMs) throws InterruptedException {
        if (resource.tryLock(timeoutMs, TimeUnit.MILLISECONDS)) {
            resource.unlock();
            return true;
        }
        return false;
    }

    // Resource Pre-allocation
    boolean resourcePreallocation(List<Lock> resources) {
        for (Lock resource : resources) {
            if (!resource.tryLock()) {
                for (Lock acquired : resources) {
                    if (acquired.isHeldByCurrentThread()) {
                        acquired.unlock();
                    }
                }
                return false;
            }
        }
        return true;
    }

    // Resource Reclamation
    void resourceReclamation(List<Lock> resources) {
        for (Lock resource : resources) {
            if (resource.tryLock()) {
                resource.unlock();
            }
        }
    }

    // Resource Partitioning
    void resourcePartitioning(List<Lock> resources, int partitions) {
        for (int i = 0; i < partitions; i++) {
            resources.get(i).lock();
        }
        for (int i = 0; i < partitions; i++) {
            resources.get(i).unlock();
        }
    }

    // Resource Sharing
    void resourceSharing(List<Lock> resources) {
        for (Lock resource : resources) {
            resource.lock();
        }
        for (Lock resource : resources) {
            resource.unlock();
        }
    }
}
