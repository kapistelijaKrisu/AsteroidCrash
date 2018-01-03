package tools;

import java.util.HashMap;
import java.util.Random;

import object.entites.SpawnType;

public class RandomEngine {
	public static final Random random = new Random();

	public static float randomNumber(float min, float max) {

		return random.nextFloat() * (max + 1) + min;
	}

	public static vec2 randomVectorLenghtOfOne() {
		vec2 vec = new vec2();
		vec.x = (randomNumber(-1, 1));
		int posOrNeg = random.nextInt(2);
		float lengthLeft = 1.0f - Math.abs(vec.x);

		if (posOrNeg == 0) {
			vec.y = lengthLeft;
		} else {
			vec.y = -lengthLeft;
		}

		return vec;
	}

	public static vec2 randomVector(float maxLength) {
		vec2 vec = new vec2();
		vec.x = (randomNumber(-maxLength, maxLength));
		int posOrNeg = random.nextInt(2);
		float lengthLeft = maxLength - Math.abs(vec.x);

		if (posOrNeg == 0) {
			vec.y = lengthLeft;
		} else {
			vec.y = -lengthLeft;
		}
		return vec;
	}

	public static vec2 randomVector(vec2 maxLength) {
		vec2 vec = new vec2();
		vec.x = (randomNumber(-maxLength.x, maxLength.x));
		vec.y = (randomNumber(-maxLength.y, maxLength.y));

		return vec;
	}

	public static SpawnType genRandomType(HashMap<SpawnType, Integer> chanceMap) {
		int i = 0;
		for (SpawnType key : chanceMap.keySet()) {
			i += chanceMap.get(key);
		}
		int lottery = RandomEngine.random.nextInt(i);
		i = 0;
		for (SpawnType key : chanceMap.keySet()) {
			i += chanceMap.get(key);
			if (lottery < i) {
				return key;
			}
		}
		return SpawnType.ASTEROID;
	}
}
