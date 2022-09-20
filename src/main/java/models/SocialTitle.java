package models;

import java.util.Random;

public enum SocialTitle {
    MR, MRS;

    private static final Random random = new Random();

    public static SocialTitle randomSocialTitle() {
        SocialTitle[] socialTitles = values();
        return socialTitles[random.nextInt(socialTitles.length)];
    }
}
