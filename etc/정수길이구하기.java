// 굳이 String 변환후 length() 안해줘도 이렇게 구할 수 있음

int len = (int)Math.log10(N)+1;

// 자릿수 구할 때는
// N : 3289
천자리수 = N / 1000; //3
백자리수 = ( N - 천자리수 * 1000 ) / 100; //2
