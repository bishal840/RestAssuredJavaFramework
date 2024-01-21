package spotifyOAuth2.utils;

import com.github.javafaker.Faker;

public class FakerUtils {

    public static String generateName()
    {
        Faker faker = new Faker();
        return "Playlist"+faker.name().name();
    }

    public static String generateDescription()
    {
        Faker faker = new Faker();
        return faker.regexify("[A-Za-z0-9_@]{50}");
    }
}
