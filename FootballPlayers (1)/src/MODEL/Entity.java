package MODEL;

import java.util.Scanner;

/**
 * Base Entity class for all models in the Football Club Management System
 * Provides common functionality for input, output, and comparison
 */
public abstract class Entity {
    
    /**
     * Input method - each subclass implements its own input logic
     */
    public abstract void input(Scanner sc);
    
    /**
     * Output method - display entity information
     */
    public abstract void output();
    
    /**
     * Get unique identifier for the entity
     */
    public abstract int getId();
    
    /**
     * String representation of the entity
     */
    @Override
    public abstract String toString();
    
    /**
     * Compare two entities
     */
    @Override
    public abstract boolean equals(Object obj);
    
    /**
     * Hash code for entity
     */
    @Override
    public abstract int hashCode();
}
