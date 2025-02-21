package mona.task;

import java.util.logging.Logger;

/**
 * Represents the priority of a task.
 */
public enum TaskPriority {
    HIGH(1, "[HIGH]"),
    MEDIUM(2, "[MEDIUM]"),
    LOW(3, "[LOW]");

    private static final Logger logger = Logger.getLogger(TaskPriority.class.getName());
    private final int priorityLevel;
    private final String label;

    /**
     * Creates a new task priority.
     * @param i the priority level
     * @param s the label
     */
    TaskPriority(int i, String s) {
        this.priorityLevel = i;
        this.label = s;
    }

    /**
     * Returns a string representation of the task priority.
     * @return the string representation of the task priority
     */
    @Override
    public String toString() {
        return label;
    }

    /**
     * Returns the priority level of the task.
     * @return the priority level
     */
    public int getPriorityLevel() {
        return priorityLevel;
    }

    /**
     * Returns a task priority based on the given priority level.
     * @param priorityLevel the priority level
     * @return the task priority
     */
    public static TaskPriority fromPriorityLevel(int priorityLevel) {
        try {
            switch (priorityLevel) {
            case 1:
                return HIGH;
            case 2:
                return MEDIUM;
            case 3:
                return LOW;
            default:
                throw new IllegalArgumentException("Invalid priority level: " + priorityLevel);
            }
        } catch (IllegalArgumentException e) {
            logger.warning("Invalid priority level: " + priorityLevel + ". Defaulting to LOW.");
            return LOW;
        }
    }
}
