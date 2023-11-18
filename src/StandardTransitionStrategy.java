public class StandardTransitionStrategy implements TransitionStrategy{
    @Override
    public double getPrice(int weight) {
        return 2.5 * weight;
    }
}
