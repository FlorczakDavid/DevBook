package co.simplon.devbookapi.services;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.simplon.devbookapi.config.JwtProvider;
import co.simplon.devbookapi.dtos.AuthInfo;
import co.simplon.devbookapi.dtos.Authentication;
import co.simplon.devbookapi.entities.Account;
import co.simplon.devbookapi.entities.Role;
import co.simplon.devbookapi.entities.TwoFactorAuthPin;
import co.simplon.devbookapi.repositories.AccountRepository;
import co.simplon.devbookapi.repositories.TwoFactorAuthPinRepository;

@Service
@Transactional(readOnly = true)
public class AccountAuthenticateService {

	private final AccountRepository accounts;
	private final PasswordEncoder encoder;
	private final JwtProvider jwtProvider;
	private final TwoFactorAuthPinRepository pins;
	private final EmailService emailService;
	
	public AccountAuthenticateService(AccountRepository accounts, PasswordEncoder encoder, JwtProvider jwtProvider,
			EmailService emailService, TwoFactorAuthPinRepository pins) {
		this.accounts = accounts;
		this.encoder = encoder;
		this.jwtProvider = jwtProvider;
		this.emailService = emailService;
		this.pins = pins;
	}

	@Transactional
	public void authenticate(Authentication inputs) {
		String inputsUsername = inputs.username();
		//verify if username is exists in DB
		Account entity = accounts.findAllByUsernameIgnoreCase(inputsUsername)
				.orElseThrow(()-> new BadCredentialsException(inputsUsername));
		//verify if email is valid
//		if(entity.isStatusEmail()) {
			// verify the pair of username and password
			boolean compared = encoder.matches(inputs.password(), entity.getPassword());
			if(compared) {
				//generate PIN
				String pin = String.format("%04d", new Random().nextInt(10000));
				//generate temporary token
				String tempToken = UUID.randomUUID().toString();
				
				//save PIN and Token inDB
				TwoFactorAuthPin pinToken = new TwoFactorAuthPin();
				pinToken.setPinCode(pin);
				pinToken.setUuidToken(tempToken);
				pinToken.setExpiration(LocalDateTime.now().plusMinutes(15));
				pinToken.setUser(entity);
				pins.save(pinToken);
				//send email
				this.emailService.sendMail(inputsUsername, pin, tempToken);
			}else {
				throw new BadCredentialsException(inputsUsername);
			}
//		}else {//TODO : throw other exception
//			throw new BadCredentialsException("Your email is not valid");
//		}
	}

	public AuthInfo verifyPin(String pin, String token) {
		TwoFactorAuthPin pinEntity = pins.findAllByUuidToken(token);
		if(pinEntity != null && pinEntity.getExpiration().isAfter(LocalDateTime.now())) {
			if(pin.equals(pinEntity.getPinCode())) {
				Account account = accounts.findById(pinEntity.getUser().getId())
						.orElseThrow(() -> new RuntimeException("Account not found"));
				Role role = account.getRole();
				String tokenJWT = jwtProvider.create(account.getUsername(), role);
				AuthInfo info = new AuthInfo(tokenJWT);
				return info;
			}else {
				throw new BadCredentialsException("Pin is not correct");
			}
		}else {
			throw new BadCredentialsException("Try again, validation is expired");
		}
	}
	
}
