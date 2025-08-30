public class ComplexTest {

    public static void main(String[] args) {
        int[] numbers = {1, 2, 3, 4, 5};
        int sum = 0
        for (int num : numbers) {
            sum += num;
        }

        if(sum > 10) {
            System.out.println("Sum is greater than 10");
        } else
            System.out.println("Sum is 10 or less");

        String result = calculateFactorial(5);
        System.out.println("Factorial result: " + result);
    }

    public static String calculateFactorial(int n) {
        int fact = 1;
        for(int i = 1; i <= n; i++)
            fact *= i
        return String.valueOf(fact);
    }

    public void displayMessage() {
        System.out.println("Welcome to ComplexTest!");
    }
}