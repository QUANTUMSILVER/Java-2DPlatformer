package game.utils;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import lib.Vector;

public class Utils {
	public static float truncate(float n, float max) {
		if(n > max)
			return max;
		return n;
	}
	public static Vector truncate(Vector v, float max) {
		if(v.getMag() > max) {
			v.setMag(max);
			return v;
		}
		return v;
	}
	public static boolean isCollided(int x1, int y1, int w1, int h1, int x2, int y2, int w2, int h2) {
		if(x1 < x2 + w2 && x1 + w1 > x2 && y1 < y2 + h2 && y1 + h1 > y2) {
			return true;
		}
		return false;
	}
	public static String loadFileAsString(String path) {
		StringBuilder builder = new StringBuilder();
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			String line;
			while((line = br.readLine())!= null) {
				builder.append(line + "\n");
			}
			br.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
		return builder.toString();
	}
	public static int parseInt(String number) {
		try {
			return Integer.parseInt(number);
		}catch(NumberFormatException e) {
			e.printStackTrace();
			return 0;
		}
	}
	public static Color Color(int r, int g, int b, int a) {
		Color c = new Color(r, g, b, a);
		return c;
	}
}
