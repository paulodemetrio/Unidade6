public class z_Questiona_5 {
    public static void main(String[] args) {
        int vetor[];
        int A[] = {3, 1, 5, 8, 1, 8};

        for (int i = 0; i < 6; i++) {
            vetor[i] = A[i] * 2;
        }
        for (int i = 0; i < A.length; i++) {
            System.out.println(vetor[i]*vetor[i]);
        }
    }
}
