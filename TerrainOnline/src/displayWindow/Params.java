package displayWindow;
//Bartosz Ruszczak
public class Params {
	
	   static long seed;
	   private static float smoothness=2f;
	   static float roughness=0.3f;
	   static float maxHeight=320;
	   static float minHeight=0;
	   private static int octaves=4;
	   static float relativness = 4f;
	   static int length;
	   static int width;
	   private static boolean isLinear=false;


	   public Params(long seed, float roughness, float maxHeight, float minHeight, int octaves, float relativness,
			   float smoothness, int length, int width ) {
		   
		   Params.seed = seed;
		   Params.roughness = roughness;
		   Params.smoothness = smoothness;
		   Params.maxHeight = maxHeight;
		   Params.minHeight = minHeight;
		   Params.octaves = octaves;
		   Params.relativness = relativness;
		   Params.length = length;
		   Params.width = width;
		   
	   }

	   public Params() {
		   Params.seed = 647291;
		   Params.roughness=0.3f;
		   Params.smoothness = 2f;
		   Params.maxHeight=320;
		   Params.minHeight = 0;
		   Params.octaves = 4;
		   Params.relativness = 4f;
		   Params.length = 500;
		   Params.width = 500;
		   
	   }
	public static long getSeed() {
		return seed;
	}
	public static int getLength() {
		return length;
	}
	public static void setLength(int length) {
		Params.length = length;
	}
	public static int getWidth() {
		return width;
	}
	public static void setWidth(int width) {
		Params.width = width;
	}
	public static void setSeed(long seed) {
		Params.seed = seed;
	}
	public static float getSmoothness() {
		return smoothness;
	}
	public static void setSmoothness(float smoothness) {
		Params.smoothness = smoothness;
	}
	public static float getRoughness() {
		return roughness;
	}
	public static void setRoughness(float roughness) {
		Params.roughness = roughness;
	}
	public static float getMaxHeight() {
		return maxHeight;
	}
	public static void setMaxHeight(float maxHeight) {
		Params.maxHeight = maxHeight;
	}
	public static void setMinHeight(float minHeight) {
		Params.minHeight = minHeight;
	}
	   public static float getMinHeight() {
		return minHeight;
	}   
	public static int getOctaves() {
		return octaves;
	}
	public static void setOctaves(int octaves) {
		Params.octaves = octaves;
	}
	public static float getRelativness() {
		return relativness;
	}
	public static void setRelativness(float relativness) {
		Params.relativness = relativness;
	}
	public static boolean getLinear() {
		return isLinear;
	}
	public static void setIsLinear(boolean isLinear) {
		Params.isLinear = isLinear;
	}
	   
}