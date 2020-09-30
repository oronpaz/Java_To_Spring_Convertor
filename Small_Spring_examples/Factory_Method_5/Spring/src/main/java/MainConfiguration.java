import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.EnumMap;
import java.util.Map;

@Configuration
public class MainConfiguration {
    @Bean
    public EncryptFactory encryptCreatorFactory() {
        EncryptFactory factory = new EncryptFactory();
        Map<EncryptMethod, Encrypt> encrypts = new EnumMap<>(EncryptMethod.class);
        encrypts.put(EncryptMethod.REVERSE, reverseEncrypt());
        encrypts.put(EncryptMethod.REVERSE_SKIP_FIRST, reverseSkipFirstEncrypt());
        encrypts.put(EncryptMethod.SWITCH_FIRST_LAST, switchFirstLastEncrypt());
        factory.setEncryptsMap(encrypts);
        return factory;
    }

    @Bean
    public Encrypt reverseEncrypt() {
        return encryptCreatorFactory().createEncrypt(EncryptMethod.REVERSE);
    }

    @Bean
    public Encrypt reverseSkipFirstEncrypt() {
        return encryptCreatorFactory().createEncrypt(EncryptMethod.REVERSE_SKIP_FIRST);
    }

    @Bean
    public Encrypt switchFirstLastEncrypt() {
        return encryptCreatorFactory().createEncrypt(EncryptMethod.SWITCH_FIRST_LAST);
    }

    @Bean
    public RegisterUtils registerUtils() {
        return new RegisterUtils(usersRepository(), encryptCreatorFactory());
    }

    @Bean
    public UsersRepository usersRepository() {
        return new UsersRepository();
    }
}
