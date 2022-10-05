package models;

public class User {
    private SocialTitle socialTitle;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String birthdate;
    private boolean receiveOffersAgreement;
    private boolean customerDataPrivacyAgreement;
    private boolean signUpNewsletter;
    private boolean generalConditionsAcceptance;

    public static final class UserBuilder {
        private SocialTitle socialTitle;
        private String firstName;
        private String lastName;
        private String email;
        private String password;
        private String birthdate;
        private boolean receiveOffersAgreement;
        private boolean customerDataPrivacyAgreement;
        private boolean signUpNewsletter;
        private boolean generalConditionsAcceptance;


        UserBuilder socialTitle(SocialTitle socialTitle) {
            this.socialTitle = socialTitle;
            return this;
        }

        UserBuilder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        UserBuilder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        UserBuilder email(String email) {
            this.email = email;
            return this;
        }

        UserBuilder password(String password) {
            this.password = password;
            return this;
        }

        UserBuilder birthdate(String birthdate) {
            this.birthdate = birthdate;
            return this;
        }

        UserBuilder receiveOffersAgreement(boolean receiveOffersAgreement) {
            this.receiveOffersAgreement = receiveOffersAgreement;
            return this;
        }

        UserBuilder customerDataPrivacyAgreement(boolean customerDataPrivacyAgreement) {
            this.customerDataPrivacyAgreement = customerDataPrivacyAgreement;
            return this;
        }

        UserBuilder signUpNewsletter(boolean signUpNewsletter) {
            this.signUpNewsletter = signUpNewsletter;
            return this;
        }

        UserBuilder generalConditionsAcceptance(boolean generalConditionsAcceptance) {
            this.generalConditionsAcceptance = generalConditionsAcceptance;
            return this;
        }

        public User build() {
            if (firstName.isEmpty()) {
                throw new IllegalStateException("First name cannot be empty!");
            }

            if (lastName.isEmpty()) {
                throw new IllegalStateException("Last name cannot be empty!");
            }
            if (email.isEmpty()) {
                throw new IllegalStateException("Email cannot be empty!");
            }
            if (password.isEmpty()) {
                throw new IllegalStateException("Password cannot be empty!");
            }

            User user = new User();
            user.socialTitle = this.socialTitle;
            user.firstName = this.firstName;
            user.lastName = this.lastName;
            user.email = this.email;
            user.password = this.password;
            user.birthdate = this.birthdate;
            user.receiveOffersAgreement = this.receiveOffersAgreement;
            user.customerDataPrivacyAgreement = this.customerDataPrivacyAgreement;
            user.signUpNewsletter = this.signUpNewsletter;
            user.generalConditionsAcceptance = this.generalConditionsAcceptance;

            return user;
        }
    }

    public SocialTitle getSocialTitle() {
        return socialTitle;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public boolean isReceiveOffersAgreement() {
        return receiveOffersAgreement;
    }

    public boolean isCustomerDataPrivacyAgreement() {
        return customerDataPrivacyAgreement;
    }

    public boolean isSignUpNewsletter() {
        return signUpNewsletter;
    }

    public boolean isGeneralConditionsAcceptance() {
        return generalConditionsAcceptance;
    }

    public static UserBuilder userBuilder() {
        return new UserBuilder();
    }
}
