package models;

import com.github.javafaker.Faker;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class UserFactory {
    public User getRandomUser() {
        Faker faker = new Faker(new Locale("pl"));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd-yyyy");

        return User.userBuilder()
                .socialTitle(SocialTitle.randomSocialTitle())
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .email(faker.internet().emailAddress())
                .password(faker.internet().password(5, 30))
                .birthdate(simpleDateFormat.format(faker.date().birthday()))
                .receiveOffersAgreement(faker.random().nextBoolean())
                .customerDataPrivacyAgreement(true)
                .signUpNewsletter(faker.random().nextBoolean())
                .generalConditionsAcceptance(true)
                .build();
    }

    public User getAlreadyRegisteredUser() {
        return User.userBuilder()
                .socialTitle(SocialTitle.MR)
                .firstName("Jan")
                .lastName("Kowalski")
                .email("jkowal@gmail.com")
                .password("kowal123")
                .birthdate("01/01/2000")
                .receiveOffersAgreement(false)
                .customerDataPrivacyAgreement(true)
                .signUpNewsletter(true)
                .generalConditionsAcceptance(true)
                .build();
    }
}
