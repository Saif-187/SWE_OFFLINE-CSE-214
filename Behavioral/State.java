public interface State {
    boolean tryEnroll(Course course, Student student);
    boolean addToWaitlist(Course course, Student student);
    boolean dropStudent(Course course, Student student);
    boolean canTransitionTo(CourseStatus newStatus);
    void handleSetCapacity(Course course, int newCapacity);
    CourseStatus getStatus();
    default void notifyEnrollment(Course course, Student student) {
        Mediator.getInstance().notifyStudentEnrolled(student, course);
    }
    
    default void notifyWaitlisting(Course course, Student student) {
        Mediator.getInstance().notifyStudentWaitlisted(student, course);
    }
    
    default void notifyDropped(Course course, Student student) {
        Mediator.getInstance().notifyStudentDropped(student, course);
    }
    
    default void notifyPromoted(Course course, Student student) {
        Mediator.getInstance().notifyWaitlistPromoted(student, course);
    }
    default void handleCourseCancellation(Course course) {
        for (Student student : course.getEnrolledStudents()) {
            notifyDropped(course, student);
        }
        for (Student student : course.getWaitlistStudents()) {
            notifyDropped(course, student);
        }
    }
}