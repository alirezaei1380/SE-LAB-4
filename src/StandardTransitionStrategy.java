public class StandardTransitionStrategy implements TransitionStrategy{
    @Override
    public double getPrice(float weight) {
        return 2.5 * weight;
    }

    @Override
    public void getTransitionStrategy(){
        System.out.print("Transition strategy is: Standard");
    }
}
