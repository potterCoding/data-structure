import java.util.Random;

public class Test {

    //简单测试两种并查集实现方式的效率
    private static double testUF(UF uf, int m){
        int size = uf.getSize();
        Random random = new Random();

        long startTime = System.nanoTime();
        //测试连接操作
        for (int i = 0; i < m ; i ++){
            int a = random.nextInt(size);
            int b = random.nextInt(size);
            uf.unionElements(a,b);
        }

        //测试这两个元素是否连接
        for (int i = 0; i < m ; i ++){
            int a = random.nextInt(size);
            int b = random.nextInt(size);
            uf.isConnected(a,b);
        }

        long endTime = System.nanoTime();

        return (endTime - startTime) / 1000000000.0;
    }

    public static void main(String[] args) {
        int size = 100000;
        int m = 100000;

//        UnionFind01 uf01 = new UnionFind01(size);
//        System.out.println("UnionFind01 -> "+ testUF(uf01,m) +"s");
//
//        UnionFind02 uf02 = new UnionFind02(size);
//        System.out.println("UnionFind02 -> "+ testUF(uf02,m) +"s");

        UnionFind03 uf03 = new UnionFind03(size);
        System.out.println("UnionFind03 -> "+ testUF(uf03,m) +"s");

        UnionFind04 uf04 = new UnionFind04(size);
        System.out.println("UnionFind04 -> "+ testUF(uf04,m) +"s");
    }

}
