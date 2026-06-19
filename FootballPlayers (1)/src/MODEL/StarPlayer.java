
package MODEL;

public class StarPlayer extends Player {
    @Override
    public double calculateBonus(int points) {
        return points * 500000;
    }
}
