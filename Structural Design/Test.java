public class Test {
    public static void main(String[] args) {
        System.out.println("=".repeat(70));
        System.out.println("COMPREHENSIVE TESTING OF E-LEARNING CART SYSTEM");
        System.out.println("=".repeat(70));
        
        // Test 1: Basic product creation and pricing
        testBasicProductPricing();
        
        // Test 2: Test Module with PracticeSet and MentorSupport decorators
        testModuleDecorators();
        
        // Test 3: Test Course with multiple lessons
        testCourseWithLessons();
        
        // Test 4: Test cart with no discounts
        testCartWithNoDiscounts();
        
        // Test 5: Test Developing Country discount
        testDevelopingCountryDiscount();
        
        // Test 6: Test Duration discount (threshold 5+ hours)
        testDurationDiscount();
        
        // Test 7: Test MultiModule discount (2+ modules)
        testMultiModuleDiscount();
        
        // Test 8: Test multiple discounts combined
        testMultipleDiscountsCombined();
        
        // Test 9: Test edge cases - exactly at thresholds
        testThresholdEdgeCases();
        
        // Test 10: Test invalid product addition
        testInvalidProductAddition();
        
        // Test 11: Test cart operations (add, remove, clear)
        testCartOperations();
        
        // Test 12: Test complex scenario - Module with decorators inside cart
        testComplexScenarioWithDecorators();
        
        // Test 13: Test all discounts simultaneously
        testAllDiscountsSimultaneously();
        
        // Test 14: Test receipt generation for various scenarios
        testReceiptGeneration();
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
    
    private static void testModuleDecorators() {
        System.out.println("\n" + "-".repeat(70));
        System.out.println("TEST 2: Module Decorators (PracticeSet and MentorSupport)");
        System.out.println("-".repeat(70));
        
        // Create base module with lessons
        Module baseModule = new Module("Java Programming Fundamentals");
        Lesson lesson1 = new Lesson("Variables", 20.0, 1);
        Lesson lesson2 = new Lesson("Loops", 25.0, 1.5);
        Lesson lesson3 = new Lesson("Methods", 30.0, 2);
        
        baseModule.addCourse(lesson1);
        baseModule.addCourse(lesson2);
        baseModule.addCourse(lesson3);
        
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
        
        // Chain both decorators
        Module decoratedModule = new PracticeSet(new MentorSupport(baseModule));
        System.out.println("\nWith both PracticeSet and MentorSupport (+$30):");
        System.out.println("  Price: $" + decoratedModule.getPrice());
        System.out.println("  Expected: $" + (baseModule.getPrice() + 30));
        decoratedModule.displayInfo();
    }
    
    private static void testCourseWithLessons() {
        System.out.println("\n" + "-".repeat(70));
        System.out.println("TEST 3: Course with Multiple Lessons");
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
    
    private static void testCartWithNoDiscounts() {
        System.out.println("\n" + "-".repeat(70));
        System.out.println("TEST 4: Cart with No Discounts");
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
        System.out.println("  Total price before discount: $" + cart.getTotalPrice());
        System.out.println("  Total duration: " + cart.getTotalDuration() + " hours");
        
        cart.generateReceipt();
    }
    
    private static void testDevelopingCountryDiscount() {
        System.out.println("\n" + "-".repeat(70));
        System.out.println("TEST 5: Developing Country Discount (10%)");
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
        System.out.println("TEST 6: Duration Discount (12% for 5+ hours)");
        System.out.println("-".repeat(70));
        
        // Test below threshold (4 hours)
        Cart cart1 = new Cart();
        cart1.addItem(new Lesson("Short Course", 100.0, 2));
        cart1.addItem(new Lesson("Another Short", 50.0, 2));
        
        System.out.println("Scenario 1: Duration = 4 hours (below threshold)");
        System.out.println("  Base price: $150");
        System.out.println("  Duration: " + cart1.getTotalDuration() + " hours");
        System.out.println("  Expected discount: $0");
        cart1.generateReceipt();
        
        // Test exactly at threshold (5 hours)
        Cart cart2 = new Cart();
        cart2.addItem(new Lesson("Medium Course", 100.0, 3));
        cart2.addItem(new Lesson("Another Medium", 50.0, 2));
        
        System.out.println("\nScenario 2: Duration = 5 hours (at threshold)");
        System.out.println("  Base price: $150");
        System.out.println("  Duration: " + cart2.getTotalDuration() + " hours");
        System.out.println("  Expected discount: $18");
        cart2.generateReceipt();
        
        // Test above threshold (7 hours)
        Cart cart3 = new Cart();
        cart3.addItem(new Lesson("Long Course", 120.0, 4));
        cart3.addItem(new Lesson("Another Long", 80.0, 3));
        
        System.out.println("\nScenario 3: Duration = 7 hours (above threshold)");
        System.out.println("  Base price: $200");
        System.out.println("  Duration: " + cart3.getTotalDuration() + " hours");
        System.out.println("  Expected discount: $24");
        cart3.generateReceipt();
    }
    
    private static void testMultiModuleDiscount() {
        System.out.println("\n" + "-".repeat(70));
        System.out.println("TEST 7: MultiModule Discount (15% for 2+ modules)");
        System.out.println("-".repeat(70));
        
        // Test with 1 module
        Cart cart1 = new Cart();
        Module module1 = new Module("Single Module");
        module1.addCourse(new Lesson("Lesson 1", 50.0, 1));
        cart1.addItem(module1);
        
        System.out.println("Scenario 1: 1 module (below threshold)");
        System.out.println("  Modules count: " + cart1.totalModules());
        System.out.println("  Base price: $" + cart1.getTotalPrice());
        System.out.println("  Expected discount: $0");
        cart1.generateReceipt();
        
        // Test with 2 modules (at threshold)
        Cart cart2 = new Cart();
        Module module2a = new Module("Module A");
        module2a.addCourse(new Lesson("A1", 30.0, 1));
        Module module2b = new Module("Module B");
        module2b.addCourse(new Lesson("B1", 40.0, 2));
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
        module3a.addCourse(new Lesson("X1", 25.0, 1));
        Module module3b = new Module("Module Y");
        module3b.addCourse(new Lesson("Y1", 35.0, 2));
        Module module3c = new Module("Module Z");
        module3c.addCourse(new Lesson("Z1", 40.0, 1.5));
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
        System.out.println("TEST 8: Multiple Discounts Combined");
        System.out.println("-".repeat(70));
        
        Cart cart = new Cart();
        cart.setFromDevelopingCountry(true);
        
        // Add items to trigger multiple discounts
        Module module1 = new Module("Module 1");
        module1.addCourse(new Lesson("Lesson 1.1", 40.0, 2));
        module1.addCourse(new Lesson("Lesson 1.2", 30.0, 1.5));
        
        Module module2 = new Module("Module 2");
        module2.addCourse(new Lesson("Lesson 2.1", 50.0, 2.5));
        
        cart.addItem(module1);
        cart.addItem(module2);
        
        System.out.println("Cart with:");
        System.out.println("  - Developing country: true");
        System.out.println("  - Duration: " + cart.getTotalDuration() + " hours (>=5)");
        System.out.println("  - Modules: " + cart.totalModules() + " (>=2)");
        System.out.println("  Base price: $" + cart.getTotalPrice());
        System.out.println("  All 3 discounts should apply!");
        
        cart.generateReceipt();
    }
    
    private static void testThresholdEdgeCases() {
        System.out.println("\n" + "-".repeat(70));
        System.out.println("TEST 9: Edge Cases - Exactly at Thresholds");
        System.out.println("-".repeat(70));
        
        // Test exactly 5 hours for duration discount
        Cart cart1 = new Cart();
        cart1.addItem(new Lesson("Edge Case 1", 100.0, 5));
        System.out.println("Edge Case: Duration exactly 5 hours");
        cart1.generateReceipt();
        
        // Test exactly 2 modules for multi-module discount
        Cart cart2 = new Cart();
        Module m1 = new Module("M1");
        m1.addCourse(new Lesson("L1", 50.0, 1));
        Module m2 = new Module("M2");
        m2.addCourse(new Lesson("L2", 50.0, 1));
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
        System.out.println("TEST 10: Invalid Product Addition");
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
        System.out.println("TEST 11: Cart Operations (Add, Remove, Clear)");
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
        System.out.println("TEST 12: Complex Scenario - Modules with Decorators in Cart");
        System.out.println("-".repeat(70));
        
        Cart cart = new Cart();
        
        // Create decorated modules
        Module baseModule1 = new Module("Python Basics");
        baseModule1.addCourse(new Lesson("Intro to Python", 25.0, 1));
        baseModule1.addCourse(new Lesson("Python Data Types", 35.0, 2));
        
        Module baseModule2 = new Module("Web Development");
        baseModule2.addCourse(new Lesson("HTML/CSS", 30.0, 2));
        baseModule2.addCourse(new Lesson("JavaScript", 40.0, 2.5));
        
        // Decorate modules
        Module decoratedModule1 = new PracticeSet(new MentorSupport(baseModule1));
        Module decoratedModule2 = new PracticeSet(baseModule2);
        
        cart.addItem(decoratedModule1);
        cart.addItem(decoratedModule2);
        
        System.out.println("Cart with decorated modules:");
        System.out.println("  Module 1: Python Basics with PracticeSet and MentorSupport");
        System.out.println("  Module 2: Web Development with PracticeSet only");
        System.out.println("  Total modules: " + cart.totalModules());
        System.out.println("  Total price should include decorator costs");
        
        cart.generateReceipt();
    }
    
    private static void testAllDiscountsSimultaneously() {
        System.out.println("\n" + "-".repeat(70));
        System.out.println("TEST 13: All Discounts Simultaneously");
        System.out.println("-".repeat(70));
        
        Cart cart = new Cart();
        cart.setFromDevelopingCountry(true);
        
        // Create multiple modules with sufficient duration
        Module module1 = new Module("Advanced Java");
        module1.addCourse(new Lesson("Java Concurrency", 45.0, 2.5));
        module1.addCourse(new Lesson("Java Streams", 40.0, 2));
        
        Module module2 = new Module("Spring Framework");
        module2.addCourse(new Lesson("Spring Core", 50.0, 3));
        module2.addCourse(new Lesson("Spring Boot", 55.0, 2.5));
        
        Module module3 = new Module("Microservices");
        module3.addCourse(new Lesson("Docker", 35.0, 2));
        module3.addCourse(new Lesson("Kubernetes", 45.0, 2.5));
        
        // Add some extra lessons
        Lesson extraLesson = new Lesson("Bonus Lesson", 20.0, 1);
        
        cart.addItem(module1);
        cart.addItem(module2);
        cart.addItem(module3);
        cart.addItem(extraLesson);
        
        System.out.println("MAXIMUM DISCOUNT SCENARIO:");
        System.out.println("  - Developing country: true");
        System.out.println("  - Modules count: " + cart.totalModules() + " (≥2)");
        System.out.println("  - Total duration: " + cart.getTotalDuration() + " hours (≥5)");
        System.out.println("  Base price: $" + cart.getTotalPrice());
        System.out.println("  All 3 discounts should apply in sequence!");
        
        cart.generateReceipt();
    }
    
    private static void testReceiptGeneration() {
        System.out.println("\n" + "-".repeat(70));
        System.out.println("TEST 14: Receipt Generation for Various Scenarios");
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
            for (int j = 1; j <= 2; j++) {
                mod.addCourse(new Lesson("Lesson " + i + "." + j, 15.0 * i, 1.0));
            }
            cart4.addItem(mod);
        }
        cart4.generateReceipt();
    }
}