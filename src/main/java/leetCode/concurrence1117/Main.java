package leetCode.concurrence1117;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        H2O h2O = new H2O();
        Runnable h = () -> System.out.println('H');
        Runnable o = () -> System.out.println('O');

        String str = "OOOHHHHHH";

        for (char c : str.toCharArray()) {
            switch (c) {
                case 'O':
                    new Thread(
                            () -> {
                                try {
                                    h2O.oxygen(o);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                    ).start();
                    break;
                case 'H':
                    new Thread(
                            () -> {
                                try {
                                    h2O.hydrogen(h);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                    ).start();
                    break;
            }
        }
    }
}
