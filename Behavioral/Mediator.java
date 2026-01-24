public class Mediator {
    private static Mediator instance;
    private RegistrarSystem registrar;
    private Mediator() {

    }
    public static Mediator getInstance() {
        if (instance == null) {
            instance = new Mediator();
        }
        return instance;
    }
    public void setRegistrar(RegistrarSystem registrar) {
        this.registrar = registrar;
    }
     public boolean enrollStudent(Student student, Course course) {
        if (student == null || course == null) return false;
        
        if (!course.isVisibleToStudents()) {
            System.out.println("Course " + course.code + " is not available for student enrollment.");
            return false;
        }
        
        if (student.getEnrolledCourses().contains(course)) {
            System.out.println(student.name + " is already enrolled in " + course.code);
            return true;
        }
        
        boolean success = course.tryEnroll(student);
        
        if (success) {
            student.addEnrolledCourseDirect(course);
        }
        
        return success;
    }
    public boolean waitlistStudent(Student student, Course course) {
        if (student == null || course == null) return false;
        
        if (!course.isVisibleToStudents()) {
            System.out.println("Course " + course.code + " is not available.");
            return false;
        }
        
        if (student.getWaitlistedCourses().contains(course)) {
            System.out.println(student.name + " is already waitlisted for " + course.code);
            return false;
        }
        
        if (student.getEnrolledCourses().contains(course)) {
            System.out.println(student.name + " is already enrolled in " + course.code + "; cannot waitlist.");
            return false;
        }
        
        boolean success = course.addToWaitlist(student);
        
        if (success) {
            student.addWaitlistCourseDirect(course);
        }
        
        return success;
    }
    public boolean dropStudent(Student student, Course course) {
        if (student == null || course == null) return false;
        
        boolean success = course.dropStudent(student);
        
        if (success) {
            student.removeCourseDirect(course);
        }
        
        return success;
    }
    public void promoteFromWaitlist(Course course, Student student) {
        if (student == null || course == null) return;
        
        student.removeCourseDirect(course);
        student.addEnrolledCourseDirect(course);
        
        System.out.println("Promoted from waitlist: " + student.name + " into " + course.code);
    }
     public void handleCourseCancellation(Course course) {
        if (course == null) return;
        
        for (Student student : course.getEnrolledStudents()) {
            student.removeCourseDirect(course);
        }
        
        for (Student student : course.getWaitlistStudents()) {
            student.removeCourseDirect(course);
        }
        
        System.out.println(course.code + " has been CANCELLED. All students dropped and waitlist cleared.");
    }
}
