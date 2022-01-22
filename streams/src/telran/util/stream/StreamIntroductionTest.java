package telran.util.stream;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StreamIntroductionTests {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void streamArraySourceTest() {
		int ar[] = {10, -8, 17, 13, 10};
		int expected[] = {-8, 10};
		int actual[] = Arrays.stream(ar).filter(n -> n % 2 == 0).distinct().sorted()
				.toArray();
		assertArrayEquals(expected, actual);
		
	}
	@Test
	void streamRandomSourceTest() {
		Random gen = new Random();
		assertEquals(10,gen.ints().limit(10).toArray().length);
		gen.ints(10, 10, 25).forEach(n -> assertTrue(n >= 10 && n < 25));
	}
	@Test
	void streamCollectionSourceTest() {
		List<Integer> list = Arrays.asList(10, -8, 30);
		
		Integer [] actual = list.stream().filter(n -> n < 30).sorted().toArray(Integer[]::new);
		Integer [] expected = {-8, 10};
		assertArrayEquals(expected, actual);
	}
	@Test
	void streamStringSourceTest() {
		String str = "Hello";
		str.chars().forEach(n -> System.out.printf("%c;", n));
		
		
	}
	@Test
	void conversionFromIntToInteger( ) {
		List<Integer> expected = Arrays.asList(1, 2, 3);
		int ar[] = {1, 2, 3};
		List<Integer> actual = Arrays.stream(ar).boxed().toList();
		assertIterableEquals(expected, actual);
	}
	@Test
	void conversionFromIntegerToInt() {
		List<Integer> list = Arrays.asList(1, 2, 3);
		assertEquals(6, list.stream().mapToInt(n -> n).sum());
		assertArrayEquals(new int[] {1, 2,3}, list.stream().mapToInt(n -> n).toArray());
	}
	private Integer [] getLotoNumbers(int nNumbers, int min, int max) {
		//TODO
		//using one stream to get array of unique random numbers in the given range
		return new Random().ints(min, max).distinct().limit(nNumbers).boxed().toArray(Integer[]::new);	}
	@Test
	void lotoTest () {
		Integer [] lotoNumbers = getLotoNumbers(7, 1, 49);
		assertEquals(7, lotoNumbers.length);
		
		assertEquals(7, new HashSet<Integer>(Arrays.asList(lotoNumbers)).size());
		Arrays.stream(lotoNumbers).forEach(n -> assertTrue(n >= 1 && n <= 49));
	}
	/**
	 * 
	 * @param ar
	 * @return true if ar contains two numbers, the sum of which equals half of all array's numbers
	 * complexity O[N] 
	 */
	private boolean isHalfSum(int []ar) {
		int halfSum = Arrays.stream(ar).sum() / 2;
		HashSet<Integer> set = new HashSet<>();
		for (int i = 0; i < ar.length; i++) {
			int sum = halfSum - ar[i];
			if (set.contains(sum)) {
				return true;
			}
			set.add(ar[i]);
		}
		return false;
	}
	private boolean isHalfSum2(int[] ar) {
		Arrays.sort(ar);
		int halfSum = Arrays.stream(ar).sum() / 2;
		int i = 0;
		int j = ar.length - 1;
		while (i < j) {
			if (ar[i] + ar[j] == halfSum) {
				return true;
			}
			if (ar[i] + ar[j] > halfSum) {
				j--;
			} else {
				i++;
			}
		}
		return false;
	}
	private boolean isHalfSum3(int [] ar) {
		int sum = Arrays.stream(ar).sum() / 2;
		HashSet<Integer> hash = new HashSet<>();
		for(int n : ar) {
			int x = sum - n;
			if(hash.contains(x)) {
				return true;
			}
			hash.add(n);
		}
		return false;
	}
	@Test
	void isHalfSumTest() {
		int ar[] = {1,2, 10, -7};
		assertTrue(isHalfSum(ar));
		assertTrue(isHalfSum2(ar));
		assertTrue(isHalfSum3(ar));
		int ar1[] = {1, 2, 10, 7};
		assertFalse(isHalfSum(ar1));
		assertTrue(isHalfSum2(ar1));
		assertTrue(isHalfSum3(ar1));
		int ar3[] = { 4, 5, 6, 17};
		assertTrue(isHalfSum(ar3));
		assertTrue(isHalfSum2(ar3));
		assertTrue(isHalfSum3(ar3));
	}

}