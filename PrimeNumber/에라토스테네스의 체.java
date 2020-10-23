//소수가 false고 소수 아닌 것이 true
falseIsPrime = new boolean[10000];
falseIsPrime[0] = true;
falseIsPrime[1] = true;
for (int i = 2; i <= 5000; i++) {
	for (int j = 2; i * j <= 9999; j++) {
		falseIsPrime[i * j] = true;
	}
}
