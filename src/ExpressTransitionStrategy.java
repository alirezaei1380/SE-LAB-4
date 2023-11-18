public class ExpressTransitionStrategy implements TransitionStrategy{
    @Override
    public double getPrice(float weight) {
        return 3.5 * weight;
    }

    @Override
    public void getTransitionStrategy(){
        System.out.print("Transition strategy is: EXPRESS");
    }
}
