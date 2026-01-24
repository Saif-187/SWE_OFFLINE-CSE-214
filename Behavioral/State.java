public interface State {
    boolean tryEnroll(Course course, Student student);
    boolean addToWaitlist(Course course, Student student);
    boolean dropStudent(Course course, Student student);
    boolean canTransitionTo(CourseStatus newStatus);
    void handleSetCapacity(Course course, int newCapacity);
    CourseStatus getStatus();
}