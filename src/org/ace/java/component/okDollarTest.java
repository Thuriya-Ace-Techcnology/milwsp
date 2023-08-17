package org.ace.java.component;

public class okDollarTest {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Welcome!");

		// write your code here
		try {
			String test = "<student><id>0001</id><name>aung</name></student>";
			System.out.println("test - " + test);

			String encrypted = ASE128.encrypt("A791B7F6229B4F3A", "GGIP000000000002", test);
			System.out.println("encrypted - " + encrypted);

			String decrypted = ASE128.decrypt("A791B7F6229B4F3A", "GGIP000000000002", encrypted);
			System.out.println("decrypted - " + decrypted);

			// String response =
			// "zSYJOuSw%2FxSDMAi48BGmYu0GkmDn%2F5j1OzZ806XcLGxwCfn7v4Z5L8hHZjiyVk8pjlHsytI0MJIAk5oSGSRgVKTC6SxBMtDCkKRIuoXAZtb%2F4kCe72ZsTniPpRl9%2FqYiI64crjpBAXTJ5601offfPAmgb9aT6ygpxjbwldl2UL%2BZABCtpqFYO%2B3PZsb42HhHYq9UzStKo5FS0CYPm%2BGK9Q%2Bmtl%2B89OPU0FlYc9ADLjVieDidltNPoH4h%2BaPzGwHDBcih25cR7WQ%2Fn4LELsBxfJK8dK1iqJe%2FWVCkclh69E8Rqwm2dibvUJUb9qGlIh0vgRKp29d8FMuC6XfKOONu%2BGNQAWnPbpxoEe3keY4u4ssdQgz3OnE77FVFMGr3ygHS0zPyYgXCIJLhwZzRZZjxaOoEz14Mo69i38CpJOibh62%2FaJvzGKHI4WW9x8IKF5jauJvvG1vpJ0HEjA1nqaZYEn0%2BDxE6hvjBi1ecwMpe2vmGzZZf%2BJ6d4v4KMhAX4EU3IZsCjHfqM1vv%2FgKhDXKUe9gtqicdz41XoHiRreN6CRZHywv57i6uLThe0G2VCR2E5J4If7WljQMD7VfFe7PSWutfep%2F6WUHnIGVWFNnM7Us%3D%2CGGIP000000000002%2C00959768187249";
			// // String response =
			// //
			// "zSYJOuSw%2FxSDMAi48BGmYu0GkmDn%2F5j1OzZ806XcLGxwCfn7v4Z5L8hHZjiyVk8pjlHsytI0MJIAk5oSGSRgVKTC6SxBMtDCkKRIuoXAZtb%2F4kCe72ZsTniPpRl9%2FqYiI64crjpBAXTJ5601offfPAmgb9aT6ygpxjbwldl2UL%2BZABCtpqFYO%2B3PZsb42HhHYq9UzStKo5FS0CYPm%2BGK9Q%2Bmtl%2B89OPU0FlYc9ADLjVieDidltNPoH4h%2BaPzGwHDBcih25cR7WQ%2Fn4LELsBxfJK8dK1iqJe%2FWVCkclh69E8Rqwm2dibvUJUb9qGlIh0vgRKp29d8FMuC6XfKOONu%2BGNQAWnPbpxoEe3keY4u4ssdQgz3OnE77FVFMGr3ygHS0zPyYgXCIJLhwZzRZZjxaOoEz14Mo69i38CpJOibh62%2FaJvzGKHI4WW9x8IKF5jauJvvG1vpJ0HEjA1nqaZYEn0%2BDxE6hvjBi1ecwMpe2vmGzZZf%2BJ6d4v4KMhAX4EU3IZsCjHfqM1vv%2FgKhDXKUe9gtqicdz41XoHiRreN6CRZHywv57i6uLThe0G2VCR2E5J4If7WljQMD7VfFe7PSWutfep%2F6WUHnIGVWFNnM7Us%3D";
			//
			// String encrypted = java.net.URLDecoder.decode(response, "UTF-8");
			// System.out.println(encrypted);
			//
			// String decrypted = ASE128.decrypt("A791B7F6229B4F3A",
			// "GGIP000000000002", encrypted);
			// System.out.println("decrypted - " + decrypted);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
