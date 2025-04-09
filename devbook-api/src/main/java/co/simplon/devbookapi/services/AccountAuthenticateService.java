package co.simplon.devbookapi.services;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.simplon.devbookapi.config.JwtProvider;
import co.simplon.devbookapi.dtos.AuthInfo;
import co.simplon.devbookapi.dtos.Authentication;
import co.simplon.devbookapi.entities.Account;
import co.simplon.devbookapi.repositories.AccountRepository;

@Service
@Transactional(readOnly = true)
public class AccountAuthenticateService {

	private final AccountRepository accounts;
	//TODO : private final RoleJPARepository roles;
	private final PasswordEncoder encoder;
	private final JwtProvider jwtProvider;
	public AccountAuthenticateService(AccountRepository accounts, PasswordEncoder encoder, JwtProvider jwtProvider) {
		super();
		this.accounts = accounts;
		this.encoder = encoder;
		this.jwtProvider = jwtProvider;
	}
	
	public AuthInfo authenticate(Authentication inputs) {
		String inputsUsername = inputs.username();
		Account entity = accounts.findAllByUsernameIgnoreCase(inputsUsername)
				.orElseThrow(()-> new BadCredentialsException(inputsUsername));
		
		boolean compared = encoder.matches(inputs.username(), entity.getPassword());
		if(compared) {
			String tokenJWT = jwtProvider.create(inputsUsername);
			AuthInfo info = new AuthInfo(tokenJWT);
			return info;
		}else {
			throw new BadCredentialsException(inputsUsername);
		}
	}

	
}
