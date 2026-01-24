import java.util.ArrayList;
import java.util.List;

public class Cancelled implements State {
    @Override
    public boolean tryEnroll(Course course, Student student) {
        System.out.println("Cannot enroll; course is CANCELLED: " + course.code);
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
        return newStatus == CourseStatus.DRAFT;
    }
    
    @Override
    public void handleSetCapacity(Course course, int newCapacity) {
        System.out.println("Course is CANCELLED; capacity change has no effect.");
    }
    
    @Override
    public CourseStatus getStatus() {
        return CourseStatus.CANCELLED;
    }
    @Override
    public void handleCourseCancellation(Course course) {
        
        List<Student> enrolledCopy = new ArrayList<>(course.getEnrolledStudents());
        List<Student> waitlistCopy = new ArrayList<>(course.getWaitlistStudents());
        
        for (Student student : enrolledCopy) {
            notifyDropped(course, student);
        }
        for (Student student : waitlistCopy) {
            notifyDropped(course, student);
        }
        
        course.getEnrolledStudents().clear();
        course.getWaitlistStudents().clear();
        
        
    }
}