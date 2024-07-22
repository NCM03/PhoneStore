package fa.training.phonestore.sercurity;

public class SecurityConstraints {
    public static final String SECRET_KEY="cbc072af43d48cfa11c76c6dc4454e35ede6bb129464076aaa85e8aee0c8499872682b868877a8d11a972a1b80b3b2b5bb19f20cfcbf21fabdec330fab93b27f";

    public static final String BEARER = "Bearer ";
    public static final String AUTHORIZATION = "Authorization";
    public static final String Authenticate="http://localhost:2612/authenticate";
    public static final long TOKEN_EXPIRATION = 900_000; // 15 minutes
    public static final long REMEMBER_ME_EXPIRATION = 604_800_000; // 7 days
}
