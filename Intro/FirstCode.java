package Intro;
//my code
class FirstCode {
    public static void main(String[] args) {
        System.out.println("Hello, World!"); 
        Respond r = new Respond();
        r.response();
    }
}
class Respond {
    private Integer i;
    public Respond()
    {
        i = 5;
    }
    public void response() {
        String x = "Hello, Arjun! " + i;
        System.out.println(x);
    }
}