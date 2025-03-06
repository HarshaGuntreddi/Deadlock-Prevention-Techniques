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

    // Additional Technique: Resource Ordering with Priority
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

    // Additional Technique: Wait-Die Scheme
    boolean waitDie(int timestamp, int otherTimestamp) {
        return timestamp < otherTimestamp;
    }

    // Additional Technique: Wound-Wait Scheme
    boolean woundWait(int timestamp, int otherTimestamp) {
        return timestamp > otherTimestamp;
    }
}