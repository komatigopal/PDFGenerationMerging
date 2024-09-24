public class Test {
	public static void main(String[] args) {
		int arra[] = { 1, -5, 7, -19, 3, 2, 1, 7 };
		System.out.println("Maximum Sum of Contigeous Sub Array - " + test(arra));
	}

	public static int test(int a[]) {
		int noOfTerms = 2, maxSum = 0, sum = 0, j = 1;
		for (int i = 0; i < a.length; i++) {
			noOfTerms = 2;
			sum = a[i];
			j = 1;
			while (noOfTerms < a.length && (i + j) < a.length) {
				sum += a[i + j];
				if (sum > maxSum) {
					maxSum = sum;
				}
				noOfTerms++;
				j++;
			}
		}
		return maxSum;
	}
}