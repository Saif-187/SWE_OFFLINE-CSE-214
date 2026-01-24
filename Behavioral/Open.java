public class Open implements State {
    @Override
    public boolean tryEnroll(Course course, Student student) {
        if (student == null) return false;
        
        if (course.getEnrolledStudents().contains(student)) {
            System.out.println("Already enrolled: " + student.name + " in " + course.code);
            return true;
        }
        
        if (course.getEnrolledStudents().size() < course.getCapacity()) {
            course.getEnrolledStudents().add(student);
            System.out.println("Enrolled: " + student.name + " in " + course.code);
            
            if (course.getEnrolledStudents().size() >= course.getCapacity()) {
                course.transitionTo(CourseStatus.FULL);
                System.out.println(course.code + " is now FULL.");
            }
            return true;
        } else {
            course.transitionTo(CourseStatus.FULL);
            System.out.println(course.code + " reached capacity; status set to FULL. Try waitlisting.");
            return false;
        }
    }
    
    @Override
    public boolean addToWaitlist(Course course, Student student) {
        System.out.println("Course is OPEN; try enrolling instead: " + course.code);
        return false;
    }
    
    @Override
    public boolean dropStudent(Course course, Student student) {
        boolean changed = false;
        
        if (course.getEnrolledStudents().contains(student)) {
            course.getEnrolledStudents().remove(student);
            System.out.println("Dropped from enrolled: " + student.name + " from " + course.code);
            changed = true;
        }
        
        return changed;
    }
    
    @Override
    public boolean canTransitionTo(CourseStatus newStatus) {
        return newStatus == CourseStatus.CLOSED || 
               newStatus == CourseStatus.DRAFT || 
               newStatus == CourseStatus.CANCELLED;
    }
    
    @Override
    public void handleSetCapacity(Course course, int newCapacity) {
        if (newCapacity < 0) newCapacity = 0;
        System.out.println("Setting capacity of " + course.code + " to " + newCapacity);
        if (course.getEnrolledStudents().size() < newCapacity) {
            course.setCapacityInternal(newCapacity);
            System.out.println(course.code + " status changed to OPEN (capacity allows enrollment).");
        } else if (course.getEnrolledStudents().size() == newCapacity) {
            course.setCapacityInternal(newCapacity);
            course.transitionTo(CourseStatus.FULL);
            System.out.println(course.code + " status changed to FULL (at capacity).");
        } else {
            course.setCapacityInternal(newCapacity);
            course.transitionTo(CourseStatus.FULL);
            System.out.println(course.code + " over capacity; remains FULL.");
        }
    }
    
    @Override
    public CourseStatus getStatus() {
        return CourseStatus.OPEN;
    }
}

