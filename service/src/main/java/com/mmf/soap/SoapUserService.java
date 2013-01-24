package com.mmf.soap;

import com.mmf.business.domain.User;

import javax.jws.WebService;
import java.util.List;

/**
 * @author svetlana.voyteh
 * @date: 5/12/12
 */
@WebService
public interface SoapUserService {

    List<User> getAllLecturers();
}
