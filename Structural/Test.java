public class Test {
    public static void main(String[] args) {
        System.out.println("=".repeat(70));
        System.out.println("COMPREHENSIVE TESTING OF E-LEARNING CART SYSTEM");
        System.out.println("=".repeat(70));
        
        // Test 1: Basic product creation and pricing
        testBasicProductPricing();
        
        // Test 2: Test AbstractProduct hierarchy
        testAbstractProductHierarchy();
        
        // Test 3: Test Module with PracticeSet and MentorSupport decorators
        testModuleDecorators();
        
        // Test 4: Test Course with multiple lessons
        testCourseWithLessons();
        
        // Test 5: Test type safety in composites
        testTypeSafety();
        
        // Test 6: Test cart with no discounts
        testCartWithNoDiscounts();
        
        // Test 7: Test Developing Country discount
        testDevelopingCountryDiscount();
        
        // Test 8: Test Duration discount (threshold 5+ hours)
        testDurationDiscount();
        
        // Test 9: Test MultiModule discount (2+ modules)
        testMultiModuleDiscount();
        
        // Test 10: Test multiple discounts combined
        testMultipleDiscountsCombined();
        
        // Test 11: Test edge cases - exactly at thresholds
        testThresholdEdgeCases();
        
        // Test 12: Test invalid product addition
        testInvalidProductAddition();
        
        // Test 13: Test cart operations (add, remove, clear)
        testCartOperations();
        
        // Test 14: Test complex scenario - Module with decorators inside cart
        testComplexScenarioWithDecorators();
        
        // Test 15: Test all discounts simultaneously
        testAllDiscountsSimultaneously();
        
        // Test 16: Test receipt generation for various scenarios
        testReceiptGeneration();
        
        // Test 17: Test polymorphism with AbstractProduct
        testPolymorphism();
        
        // Test 18: Test decorator chaining
        testDecoratorChaining();
    }
    
    private static void testBasicProductPricing() {
        System.out.println("\n" + "-".repeat(70));
        System.out.println("TEST 1: Basic Product Pricing");
        System.out.println("-".repeat(70));
        
        Lesson lesson = new Lesson("Java Basics", 49.99, 3);
        System.out.println("Lesson: " + lesson.getName());
        System.out.println("  Price: $" + lesson.getPrice());
        System.out.println("  Duration: " + lesson.getDuration() + " hours");
        lesson.displayInfo();
    }
    
    private static void testAbstractProductHierarchy() {
        System.out.println("\n" + "-".repeat(70));
        System.out.println("TEST 2: AbstractProduct Hierarchy");
        System.out.println("-".repeat(70));
        
        // Create products using polymorphism
        AbstractProduct product1 = new Course("Polymorphism Course");
        AbstractProduct product2 = new Module("Polymorphism Module");
        
        System.out.println("Course as AbstractProduct: " + product1.getName());
        System.out.println("Module as AbstractProduct: " + product2.getName());
        System.out.println("Both extend AbstractProduct correctly!");
    }
    
    private static void testModuleDecorators() {
        System.out.println("\n" + "-".repeat(70));
        System.out.println("TEST 3: Module Decorators (PracticeSet and MentorSupport)");
        System.out.println("-".repeat(70));
        
        // Create base module with lessons
        Module baseModule = new Module("Java Programming Fundamentals");
        Lesson lesson1 = new Lesson("Variables", 20.0, 1);
        Lesson lesson2 = new Lesson("Loops", 25.0, 1.5);
        Lesson lesson3 = new Lesson("Methods", 30.0, 2);
        
        // Create Courses first (since Module only accepts Courses)
        Course course1 = new Course("Variables Course");
        course1.addLesson(lesson1);
        
        Course course2 = new Course("Loops Course");
        course2.addLesson(lesson2);
        
        Course course3 = new Course("Methods Course");
        course3.addLesson(lesson3);
        
        baseModule.addCourse(course1);
        baseModule.addCourse(course2);
        baseModule.addCourse(course3);
        
        System.out.println("Base Module:");
        System.out.println("  Name: " + baseModule.getName());
        System.out.println("  Base Price: $" + baseModule.getPrice());
        System.out.println("  Base Duration: " + baseModule.getDuration() + " hours");
        
        // Add PracticeSet decorator
        PracticeSet withPracticeSet = new PracticeSet(baseModule);
        System.out.println("\nWith PracticeSet (+$10):");
        System.out.println("  Price: $" + withPracticeSet.getPrice());
        System.out.println("  Expected: $" + (baseModule.getPrice() + 10));
        withPracticeSet.displayInfo();
        
        // Add MentorSupport decorator
        MentorSupport withMentor = new MentorSupport(baseModule);
        System.out.println("\nWith MentorSupport (+$20):");
        System.out.println("  Price: $" + withMentor.getPrice());
        System.out.println("  Expected: $" + (baseModule.getPrice() + 20));
        withMentor.displayInfo();
        
        // Test that decorated modules maintain AbstractModule type
        System.out.println("\nType Test:");
        System.out.println("  Is PracticeSet an AbstractModule? " + (withPracticeSet instanceof AbstractModule));
        System.out.println("  Is MentorSupport an AbstractModule? " + (withMentor instanceof AbstractModule));
    }
    
    private static void testDecoratorChaining() {
        System.out.println("\n" + "-".repeat(70));
        System.out.println("TEST 18: Decorator Chaining");
        System.out.println("-".repeat(70));
        
        // Create base module
        Module baseModule = new Module("Java Programming Fundamentals");
        Course course = new Course("Main Course");
        course.addLesson(new Lesson("Main Lesson", 50.0, 2));
        baseModule.addCourse(course);
        
        System.out.println("Base Module Price: $" + baseModule.getPrice());
        
        // Chain decorators - NOW WORKS with AbstractModule!
        System.out.println("\nChaining decorators (MentorSupport then PracticeSet):");
        AbstractModule withMentor = new MentorSupport(baseModule);
        AbstractModule withBoth = new PracticeSet(withMentor);
        
        System.out.println("  After MentorSupport (+$20): $" + withMentor.getPrice());
        System.out.println("  After both (+$30): $" + withBoth.getPrice());
        System.out.println("  Expected: $" + (baseModule.getPrice() + 30));
        
        // Try reverse order
        System.out.println("\nChaining decorators (PracticeSet then MentorSupport):");
        AbstractModule withPractice = new PracticeSet(baseModule);
        AbstractModule withBothReverse = new MentorSupport(withPractice);
        
        System.out.println("  After PracticeSet (+$10): $" + withPractice.getPrice());
        System.out.println("  After both (+$30): $" + withBothReverse.getPrice());
        System.out.println("  Expected: $" + (baseModule.getPrice() + 30));
        
        // Display info for chained decorator
        System.out.println("\nDisplay info for chained decorator:");
        withBoth.displayInfo();
    }
    
    private static void testCourseWithLessons() {
        System.out.println("\n" + "-".repeat(70));
        System.out.println("TEST 4: Course with Multiple Lessons");
        System.out.println("-".repeat(70));
        
        Course javaCourse = new Course("Complete Java Masterclass");
        
        Lesson lesson1 = new Lesson("Introduction", 0, 0.5);
        Lesson lesson2 = new Lesson("Variables & Data Types", 29.99, 2);
        Lesson lesson3 = new Lesson("OOP Concepts", 39.99, 3);
        Lesson lesson4 = new Lesson("Exceptions", 19.99, 1.5);
        
        javaCourse.addLesson(lesson1);
        javaCourse.addLesson(lesson2);
        javaCourse.addLesson(lesson3);
        javaCourse.addLesson(lesson4);
        
        System.out.println("Course: " + javaCourse.getName());
        System.out.println("  Number of lessons: " + javaCourse.getLessons().size());
        System.out.println("  Total Price: $" + javaCourse.getPrice());
        System.out.println("  Total Duration: " + javaCourse.getDuration() + " hours");
        System.out.println("  Individual lesson prices: $0 + $29.99 + $39.99 + $19.99 = $" + 
                          (0 + 29.99 + 39.99 + 19.99));
        javaCourse.displayInfo();
    }
    
    private static void testTypeSafety() {
        System.out.println("\n" + "-".repeat(70));
        System.out.println("TEST 5: Type Safety in Composites");
        System.out.println("-".repeat(70));
        
        Course course = new Course("Test Course");
        Module module = new Module("Test Module");
        
        System.out.println("Testing type safety (should throw exceptions):");
        
        // This should work
        try {
            course.addLesson(new Lesson("Valid Lesson", 10.0, 1));
            System.out.println("✅ Adding Lesson to Course: SUCCESS");
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Unexpected error: " + e.getMessage());
        }
        
        // This should fail - can't add Module to Course
        try {
            course.addLesson(module);
            System.out.println("❌ Should not allow adding Module to Course");
        } catch (IllegalArgumentException e) {
            System.out.println("✅ Course rejected Module: " + e.getMessage());
        }
        
        // This should work
        try {
            module.addCourse(course);
            System.out.println("✅ Adding Course to Module: SUCCESS");
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Unexpected error: " + e.getMessage());
        }
        
        // This should fail - can't add Lesson directly to Module
        try {
            Lesson lesson = new Lesson("Invalid", 10.0, 1);
            module.addCourse(lesson);
            System.out.println("❌ Should not allow adding Lesson directly to Module");
        } catch (IllegalArgumentException e) {
            System.out.println("✅ Module rejected Lesson: " + e.getMessage());
        }
    }
    
    private static void testCartWithNoDiscounts() {
        System.out.println("\n" + "-".repeat(70));
        System.out.println("TEST 6: Cart with No Discounts");
        System.out.println("-".repeat(70));
        
        Cart cart = new Cart();
        
        Lesson lesson = new Lesson("Python Basics", 39.99, 2);
        Course course = new Course("Web Development");
        course.addLesson(new Lesson("HTML", 19.99, 1));
        course.addLesson(new Lesson("CSS", 24.99, 1.5));
        
        cart.addItem(lesson);
        cart.addItem(course);
        
        System.out.println("Cart with 1 lesson and 1 course:");
        System.out.println("  Total items: " + cart.getItems().size());
        System.out.println("  Total modules: " + cart.totalModules());
        System.out.println("  Total price before discount: $" + cart.getPrice());
        System.out.println("  Total duration: " + cart.getDuration() + " hours");
        
        cart.generateReceipt();
    }
    
    private static void testDevelopingCountryDiscount() {
        System.out.println("\n" + "-".repeat(70));
        System.out.println("TEST 7: Developing Country Discount (10%)");
        System.out.println("-".repeat(70));
        
        // Test with developing country flag = true
        Cart cart1 = new Cart();
        cart1.setFromDevelopingCountry(true);
        cart1.addItem(new Lesson("Basic Math", 100.0, 1));
        
        System.out.println("Scenario 1: Developing country = true");
        System.out.println("  Base price: $100");
        System.out.println("  Expected discount: $10");
        cart1.generateReceipt();
        
        // Test with developing country flag = false
        Cart cart2 = new Cart();
        cart2.setFromDevelopingCountry(false);
        cart2.addItem(new Lesson("Basic Math", 100.0, 1));
        
        System.out.println("\nScenario 2: Developing country = false");
        System.out.println("  Base price: $100");
        System.out.println("  Expected discount: $0");
        cart2.generateReceipt();
    }
    
    private static void testDurationDiscount() {
        System.out.println("\n" + "-".repeat(70));
        System.out.println("TEST 8: Duration Discount (12% for 5+ hours)");
        System.out.println("-".repeat(70));
        
        // Test below threshold (4 hours)
        Cart cart1 = new Cart();
        cart1.addItem(new Lesson("Short Course", 100.0, 2));
        cart1.addItem(new Lesson("Another Short", 50.0, 2));
        
        System.out.println("Scenario 1: Duration = 4 hours (below threshold)");
        System.out.println("  Base price: $150");
        System.out.println("  Duration: " + cart1.getDuration() + " hours");
        System.out.println("  Expected discount: $0");
        cart1.generateReceipt();
        
        // Test exactly at threshold (5 hours)
        Cart cart2 = new Cart();
        cart2.addItem(new Lesson("Medium Course", 100.0, 3));
        cart2.addItem(new Lesson("Another Medium", 50.0, 2));
        
        System.out.println("\nScenario 2: Duration = 5 hours (at threshold)");
        System.out.println("  Base price: $150");
        System.out.println("  Duration: " + cart2.getDuration() + " hours");
        System.out.println("  Expected discount: $18");
        cart2.generateReceipt();
        
        // Test above threshold (7 hours)
        Cart cart3 = new Cart();
        cart3.addItem(new Lesson("Long Course", 120.0, 4));
        cart3.addItem(new Lesson("Another Long", 80.0, 3));
        
        System.out.println("\nScenario 3: Duration = 7 hours (above threshold)");
        System.out.println("  Base price: $200");
        System.out.println("  Duration: " + cart3.getDuration() + " hours");
        System.out.println("  Expected discount: $24");
        cart3.generateReceipt();
    }
    
    private static void testMultiModuleDiscount() {
        System.out.println("\n" + "-".repeat(70));
        System.out.println("TEST 9: MultiModule Discount (15% for 2+ modules)");
        System.out.println("-".repeat(70));
        
        // Test with 1 module
        Cart cart1 = new Cart();
        Module module1 = new Module("Single Module");
        Course course1 = new Course("Course 1");
        course1.addLesson(new Lesson("Lesson 1", 50.0, 1));
        module1.addCourse(course1);
        cart1.addItem(module1);
        
        System.out.println("Scenario 1: 1 module (below threshold)");
        System.out.println("  Modules count: " + cart1.totalModules());
        System.out.println("  Base price: $" + cart1.getPrice());
        System.out.println("  Expected discount: $0");
        cart1.generateReceipt();
        
        // Test with 2 modules (at threshold)
        Cart cart2 = new Cart();
        
        Module module2a = new Module("Module A");
        Course course2a = new Course("Course A1");
        course2a.addLesson(new Lesson("A1", 30.0, 1));
        module2a.addCourse(course2a);
        
        Module module2b = new Module("Module B");
        Course course2b = new Course("Course B1");
        course2b.addLesson(new Lesson("B1", 40.0, 2));
        module2b.addCourse(course2b);
        
        cart2.addItem(module2a);
        cart2.addItem(module2b);
        
        System.out.println("\nScenario 2: 2 modules (at threshold)");
        System.out.println("  Modules count: " + cart2.totalModules());
        System.out.println("  Base price: $70");
        System.out.println("  Expected discount: $10.5");
        cart2.generateReceipt();
        
        // Test with 3 modules (above threshold)
        Cart cart3 = new Cart();
        
        Module module3a = new Module("Module X");
        Course course3a = new Course("Course X1");
        course3a.addLesson(new Lesson("X1", 25.0, 1));
        module3a.addCourse(course3a);
        
        Module module3b = new Module("Module Y");
        Course course3b = new Course("Course Y1");
        course3b.addLesson(new Lesson("Y1", 35.0, 2));
        module3b.addCourse(course3b);
        
        Module module3c = new Module("Module Z");
        Course course3c = new Course("Course Z1");
        course3c.addLesson(new Lesson("Z1", 40.0, 1.5));
        module3c.addCourse(course3c);
        
        cart3.addItem(module3a);
        cart3.addItem(module3b);
        cart3.addItem(module3c);
        
        System.out.println("\nScenario 3: 3 modules (above threshold)");
        System.out.println("  Modules count: " + cart3.totalModules());
        System.out.println("  Base price: $100");
        System.out.println("  Expected discount: $15");
        cart3.generateReceipt();
    }
    
    private static void testMultipleDiscountsCombined() {
        System.out.println("\n" + "-".repeat(70));
        System.out.println("TEST 10: Multiple Discounts Combined");
        System.out.println("-".repeat(70));
        
        Cart cart = new Cart();
        cart.setFromDevelopingCountry(true);
        
        // Add items to trigger multiple discounts
        Module module1 = new Module("Module 1");
        Course course1 = new Course("Course 1.1");
        course1.addLesson(new Lesson("Lesson 1.1", 40.0, 2));
        Course course2 = new Course("Course 1.2");
        course2.addLesson(new Lesson("Lesson 1.2", 30.0, 1.5));
        module1.addCourse(course1);
        module1.addCourse(course2);
        
        Module module2 = new Module("Module 2");
        Course course3 = new Course("Course 2.1");
        course3.addLesson(new Lesson("Lesson 2.1", 50.0, 2.5));
        module2.addCourse(course3);
        
        cart.addItem(module1);
        cart.addItem(module2);
        
        System.out.println("Cart with:");
        System.out.println("  - Developing country: true");
        System.out.println("  - Duration: " + cart.getDuration() + " hours (>=5)");
        System.out.println("  - Modules: " + cart.totalModules() + " (>=2)");
        System.out.println("  Base price: $" + cart.getPrice());
        System.out.println("  All 3 discounts should apply!");
        
        cart.generateReceipt();
    }
    
    private static void testThresholdEdgeCases() {
        System.out.println("\n" + "-".repeat(70));
        System.out.println("TEST 11: Edge Cases - Exactly at Thresholds");
        System.out.println("-".repeat(70));
        
        // Test exactly 5 hours for duration discount
        Cart cart1 = new Cart();
        cart1.addItem(new Lesson("Edge Case 1", 100.0, 5));
        System.out.println("Edge Case: Duration exactly 5 hours");
        cart1.generateReceipt();
        
        // Test exactly 2 modules for multi-module discount
        Cart cart2 = new Cart();
        
        Module m1 = new Module("M1");
        Course cm1 = new Course("CM1");
        cm1.addLesson(new Lesson("L1", 50.0, 1));
        m1.addCourse(cm1);
        
        Module m2 = new Module("M2");
        Course cm2 = new Course("CM2");
        cm2.addLesson(new Lesson("L2", 50.0, 1));
        m2.addCourse(cm2);
        
        cart2.addItem(m1);
        cart2.addItem(m2);
        System.out.println("\nEdge Case: Exactly 2 modules");
        cart2.generateReceipt();
        
        // Test zero items cart
        Cart cart3 = new Cart();
        cart3.setFromDevelopingCountry(true);
        System.out.println("\nEdge Case: Empty cart with developing country flag true");
        cart3.generateReceipt();
    }
    
    private static void testInvalidProductAddition() {
        System.out.println("\n" + "-".repeat(70));
        System.out.println("TEST 12: Invalid Product Addition");
        System.out.println("-".repeat(70));
        
        Cart cart = new Cart();
        
        // Create an anonymous class that's not Lesson, Course, or Module
        Product invalidProduct = new Product() {
            @Override
            public String getName() { return "Invalid Product"; }
            @Override
            public double getPrice() { return 10.0; }
            @Override
            public double getDuration() { return 1.0; }
            @Override
            public void displayInfo() {
                System.out.println("This is an invalid product");
            }
        };
        
        System.out.println("Attempting to add invalid product:");
        cart.addItem(invalidProduct);
        System.out.println("Cart size after attempt: " + cart.getItems().size() + " (should be 0)");
    }
    
    private static void testCartOperations() {
        System.out.println("\n" + "-".repeat(70));
        System.out.println("TEST 13: Cart Operations (Add, Remove, Clear)");
        System.out.println("-".repeat(70));
        
        Cart cart = new Cart();
        Lesson lesson1 = new Lesson("Lesson 1", 10.0, 1);
        Lesson lesson2 = new Lesson("Lesson 2", 20.0, 2);
        Lesson lesson3 = new Lesson("Lesson 3", 30.0, 3);
        
        System.out.println("Initial cart size: " + cart.getItems().size());
        
        cart.addItem(lesson1);
        cart.addItem(lesson2);
        cart.addItem(lesson3);
        System.out.println("After adding 3 items: " + cart.getItems().size());
        
        cart.removeItem(lesson2);
        System.out.println("After removing 1 item: " + cart.getItems().size());
        
        cart.clearCart();
        System.out.println("After clearing: " + cart.getItems().size());
    }
    
    private static void testComplexScenarioWithDecorators() {
        System.out.println("\n" + "-".repeat(70));
        System.out.println("TEST 14: Complex Scenario - Modules with Decorators in Cart");
        System.out.println("-".repeat(70));
        
        Cart cart = new Cart();
        
        // Create decorated modules
        Module baseModule1 = new Module("Python Basics");
        Course course1 = new Course("Python Intro Course");
        course1.addLesson(new Lesson("Intro to Python", 25.0, 1));
        Course course2 = new Course("Python Data Types Course");
        course2.addLesson(new Lesson("Python Data Types", 35.0, 2));
        baseModule1.addCourse(course1);
        baseModule1.addCourse(course2);
        
        Module baseModule2 = new Module("Web Development");
        Course course3 = new Course("HTML/CSS Course");
        course3.addLesson(new Lesson("HTML/CSS", 30.0, 2));
        Course course4 = new Course("JavaScript Course");
        course4.addLesson(new Lesson("JavaScript", 40.0, 2.5));
        baseModule2.addCourse(course3);
        baseModule2.addCourse(course4);
        
        // Decorate modules - NOW WORKS!
        System.out.println("Creating decorated modules...");
        AbstractModule decoratedModule1 = new PracticeSet(new MentorSupport(baseModule1));
        AbstractModule decoratedModule2 = new PracticeSet(baseModule2);
        
        // Cart expects Module type, but AbstractModule is parent class
        // Since Module extends AbstractModule, we need to cast carefully
        // Better approach: Add items as Product (interface)
        cart.addItem(decoratedModule1);  // Works because AbstractModule implements Product
        cart.addItem(decoratedModule2);  // Works because AbstractModule implements Product
        
        System.out.println("Cart with decorated modules:");
        System.out.println("  Module 1: Python Basics with PracticeSet and MentorSupport");
        System.out.println("  Module 2: Web Development with PracticeSet only");
        System.out.println("  Total modules: " + cart.totalModules());
        System.out.println("  Total price should include decorator costs");
        
        cart.generateReceipt();
    }
    
    private static void testAllDiscountsSimultaneously() {
        System.out.println("\n" + "-".repeat(70));
        System.out.println("TEST 15: All Discounts Simultaneously");
        System.out.println("-".repeat(70));
        
        Cart cart = new Cart();
        cart.setFromDevelopingCountry(true);
        
        // Create multiple modules with sufficient duration
        Module module1 = new Module("Advanced Java");
        Course course1 = new Course("Java Concurrency Course");
        course1.addLesson(new Lesson("Java Concurrency", 45.0, 2.5));
        Course course2 = new Course("Java Streams Course");
        course2.addLesson(new Lesson("Java Streams", 40.0, 2));
        module1.addCourse(course1);
        module1.addCourse(course2);
        
        Module module2 = new Module("Spring Framework");
        Course course3 = new Course("Spring Core Course");
        course3.addLesson(new Lesson("Spring Core", 50.0, 3));
        Course course4 = new Course("Spring Boot Course");
        course4.addLesson(new Lesson("Spring Boot", 55.0, 2.5));
        module2.addCourse(course3);
        module2.addCourse(course4);
        
        Module module3 = new Module("Microservices");
        Course course5 = new Course("Docker Course");
        course5.addLesson(new Lesson("Docker", 35.0, 2));
        Course course6 = new Course("Kubernetes Course");
        course6.addLesson(new Lesson("Kubernetes", 45.0, 2.5));
        module3.addCourse(course5);
        module3.addCourse(course6);
        
        // Add some extra lessons
        Lesson extraLesson = new Lesson("Bonus Lesson", 20.0, 1);
        
        cart.addItem(module1);
        cart.addItem(module2);
        cart.addItem(module3);
        cart.addItem(extraLesson);
        
        System.out.println("MAXIMUM DISCOUNT SCENARIO:");
        System.out.println("  - Developing country: true");
        System.out.println("  - Modules count: " + cart.totalModules() + " (≥2)");
        System.out.println("  - Total duration: " + cart.getDuration() + " hours (≥5)");
        System.out.println("  Base price: $" + cart.getPrice());
        System.out.println("  All 3 discounts should apply in sequence!");
        
        cart.generateReceipt();
    }
    
    private static void testReceiptGeneration() {
        System.out.println("\n" + "-".repeat(70));
        System.out.println("TEST 16: Receipt Generation for Various Scenarios");
        System.out.println("-".repeat(70));
        
        // Scenario 1: Minimal cart
        System.out.println("\n--- Scenario 1: Minimal Cart (1 lesson) ---");
        Cart cart1 = new Cart();
        cart1.addItem(new Lesson("Quick Tip", 9.99, 0.5));
        cart1.generateReceipt();
        
        // Scenario 2: Course only
        System.out.println("\n--- Scenario 2: Course Only ---");
        Cart cart2 = new Cart();
        Course course = new Course("Data Science Basics");
        course.addLesson(new Lesson("Statistics", 30.0, 2));
        course.addLesson(new Lesson("Python for Data", 40.0, 3));
        cart2.addItem(course);
        cart2.generateReceipt();
        
        // Scenario 3: Mixed items with one discount
        System.out.println("\n--- Scenario 3: Mixed Items with Duration Discount Only ---");
        Cart cart3 = new Cart();
        cart3.addItem(new Lesson("Long Lesson 1", 60.0, 3));
        cart3.addItem(new Lesson("Long Lesson 2", 70.0, 3));
        cart3.generateReceipt();
        
        // Scenario 4: Bulk purchase with all discounts
        System.out.println("\n--- Scenario 4: Bulk Purchase with All Discounts ---");
        Cart cart4 = new Cart();
        cart4.setFromDevelopingCountry(true);
        
        for (int i = 1; i <= 3; i++) {
            Module mod = new Module("Module " + i);
            Course modCourse = new Course("Course for Module " + i);
            for (int j = 1; j <= 2; j++) {
                modCourse.addLesson(new Lesson("Lesson " + i + "." + j, 15.0 * i, 1.0));
            }
            mod.addCourse(modCourse);
            cart4.addItem(mod);
        }
        cart4.generateReceipt();
    }
    
    private static void testPolymorphism() {
        System.out.println("\n" + "-".repeat(70));
        System.out.println("TEST 17: Polymorphism with AbstractProduct");
        System.out.println("-".repeat(70));
        
        // All can be treated as Product
        Product[] products = {
            new Lesson("Polymorphism Lesson", 10.0, 1),
            new Course("Polymorphism Course"),
            new Module("Polymorphism Module"),
            new PracticeSet(new Module("Decorated Module"))  // NOW WORKS!
        };
        
        System.out.println("All products can be treated as Product interface:");
        for (Product p : products) {
            System.out.println("  - " + p.getName() + " (" + p.getClass().getSimpleName() + ")");
        }
        
        // Course and Module can be treated as AbstractProduct
        AbstractProduct[] abstractProducts = {
            new Course("Abstract Course"),
            new Module("Abstract Module"),
            new PracticeSet(new Module("Decorated in Abstract"))  // NOW WORKS!
        };
        
        System.out.println("\nCourse, Module, and Decorated Modules can be treated as AbstractProduct:");
        for (AbstractProduct ap : abstractProducts) {
            System.out.println("  - " + ap.getName() + " (" + ap.getClass().getSimpleName() + ")");
        }
        
        System.out.println("\n✅ Polymorphism works correctly with updated decorators!");
    }
}