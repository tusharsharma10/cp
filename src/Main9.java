import java.util.*;

public class Main9 {

  public static void main(String[] args) {
    String[] cities = {
        "Yonkers", "Charleston", "Murfreesboro", "South Bend", "Harlingen", "Anchorage",
        "Virginia Beach",
        "Erie", "Manchester", "Roanoke", "Knoxville", "Raleigh", "Gulfport-Biloxi", "Daly City",
        "Kennewick",
        "Racine", "Aurora", "Hayward", "Pensacola", "Champaign", "New Bedford", "Seattle",
        "Independence",
        "Tampa", "Richmond County", "Newburgh", "Sioux City", "Hagerstown", "Muskegon",
        "Huntsville",
        "Lafayette", "Vallejo", "Ocala", "North Las Vegas", "Kansas City", "Fort Walton Beach",
        "Concord",
        "Escondido", "Akron", "Panama City", "Sterling Heights", "Albuquerque", "Sacramento",
        "Stamford",
        "Downey", "Olathe", "Glendale", "Frederick", "Victorville", "Tyler"
    };
    int[] distances = {
        7956, 8228, 5135, 8501, 8721, 7051, 7511, 4594, 6618, 509, 3067, 6613, 12, 3691, 1327, 5200,
        2617,
        2944, 2398, 7758, 3303, 340, 9892, 5613, 1065, 5759, 9654, 7083, 7797, 8071, 2658, 7508,
        6222, 7423,
        5567, 5455, 1182, 9518, 2227, 511, 4346, 8609, 8673, 2682, 8503, 3517, 1181, 1718, 4027,
        9207
    };

    int maxCities = maxCitiesToTravel(cities, distances);
    System.out.println("Maximum number of cities Jeff can travel to: " + maxCities);
  }

  public static int maxCitiesToTravel(String[] cities, int[] distances) {
    List<Pair> pairs = new ArrayList<>();
    for (int i = 0; i < cities.length; i++) {
      pairs.add(new Pair(cities[i], distances[i]));
    }

    Collections.sort(pairs, (p1, p2) -> p1.dist - p2.dist);

    Map<String, Integer> memo = new HashMap<>();
    return maxCitiesToTravel(pairs, 0, "", memo);
  }

  public static int maxCitiesToTravel(List<Pair> pairs, int index, String prevCity,
      Map<String, Integer> memo) {
    if (index == pairs.size()) {
      return 0;
    }

    String key = index + "-" + prevCity;
    if (memo.containsKey(key)) {
      return memo.get(key);
    }

    Pair pair = pairs.get(index);
    int includeCurrentCity = 0;
    if (prevCity.isEmpty() || pair.city.compareTo(prevCity) > 0) {
      includeCurrentCity = 1 + maxCitiesToTravel(pairs, index + 1, pair.city, memo);
    }

    int skipCurrentCity = maxCitiesToTravel(pairs, index + 1, prevCity, memo);

    int maxCities = Math.max(includeCurrentCity, skipCurrentCity);
    memo.put(key, maxCities);
    return maxCities;
  }
}

class Pair {

  String city;
  int dist;

  public Pair(String city, int dist) {
    this.city = city;
    this.dist = dist;
  }
}
