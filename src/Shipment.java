
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
