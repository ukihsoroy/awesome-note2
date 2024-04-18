package org.ko.oauth.service.impl;

import org.ko.oauth.repository.OAuthClientDetailsRepository;
import org.ko.oauth.domain.OauthClientDetailsEntity;
import org.ko.oauth.service.IClientDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional(rollbackOn = Throwable.class)
public class ClientDetailsServiceImpl implements IClientDetailsService {

    @Autowired
    private OAuthClientDetailsRepository authClientDetailsRepository;

    @Override
    public void save(OauthClientDetailsEntity client) {
        authClientDetailsRepository.save(client);
    }
}
