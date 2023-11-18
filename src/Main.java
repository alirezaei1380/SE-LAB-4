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
