package mit6005.w1;

public class RecursiveDataType {
    public static void main(String[] args) {
        ImList<Integer> nil = ImList.empty();
        nil.cons(0);
        nil.cons(2).cons(1).cons(0);
        ImList<Integer> x = nil.cons(2).cons(1).cons(0);
        assert(x.first() == 0);
        
    }
}
