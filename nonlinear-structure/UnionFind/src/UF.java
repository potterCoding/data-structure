/**
 * @author sun
 * @date 2020-04-24 11:47
 * @description
 */
public interface UF {

    int getSize();
    boolean isConnected(int p ,int q);
    void unionElements(int p,int q);

}
