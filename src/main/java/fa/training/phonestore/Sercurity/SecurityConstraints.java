package fa.training.phonestore.Sercurity;

public class SecurityConstraints {
    public static final String SECRET_KEY="cbc072af43d48cfa11c76c6dc4454e35ede6bb129464076aaa85e8aee0c8499872682b868877a8d11a972a1b80b3b2b5bb19f20cfcbf21fabdec330fab93b27f";
    public static final int TOKEN_EXPIRATION = 86400000; // 1 day in milliseconds

    public static final String BEARER = "Bearer ";
    public static final String AUTHORIZATION = "Authorization";
    public static final String REGISTER_PATH="/Person/register";
}
