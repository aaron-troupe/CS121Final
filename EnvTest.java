public class EnvTest {
    public static void main(String[] args) {
        String key = System.getenv("EXCHANGE_API_KEY");
        System.out.println("EXCHANGE_API_KEY = " + key);
    }
}

