class FirstApp
{
    public static void main(String args[])
    {
        System.out.println("Hello, World");
        String languages[] = {"C", "C++", "C#", "Python", "Go", "Rust", "JavaScript", "PHP", "Swift", "Java"};
        int n = ( int ) ( Math.random () * 1000000) ;
        n = n * 3;
        String input0 = "10101";
        int number0 = Integer.parseInt(input0, 2);
        n = n + number0;
        String input1 = "FF";
        int number1 = Integer.parseInt(input1, 16);
        n = n + number1;
        n = n * 6;

        while(n>9)
        {
            n=n/10+n%10;
        }

        System.out.println(n);

        System.out.println("Willy-nilly, this semester I will learn " + languages[n]);
    }
}
