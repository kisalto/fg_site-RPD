package app.config;

import io.jsonwebtoken.SignatureAlgorithm;

public class JwtConfig {

	// Parâmetros para geração do token
	public static final String SECRET_KEY = "236A/A/Acs+microdash+2C/2C+firsthitwhiff+j236Bj214B+j2CjB+dlrejumpdlj/holdC+66C+665C6C5B3C/dlA623A";
	public static final SignatureAlgorithm ALGORITMO_ASSINATURA = SignatureAlgorithm.HS256;
	public static final int HORAS_EXPIRACAO_TOKEN = 1;

}