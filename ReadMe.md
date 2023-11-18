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
