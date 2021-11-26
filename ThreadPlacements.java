import java.util.Vector;

public class ThreadPlacements extends Thread{
    int begin;
    int end;
    int m;
    int n;
    int f;
    Vector<Integer> tmpA;
    Vector<Integer> vector;
    Vector<Integer> result;
    int Fact;
    public ThreadPlacements( Vector<Integer> vector,int n, int begin, int end, int m, int f , int Fact) {
        this.begin = begin;
        this.end = end;
        this.m = m;
        this.f = f;
        this.n = n;
        this.vector = vector;

        this.Fact = Fact;
    }

    @Override
    public void run() {
        for ( int count = begin ; count < end; count++){
            int value1 = 0;
            int value2 = 0;
            int tmpFact = Fact;

            tmpA = (Vector<Integer>) vector.clone();
            result = new Vector<>(m);

            for( int i = 0 ; i < m ; i++){

                tmpFact = (m-i <= 1) ? 1 : tmpFact/(m-i);
                value1 = count/ tmpFact;
                value2 = value1% tmpA.size();

                result.add(tmpA.get(value2));


                tmpA.remove(value2);// Удаляет элемент в указанной позиции в этом векторе.
            }

          //  System.out.println(result);
        }

    }

    }
