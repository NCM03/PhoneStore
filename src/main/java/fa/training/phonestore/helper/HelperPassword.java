package fa.training.phonestore.helper;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HelperPassword {
   public String generateRandomPassword() {
        String upperCaseLetters = RandomStringUtils.random(2, 65, 90, true, true);
        String lowerCaseLetters = RandomStringUtils.random(3, 97, 122, true, true);
        String numbers = RandomStringUtils.randomNumeric(3);
        String specialChar = RandomStringUtils.random(2, 33, 47, false, false);

        String combinedChars = upperCaseLetters.concat(lowerCaseLetters)
                .concat(numbers)
                .concat(specialChar);

        List<Character> pwdChars = new ArrayList<>();
        for (char c : combinedChars.toCharArray()) {
            pwdChars.add(c);
        }
        Collections.shuffle(pwdChars);

        StringBuilder password = new StringBuilder();
        for (Character c : pwdChars) {
            password.append(c);
        }

        return password.toString();
    }

}
