package com.mmf.soap;

import com.mmf.business.domain.User;

import javax.jws.WebService;

/**
 * @author svetlana.voyteh
 * @date: 5/12/12
 */
@WebService
public interface SoapAuthenticationService {

    User authenticate(String login, String password);
}
