import java.util.Random;

public class Noise {
    private Random random;
    private int[] permutationTable;

    public Noise(long seed) {
        random = new Random(seed);
        permutationTable = new int[256];

        // Initialize and shuffle permutation table
        for (int i = 0; i < 256; i++) {
            permutationTable[i] = i;
        }

        for (int i = 255; i > 0; i--) {
            int swapIndex = random.nextInt(i + 1);
            int temp = permutationTable[i];
            permutationTable[i] = permutationTable[swapIndex];
            permutationTable[swapIndex] = temp;
        }

        
    }

    public double generate(double x, double y, int octaves, double lacunarity, double gain, double maxValue, double frequency) {
        double amplitude = 1;
        double total = 0;

        for (int i = 0; i < octaves; i++) {
            total += amplitude * noise(x * frequency, y * frequency);
            maxValue += amplitude;

            amplitude *= gain;
            frequency *= lacunarity;
        }

        return (total / maxValue);  // Normalize from -1 to 1 → 0 to 1
    }

    private double noise(double x, double y) {
        int X = ((int) Math.floor(x)) & 255;
        int Y = ((int) Math.floor(y)) & 255;
        x -= Math.floor(x);
        y -= Math.floor(y);
        double u = fade(x);
        double v = fade(y);

        int a = permutationTable[X & 255] + Y;
        int b = permutationTable[(X + 1) & 255] + Y;

        return lerp(v, lerp(u, grad(permutationTable[a & 255], x, y), grad(permutationTable[b & 255], x - 1, y)),
                lerp(u, grad(permutationTable[a & 254 + 1], x, y - 1), grad(permutationTable[b & 254 + 1], x - 1, y - 1)));
    }

    private double fade(double t) {
        return t * t * t * (t * (t * 6 - 15) + 10);
    }

    private double lerp(double t, double a, double b) {
        return a + t * (b - a);
    }

    private double grad(int hash, double x, double y) {
        int h = hash & 7;  // Use only first 3 bits to get 8 possible directions
        double u = h < 4 ? x : y;
        double v = h < 2 ? y : x;
        return ((h & 1) == 0 ? u : -u) + ((h & 2) == 0 ? v : -v);
    }
}
