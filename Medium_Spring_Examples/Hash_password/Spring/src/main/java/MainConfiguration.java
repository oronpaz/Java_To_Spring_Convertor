import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.EnumMap;
import java.util.Map;

@Configuration
@PropertySource("classpath:config/config.properties")
public class MainConfiguration {

    @Bean
    public HashExecutorFactory ExecutorCreatorFactory() {
        HashExecutorFactory factory = new HashExecutorFactory();
        Map<HashMethod, HashExecutor> executors = new EnumMap<>(HashMethod.class);
        executors.put(HashMethod.SHA512, sha512Hash());
        executors.put(HashMethod.ADLER32, adler32Hash());
        executors.put(HashMethod.CRC32, crc32Hash());
        factory.setEncryptsMap(executors);
        return factory;
    }

    @Bean
    public RegisterManager registerManager() {
        return new RegisterManager(usersRepository(), ExecutorCreatorFactory());
    }

    @Bean
    public UsersRepository usersRepository() {
        return new UsersRepository();
    }

    @Bean
    public SHA512Hash sha512Hash() {
        return new SHA512Hash("SHA512", 3);
    }

    @Bean
    public Adler32Hash adler32Hash() {
        return new Adler32Hash("Adler32", 2);
    }

    @Bean
    public Crc32Hash crc32Hash() {
        return new Crc32Hash("Crc32", 3);
    }

}
