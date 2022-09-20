package models;

import com.github.javafaker.Faker;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class UserFactory {
    public User getRandomUser() {
        SocialTitle socialTitle = SocialTitle.randomSocialTitle();
        Faker faker = new Faker(new Locale("pl"));
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = faker.internet().emailAddress();
        String password = faker.internet().password(5, 30);
        String birthdatePattern = "MM-dd-yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(birthdatePattern);
        String birthdate = simpleDateFormat.format(faker.date().birthday());
        boolean receiveOffersAgreement = faker.random().nextBoolean();
        boolean signUpNewsletter = faker.random().nextBoolean();

        return User.userBuilder()
                .socialTitle(socialTitle)
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .password(password)
                .birthdate(birthdate)
                .receiveOffersAgreement(receiveOffersAgreement)
                .customerDataPrivacyAgreement(true)
                .signUpNewsletter(signUpNewsletter)
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
