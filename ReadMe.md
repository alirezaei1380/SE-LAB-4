# Design Patterns Examination

## Report

### Step1: Select how to use patterns

In this examination we have two main topics:
1. `State`
2. `Transition`

We can consider `state` as two classes called `Delivered` and `Transit` which both have only a single function for printing the status,
so they are two classes with same function schema. But on the other hand, we have `Transition` which has two different approaches: `Standard` and `Express`.
In these two approaches we have two different strategies for transit a shipment, so they are not different types of a single entity and they have their own way of overall behaviour.
So, according to the previous statements we can use `State` pattern for `State` and `Strategy` pattern for `transit`.


### Step2: Implement tests for state/transition interfaces 

Here are some of tests we implemented:

```java
    @Test
    @DisplayName("simple delivered state status test")
    void testDeliveredState() {
        System.setOut(new PrintStream(outContent));
        State state = new DeliveredState();
        state.printStatus();
        assertEquals("package is delivered", outContent.toString());
        System.setOut(originalOut);
    }

```
```java
    @Test
    @DisplayName("simple express transition price test")
    void expressTransitionPriceTest() {
        ExpressTransitionStrategy strategy = new ExpressTransitionStrategy();
        assertEquals(35, strategy.getPrice(10));
    }
```

### Step3: Implement classes

Then based on TDD, we started defining different classes and interfaces based on what we discussed in step 1:
Initially we defined two interfaces:

```java
public interface State {
    void printStatus();
}
public interface TransitionStrategy {

    double getPrice(float weight);
}
```

Then we implemented these interfaces via two specific states and transition types:
Here are some related codes regarding this goal:

```java
public class TransitState implements State{
    @Override
    public void printStatus() {
        System.out.print("package is in transit state");
    }
}

public class ExpressTransitionStrategy implements TransitionStrategy{
    @Override
    public double getPrice(float weight) {
        return 3.5 * weight;
    }
}

```

### Step4: Add Shipment class
In this step we added some setters and getters for state/transition:

```java

public class Shipment {
    private State state;
    private TransitionStrategy transitionStrategy;
    private final float weight;

    Shipment (float weight){
        this.weight = weight;
    }

    public void setTransitionStrategy(TransitionStrategy transitionStrategy){
        this.transitionStrategy = transitionStrategy;
    }

    public double executeTransition(){
        return this.transitionStrategy.getPrice(this.weight);
    }

    public void setState(State state){
        this.state = state;
    }

    public void getState(){
        this.state.printStatus();
    }

}

```



### Step5: Add Main class
As a runner we added a main class which adds some menu for creating our shipment and handle its states and transition:

```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get weight and instantiate the shipment
        System.out.println("Enter the shipment weight:");
        float weight = scanner.nextFloat();
        Shipment shipment = new Shipment(weight);

        while (true){
            System.out.println("\nEnter what you desire to change: \n1.State\n2.Transition");
            int option = scanner.nextInt();

            switch (option){
                // Handle state
                case 1: {
                    System.out.println("Select the state: \n1.In-transit\n2.Delivered");
                    int state = scanner.nextInt();
                    if(state==1)
                        shipment.setState(new TransitState());
                    else{
                        shipment.setState(new DeliveredState());
                        shipment.getState();
                        return;
                    }
                    shipment.getState();
                    break;
                }
                // Handle transition
                case 2: {
                    System.out.println("Select the transition: \n1.Standard\n2.Express");
                    int state = scanner.nextInt();
                    if(state==1)
                        shipment.setTransitionStrategy(new StandardTransitionStrategy());
                    else
                        shipment.setTransitionStrategy(new ExpressTransitionStrategy());
                    System.out.println("The transition changed and the price is " + shipment.executeTransition());
                    break;
                }

            }

        }
    }
}

```

## Questions

### 1
Creational Design Patterns: These patterns are design patterns that deal with object creation mechanisms, trying to create objects in a manner suitable to the situation. The basic form of object creation could result in design problems or added complexity to the design. Creational design patterns solve this problem by somehow controlling this object creation.\

Structural Design Patterns: This kind of pattern is a blueprint of how different objects and classes are combined together to form a bigger structure for achieving multiple goals altogether. The patterns in structural designs show how unique pieces of a system can be combined together in an extensible and flexible manner. So, with the help structural design pattern we can target and change a specific parts of the structure without changing the entire structure.\

Behavioral Design Patterns: These patterns are design patterns that identify common communication patterns between objects and realize these patterns. By doing so, these patterns increase flexibility in carrying out this communication.

### 2
Behavioral Design Patterns

### 3
Singleton: It is a creational design pattern that lets you ensure that a class has only one instance, while providing a global access point to this instance.\
We choose this pattern to make sure that we get the same instance everytime we access the class.\

To implement this pattern we should first make the constructor private so that no one can make a new instance. Then we add a private static attribute to save our single instance of the class. Finally we add a new method called getInstance for other classes to access the single instance of the class.\
Shipment class will look like this after our changes (skipped unchanged parts):
```java
public class Shipment {
    private static Shipment shipment;
    private State state;
    private TransitionStrategy transitionStrategy;
    private final float weight;

    private Shipment(float weight) {
        this.weight = weight;
    }

    public static Shipment getInstance(float weight) {
        if (shipment == null) {
            shipment = new Shipment(weight);
        }
        return shipment;
    }
}
```
### 4
SRP: It Violates the Single Responsibility Principle. The pattern solves two problems at the time (Ensuring that a class has just a single instance and Providing a global access point to that instance).

OCP: For a class to be "open" it must be possible to inherit from it. Inheritance is an "is-a" relationship. If you inherit from a singleton-class then instances of the child-class are also instances of the parent class due to the "is-a" relationship, meaning you can suddenly have multiple instances of the singleton class. If the singleton class inhibits inheritance, it's no longer "open". If a singleton class allows inheritance, and is "open" for extension, then it can no longer enforce the singleton pattern.

LSP: The Singleton pattern itself doesn't inherently violate the Liskov Substitution Principle. However, the implementation of the Singleton pattern can sometimes lead to issues that might indirectly conflict with the LSP if not designed carefully (like last one).

ISP: The Singleton pattern itself does not inherently violate the Interface Segregation Principle (It could violate if the interface is designed poorly).

DIP: It dosen't necessarily violate Dependency Inversion Principle but like the last one, it could violate if it is designed poorly.