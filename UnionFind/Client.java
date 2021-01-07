package UnionFind;

import java.io.IOException;

//example of how it would be used
public class Client {
    public static void main(String[] args) throws IOException { // client
        int N = System.in.read(), p, q;
        UF_Shell uf = new UF_Shell(N);
        while (( p = System.in.read() ) != -1/*is not empty*/) {
            p = System.in.read();
            q = System.in.read();
            if (!uf.connected(p, q)) {
                uf.union(p, q);
                System.out.println(p + " " + q);
            }
        }
    }
}