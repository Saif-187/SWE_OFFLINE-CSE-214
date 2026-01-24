public class Full implements State {
    @Override
    public boolean tryEnroll(Course course, Student student) {
        System.out.println("Cannot enroll; course is FULL. You may waitlist: " + course.code);
        return false;
    }
    
    @Override
    public boolean addToWaitlist(Course course, Student student) {
        if (student == null) return false;
        
        if (course.getEnrolledStudents().contains(student)) {
            System.out.println("Already enrolled; no need to waitlist: " + student.name + " for " + course.code);
            return false;
        }
        
        if (course.getWaitlistStudents().contains(student)) {
            System.out.println("Already waitlisted: " + student.name + " for " + course.code);
            return false;
        }
        
        course.getWaitlistStudents().add(student);
        System.out.println("Waitlisted: " + student.name + " for " + course.code);
        notifyWaitlisting(course, student);
        return true;
    }
    
    @Override
    public boolean dropStudent(Course course, Student student) {
        boolean changed = false;
        
        if (course.getEnrolledStudents().contains(student)) {
            course.getEnrolledStudents().remove(student);
            System.out.println("Dropped from enrolled: " + student.name + " from " + course.code);
            changed = true;
            notifyDropped(course, student);
            if (!course.getWaitlistStudents().isEmpty() && 
                course.getEnrolledStudents().size() < course.getCapacity()) {
                Student promoted = course.getWaitlistStudents().remove(0);
                course.getEnrolledStudents().add(promoted);
                notifyPromoted(course, promoted);
                
                if (course.getEnrolledStudents().size() < course.getCapacity()) {
                    course.transitionTo(CourseStatus.OPEN);
                    System.out.println(course.code + " status changed to OPEN due to available capacity.");
                }
            }
        } else if (course.getWaitlistStudents().contains(student)) {
            course.getWaitlistStudents().remove(student);
            System.out.println("Removed from waitlist: " + student.name + " for " + course.code);
            changed = true;
            notifyDropped(course, student);
        } else {
            System.out.println(student.name + " is neither enrolled nor waitlisted for " + course.code);
        }
        
        return changed;
    }
    
    @Override
    public boolean canTransitionTo(CourseStatus newStatus) {
        return newStatus == CourseStatus.CLOSED || 
               newStatus == CourseStatus.CANCELLED;
    }
    
    @Override
    public void handleSetCapacity(Course course, int newCapacity) {
        if (newCapacity < 0) newCapacity = 0;
        
        course.setCapacityInternal(newCapacity);
        System.out.println("Setting capacity of " + course.code + " to " + newCapacity);
        if (course.getEnrolledStudents().size() < newCapacity) {
            course.transitionTo(CourseStatus.OPEN);
            System.out.println(course.code + " status changed to OPEN (capacity allows enrollment).");
        } else if (course.getEnrolledStudents().size() == newCapacity) {
            System.out.println(course.code + " remains FULL (at capacity).");
        } else {
            System.out.println(course.code + " over capacity; remains FULL.");
        }
    }
    
    @Override
    public CourseStatus getStatus() {
        return CourseStatus.FULL;
    }
}