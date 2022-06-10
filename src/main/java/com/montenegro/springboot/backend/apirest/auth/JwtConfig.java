package com.montenegro.springboot.backend.apirest.auth;

public class JwtConfig {

	public static final  String LLAVE_SECRETA = "alguna.clave.secreta.12345678";
	
	public static final String RSA_PRIVADA = "-----BEGIN RSA PRIVATE KEY-----\r\n"
			+ "MIIEpQIBAAKCAQEA6l43ozKxESM8eedQAwTjC9IGKopqjuKuavyPL0alqgWIQ/pk\r\n"
			+ "PcQ1S9C2jIW1WVFdStNvOs9bj6Ml9ZmL0WNhwtIWy8CkIC+doCPLtqnUcas1QpXl\r\n"
			+ "SZHswlDLPrcuqxZiINTjs0GYspNq/ZMSh5ojH8b11vJ28hAxxsUh2xAIFQWn6A7T\r\n"
			+ "0yyEzi0YGj8OJLOYVqd1HzhRLt0szDsyeCQeRtTDOeU0CYDF5VTpxYv9Nhw2CmzS\r\n"
			+ "I4WUAae/Q6FiSoSvxixt2W0kL/p9EcrtpGRgeOK+Bo5BsiibSnkYTw6iNqa8p501\r\n"
			+ "/zXprPimoOkFR99FbgK+pSxF/Fvo6lIXR0Lb/QIDAQABAoIBAQDFJOv4Q7Pejp1Y\r\n"
			+ "jdMoNvwl/U1ii3yHJPdC16A6TFseYfj19bJMoreSRsbH/dFGiMLJJoHyHmsOsFZF\r\n"
			+ "+hs6zzZ1AvRaAHoO+P7GrlW2GbWyyCnNEPoGoNFfw0a0QrLnB7UTmQDQ0OT5kjf1\r\n"
			+ "hbmYh3f0SxCQ9vRHOogkg76u3xVtV/BVy45dMCJflSyIWMem/BiDVMaKxQbufyUt\r\n"
			+ "BLXfgURT/g0/bqvxldEQkYxxob4Crtfh1HHAgUuxb4Ez9430vODpB9A/QEfPxDx3\r\n"
			+ "dqeerT42/RYKo6jH7/RfS0Tc37ByNrsiFB1Um3KO74Y36zviQXYSFnhqFjfrGuiW\r\n"
			+ "lMko5VYBAoGBAPyGMihW/09vJW4cyhGWs4fPoLQFORJ/EsHMW41aUKPnvmQk7SM6\r\n"
			+ "LrFkL1hd6KXez7b3UtmV7Oy8TSc+VSh9sLsi6oI8XODp2dGCXEagxmt+wtjVkfmY\r\n"
			+ "lLjj8/08q0fGAmp3OKNYIykVSwLclS0BBxPn0QSwA4Q2+pwbxs/C2MqxAoGBAO2Y\r\n"
			+ "C66tLy3xSo1I4j6SN8qwM3leAIAEWlHUZkNH/sbpuEPTWBUeOAaUrOtBdNTGk8/y\r\n"
			+ "Y7JkH3NvIfcSOO+hA9pO4v8VnM4j20qn+UzTmMU4QtR5pI4JVvnFcIrkcx4GgifM\r\n"
			+ "F3PQmm16ImpC7XqrroJ+mDYcLEbfe7vNxq7qi+ENAoGBAPHQ+BsVJCvG/YryWrZ5\r\n"
			+ "H7aAJx38mmiR8z5OGaXExNAQTYvMqKFBSBFrJMN0OiMMuNA3Csl9/oG82A0qbZX2\r\n"
			+ "ttSdXQAWGcLvbTxpnz+rGhc6LhBChCUF7fUXpT/tZv+2J/4X6M7Syei/kTNOVDPi\r\n"
			+ "dgqjxM6+Vr+3fkkbeEjgB4YhAoGBALe3vjDPkY/A6JjfJrKjdaixHtxWJ6TVuNFV\r\n"
			+ "n82eug8tYmJv5chOmZvdEujGsIRnGJzPHPAwI3GrGWRnHseMheIk5bnBCIMNjt/n\r\n"
			+ "iwoxBAxcOLD7bpa1+h8g4KlFhQ5Okg4b7naLcx38Nuhb4C3e2w3kveX5WvNik9bg\r\n"
			+ "gyV2D7txAoGAEMmCBrgIgMT0pivu3moQFPHy21ro7RRX7Bmh5/+4ORkeV9HDQQ4J\r\n"
			+ "Ra4vgCFyxGnKdiCpZZwwjc5r0MFIvXrMAfh1vyjiW7UxgiqaLLk8Q4j07gzzk1wX\r\n"
			+ "QwB6GLyMM37+iVb670a/QUNbMQTi/JnUeIdUcyavVOPmf3Z4c5aHC2c=\r\n"
			+ "-----END RSA PRIVATE KEY-----";
	
	public static final String RSA_PUBLICA = "-----BEGIN PUBLIC KEY-----\r\n"
			+ "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA6l43ozKxESM8eedQAwTj\r\n"
			+ "C9IGKopqjuKuavyPL0alqgWIQ/pkPcQ1S9C2jIW1WVFdStNvOs9bj6Ml9ZmL0WNh\r\n"
			+ "wtIWy8CkIC+doCPLtqnUcas1QpXlSZHswlDLPrcuqxZiINTjs0GYspNq/ZMSh5oj\r\n"
			+ "H8b11vJ28hAxxsUh2xAIFQWn6A7T0yyEzi0YGj8OJLOYVqd1HzhRLt0szDsyeCQe\r\n"
			+ "RtTDOeU0CYDF5VTpxYv9Nhw2CmzSI4WUAae/Q6FiSoSvxixt2W0kL/p9EcrtpGRg\r\n"
			+ "eOK+Bo5BsiibSnkYTw6iNqa8p501/zXprPimoOkFR99FbgK+pSxF/Fvo6lIXR0Lb\r\n"
			+ "/QIDAQAB\r\n"
			+ "-----END PUBLIC KEY-----";
}
