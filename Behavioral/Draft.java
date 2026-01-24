public class Draft implements State {
    @Override
    public boolean tryEnroll(Course course, Student student) {
        System.out.println("Cannot enroll; course is DRAFT (not visible): " + course.code);
        return false;
    }
    
    @Override
    public boolean addToWaitlist(Course course, Student student) {
        System.out.println("Cannot waitlist; course not accepting waitlist: " + course.code);
        return false;
    }
    
    @Override
    public boolean dropStudent(Course course, Student student) {
        return false;
    }
    
    @Override
    public boolean canTransitionTo(CourseStatus newStatus) {
        return newStatus == CourseStatus.OPEN || 
               newStatus == CourseStatus.CLOSED || 
               newStatus == CourseStatus.CANCELLED;
    }
    
    @Override
    public void handleSetCapacity(Course course, int newCapacity) {
        
    }
    
    @Override
    public CourseStatus getStatus() {
        return CourseStatus.DRAFT;
    }
}
