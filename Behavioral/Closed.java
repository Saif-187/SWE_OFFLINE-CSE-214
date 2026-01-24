public class Closed implements State {
    @Override
    public boolean tryEnroll(Course course, Student student) {
        System.out.println("Cannot enroll; course is CLOSED: " + course.code);
        return false;
    }
    
    @Override
    public boolean addToWaitlist(Course course, Student student) {
        System.out.println("Cannot waitlist; course not accepting waitlist: " + course.code);
        return false;
    }
    
    @Override
    public boolean dropStudent(Course course, Student student) {
        boolean changed = false;
        
        if (course.getEnrolledStudents().contains(student)) {
            course.getEnrolledStudents().remove(student);
            System.out.println("Dropped from enrolled: " + student.name + " from " + course.code);
            changed = true;
            notifyDropped(course, student);
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
        return newStatus == CourseStatus.OPEN || 
               newStatus == CourseStatus.DRAFT || 
               newStatus == CourseStatus.CANCELLED;
    }
    
    @Override
    public void handleSetCapacity(Course course, int newCapacity) {
        if (newCapacity < 0) newCapacity = 0;
        course.setCapacityInternal(newCapacity);
        System.out.println("Setting capacity of " + course.code + " to " + newCapacity);
    }
    
    @Override
    public CourseStatus getStatus() {
        return CourseStatus.CLOSED;
    }
}