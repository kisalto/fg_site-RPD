package app.config;

import io.jsonwebtoken.SignatureAlgorithm;

public class JwtConfig {

	// Parâmetros para geração do token
	public static final String SECRET_KEY = "236A~A~A cs > (microdash) 2C(2) (first hit whiff) > j236B j214B > j2C jB > dl rejump dl j[C] > 66C > 665C 6C 5B 3C~dlA 623A";
	public static final SignatureAlgorithm ALGORITMO_ASSINATURA = SignatureAlgorithm.HS256;
	public static final int HORAS_EXPIRACAO_TOKEN = 1;

}