public class ExpressTransitionStrategy implements TransitionStrategy{
    @Override
    public double getPrice(int weight) {
        return 3.5 * weight;
    }
}
