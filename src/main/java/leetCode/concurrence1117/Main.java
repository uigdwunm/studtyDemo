package leetCode.concurrence1117;

public class Main {
    public static void main(String[] args) {
        // TODO 改没改
        H2O h2O = new H2O();
        Runnable h = () -> System.out.print('H');
        Runnable o = () -> System.out.print('O');

        String str = "OOOOHHHHHHHH";

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
