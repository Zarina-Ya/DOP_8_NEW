import java.util.Vector;
public class Placements {
    int n;
    int m;
    Vector<Integer> vector;

    public Placements(int n , int m ){
        this.n = n;
        this.m = m;
        vector = new Vector<>(n);
        for( int i = 0 ; i < n; i++){
            vector.add(i);
        }

        System.out.println(vector);

    }

    private static int getFactorial(int f) {
        int result = 1;
        for (int i = 1; i <= f; i++) {
            result = result * i;
        }
        return result;
    }

    public void gen1(){
        int f = getFactorial(n)/getFactorial(n-m);
        System.out.println("f = " + f);
        int factorial = getFactorial(m);
        for ( int count = 0 ; count < f; count++){
            Vector<Integer> tmpA = (Vector<Integer>) vector.clone();
            Vector<Integer> result = new Vector<>(m);

            int value1 = 0;
            int value2 = 0;
            int tmpFactorial = factorial;
            for( int i = 0 ; i < m ; i++) {
                tmpFactorial = (m-i <= 1) ? 1 : tmpFactorial/(m-i);
                value1 = count/ tmpFactorial;
                value2 = value1% tmpA.size();

                result.add(tmpA.get(value2));
                //result.set(i,tmpA.get(value2));// Возвращает элемент в указанной позиции в этом векторе.

                tmpA.remove(value2);// Удаляет элемент в указанной позиции в этом векторе.
            }

          //  System.out.println(result);
        }
    }


    public void gen(int thread){
        int f = getFactorial(n)/getFactorial(n-m);
        System.out.println("f = " + f);
        int Fact = (getFactorial(m));
        ThreadGen(thread,f, Fact);
    }

    public void ThreadGen(int thread, int f, int Fact){
        ThreadPlacements[] threadPlacements = new ThreadPlacements[thread];
        int cellsForThread = f / thread;  //Вычисляемые ячейки

        int begin = 0;

        for (int threadIndex = thread - 1; threadIndex >= 0; --threadIndex) {
            int endIndex = begin + cellsForThread;  //Последняя вычисляемой ячейки.
            //System.out.println("Индекс последней вычисляемой ячейки.: " + endIndex + " Индекс первой вычисляемой ячейки " + begin);

            if (threadIndex == 0) {
                endIndex = f; // проверочка на то , что у нас отсталиись незаппреденные ячейки, все приходится на посдений поток
            }

            threadPlacements[threadIndex] =  new ThreadPlacements(vector,n,begin,endIndex,m,f, Fact);
            threadPlacements[threadIndex].start();
            begin = endIndex;

        }

        try {

            for (ThreadPlacements threadPlacements1 : threadPlacements) {
                threadPlacements1.join();

            }


        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void main(String args[])
    {
        Placements placements = new Placements(9,8);
        long startTime1 = System.currentTimeMillis();

        int valThread = 3;
        placements.gen(valThread);
        long estimatedTime1 = System.currentTimeMillis() ;
        System.out.println("Умножается за время: " + (estimatedTime1 -startTime1 ) + "ms");


        startTime1 = System.currentTimeMillis();
        placements.gen1();
        estimatedTime1 = System.currentTimeMillis() ;
        System.out.println("Умножается за время: " + (estimatedTime1 -startTime1 ) + "ms");

    }
}
