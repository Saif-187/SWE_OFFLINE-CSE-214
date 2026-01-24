import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Course {
    public final String code;
    public final String title;
    private int capacity;
    private State currentState;
    private final List<Student> enrolled = new ArrayList<>();
    private final LinkedList<Student> waitlist = new LinkedList<>();
    public CourseStatus status;
    // State instances
    private static final State DRAFT_STATE = new Draft();
    private static final State OPEN_STATE = new Open();
    private static final State FULL_STATE = new Full();
    private static final State CLOSED_STATE = new Closed();
    private static final State CANCELLED_STATE = new Cancelled();

    public Course(String code, String title, int capacity, CourseStatus status) {
        this.code = code;
        this.title = title;
        this.capacity = Math.max(0, capacity);
        this.status = status;
        setStateFromStatus(status);
    }
    
    private void setStateFromStatus(CourseStatus status) {
        switch (status) {
            case DRAFT:
                currentState = DRAFT_STATE;
                break;
            case OPEN:
                currentState = OPEN_STATE;
                break;
            case FULL:
                currentState = FULL_STATE;
                break;
            case CLOSED:
                currentState = CLOSED_STATE;
                break;
            case CANCELLED:
                currentState = CANCELLED_STATE;
                break;
        }
    }
    
    public CourseStatus getStatus() {
        return currentState.getStatus();
    }
    
    public void transitionTo(CourseStatus newStatus) {
        this.status = newStatus;
        switch (newStatus) {
            case DRAFT:
                currentState = DRAFT_STATE;
                break;
            case OPEN:
                currentState = OPEN_STATE;
                break;
            case FULL:
                currentState = FULL_STATE;
                break;
            case CLOSED:
                currentState = CLOSED_STATE;
                break;
            case CANCELLED:
                currentState = CANCELLED_STATE;
                break;
        }
    }

    public boolean isVisibleToStudents() {
        return getStatus() != CourseStatus.DRAFT && getStatus() != CourseStatus.CANCELLED;
    }

    public boolean tryEnroll(Student s) {
        return currentState.tryEnroll(this, s);
    }

    public boolean addToWaitlist(Student s) {
        return currentState.addToWaitlist(this, s);
    }

    public boolean dropStudent(Student s) {
        return currentState.dropStudent(this, s);
    }

    public void setCapacity(int newCapacity) {
        currentState.handleSetCapacity(this, newCapacity);
    }
    
    public void setCapacityInternal(int newCapacity) {
        this.capacity = Math.max(0, newCapacity);
    }

    public void setStatusAdmin(CourseStatus newStatus) {
        if (newStatus == null) return;
        if (getStatus() == newStatus) {
            System.out.println("No change: " + code + " already " + getStatus());
            return;
        }
        if (getStatus() == CourseStatus.CANCELLED) {
        if (newStatus == CourseStatus.DRAFT) {
            transitionTo(CourseStatus.DRAFT);
            System.out.println(code + " transitioned CANCELLED -> DRAFT (reinstating course)");
        } else {
            System.out.println("Invalid: CANCELLED can only transition to DRAFT for " + code);
        }
        return;
    }
    if (getStatus() == CourseStatus.FULL && newStatus == CourseStatus.OPEN) {
        System.out.println("Invalid transition from FULL to " + newStatus + " (FULL->OPEN is automatic on drop)");
        return;
    }
        if (currentState.canTransitionTo(newStatus)) {
            switch (getStatus()) {
                case DRAFT:
                    if (newStatus == CourseStatus.OPEN) {
                        transitionTo(CourseStatus.OPEN);
                        System.out.println(code + " transitioned DRAFT -> OPEN");
                    } else if (newStatus == CourseStatus.CLOSED) {
                        transitionTo(CourseStatus.CLOSED);
                        System.out.println(code + " transitioned DRAFT -> CLOSED");
                    } else if (newStatus == CourseStatus.CANCELLED) {
                        cancelCourse();
                    }
                    break;
                case OPEN:
                    if (newStatus == CourseStatus.CLOSED) {
                        transitionTo(CourseStatus.CLOSED);
                        System.out.println(code + " transitioned OPEN -> CLOSED");
                    } else if (newStatus == CourseStatus.DRAFT) {
                        transitionTo(CourseStatus.DRAFT);
                        System.out.println(code + " transitioned OPEN -> DRAFT");
                    } else if (newStatus == CourseStatus.CANCELLED) {
                        cancelCourse();
                    }
                    break;
                case FULL:
                    if (newStatus == CourseStatus.CLOSED) {
                        closeWithRandomWaitlistSelection(capacity);
                    } else if (newStatus == CourseStatus.CANCELLED) {
                        cancelCourse();
                    }else{
                        System.out.println("Invalid transition from FULL to " + newStatus + " (FULL->OPEN is automatic on drop)");
                    }
                    break;
                case CLOSED:
                    if (newStatus == CourseStatus.OPEN) {
                        transitionTo(CourseStatus.OPEN);
                        System.out.println(code + " transitioned CLOSED -> OPEN");
                    } else if (newStatus == CourseStatus.DRAFT) {
                        transitionTo(CourseStatus.DRAFT);
                        System.out.println(code + " transitioned CLOSED -> DRAFT");
                    } else if (newStatus == CourseStatus.CANCELLED) {
                        cancelCourse();
                    }
                    break;
                case CANCELLED:
                    if (newStatus == CourseStatus.DRAFT) {
                        transitionTo(CourseStatus.DRAFT);
                        System.out.println(code + " transitioned CANCELLED -> DRAFT (reinstating course)");
                    } else {
                        System.out.println("Invalid: CANCELLED can only transition to DRAFT for " + code);
                    }
                    break;
            }
        } else {
        System.out.println("Invalid transition from " + getStatus() + " to " + newStatus);
        }
    }

    public void setStatusAdminInteractive(CourseStatus newStatus, Scanner scanner) {
        if (newStatus == null) return;
        if (getStatus() == newStatus) {
            System.out.println("No change: " + code + " already " + getStatus());
            return;
        }

        if (getStatus() == CourseStatus.FULL && newStatus == CourseStatus.CLOSED) {
            if (!waitlist.isEmpty()) {
                System.out.println(code + " has " + waitlist.size() + " student(s) on waitlist.");
                System.out.print("Do you want to increase capacity before closing? (Enter new capacity, or 0 to not increase): ");
                try {
                    int newCapacity = Integer.parseInt(scanner.nextLine().trim());
                    if (newCapacity > 0) {
                        if (newCapacity > capacity) {
                            capacity = newCapacity;
                            System.out.println("Capacity increased to " + newCapacity);
                            closeWithRandomWaitlistSelection(newCapacity);
                        } else {
                            System.out.println("New capacity must be greater than current capacity (" + capacity + "). No change.");
                            closeWithRandomWaitlistSelection(capacity);
                        }
                    } else {
                        System.out.println("No capacity increase.");
                        closeWithRandomWaitlistSelection(capacity);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Closing without capacity increase.");
                    closeWithRandomWaitlistSelection(capacity);
                }
            } else {
                closeWithRandomWaitlistSelection(capacity);
            }
            return;
        }

        setStatusAdmin(newStatus);
    }

    private void cancelCourse() {
        transitionTo(CourseStatus.CANCELLED);
        System.out.println(code + " has been CANCELLED. All students dropped and waitlist cleared.");
        System.out.println();
        currentState.handleCourseCancellation(this);
    }

    private void closeWithRandomWaitlistSelection(int targetCapacity) {
        transitionTo(CourseStatus.CLOSED);
        System.out.println(code + " transitioned FULL -> CLOSED");
        
        if (!waitlist.isEmpty()) {
            int availableSlots = targetCapacity - enrolled.size();
            if (availableSlots > 0) {
                Random random = new Random();
                List<Student> waitlistCopy = new ArrayList<>(waitlist);
                int promotionCount = Math.min(availableSlots, waitlistCopy.size());
                
                System.out.println("Randomly selecting " + promotionCount + " student(s) from waitlist:");
                for (int i = 0; i < promotionCount; i++) {
                    int randomIndex = random.nextInt(waitlistCopy.size());
                    Student promoted = waitlistCopy.remove(randomIndex);
                    waitlist.remove(promoted);
                    enrolled.add(promoted);
                    currentState.notifyPromoted(this, promoted);
                    System.out.println("  Randomly selected: " + promoted.name + " for " + code);
                }
            }
        }
    }

    public void printRoster() {
        System.out.println("Roster for " + code + " - " + title + " (" + getStatus() + ", cap=" + capacity + "):");
        if (enrolled.isEmpty()) {
            System.out.println("  [no enrolled]");
        } else {
            for (Student s : enrolled) {
                System.out.println("  " + s.id + " - " + s.name);
            }
        }
    }

    public void printWaitlist() {
        System.out.println("Waitlist for " + code + ":");
        if (waitlist.isEmpty()) {
            System.out.println("  [no waitlisted]");
        } else {
            for (Student s : waitlist) {
                System.out.println("  " + s.id + " - " + s.name);
            }
        }
    }

    public int getCapacity() { return capacity; }
    public int getEnrolledCount() { return enrolled.size(); }
    public int getWaitlistCount() { return waitlist.size(); }
    public List<Student> getEnrolledStudents() { return enrolled; }
    public LinkedList<Student> getWaitlistStudents() { return waitlist; }
    
    
}