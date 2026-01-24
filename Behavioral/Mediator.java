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
        
        return course.tryEnroll(student);
    }
    public boolean waitlistStudent(Student student, Course course) {
        if (student == null || course == null) return false;
        return course.addToWaitlist(student);
    }
    public boolean dropStudent(Student student, Course course) {
        if (student == null || course == null) return false;
        return course.dropStudent(student);
    }
     public void notifyStudentEnrolled(Student student, Course course) {
        student.addEnrolledCourseDirect(course);
    }
    
    public void notifyStudentWaitlisted(Student student, Course course) {
        student.addWaitlistCourseDirect(course);
    }
    
    public void notifyStudentDropped(Student student, Course course) {
        student.removeCourseDirect(course);
    }
    
    public void notifyWaitlistPromoted(Student student, Course course) {
        student.removeCourseDirect(course);
        student.addEnrolledCourseDirect(course);
        System.out.println("Promoted from waitlist: " + student.name + " into " + course.code);
    }
    
    public void promoteFromWaitlist(Course course, Student student) {
        notifyWaitlistPromoted(student, course);
    }
    
}
