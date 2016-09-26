package lesson10.labs;

/**
 * Created by Asme on 9/26/2016.
 */
public class RaceCondition implements Runnable {
    static Queue queue = new Queue();
    @Override
    public void run() {
        for (int i = 1; i <= 1000; i++) {
            Queue.Node node = queue.new Node();
            node.value=100;
            queue.add(node);
        }

    }

    public static void main(String[] args) {

        RaceCondition raceCondition = new RaceCondition();
        Thread thread1 = new Thread(raceCondition);
        Thread thread2 = new Thread(raceCondition);
        thread1.start();
        thread2.start();


        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Remove check weather there are exactly 2000 nodes with value.
        //Check it by removing synchronized key word from add method of
        //Queue Class to see NullPointerException thrown
        for(int i=1;i<=2000;i++){
            Object value=queue.remove();
            if(value==(null)){
                throw new NullPointerException("Race problem detected. 2000 nodes has not been added.");
            }
        }

    }

}
